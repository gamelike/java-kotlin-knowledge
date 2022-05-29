package json.jackson.serialize.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import json.jackson.custom.serialize.CustomDataSerializer;

import java.time.LocalDateTime;

/**
 * @author violet.
 */
public record JsonSerializeDto(String name, @JsonSerialize(using = CustomDataSerializer.class) LocalDateTime eventDate) {
}
