package json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * @author violet.
 */
@Slf4j
public class GsonUsage {

    public static void ParserJson(String s) {
        JsonElement jsonElement = JsonParser.parseString(s);
        if (jsonElement.isJsonObject()) {
            log.info("jsonElement: {}", jsonElement.getAsJsonObject());
        } else if (jsonElement.isJsonArray()) {
            log.info("jsonElement: {}", jsonElement.getAsJsonArray());
        } else {
            log.info("jsonElement: {}", jsonElement.getAsString());
        }
    }

    public static void main(String[] args) {
        String str = """
               abc
               """;
        ParserJson(str);
    }

}
