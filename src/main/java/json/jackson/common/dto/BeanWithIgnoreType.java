package json.jackson.common.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author violet.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanWithIgnoreType {

    private int id;

    private IgnoreType ignoreType;

    @AllArgsConstructor
    @JsonIgnoreType
    public static class IgnoreType {
        private String firstName;
        private String lastName;
    }

}
