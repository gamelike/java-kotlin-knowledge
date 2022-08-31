package jdkStudy.api.reflect;

/**
 * @author gjd
 */
public class CodeGenerator {

  static{
    System.out.println("123");
  }

  public CodeGenerator(String name) {

  }

  public static String generate(String name) {
    return name;
  }
}
