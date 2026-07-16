package com.utp.sistemaclinicaveterinaria.modulos.Venta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class VentaServiceImpl implements VentaService {
    private static final Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);


    private static final BigDecimal IGV_FACTOR = new BigDecimal("1.18");

    private final VentaRepository r;
    private final VentaDetalleRepository rd;
    private final VentaMapper m;

    public VentaServiceImpl(VentaRepository r, VentaDetalleRepository rd, VentaMapper m) {
        this.r = r;
        this.rd = rd;
        this.m = m;
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

        log.info("Venta registrada: {} (total S/{}) por empleado {}", guardada.getCodigoVenta(), total, idUsuario);
        return guardada.getIdVenta();
    }

    @Override
    @Transactional
    public void anular(VentaDeleteRequest d) {
        r.anular(d.idVenta(), UsuarioActual.getId());
        log.info("Venta anulada: id={} por empleado {}", d.idVenta(), UsuarioActual.getId());
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
