package json.jackson.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author violet.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record BeanWithIgnoreIncluded(String firstName,String lastName) {
    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }
}
