package jdkStudy.jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import java.util.Optional;
import java.util.Scanner;

/**
 * @author gjd
 */
public class OptionalStudy {
  private static Logger log = LoggerFactory.getLogger(OptionalStudy.class);

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String str = null;
//      String str = sc.nextLine();
      if (str != null) {
        log.info(str.toLowerCase());
      }
      Optional
          .ofNullable(str)  //封装
          .ifPresent(s -> log.info(s.toUpperCase()));  // 对象不是null才会被接受.
      log.info(Optional
          .ofNullable(str)
          .orElse("VSW"));
      break;
    }

  }
}
