package json.jackson.serialize.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import json.jackson.serialize.customSerialization.CustomDataSerializer;

import java.time.LocalDateTime;

/**
 * @author violet.
 */
public record JsonSerializeDto(String name, @JsonSerialize(using = CustomDataSerializer.class) LocalDateTime eventDate) {
}
