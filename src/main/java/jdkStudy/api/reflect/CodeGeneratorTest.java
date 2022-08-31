package jdkStudy.api.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author gjd
 */
public class CodeGeneratorTest {
  @Test
  public void generateTest() throws Exception {
    for (int i = 1;i<10;i++){

      //false时，不执行静态代码
      Class<?> codeGeneratorClass = Class.forName("jdkStudy.api.reflect.CodeGenerator", false, Thread.currentThread().getContextClassLoader());
    }
    //true时，会加载
    Class<?> codeGeneratorClass = Class.forName("jdkStudy.api.reflect.CodeGenerator", true, Thread.currentThread().getContextClassLoader());
    //如果在执行构造之前一直是false，则知道执行构造方法才会执行static内的代码
    Object test = codeGeneratorClass.getConstructor(String.class).newInstance("test");
    System.out.println(test);
    Method generate = codeGeneratorClass.getMethod("generate", String.class);
    //执行静态方法，传递的参数可以为对象可以为null
    System.out.println(generate.invoke(null, "gjd"));
  }
}
