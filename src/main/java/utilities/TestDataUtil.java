package utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class TestDataUtil {

    public static JsonObject getTestData(String key) {
        try (FileReader reader = new FileReader("src/test/resources/testdata.json")) {
            JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
            return data.getAsJsonObject(key);
        } catch (IOException e) {
            throw new RuntimeException("Gagal membaca testdata.json: " + e.getMessage());
        }
    }
}
