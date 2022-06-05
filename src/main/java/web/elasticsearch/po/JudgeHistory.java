package web.elasticsearch.po;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author violet.
 */
@Document(indexName = "judge_history")
@Data
public class JudgeHistory {

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "id", type = FieldType.Text)
    private String id;

    @Field(name = "converter")
    private String converter;

    @Field(name = "enum")
    @Enumerated(EnumType.STRING)
    private EnumType enumType;

}
