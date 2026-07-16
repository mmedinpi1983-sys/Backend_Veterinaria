package com.utp.sistemaclinicaveterinaria.modulos.Producto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.CategoriaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCatalogResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoCreateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDeleteRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoDetailResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoFilterRequest;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoListResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoStatsResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Producto.ProductoDTO.ProductoUpdateRequest;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiException;
import com.utp.sistemaclinicaveterinaria.modulos.common.UsuarioActual;

@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private final ProductoRepository r;
    private final ProductoMapper m;

    public ProductoServiceImpl(ProductoRepository r, ProductoMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public List<ProductoCatalogResponse> catalogo() {
        return m.toCatalog(r.catalogo(UsuarioActual.getAsociadoId()));
    }

    @Override
    public List<ProductoListResponse> listar(ProductoFilterRequest f) {
        String q = f == null || f.q() == null ? "" : f.q().trim();
        Integer idCat = f == null || f.idCategoria() == null ? 0 : f.idCategoria();
        return m.toList(r.listar(UsuarioActual.getAsociadoId(), q, idCat));
    }

    @Override
    public List<CategoriaResponse> categorias() {
        return m.toCategoria(r.categorias(UsuarioActual.getAsociadoId()));
    }

    @Override
    public ProductoStatsResponse stats() {
        return m.toStats(r.stats(UsuarioActual.getAsociadoId()));
    }

    @Override
    public ProductoDetailResponse obtenerId(Integer id) {
        var d = r.detalle(id);
        if (d == null) throw new ApiException("El producto no existe", "NOT_FOUND");
        return m.toDetail(d);
    }

    @Override
    public void crear(ProductoCreateRequest c) {
        Integer uid = UsuarioActual.getId();
        LocalDateTime ahora = LocalDateTime.now();
        Producto e = m.toEntity(c);
        e.setEstado(true);
        e.setLote("LOTE-" + ahora.toLocalDate().toString().replace("-", ""));
        e.setFechaFabricacion(LocalDate.now());
        e.setRequiereReceta(false);
        e.setRequiereInventario(true);
        e.setIdEmpleadoAsociado(uid);
        e.setIdEmpleadoCreador(uid);
        e.setFechaCreacion(ahora);
        if (e.getCantidadIngreso() == null) e.setCantidadIngreso(0);
        if (e.getCantidadMinima() == null) e.setCantidadMinima(0);
        r.save(e);
        log.info("Producto creado: {} (stock inicial {})", e.getNombre(), e.getCantidadIngreso());
    }

    @Override
    public void actualizar(Integer id, ProductoUpdateRequest u) {
        Producto e = r.getReferenceById(id);
        m.updateEntity(e, u);
        e.setIdEmpleadoModificador(UsuarioActual.getId());
        e.setFechaModificacion(LocalDateTime.now());
        r.save(e);
    }

    @Override
    public void eliminar(ProductoDeleteRequest d) {
        r.eliminar(d.idProducto(), UsuarioActual.getId());
        log.info("Producto eliminado (baja logica): id={}", d.idProducto());
    }
}
