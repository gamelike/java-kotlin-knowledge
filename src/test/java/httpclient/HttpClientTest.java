package httpclient;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpContent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.function.Supplier;


public class HttpClientTest {


    @Test
    @SneakyThrows
    public void runHttpGet() {
//        String url = "https://httpbin.org/get";
        String url = "https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
//        URI uri = URI.create(url);
//        URL URL = new URL(url);

//        ?limit=10
        HttpContext httpContext = new HttpCoreContext();
        httpContext.setAttribute("code", "utf-8");
        httpContext.setAttribute("q", "卫衣");
        httpContext.setAttribute("callback", "cb");
//        HttpResponse response = httpClient.execute(httpGet, httpContext);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(IOUtils.toString(entity.getContent()));
    }

    @Test
    @SneakyThrows
    public void runHttpPOST() {
        String url = "https://httpbin.org/200";
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(Lists.newArrayList(), StandardCharsets.UTF_8));
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse execute = httpClient.execute(post);
        System.out.println(IOUtils.toString(execute.getEntity().getContent()));
    }

    @Test
    @SneakyThrows
    public void http_thread_callable_get() {
//        CloseableHttpClient client = HttpClients.createDefault();
//        String url = "http://10.7.212.41:22100/pensieve/resources/types/typesStorage";
//        HttpGet get = new HttpGet(url);
//        HttpContext httpContent = new HttpCoreContext();
//        httpContent.setAttribute("dataSourceType", "DATABASE_ES");
//        HttpResponse response = client.execute(get);
//        int statusCode = response.getStatusLine().getStatusCode();
        Supplier<Future<HttpEntity>> supplier = () -> Executors.newSingleThreadExecutor().submit(() -> {
            CloseableHttpClient client = HttpClients.createDefault();
            String url = "http://10.7.212.41:22100/pensieve/resources/types/typesStorage";
            HttpGet get = new HttpGet(url);
            HttpContext httpContent = new HttpCoreContext();
            httpContent.setAttribute("dataSourceType", "DATABASE_ES");
            HttpResponse response;
            try {
                response = client.execute(get);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int statusCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(statusCode, 200);
            return response.getEntity();
        });

        Callable<HttpEntity> callable = () -> {
            Future<HttpEntity> future = supplier.get();
            try {
                return future.get(1, TimeUnit.SECONDS);
            }catch (TimeoutException e) {
                System.out.println("超时异常");
                throw new RuntimeException(e);
            }
        };
        HttpEntity call = callable.call();
        System.out.println(call);
    }
}
