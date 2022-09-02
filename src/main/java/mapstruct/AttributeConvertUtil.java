package mapstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.SneakyThrows;
import org.mapstruct.Named;

/**
 * @author gjd
 */
public class AttributeConvertUtil {
  @SneakyThrows
  @Named("json2Object")
  public Attributes json2Object(String jsonStr) {
    if (Strings.isNullOrEmpty(jsonStr)) {
      return null;
    }
    return new ObjectMapper().readValue(jsonStr, Attributes.class);
  }
}
