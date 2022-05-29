package json.jackson.deserialize.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import json.jackson.custom.deserialize.CustomDateDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author violet.
 */
@Getter
@Setter
@ToString
public class BeanWithDeserialize {

    private int id;

    private String name;

    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

}
