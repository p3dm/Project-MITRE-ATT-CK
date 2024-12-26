package Atomic_Red_Team_technique;

import Mitre_ATTxCK.Data.DataIntegrator;
import Mitre_ATTxCK.MitreTechnique;
import ExcelExport.ExcelExporter;

import java.util.List;

public class Main {
    // Main Application
    public static void main(String[] args) throws Exception {
        // Đọc dữ liệu từ Atomic Red Team
        AtomicTest atomicTest = DocFileYamlAtomic.readYaml("path/to/atomic.yaml");

        // Đọc dữ liệu từ MITRE ATT&CK
        List<MitreTechnique> mitreTechniques = DocMitreJSON.readAllJsonInDirectory("resources/enterprise-attack");

        // Kết hợp dữ liệu
        DataIntegrator.connectTechniques(mitreTechniques, List.of(atomicTest)).forEach(System.out::println);

        // Xuất ra Excel
        ExcelExporter.exportToExcel(List.of(atomicTest), "output.xlsx");

        System.out.println("Exported successfully!");
    }
}
