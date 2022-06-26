package web.elasticsearch.version8;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import web.elasticsearch.version8.connection.Config;
import web.elasticsearch.version8.dto.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class Index {

    @SneakyThrows
    public static void main(String[] args) {
        ElasticsearchClient elasticsearchClient = Config.elasticsearchClient();
        try {
            // create index
            // package structure and name space
            elasticsearchClient.indices().create(c -> c.index("history"));
        } catch (IOException e) {
            log.error("创建索引history失败!", e);
        }

        // blocking and asynchronous clients
        if (elasticsearchClient.exists(c -> c.index("history").id("foo")).value()) {
            log.info("history exists");
        }
        ElasticsearchAsyncClient asyncClient = Config.elasticsearchAsyncClient();
        asyncClient.exists(b -> b.index("history").id("foo")).whenComplete((response, exception) -> {
            if (exception != null) {
                log.error("failed to index", exception);
            } else {
                log.info("history exists");
            }
        });

        Config.closeTransport();
    }

    public void listAndMaps(ElasticsearchClient client) {
        // prepare a list of index names
        List<String> names = Arrays.asList("inx-a", "inx-b", "inx-c");
        // prepare cardinally aggregations for fields "foo" and "bar"
        Map<String, Aggregation> cardinalities = Maps.newHashMap();
        cardinalities.put("foo-count", Aggregation.of(a -> a.cardinality(c -> c.field("foo"))));
        cardinalities.put("bar-count", Aggregation.of(a -> a.cardinality(c -> c.field("bar"))));

        // prepare an aggregation that computes the average of the "size" field
        final Aggregation avgSize = Aggregation.of(a -> a.avg(v -> v.field("size")));

        SearchRequest request = SearchRequest.of(r -> r
                // index list
                // - add all elements of a list
                .index(names)
                // - add a single element
                .index("inx-d")
                // - add a vararg list of elements
                .index("inx-e", "inx-f", "inx-g")

                // sort order list: add elements defined by builder
                .sort(s -> s.field(f -> f.field("foo").order(SortOrder.Desc)))
                .sort(s -> s.field(f -> f.field("bar").order(SortOrder.Asc)))

                // Aggregations map:
                // - add all entries of an existing map
                .aggregations(cardinalities)
                // - add a key/value entity
                .aggregations("avg-size", avgSize)
                // - add a key/value defined by a builder lambda
                .aggregations("price-histogram",
                        a -> a.histogram(h -> h.field("price")))
        );
    }


    ///////////////////////////////////
    /////// index api /////////////////
    ///////////////////////////////////
    // building API
    @SneakyThrows
    public CreateIndexResponse normalCreateIndex(ElasticsearchClient client) {
        return client.indices().create(new CreateIndexRequest.Builder().index("history").aliases("foo", new Alias.Builder().isWriteIndex(true).build()).build());
    }

    @SneakyThrows
    public CreateIndexResponse streamCreateIndex(ElasticsearchClient client) {
        return client.indices().create(c -> c.index("history").aliases("foo", a -> a.isWriteIndex(true)));
    }


    //////////////////////////////////
    ////// search api ////////////////
    //////////////////////////////////
    @SneakyThrows
    public SearchResponse<Data> searchResponse(ElasticsearchClient client) {
        return client.search(b0 ->
                        b0.query(b1 -> b1.intervals(b2 -> b2.field("my_text")
                                .allOf(b3 -> b3.ordered(true)
                                        .intervals(b4 -> b4.match(b5 -> b5.query("my favorite food").maxGaps(0).ordered(true)))
                                        .intervals(b4 -> b4.anyOf(b5 -> b5.intervals(b6 -> b6.match(b7 -> b7.query("hot water")))
                                                .intervals(b6 -> b6.match(b7 -> b7.query("cold porridge")))))))),
                Data.class);
    }

    @SneakyThrows
    public SearchResponse<Data> termsQuery(ElasticsearchClient client) {
        return client.search(b0 -> b0.query(
                b1 -> b1.term(f2 -> f2
                        .field("name")
                        .value(v3 -> v3.stringValue("bob")))
        ), Data.class);
    }

    @SneakyThrows
    @Test
    public void termsQuery() {
        Query query = new Query.Builder()
                .term(t -> t.field("name")
                        .value(v -> v.stringValue("bob")))
                .build();

        Assert.assertEquals("bob", query.term().value().stringValue());

        if (query.isTerm()) {
            log.info("this is term query.");
        }

        switch (query._kind()) {
            case Term -> log.info("term query condition: {}", query._get());
            case Intervals -> log.info("intervals query condition: {}", query._get());
            default -> log.error("this is err query");
        }

        SearchRequest build = new SearchRequest.Builder().query(query).build();
        log.info("query dsl : {}", build.toString());
    }

}