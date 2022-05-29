package json.jackson.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author violet.
 */
@JsonIgnoreProperties({"id"})
public record BeanWithIgnore(
        Integer id,
        String name
) {
    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}
