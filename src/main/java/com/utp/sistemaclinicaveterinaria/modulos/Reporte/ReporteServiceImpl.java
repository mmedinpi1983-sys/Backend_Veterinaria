package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ReporteResponse;

@Service
public class ReporteServiceImpl implements ReporteService {
    private final ReporteRepository r;
    private final ReporteMapper m;

    public ReporteServiceImpl(ReporteRepository r, ReporteMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public ReporteResponse obtener() {
        return new ReporteResponse(
                m.toResumen(r.resumen()),
                m.toCitaSemana(r.citasSemana()),
                m.toIngresoCategoria(r.ingresosCategoria()),
                m.toProductoTop(r.productosTop()),
                m.toPacienteMes(r.pacientesMes()));
    }

    @Override
    public List<DetalleResponse> detalle(String fechaInicio, String fechaFin) {
        String fi = fechaInicio == null ? "" : fechaInicio.trim();
        String ff = fechaFin == null ? "" : fechaFin.trim();
        return m.toDetalle(r.detalle(fi, ff));
    }
}
