package ExcelExport;

import Model.AtomicTest;
import Model.MitreTechnique;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void exportToExcel(String filePath, List<MitreTechnique> mitreTechniques, List<AtomicTest> atomicTests) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        // Sheet 1: MITRE ATT&CK
        Sheet mitreSheet = workbook.createSheet("MITRE ATT&CK");
        Row mitreHeader = mitreSheet.createRow(0);
        mitreHeader.createCell(0).setCellValue("Technique ID");
        mitreHeader.createCell(1).setCellValue("Name");
        mitreHeader.createCell(2).setCellValue("Description");
        mitreHeader.createCell(3).setCellValue("Tactic");

        int mitreRowNum = 1;
        for (MitreTechnique technique : mitreTechniques) {
            Row row = mitreSheet.createRow(mitreRowNum++);
            row.createCell(0).setCellValue(technique.getId());
            row.createCell(1).setCellValue(technique.getName());
            row.createCell(2).setCellValue(technique.getDescription());
            row.createCell(3).setCellValue(technique.getType());
        }

        // Sheet 2: Atomic Tests
        Sheet atomicSheet = workbook.createSheet("Atomic Tests");
        Row atomicHeader = atomicSheet.createRow(0);
        atomicHeader.createCell(0).setCellValue("Technique ID");
        atomicHeader.createCell(1).setCellValue("Name");
        atomicHeader.createCell(2).setCellValue("Description");
        atomicHeader.createCell(3).setCellValue("Commands");

        int atomicRowNum = 1;
        for (AtomicTest test : atomicTests) {
            Row row = atomicSheet.createRow(atomicRowNum++);
            row.createCell(0).setCellValue(test.getTechniqueId());
            row.createCell(1).setCellValue(test.getName());
            row.createCell(2).setCellValue(test.getDescription());
            row.createCell(3).setCellValue(String.join(", ", test.getCommands()));
        }

        // Ghi file ra hệ thống
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        workbook.close();
    }
}
