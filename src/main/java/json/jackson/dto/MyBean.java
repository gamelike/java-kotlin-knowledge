package json.jackson.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
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
