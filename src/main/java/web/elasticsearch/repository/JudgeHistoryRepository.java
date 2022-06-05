package web.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import web.elasticsearch.po.JudgeHistory;

import java.util.List;

/**
 * @author violet.
 */
public interface JudgeHistoryRepository extends ElasticsearchRepository<JudgeHistory, String> {

    List<JudgeHistory> findAllByNameEquals(String name);

}
