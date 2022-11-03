package web.design.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.mapper.ParseContext;
import org.springframework.stereotype.Service;
import web.design.StrategyPattern;
import web.design.model.SearchType;
import web.design.model.po.StrategyEntity;

import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author violet
 * @since 2022/11/3
 */
@Slf4j
@Service("Elasticsearch")
@RequiredArgsConstructor
public class EsStrategyPatternImpl implements StrategyPattern {

    private final ElasticsearchClient client;

    @Override
    public SearchType type() {
        return SearchType.Elasticsearch;
    }

    @Override
    public List<StrategyEntity> queryDataList() {
        try {
            SearchResponse<StrategyEntity> response = client.search(SearchRequest.of(r -> r.index("strategy_index")
                    .size(10)), StrategyEntity.class);
            return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("es search error");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public StrategyEntity saveData(StrategyEntity entity) {
        try {
            UpdateResponse<StrategyEntity> update = client.update(UpdateRequest.of(r -> r.index("strategy-entity")
                    .docAsUpsert(true)
                    .doc(entity)
                    .id(String.valueOf(entity.getId()))), StrategyEntity.class);
            return Objects.requireNonNull(update.get()).source();
        } catch (IOException e) {
            log.error("es save error");
            throw new RuntimeException(e);
        }
    }
}
