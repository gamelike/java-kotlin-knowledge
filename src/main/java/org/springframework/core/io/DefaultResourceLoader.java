package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author gjd
 */
public class DefaultResourceLoader implements ResourceLoader {

  public static final String CLASS_PATH_PREFIX = "classpath:";

  @Override
  public Resource getResource(String location) {
    if (location.startsWith(CLASS_PATH_PREFIX)) {
      //classpath下的资源
      return new ClassPathResource(location.substring(CLASS_PATH_PREFIX.length()));
    } else {
//尝试当成URL处理
      try {
        URL url = new URL(location);
        return new URLResource(url);
      } catch (MalformedURLException e) {
        //当成文件来处理
        return new FileSystemResource(location);
      }
    }
  }
}
