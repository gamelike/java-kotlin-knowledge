package jdkStudy.api;

/**
 * @author gjd
 */
public class SystemApi {
  public static void main(String[] args) {
    System.out.println(System.getenv());  //读电脑环境变量
    System.out.println(System.getProperties());  //读jvm tomcat配置
  }

}