package jdkStudy.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author gjd3
 * todo https://blog.csdn.net/qq_33168734/article/details/87563510
 */
public class ParallelStreamDemo {
  @Test
  public void createParalleelStream() {
    //修改线程数
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
    Integer[] intArr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    //默认为cpu个数不同影响
    System.out.println(Arrays.stream(intArr).parallel().findAny().get());

//    IntStream.range(1,100).forEach(System.out::println);
//    IntStream.range(1,100).parallel().forEach(StreamApiDemo::printDebug);
    //先并行 在串行， 不再收并行影响
    IntStream.range(1, 100).parallel().peek(ParallelStreamDemo::printDebug).sequential().peek(ParallelStreamDemo::printDebug2);

    //修改线程数
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
    IntStream.range(1, 100).parallel().forEach(ParallelStreamDemo::printDebug);

  }

  public static void printDebug(int i) {
//        System.out.println(i);
    System.out.println(Thread.currentThread().getName() + "debug:" + i);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void printDebug2(int i) {
    System.err.println(i);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
