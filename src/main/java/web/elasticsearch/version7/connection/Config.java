package web.elasticsearch.version7.connection;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
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
    public static RestHighLevelClient connection() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        X509TrustManager trustAll = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new X509TrustManager[]{trustAll}, new SecureRandom());
        return new RestHighLevelClient(
                RestClient.builder(
                                new HttpHost("10.7.212.241", 23200, "http"))
                        .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                            httpAsyncClientBuilder.setSSLContext(sslContext);
                            return httpAsyncClientBuilder;
                        })
        );
    }

    /**
     * @param client {@link RestHighLevelClient} close connection.
     */
    public static void close(RestHighLevelClient client) {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
