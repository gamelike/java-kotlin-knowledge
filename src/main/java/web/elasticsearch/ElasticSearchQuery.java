package web.elasticsearch;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import web.connection.elasticsearch.Config;

/**
 * @author violet.
 */
@Slf4j
public class ElasticSearchQuery {

    /**
     * elasticsearch query api.
     *
     * @param index elasticsearch index.
     * @param searchSourceBuilder query condition builder.
     * @return {@link SearchRequest}.
     */
    private static SearchRequest Query(String index, SearchSourceBuilder searchSourceBuilder) {
        // create search request /////////////
        SearchRequest searchRequest = new SearchRequest(index);
        // set query condition to search request //
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    @SneakyThrows
    public static void main(String[] args) {
        // log level not working //////////////
        Config.setLogLevel();
        // elasticsearch connection //////////
        RestHighLevelClient rhl = Config.connection();

        // query condition ///////////////////
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // search response ///////////////////
        SearchResponse search = rhl.search(Query("alarm_indexing", searchSourceBuilder), RequestOptions.DEFAULT);

        // query DSL /////////////////////////
        log.info("{}", searchSourceBuilder);

        // total hits ////////////////////////
        log.info("{}", search.getHits().getTotalHits());
        // close connection ///////////////////
        Config.close(rhl);
    }

}
