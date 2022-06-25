package web.elasticsearch.version7;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Assert;
import web.elasticsearch.version7.connection.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author violet.
 */
@Slf4j
public class ElasticSearchScroll {

    /**
     * elastic scroll search.
     *
     * @param searchSourceBuilder search source builder.
     * @param index index name.
     * @return {@link SearchResponse}
     */
    @SneakyThrows
    private static SearchResponse scrollResp(SearchSourceBuilder searchSourceBuilder, String... index) {
        // create connection //
        RestHighLevelClient rhl = Config.connection();
        // 滚动查询 //
        SearchRequest request = new SearchRequest(index).scroll(TimeValue.timeValueMinutes(1));
        // 设置查询条件 //
        request.source(searchSourceBuilder);
        // 执行查询 //
        SearchResponse response = rhl.search(request, RequestOptions.DEFAULT);
        // 获取查询结果 //
        long totalHits = response.getHits().getTotalHits();
        log.info("{}", response.getHits().getTotalHits());

        // store scroll id, completed search will clear scroll dump.//
        List<String> scrollIds = new ArrayList<>();
        List<SearchHit> hits = new ArrayList<>(Arrays.asList(response.getHits().getHits()));
        // search all hits by condition. //
        while(response.getHits().getHits() != null && response.getHits().getHits().length > 0) {
            for (SearchHit hit : response.getHits().getHits()) {
                log.info("{}", hit);
            }
            // store scroll id. //
            scrollIds.add(response.getScrollId());
            // search next scroll dump. //
            SearchScrollRequest scrollRequest = new SearchScrollRequest(response.getScrollId());
            // scroll expire time. //
            scrollRequest.scroll(TimeValue.MINUS_ONE);
            // execute search. //
            response = rhl.scroll(scrollRequest, RequestOptions.DEFAULT);
            // current scroll dump result. //
            SearchHit[] as = response.getHits().getHits();
            // store scroll dump result. //
            hits.addAll(Arrays.asList(as));
        }
        // last scroll id. //
        scrollIds.add(response.getScrollId());
        // clear scroll dump. //
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.scrollIds(scrollIds);
        ClearScrollResponse clearScrollResponse = rhl.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        // print clearResponse. //
        log.info("{}", clearScrollResponse);

        Assert.assertEquals(totalHits, hits.size());

        // print last scroll id. //
        log.info("scrollId: {}", response.getScrollId());
        // close connection. //
        Config.close(rhl);
        return response;
    }

    public static void main(String[] args) {
        Config.setLogLevel();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        System.out.println(scrollResp(searchSourceBuilder, "alarm_indexing").getScrollId());
    }

}
