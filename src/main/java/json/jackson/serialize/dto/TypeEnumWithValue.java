package json.jackson.serialize.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @author violet.
 */
public enum TypeEnumWithValue {
    TYPE1(1 ,"type1"),

    TYPE2(2 ,"type2");

    private Integer id;

    private String name;

    TypeEnumWithValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
