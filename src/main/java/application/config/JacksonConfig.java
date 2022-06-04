package application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author gjd
 */
//@Configuration
public class JacksonConfig {

  @Primary
  @Bean
  public ObjectMapper objectMapper() {
    //todo 通过OBJECT_MAPPER返回的 fruit对象，
    // 其中枚举会被转化为小写，new 的新对象不会影响
    // RequestResponseBodyMethodProcessor  handleReturnValue
    // AbstractJackson2HttpMessageConverter 了解其中objectmapper被注册了那些值
//    return new ObjectMapper().disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
    return ToolUtils.OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
  }
}
