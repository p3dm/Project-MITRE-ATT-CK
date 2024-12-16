package org.example;

import java.util.List;

public class Main {
    // Main Application
        public static void main(String[] args) {
            String jsonData = MitreAttackApi.fetchData("techniques");
            List<Technique> techniques = MitreAttackApi.parseTechniques(jsonData);
            ExcelExporter.exportToExcel(techniques, "output.xlsx");
        }
}