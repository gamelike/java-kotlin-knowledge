package json.jackson.deserialize.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

/**
 * @author violet.
 */
public class BeanWithInject {

    @JacksonInject
    private int id;

    @JsonProperty
    private String name;

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "BeanWithInject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
