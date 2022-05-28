package json.jackson.serialize.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * namespace: serialization.
 * <user xmlns="users">
 *     <id xmlns="">1</id>
 *     <name xmlns="">violet</name>
 *     <items xmlns=""/>
 * </user>
 *
 * @author violet.
 */
@JsonRootName(value = "user", namespace = "user")
@JsonPropertyOrder({"id", "name"})
public record User(int id, String name) {
    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}
