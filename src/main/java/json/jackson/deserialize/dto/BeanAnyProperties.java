package json.jackson.deserialize.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

/**
 * @author violet.
 */
public class BeanAnyProperties {

    private String name;

    public Map<String,Object> properties;

    public BeanAnyProperties() {
        properties = new java.util.HashMap<>();
    }

    @JsonAnySetter
    public void add(String key, Object value){
        this.properties.put(key, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
