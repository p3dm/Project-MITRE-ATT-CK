package Processor;


import Model.AtomicTest;
import Model.MitreTechnique;

import java.util.ArrayList;
import java.util.List;


public class DataIntegrator {
    public static List<AtomicTest> integrateMitreAndAtomic(List<MitreTechnique> mitreTechniques, List<AtomicTest> atomicTests) {
        List<AtomicTest> integratedTests = new ArrayList<>();

        for (MitreTechnique technique : mitreTechniques) {
            for (AtomicTest atomicTest : atomicTests) {
                if (technique.getTechniqueId().equals(atomicTest.getTechniqueId())) {
                    // Thêm thông tin từ MITRE vào Atomic Test
                    atomicTest.setDescription(technique.getDescription());
                    atomicTest.setName(technique.getName());
                    integratedTests.add(atomicTest);
                }
            }
        }

        return integratedTests;
    }
}
