package com.utp.sistemaclinicaveterinaria.modulos.DocumentoIdentidad;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;
public interface DocumentoIdentidadDTO {
    record Request(
                 String descripcion,
                 Boolean estado,
                 Integer idAsociado
    ) {}
    record Response(
                 Integer idDocumentoIdentidad,
                 String descripcion,
                 Boolean estado,
                 Integer idAsociado
    ) {}
    record ListItem(
                 Integer idDocumentoIdentidad,
                 String descripcion,
                 Boolean estado
    ) {}
    record ListResponse(List<ListItem> items) {}
}
