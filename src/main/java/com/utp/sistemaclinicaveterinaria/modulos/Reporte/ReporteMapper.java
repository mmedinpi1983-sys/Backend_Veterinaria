package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.CitaSemanaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.IngresoCategoriaResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.PacienteMesResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ProductoTopResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ResumenResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.CitaSemanaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.DetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.IngresoCategoriaProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.PacienteMesProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ProductoTopProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ResumenProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReporteMapper {
    ResumenResponse toResumen(ResumenProjection p);
    CitaSemanaResponse toCitaSemana(CitaSemanaProjection p);
    List<CitaSemanaResponse> toCitaSemana(List<CitaSemanaProjection> p);
    IngresoCategoriaResponse toIngresoCategoria(IngresoCategoriaProjection p);
    List<IngresoCategoriaResponse> toIngresoCategoria(List<IngresoCategoriaProjection> p);
    ProductoTopResponse toProductoTop(ProductoTopProjection p);
    List<ProductoTopResponse> toProductoTop(List<ProductoTopProjection> p);
    PacienteMesResponse toPacienteMes(PacienteMesProjection p);
    List<PacienteMesResponse> toPacienteMes(List<PacienteMesProjection> p);
    DetalleResponse toDetalle(DetalleProjection p);
    List<DetalleResponse> toDetalle(List<DetalleProjection> p);
}
