package json.jackson.serialize.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author violet.
 */
@Getter
@Setter
public class JackSonExtend {

    public String name;

    private Map<String, String> properties;

    public JackSonExtend() {
        this.properties = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public void add(String key, String value) {
        this.properties.put(key, value);
    }

}
