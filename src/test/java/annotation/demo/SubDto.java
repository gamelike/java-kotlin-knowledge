package annotation.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author violet
 */
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class SubDto {
    final String id;
    final String name;
}
