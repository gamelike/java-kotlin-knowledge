package jdkStudy.jdk8;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author gjd3
 */
public class FunctionDemo {

  //断言类型
  @Test
  public void predicate() {
    Predicate<Integer> predicate = new Predicate<Integer>() {
      @Override
      public boolean test(Integer integer) {
        return integer > 0;
      }
    };

    Predicate<Integer> predicate2 = integer -> integer > 0;

    System.out.println(predicate2.test(6));
    System.out.println(predicate2.test(-1));
  }

  //消费者 会将拿到的东西做处理
  @Test
  public void consumer() {
    Consumer<String> consumer = new Consumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(s);
      }
    };

    consumer.accept("test");
  }


  //提供者 从函数中取值
  @Test
  public void supplier() {
    Supplier<String> supplier = new Supplier<String>() {
      @Override
      public String get() {
        return "data";
      }
    };
    System.out.println(supplier.get());
  }

  //一元函数 输入输出 不同  将指定类型转化为指定类型
  @Test
  public void function() {
    Function<Integer, String> function = new Function<Integer, String>() {
      @Override
      public String apply(Integer integer) {
        if (integer == 1) {
          return "1";
        } else {
          return "no 1";
        }
      }
    };
    String result = function.apply(1);
    System.out.println(result);
    result = function.apply(2);
    System.out.println(result);
  }

  // 一元函数 输入输出类型相同
  @Test
  public void unaryOperator() {
    UnaryOperator<Integer> unaryOperator = new UnaryOperator<Integer>() {
      @Override
      public Integer apply(Integer integer) {
        ++integer;
        return integer;
      }
    };
    System.out.println(unaryOperator.apply(1));
  }

  //二元函数 输入输出不同
  @Test
  public void binaryFunction() {
    BiFunction<Integer, Integer, Double> biFunction = new BiFunction<Integer, Integer, Double>() {
      @Override
      public Double apply(Integer integer, Integer integer2) {
        return Double.valueOf(integer + integer2);
      }
    };
    System.out.println(biFunction.apply(5, 6));
  }


  //二元函数 输入输出相同
  @Test
  public void binaryOperator() {
    BinaryOperator<Integer> binaryOperator = new BinaryOperator<Integer>() {
      @Override
      public Integer apply(Integer integer, Integer integer2) {
        return integer + integer2;
      }
    };
    System.out.println(binaryOperator.apply(1, 2));
  }


}
