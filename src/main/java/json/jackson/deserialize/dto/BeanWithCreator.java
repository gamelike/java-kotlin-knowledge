package json.jackson.deserialize.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

/**
 * @author violet.
 */
@ToString
public class BeanWithCreator {

    public int id;

    public String name;

    @JsonCreator
    public BeanWithCreator(
            @JsonProperty("id") int id,
            @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
