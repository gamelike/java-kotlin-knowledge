package web.elasticsearch.version7.connection;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author violet.
 */
public class Config {

    // bugs //
    public static void setLogLevel() {
        Logger.getAnonymousLogger().setLevel(Level.INFO);
    }

    /**
     * @return {@link RestHighLevelClient}
     */
    public static RestHighLevelClient connection(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.7.212.241", 23200, "http"))
        );
    }

    /**
     * @param client {@link RestHighLevelClient} close connection.
     */
    public static void close(RestHighLevelClient client){
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
