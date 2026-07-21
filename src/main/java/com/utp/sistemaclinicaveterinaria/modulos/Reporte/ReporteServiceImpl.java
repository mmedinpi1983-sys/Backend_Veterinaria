package com.utp.sistemaclinicaveterinaria.modulos.Reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.DetalleResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.ReporteDTO.ReporteResponse;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.CitaDetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.DetalleProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.ResumenRangoProjection;
import com.utp.sistemaclinicaveterinaria.modulos.Reporte.Projection.VeterinarioProductividadProjection;

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

    // ==================== EXCEL ====================
    @Override
    public byte[] generarExcel(List<String> tipos, String fechaInicio, String fechaFin) {
        String fi = fechaInicio == null ? "" : fechaInicio.trim();
        String ff = fechaFin == null ? "" : fechaFin.trim();
        List<String> t = (tipos == null || tipos.isEmpty()) ? List.of("resumen") : tipos;

        try (XSSFWorkbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Estilos e = new Estilos(wb);
            if (t.contains("resumen")) hojaResumen(wb, e, fi, ff);
            if (t.contains("ventas")) hojaVentas(wb, e, fi, ff);
            if (t.contains("citas")) hojaCitas(wb, e, fi, ff);
            if (t.contains("veterinarios")) hojaVeterinarios(wb, e, fi, ff);
            if (wb.getNumberOfSheets() == 0) hojaResumen(wb, e, fi, ff);
            wb.write(out);
            return out.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo generar el Excel", ex);
        }
    }

    // Estilos reutilizables del libro
    private static class Estilos {
        final CellStyle titulo;
        final CellStyle header;
        final CellStyle money;
        final CellStyle date;

        Estilos(XSSFWorkbook wb) {
            Font fTit = wb.createFont();
            fTit.setBold(true);
            fTit.setFontHeightInPoints((short) 14);
            titulo = wb.createCellStyle();
            titulo.setFont(fTit);

            Font fHea = wb.createFont();
            fHea.setBold(true);
            fHea.setColor(IndexedColors.WHITE.getIndex());
            header = wb.createCellStyle();
            header.setFont(fHea);
            header.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            header.setBorderBottom(BorderStyle.THIN);

            DataFormat df = wb.createDataFormat();
            money = wb.createCellStyle();
            money.setDataFormat(df.getFormat("#,##0.00"));
            date = wb.createCellStyle();
            date.setDataFormat(df.getFormat("dd/mm/yyyy"));
        }
    }

    private void headers(Sheet s, int rowIdx, Estilos e, String... cols) {
        Row h = s.createRow(rowIdx);
        for (int i = 0; i < cols.length; i++) {
            Cell c = h.createCell(i);
            c.setCellValue(cols[i]);
            c.setCellStyle(e.header);
        }
    }

    private String periodo(String fi, String ff) {
        if (fi.isEmpty() && ff.isEmpty()) return "Todo el historial";
        return (fi.isEmpty() ? "inicio" : fi) + " a " + (ff.isEmpty() ? "hoy" : ff);
    }

    private String nvl(String s) {
        return s == null ? "" : s;
    }

    private int val(Integer i) {
        return i == null ? 0 : i;
    }

    private void hojaResumen(XSSFWorkbook wb, Estilos e, String fi, String ff) {
        ResumenRangoProjection k = r.resumenRango(fi, ff);
        Sheet s = wb.createSheet("Resumen");

        Row tr = s.createRow(0);
        Cell tc = tr.createCell(0);
        tc.setCellValue("Reporte de la Clínica Veterinaria");
        tc.setCellStyle(e.titulo);
        Row pr = s.createRow(1);
        pr.createCell(0).setCellValue("Periodo:");
        pr.createCell(1).setCellValue(periodo(fi, ff));

        headers(s, 3, e, "Indicador", "Valor");
        BigDecimal ingresos = k.getIngresos() == null ? BigDecimal.ZERO : k.getIngresos();
        int nv = val(k.getNumVentas());
        double ticket = nv > 0 ? ingresos.doubleValue() / nv : 0;

        int rn = 4;
        rn = kpi(s, rn, "Ingresos (S/)", ingresos.doubleValue(), e.money);
        rn = kpi(s, rn, "Número de ventas", nv, null);
        rn = kpi(s, rn, "Ticket promedio (S/)", ticket, e.money);
        rn = kpi(s, rn, "Citas atendidas", val(k.getCitasAtendidas()), null);
        rn = kpi(s, rn, "Citas canceladas", val(k.getCitasCanceladas()), null);
        rn = kpi(s, rn, "Productos vendidos", val(k.getProductosVendidos()), null);
        rn = kpi(s, rn, "Pacientes nuevos", val(k.getPacientesNuevos()), null);

        s.setColumnWidth(0, 22 * 256);
        s.setColumnWidth(1, 16 * 256);
    }

    private int kpi(Sheet s, int rowIdx, String label, double value, CellStyle style) {
        Row row = s.createRow(rowIdx);
        row.createCell(0).setCellValue(label);
        Cell v = row.createCell(1);
        v.setCellValue(value);
        if (style != null) v.setCellStyle(style);
        return rowIdx + 1;
    }

    private void hojaVentas(XSSFWorkbook wb, Estilos e, String fi, String ff) {
        List<DetalleProjection> data = r.detalle(fi, ff);
        Sheet s = wb.createSheet("Ventas");
        headers(s, 0, e, "Fecha", "Cliente", "Ítem", "Vendedor", "Total (S/)");
        int rn = 1;
        for (DetalleProjection d : data) {
            Row row = s.createRow(rn++);
            Cell f = row.createCell(0);
            if (d.getFecha() != null) {
                f.setCellValue(d.getFecha());
                f.setCellStyle(e.date);
            }
            row.createCell(1).setCellValue(d.getCliente() == null ? "Cliente general" : d.getCliente());
            row.createCell(2).setCellValue(nvl(d.getItem()));
            row.createCell(3).setCellValue(nvl(d.getVendedor()));
            Cell tot = row.createCell(4);
            if (d.getTotal() != null) {
                tot.setCellValue(d.getTotal().doubleValue());
                tot.setCellStyle(e.money);
            }
        }
        anchos(s, 12, 26, 26, 22, 12);
    }

    private void hojaCitas(XSSFWorkbook wb, Estilos e, String fi, String ff) {
        List<CitaDetalleProjection> data = r.detalleCitas(fi, ff);
        Sheet s = wb.createSheet("Citas");
        headers(s, 0, e, "Fecha", "Mascota", "Dueño", "Especie", "Raza", "Servicio", "Veterinario", "Estado", "Motivo");
        int rn = 1;
        for (CitaDetalleProjection c : data) {
            Row row = s.createRow(rn++);
            Cell f = row.createCell(0);
            if (c.getFecha() != null) {
                f.setCellValue(c.getFecha());
                f.setCellStyle(e.date);
            }
            row.createCell(1).setCellValue(nvl(c.getMascota()));
            row.createCell(2).setCellValue(nvl(c.getDueno()));
            row.createCell(3).setCellValue(nvl(c.getEspecie()));
            row.createCell(4).setCellValue(nvl(c.getRaza()));
            row.createCell(5).setCellValue(nvl(c.getServicio()));
            row.createCell(6).setCellValue(nvl(c.getVeterinario()));
            row.createCell(7).setCellValue(nvl(c.getEstado()));
            row.createCell(8).setCellValue(nvl(c.getMotivo()));
        }
        anchos(s, 12, 20, 24, 14, 16, 20, 22, 14, 30);
    }

    private void hojaVeterinarios(XSSFWorkbook wb, Estilos e, String fi, String ff) {
        List<VeterinarioProductividadProjection> data = r.productividadVeterinario(fi, ff);
        Sheet s = wb.createSheet("Veterinarios");
        headers(s, 0, e, "Veterinario", "Atendidas", "Canceladas", "Total citas");
        int rn = 1;
        for (VeterinarioProductividadProjection v : data) {
            Row row = s.createRow(rn++);
            row.createCell(0).setCellValue(nvl(v.getVeterinario()));
            row.createCell(1).setCellValue(val(v.getAtendidas()));
            row.createCell(2).setCellValue(val(v.getCanceladas()));
            row.createCell(3).setCellValue(val(v.getTotalCitas()));
        }
        anchos(s, 26, 12, 12, 12);
    }

    private void anchos(Sheet s, int... cols) {
        for (int i = 0; i < cols.length; i++) {
            s.setColumnWidth(i, cols[i] * 256);
        }
    }
}
