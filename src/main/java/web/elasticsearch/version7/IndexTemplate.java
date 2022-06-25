package web.elasticsearch.version7;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import web.elasticsearch.version7.connection.Config;
import web.elasticsearch.model.dto.TemplateEngine;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author violet
 */
@Slf4j
public class IndexTemplate {

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static PutIndexTemplateRequest indexTemplate() {
        PutIndexTemplateRequest indexTemplateRequest = new PutIndexTemplateRequest("judge-history-template");

        ObjectMapper objectMapper = new ObjectMapper();
        // 请改为自己电脑路径
        File file = Paths.get("E:\\Project\\study\\src\\main\\resources\\template", "judge-history-template.json").toFile();
        TemplateEngine templateEngine = objectMapper.readValue(file, TemplateEngine.class);

        indexTemplateRequest.mapping(templateEngine.getMappings());
        indexTemplateRequest.settings(templateEngine.getIndex());
        indexTemplateRequest.order(templateEngine.getOrder());
        indexTemplateRequest.patterns(templateEngine.getIndexPatterns());

        return indexTemplateRequest;
    }

    @SneakyThrows
    public static void main(String[] args) {
        RestHighLevelClient client = Config.connection();
        AcknowledgedResponse acknowledgedResponse = client.indices().putTemplate(indexTemplate(), RequestOptions.DEFAULT);
        log.info("{}", acknowledgedResponse);
        Config.close(client);
    }

}
