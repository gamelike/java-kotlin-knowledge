package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author gjd
 */
public class URLResource implements Resource {
  private final URL url;

  public URLResource(URL url) {
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    URLConnection urlConnection = this.url.openConnection();
    try {
      return urlConnection.getInputStream();
    } catch (IOException e) {
      throw e;
    }
  }
}
