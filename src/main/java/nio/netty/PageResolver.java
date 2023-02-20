package nio.netty;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gjd3
 */
public class PageResolver {
  private static final PageResolver INSTANCE = new PageResolver();

  private PageResolver() {
  }

  public static PageResolver getInstance() {
    return INSTANCE;
  }

  public FullHttpResponse resolveResource(String path) {
    if (path.startsWith("/")) {
      path = path.equals("/") ? "netty/index.html" : "netty/" + path.substring(1);
      try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path)) {

        if (stream != null) {
          byte[] bytes = new byte[stream.available()];
          stream.read(bytes);
          return this.packet(HttpResponseStatus.OK, bytes);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return this.packet(HttpResponseStatus.NOT_FOUND, "404 NOT FOUND".getBytes());
  }

  private FullHttpResponse packet(HttpResponseStatus status, byte[] bytes) {
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
    response.content().writeBytes(bytes);
    return response;
  }
}
