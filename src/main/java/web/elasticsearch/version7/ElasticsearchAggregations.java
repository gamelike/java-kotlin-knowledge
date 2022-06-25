package web.elasticsearch.version7;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import web.elasticsearch.version7.connection.Config;
import web.utils.DateUtils;
import web.utils.EsUtils;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author violet.
 */
@Slf4j
public class ElasticsearchAggregations {

    @SneakyThrows
    public static void main(String[] args) {
        // elasticsearch
        SearchRequest searchRequest = new SearchRequest("alarm_event*");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        searchRequest.source().query(QueryBuilders.boolQuery()
                .filter(EsUtils.TimeSet((String) null, null))
        ).size(0).aggregation(
                AggregationBuilders.dateHistogram("TIME").field("TIME").dateHistogramInterval(
                                DateUtils.dataHistogram("days", 1)
                        ).extendedBounds(new ExtendedBounds(DateUtils.DateToString(DateUtils.addDay(date,DateUtils.DEFAULT)),
                                DateUtils.DateToString(date)))
                        .subAggregation(AggregationBuilders.terms("CAT1").field("CAT1"))
        );

        try (RestHighLevelClient client = Config.connection()){
            SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

            Aggregations aggregations = search.getAggregations();

            for (Aggregation aggregation : aggregations) {
                log.info("{}",aggregation.getMetaData());
            }
        }
    }
}
