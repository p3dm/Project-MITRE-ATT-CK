package API;

import Model.MitreTechnique;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MitreApiFetcher {
    private static final String MITRE_API_URL = "https://raw.githubusercontent.com/mitre/cti/ATT%26CK-v6.0/enterprise-attack/enterprise-attack.json";

    public static List<MitreTechnique> fetchMitreTechniques() throws Exception {
        URL url = new URI(MITRE_API_URL).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch data. HTTP response code: " + responseCode);
        }

        try (InputStream inputStream = connection.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode objectsNode = rootNode.get("objects");
            if (objectsNode == null || !objectsNode.isArray()) {
                throw new IOException("Invalid JSON format: 'objects' field is missing or not an array.");
            }

            List<MitreTechnique> techniques = new ArrayList<>();
            for (JsonNode node : objectsNode) {
                // Process only "attack-pattern" types
                if (node.has("type") && node.get("type").asText().equals("attack-pattern")) {
                    MitreTechnique technique = new MitreTechnique();
                    technique.setAttackTechnique(node.get("type").asText());

                    // Set technique ID
                    if (node.has("id")) {
                        technique.setId(node.get("id").asText());
                    }

                    // Set name
                    if (node.has("name")) {
                        technique.setName(node.get("name").asText());
                    }

                    // Set description
                    if (node.has("description")) {
                        technique.setDescription(node.get("description").asText());
                    }

                    // Extract the external_references field
                    if (node.has("external_references")) {
                        JsonNode externalRefsNode = node.get("external_references");
                        if (externalRefsNode.isArray()) {
                            for (JsonNode refNode : externalRefsNode) {
                                if (refNode.has("external_id")) {
                                    technique.setExternal_id(refNode.get("external_id").asText());
                                    break; // Use the first external ID found
                                }
                            }
                        }
                    }

                    techniques.add(technique);
                }
            }

            return techniques;
        }
    }
}
