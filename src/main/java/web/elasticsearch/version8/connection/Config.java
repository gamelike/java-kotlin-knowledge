package web.elasticsearch.version8.connection;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class Config {

    private final static ElasticsearchTransport transport = new RestClientTransport(
            restClient(),
            new JacksonJsonpMapper()
    );


    /**
     * @return {@link ElasticsearchClient}
     */
    public static ElasticsearchClient elasticsearchClient() {
        return new ElasticsearchClient(transport);
    }

    public static ElasticsearchAsyncClient elasticsearchAsyncClient() {
        return new ElasticsearchAsyncClient(transport);
    }

    @SneakyThrows
    public static void closeTransport() {
        transport.close();
    }

    /////////////////////////////
    //// private method /////////
    /////////////////////////////

    private static RestClient restClient() {
        return RestClient.builder(
                new HttpHost("120.25.235.79", 3000)
        ).build();
    }

}
