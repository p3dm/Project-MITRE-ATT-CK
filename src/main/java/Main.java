import API.MitreApiFetcher;
import Model.MitreTechnique;
import ExcelExport.ExcelExporter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<MitreTechnique> mitreTechniques = MitreApiFetcher.fetchMitreTechniques();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("\n\033[1;34m===== Atomic Red Team Data Fetcher =====\033[0m");
                System.out.println("1. Thu thap du lieu tu GitHub");
                System.out.println("2. Xuat du lieu ra file Excel");
                System.out.println("3. Thong ke ty le bao phu MITRE ATT&CK");
                System.out.println("4. Thoat chuong trinh");
                System.out.print("Lua chon cua ban: ");

                int choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        System.out.println("\033[1;33mDang thu thap du lieu...\033[0m");
                        mitreTechniques = MitreApiFetcher.fetchMitreTechniques();
                        try {
                            System.out.println("\033[1;32mThu thap du lieu thanh cong! So luong ky thuat: " + mitreTechniques.size() + "\033[0m");
                        } catch (Exception e) {
                            System.err.println("\033[1;31mLoi khi thu thap du lieu: " + e.getMessage() + "\033[0m");
                        }
                        break;
                    case 2:
                        if (mitreTechniques.isEmpty()) {
                            System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
                        } else {
                            System.out.print("Nhap ten file Excel (vi du: AtomicData.xlsx): ");
                            String fileName = br.readLine();
                            String outputPath = "output" +"/" + fileName;
                            ExcelExporter.exportToExcel(outputPath, mitreTechniques);
                            System.out.println("Data integration and export completed successfully!");
                        }
                        break;
//                    case 3:
//                        if (techniques.isEmpty()) {
//                            System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
//                        } else {
//                            analyzer.analyzeCoverage(techniques);
//                        }
//                        break;
                    case 4:
                        System.out.println("\033[1;34mThoat chuong trinh!\033[0m");
                        br.close();
                        return;
                    default:
                        System.out.println("\033[1;31mLua chon khong hop le. Vui long thu lai.\033[0m");
                }
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
