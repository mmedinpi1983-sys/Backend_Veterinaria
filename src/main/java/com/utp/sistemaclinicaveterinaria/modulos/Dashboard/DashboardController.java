package com.utp.sistemaclinicaveterinaria.modulos.Dashboard;

import org.springframework.web.bind.annotation.*;

import com.utp.sistemaclinicaveterinaria.modulos.Dashboard.DashboardDTO.DashboardResponse;
import com.utp.sistemaclinicaveterinaria.modulos.common.ApiResponse;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<DashboardResponse> obtener() {
        return ApiResponse.ResponseAn(service.obtener());
    }
}
