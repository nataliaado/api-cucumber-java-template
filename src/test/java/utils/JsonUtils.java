package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    public static String readJson(String path) {

        try {
            return Files.readString(
                    Paths.get("src/test/resources/" + path));

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao ler arquivo JSON: " + path,
                    e);
        }
    }

    public static JsonNode readJsonNode(String path) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readTree(
                    Files.readString(
                            Paths.get("src/test/resources/" + path)));

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao ler arquivo JSON: " + path,
                    e);
        }
    }
}