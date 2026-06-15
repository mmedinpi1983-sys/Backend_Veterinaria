package com.utp.sistemaclinicaveterinaria.modulos.MedicamentoCatalogo;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface MedicamentoCatalogoDTO {
    record Request(
                 String codigoMedicamento,
                 String nombreMedicamento,
                 String descripcion,
                 String concentracion,
                 String presentacion
    ) {}
    record Response(
                 Integer idMedicamento,
                 String codigoMedicamento,
                 String nombreMedicamento,
                 String descripcion,
                 String concentracion,
                 String presentacion
    ) {}
    record ListItem(
                 Integer idMedicamento,
                 String codigoMedicamento,
                 String nombreMedicamento,
                 String descripcion
    ) {}
    record ListResponse(List<ListItem> items) {}
}
