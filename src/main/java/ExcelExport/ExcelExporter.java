package Atomic_Red_Team_technique;
import java.util.List;

public class ExcelExporter {
    public static void exportToExcel(List<AtomicTest> atomicTests, String filePath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Atomic Red Team");

        // Tạo tiêu đề
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Test Name");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Commands");

        // Ghi dữ liệu
        int rowIndex = 1;
        for (AtomicTest test : atomicTests) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(test.getTechniqueId());
            row.createCell(1).setCellValue(test.getName());
            row.createCell(2).setCellValue(test.getDescription());
            row.createCell(3).setCellValue(String.join(", ", test.getCommands()));
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}
