package httpclient;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.junit.Test;

import java.nio.charset.StandardCharsets;


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
}
