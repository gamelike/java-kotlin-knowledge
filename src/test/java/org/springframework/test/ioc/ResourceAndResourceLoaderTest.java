package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import jdk.jpackage.internal.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.URLResource;

import java.io.InputStream;
import java.util.Date;

/**
 * @author gjd
 */
public class ResourceAndResourceLoaderTest {
  @Test
  public void testResourceLoader() throws Exception {
    DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    //加载classPath下文件
    Resource classPathResource = resourceLoader.getResource("classpath:hello.txt");
    InputStream inputStream = classPathResource.getInputStream();
    String content = IoUtil.readUtf8(inputStream);
    System.out.println(content);
    Assertions.assertThat(content).isEqualTo("hello world");

    //加载磁盘文件
    Resource fileSystemFile = resourceLoader.getResource("src/test/resources/hello.txt");
    InputStream inputStream1 = fileSystemFile.getInputStream();
    String content1 = IoUtil.readUtf8(inputStream1);
    System.out.println(content1);
    Assertions.assertThat(content1).isEqualTo("hello world");

    //加载网络文件
    Resource urlResource = resourceLoader.getResource("https://github.com/DerekYRC/mini-spring/blob/main/README.md");
    Assertions.assertThat(urlResource instanceof URLResource).isTrue();
    String content2 = IoUtil.readUtf8(urlResource.getInputStream());
    System.out.println(content2);
  }
}
