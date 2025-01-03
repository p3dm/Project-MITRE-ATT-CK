package ExcelExport;

import Model.MitreTechnique;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void exportToExcel(String filePath, List<MitreTechnique> mitreTechniques) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        
        Sheet mitreSheet = workbook.createSheet("MITRE ATT&CK");
        Row mitreHeader = mitreSheet.createRow(0);
        mitreHeader.createCell(0).setCellValue("Technique ID");
        mitreHeader.createCell(1).setCellValue("Name");
        mitreHeader.createCell(2).setCellValue("Description");
        mitreHeader.createCell(3).setCellValue("Attack Technique");
        mitreHeader.createCell(4).setCellValue("External ID");

        int mitreRowNum = 1;
        for (MitreTechnique technique : mitreTechniques) {
            Row row = mitreSheet.createRow(mitreRowNum++);
            row.createCell(0).setCellValue(technique.getId());
            row.createCell(1).setCellValue(technique.getName());
            row.createCell(2).setCellValue(technique.getDescription());
            row.createCell(3).setCellValue(technique.getAttackTechnique());
            row.createCell(4).setCellValue(technique.getExternalId());
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new IOException("Failed to save Excel file: " + e.getMessage(), e);
        } finally {
            workbook.close();
        }

        System.out.println("Excel file saved successfully to: " + filePath);
    }
}
