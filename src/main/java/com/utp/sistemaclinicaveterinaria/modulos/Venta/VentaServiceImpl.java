package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.MetodoPagoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.ProductoCatalogoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaItemRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaLineaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.VentaDTO.VentaListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Venta.Projection.VentaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.VentaDetalle.VentaDetalle;
import com.utp.sistemaclinicaveterinaria.modulos.VentaDetalle.VentaDetalleRepository;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProducto;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoRepository;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.Projection.StockActualProjection;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class VentaServiceImpl implements VentaService {
    private static final Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);


    private static final BigDecimal IGV_FACTOR = new BigDecimal("1.18");

    private final VentaRepository r;
    private final VentaDetalleRepository rd;
    private final VentaMapper m;
    private final MovimientoProductoRepository mr;

    public VentaServiceImpl(VentaRepository r, VentaDetalleRepository rd, VentaMapper m, MovimientoProductoRepository mr) {
        this.r = r;
        this.rd = rd;
        this.m = m;
        this.mr = mr;
    }

    @Override
    public List<ProductoCatalogoResponse> productosCatalogo() {
        return m.toProductoResponse(r.productosCatalogo(UsuarioActual.getAsociadoId()));
    }

    @Override
    public List<MetodoPagoResponse> metodosPago() {
        return m.toMetodoPagoResponse(r.metodosPago());
    }

    @Override
    public List<VentaListResponse> listar(VentaFilterRequest f) {
        String q = f == null || f.q() == null ? "" : f.q().trim();
        String tipo = f == null || f.tipoComprobante() == null ? "" : f.tipoComprobante().trim();
        return m.toListResponse(r.listar(UsuarioActual.getAsociadoId(), q, tipo));
    }

    @Override
    public VentaDetailResponse obtenerId(Integer idVenta) {
        VentaDetalleProjection v = r.detalle(idVenta);
        if (v == null) {
            throw new ApiException("La venta no existe", "NOT_FOUND");
        }
        List<VentaLineaResponse> items = m.toLineaResponse(r.lineas(idVenta));
        return new VentaDetailResponse(
                v.getIdVenta(), v.getCodigoVenta(), v.getIdDueno(), v.getNombreComprador(), v.getDniComprador(),
                v.getTipoComprobante(), v.getFechaVenta(), v.getSubTotal(), v.getDescuento(), v.getIgv(),
                v.getTotal(), v.getMontoPagado(), v.getEstadoVenta(), v.getIdMetodoPago(), v.getMetodoPago(), items);
    }

    @Override
    @Transactional
    public Integer crear(VentaCreateRequest c) {
        if (c.items() == null || c.items().isEmpty()) {
            throw new ApiException("La venta debe tener al menos un ítem", "VALIDATION_ERROR");
        }

        Integer idUsuario = UsuarioActual.getId();
        LocalDateTime ahora = LocalDateTime.now();

        // Los precios ya vienen con el IGV incluido
        BigDecimal totalBruto = BigDecimal.ZERO;
        for (VentaItemRequest it : c.items()) {
            if (it.cantidad() == null || it.cantidad() <= 0) {
                throw new ApiException("La cantidad de cada ítem debe ser mayor a 0", "VALIDATION_ERROR");
            }
            if (it.idProducto() == null && it.idServicio() == null) {
                throw new ApiException("Cada ítem debe ser un producto o un servicio", "VALIDATION_ERROR");
            }
            BigDecimal precio = it.precioUnitario() == null ? BigDecimal.ZERO : it.precioUnitario();
            totalBruto = totalBruto.add(precio.multiply(BigDecimal.valueOf(it.cantidad())));
        }

        BigDecimal descuento = c.descuento() == null ? BigDecimal.ZERO : c.descuento();
        BigDecimal total = totalBruto.subtract(descuento).setScale(2, RoundingMode.HALF_UP);
        if (total.signum() < 0) {
            total = BigDecimal.ZERO;
        }
        BigDecimal base = total.divide(IGV_FACTOR, 2, RoundingMode.HALF_UP);
        BigDecimal igv = total.subtract(base).setScale(2, RoundingMode.HALF_UP);

        String tipo = c.tipoComprobante() == null || c.tipoComprobante().isBlank() ? "Boleta" : c.tipoComprobante();

        Venta venta = new Venta();
        venta.setCodigoVenta(generarCodigo(tipo));
        venta.setIdDueno(c.idDueno());
        venta.setIdCita(c.idCita());
        venta.setIdAtencion(c.idAtencion());
        venta.setTipoComprobante(tipo);
        venta.setFechaVenta(ahora);
        venta.setSubTotal(base);
        venta.setDescuento(descuento.setScale(2, RoundingMode.HALF_UP));
        venta.setIgv(igv);
        venta.setTotal(total);
        venta.setMontoPagado(c.montoPagado() == null ? total : c.montoPagado());
        venta.setEstadoVenta(1);
        venta.setIdMetodoPago(c.idMetodoPago());
        venta.setIdEmpleadoAsociado(idUsuario);
        venta.setDniComprador(c.dniComprador());
        venta.setNombreComprador(c.nombreComprador());
        venta.setFechaCreacion(ahora);
        venta.setIdEmpleadoCreador(idUsuario);
        Venta guardada = r.save(venta);

        List<VentaDetalle> detalles = new ArrayList<>();
        for (VentaItemRequest it : c.items()) {
            BigDecimal precio = it.precioUnitario() == null ? BigDecimal.ZERO : it.precioUnitario();
            VentaDetalle d = new VentaDetalle();
            d.setIdVenta(guardada.getIdVenta());
            d.setIdProducto(it.idProducto());
            d.setIdServicio(it.idServicio());
            d.setCantidad(it.cantidad());
            d.setPrecioUnitario(precio.setScale(2, RoundingMode.HALF_UP));
            d.setSubtotal(precio.multiply(BigDecimal.valueOf(it.cantidad())).setScale(2, RoundingMode.HALF_UP));
            d.setFechaCreacion(ahora);
            d.setIdEmpleadoCreador(idUsuario);
            detalles.add(d);
        }
        rd.saveAll(detalles);

        // Salida de stock: descuenta cada producto vendido. Si falta stock o el producto ya no
        // existe, se corta la venta entera (misma transacción que el save de arriba).
        ajustarStock(detalles, mr.idClaseSalida(), mr.idMotivoVenta(), -1, idUsuario, ahora,
                "Uno de los productos de la venta ya no existe", "Stock insuficiente para completar la venta");

        log.info("Venta registrada: {} (total S/{}) por empleado {}", guardada.getCodigoVenta(), total, idUsuario);
        return guardada.getIdVenta();
    }

    @Override
    @Transactional
    public void anular(VentaDeleteRequest d) {
        Venta venta = r.findById(d.idVenta())
                .orElseThrow(() -> new ApiException("La venta no existe", "NOT_FOUND"));
        if (venta.getEstadoVenta() != null && venta.getEstadoVenta() == 0) {
            throw new ApiException("La venta ya fue anulada", "VALIDATION_ERROR");
        }

        Integer idUsuario = UsuarioActual.getId();
        List<VentaDetalle> detalles = rd.findByIdVenta(d.idVenta());

        // Entrada de stock: devuelve cada producto de la venta anulada. Si alguno ya no existe
        // se ignora esa línea (no se puede restaurar stock de un producto eliminado), en vez de
        // abortar la anulación completa.
        ajustarStock(detalles, mr.idClaseEntrada(), mr.idMotivoDevolucion(), +1, idUsuario, LocalDateTime.now(), null, null);

        r.anular(d.idVenta(), idUsuario);
        log.info("Venta anulada: id={} por empleado {}", d.idVenta(), idUsuario);
    }

    // Ajusta el stock de cada producto de la venta (salida al vender, entrada al anular) y deja
    // el rastro de auditoría en MovimientoProducto. Compartido entre crear() y anular() para no
    // duplicar la lectura de stock / armado del movimiento con el signo invertido en cada uno.
    // mensajeSinProducto/mensajeStockInsuficiente en null = modo tolerante (anular): una línea
    // cuyo producto ya no existe se ignora en vez de abortar toda la operación.
    private void ajustarStock(List<VentaDetalle> detalles, Integer idClaseMovimiento, Integer idMotivoMovimiento,
                               int signo, Integer idUsuario, LocalDateTime fecha,
                               String mensajeSinProducto, String mensajeStockInsuficiente) {
        List<Integer> idsProducto = detalles.stream()
                .map(VentaDetalle::getIdProducto)
                .filter(java.util.Objects::nonNull)
                .distinct()
                .toList();
        if (idsProducto.isEmpty()) return;

        Map<Integer, Integer> stockPorProducto = new HashMap<>();
        for (StockActualProjection p : mr.stockActualEnLote(idsProducto)) {
            stockPorProducto.put(p.getIdProducto(), p.getStock());
        }

        List<MovimientoProducto> movimientos = new ArrayList<>();
        for (VentaDetalle det : detalles) {
            if (det.getIdProducto() == null) continue;
            Integer stockAnterior = stockPorProducto.get(det.getIdProducto());
            if (stockAnterior == null) {
                if (mensajeSinProducto != null) {
                    throw new ApiException(mensajeSinProducto, "NOT_FOUND");
                }
                continue;
            }
            int stockNuevo = stockAnterior + signo * det.getCantidad();
            if (signo < 0 && stockNuevo < 0) {
                throw new ApiException(mensajeStockInsuficiente, "VALIDATION_ERROR");
            }
            MovimientoProducto mov = new MovimientoProducto();
            mov.setIdProducto(det.getIdProducto());
            mov.setIdClaseMovimiento(idClaseMovimiento);
            mov.setCantidad(det.getCantidad());
            mov.setStockAnterior(stockAnterior);
            mov.setStockNuevo(stockNuevo);
            mov.setIdMotivoMovimiento(idMotivoMovimiento);
            mov.setIdAsociado(UsuarioActual.getAsociadoId());
            mov.setFechaCreacion(fecha);
            mov.setIdEmpleadoCreador(idUsuario);
            movimientos.add(mov);
            mr.actualizarStock(det.getIdProducto(), stockNuevo, idUsuario);
            // Si el mismo producto aparece dos veces en la venta, la segunda línea debe partir
            // del stock ya descontado por la primera, no del valor leído en el lote inicial.
            stockPorProducto.put(det.getIdProducto(), stockNuevo);
        }
        mr.saveAll(movimientos);
    }

    private String generarCodigo(String tipo) {
        String prefijo = switch (tipo == null ? "" : tipo.toLowerCase()) {
            case "factura" -> "F";
            case "boleta" -> "B";
            default -> "V";
        };
        Integer contador = r.contarPorTipo(UsuarioActual.getAsociadoId(), tipo == null ? "" : tipo);
        int siguiente = (contador == null ? 0 : contador) + 1;
        return prefijo + "-" + String.format("%04d", siguiente);
    }
}
