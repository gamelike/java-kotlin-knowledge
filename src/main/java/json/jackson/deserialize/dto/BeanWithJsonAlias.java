package json.jackson.deserialize.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author violet.
 */
public record BeanWithJsonAlias(
        @JsonAlias({"fName", "f_name"}) String firstName,
        String lastName) {
    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }
}
