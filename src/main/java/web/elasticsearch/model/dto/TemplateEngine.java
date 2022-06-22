package web.elasticsearch.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author violet
 */
@JsonDeserialize
@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TemplateEngine {

    int order;

    @JsonAlias("index_patterns")
    List<String> indexPatterns;

    Map<String,Object> index;

    Map<String,Object> mappings;

}
