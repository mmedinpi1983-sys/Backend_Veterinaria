package com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.ClaseResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MotivoResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.MovimientoProducto.MovimientoProductoDTO.MovimientoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.PermisoAccessService;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class MovimientoProductoServiceImpl implements MovimientoProductoService {
    private static final Logger log = LoggerFactory.getLogger(MovimientoProductoServiceImpl.class);

    private final MovimientoProductoRepository r;
    private final MovimientoProductoMapper m;

    public MovimientoProductoServiceImpl(MovimientoProductoRepository r, MovimientoProductoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<MovimientoListResponse> listar(MovimientoFilterRequest f) {
        String q = f == null || f.q() == null ? "" : f.q().trim();
        Integer idClase = f == null || f.idClaseMovimiento() == null ? 0 : f.idClaseMovimiento();
        return m.toList(r.listar(UsuarioActual.getAsociadoId(), q, idClase));
    }

    @Override
    public MovimientoStatsResponse stats() {
        return m.toStats(r.stats(UsuarioActual.getAsociadoId()));
    }

    @Override
    public List<ClaseResponse> clases() {
        return m.toClase(r.clases());
    }

    @Override
    public List<MotivoResponse> motivos() {
        return m.toMotivo(r.motivos());
    }

    @Override
    @Transactional
    public void crear(MovimientoCreateRequest c) {
        Integer uid = UsuarioActual.getId();
        Integer stockAnterior = r.stockActual(c.idProducto());
        if (stockAnterior == null) {
            throw new ApiException("El producto no existe", "NOT_FOUND");
        }
        String desc = PermisoAccessService.normalizar(r.descripcionClase(c.idClaseMovimiento()));
        int signo = desc.contains("salida") ? -1 : 1; // Entrada y Ajuste suman; Salida resta
        int stockNuevo = stockAnterior + signo * c.cantidad();
        if (stockNuevo < 0) {
            log.warn("Stock insuficiente: producto {} (stock {}, salida {})", c.idProducto(), stockAnterior, c.cantidad());
            throw new ApiException("Stock insuficiente para la salida solicitada", "VALIDATION_ERROR");
        }

        MovimientoProducto mov = new MovimientoProducto();
        mov.setIdProducto(c.idProducto());
        mov.setIdClaseMovimiento(c.idClaseMovimiento());
        mov.setCantidad(c.cantidad());
        mov.setStockAnterior(stockAnterior);
        mov.setStockNuevo(stockNuevo);
        mov.setIdMotivoMovimiento(c.idMotivoMovimiento());
        mov.setIdAsociado(UsuarioActual.getAsociadoId());
        mov.setFechaCreacion(LocalDateTime.now());
        mov.setIdEmpleadoCreador(uid);
        r.save(mov);

        r.actualizarStock(c.idProducto(), stockNuevo, uid);
        log.info("Movimiento de stock: producto {} {} -> {} (cantidad {})", c.idProducto(), stockAnterior, stockNuevo, c.cantidad());
    }
}
