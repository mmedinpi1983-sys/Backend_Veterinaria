package com.utp.sistemaclinicaveterinaria.modulos.Dashboard;

import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.DashboardDTO.DashboardResponse;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepository r;
    private final DashboardMapper m;

    public DashboardServiceImpl(DashboardRepository r, DashboardMapper m) {
        this.r = r;
        this.m = m;
    }

    @Override
    public DashboardResponse obtener() {
        return new DashboardResponse(
                m.toResumen(r.resumen()),
                m.toCitaSemana(r.citasSemana()),
                m.toAlertaStock(r.alertasStock()));
    }
}
