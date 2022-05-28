package json.jackson.serialize.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * @author violet.
 */
public record RawBean(String name,@JsonRawValue String text) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String text() {
        return text;
    }
}
