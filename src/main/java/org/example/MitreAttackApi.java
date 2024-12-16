package org.example;

import java.net.http.HttpClient;
import java.util.List;

public class MitreAttackApi {
    // API Helper Class
        private static final String API_URL = "https://cti-taxii.mitre.org/taxii/";

        public static String fetchData(String endpoint) {
            // Sử dụng OkHttp hoặc HttpClient để gọi API
            return jsonData;
        }

        public static List<Technique> parseTechniques(String jsonData) {
            // Parse JSON và map vào danh sách các kỹ thuật
            return techniques;
        }
}
