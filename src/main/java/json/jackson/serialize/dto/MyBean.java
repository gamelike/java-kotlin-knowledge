package json.jackson.serialize.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * use JsonPropertyOrder to specify the order of properties.
 *
 * @author violet.
 */
@JsonPropertyOrder({"username", "password"})
public record MyBean(String username, String password) {

    @JsonGetter("username")
    @Override
    public String username() {
        return username;
    }

}
