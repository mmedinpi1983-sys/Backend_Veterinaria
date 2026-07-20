package com.utp.sistemaclinicaveterinaria.modulos.VentaDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Integer> {
    List<VentaDetalle> findByIdVenta(Integer idVenta);
}
