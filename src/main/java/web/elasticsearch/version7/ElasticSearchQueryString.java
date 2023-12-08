package web.elasticsearch.version7;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchQueryString {
    public static void main(String[] args) {
        SearchRequest request = new SearchRequest("test");
        request.source().query(QueryBuilders.boolQuery()
                .must(QueryBuilders.queryStringQuery("(name = \"zyf\" or age = \"1\") and name = \"test\"")));
        System.out.println(request.source());
    }
}
