import Model.AtomicTest;

import Processor.MitreAtomicIntegrator;
import API.MitreApiFetcher;
import API.AtomicApiFetcher;
import Model.MitreTechnique;
import ExcelExport.ExcelExporter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            List<MitreTechnique> mitreTechniques = MitreApiFetcher.fetchMitreTechniques();
            AtomicTest atomicTest = AtomicApiFetcher.fetchAtomicTest();

            List<AtomicTest> integratedTests = MitreAtomicIntegrator.integrateMitreAndAtomic(mitreTechniques, List.of(atomicTest));

            String outputPath = "output/atomic_tests.xlsx";
            ExcelExporter.exportToExcel(outputPath, mitreTechniques, integratedTests);

            System.out.println("Data integration and export completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
