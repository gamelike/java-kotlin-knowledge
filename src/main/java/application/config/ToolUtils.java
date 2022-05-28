package application.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.base.Strings;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author gjd
 */
public class ToolUtils {

  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static final Converter<String, String> HYPHEN_TO_CAMEL = CaseFormat.LOWER_HYPHEN.converterTo(CaseFormat.LOWER_CAMEL);
  private static final Converter<String, String> UNDERSCORE_TO_CAMEL = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);

  static {
    OBJECT_MAPPER.setVisibility(
            OBJECT_MAPPER.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE))
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setDateFormat(new SimpleDateFormat("yyy-MM-dd HH:mm:ss"))
        .setTimeZone(TimeZone.getTimeZone("GMT+8"))
        .addHandler(new DeserializationProblemHandler() {
          @Override
          public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
            propertyName = HYPHEN_TO_CAMEL.convert(propertyName);
            propertyName = UNDERSCORE_TO_CAMEL.convert(propertyName);

            if (deserializer instanceof BeanDeserializerBase) {
              SettableBeanProperty property = ((BeanDeserializerBase) deserializer).findProperty(propertyName);
              if (property != null) {
                property.deserializeAndSet(p, ctxt, beanOrClass);
                return true;
              }
            }

            return false;
          }
        });
    SimpleModule enumInsensitiveModule = new SimpleModule();
    enumInsensitiveModule.setDeserializerModifier(new BeanDeserializerModifier() {
      @Override
      public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
                                                           final JavaType type,
                                                           BeanDescription beanDesc,
                                                           final JsonDeserializer<?> deserializer) {
        return new JsonDeserializer<Enum>() {
          @Override
          public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
            if (Strings.isNullOrEmpty(jp.getValueAsString())) {
              return null;
            }
            return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
          }
        };
      }
    });
    enumInsensitiveModule.addSerializer(Enum.class, new StdSerializer<Enum>(Enum.class) {
      @Override
      public void serialize(Enum value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.name().toLowerCase());
      }
    });
    OBJECT_MAPPER.registerModule(enumInsensitiveModule);

    SimpleModule doubleInsensitiveModule = new SimpleModule();
    doubleInsensitiveModule.addSerializer(Double.class, new StdSerializer<Double>(Double.class) {
      public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeNumber(new BigDecimal(value).toPlainString());
      }
    });

    ToolUtils.OBJECT_MAPPER.registerModule(doubleInsensitiveModule);
  }
}
