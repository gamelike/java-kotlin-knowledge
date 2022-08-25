package jdkStudy.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author gjd3
 * https://blog.csdn.net/qq_33168734/article/details/87563510
 */
public class StreamApiDemo {
  //1. start 创建流
  //创建集合
  @Test
  public void collectionCreate() {
    List<String> list = new LinkedList<>();
    Stream<String> stream = list.stream();
    Stream<String> stringStream = list.parallelStream();
  }

  //todo  how to use?
  @Test
  public void arrayCreate() {
    Integer[] array = new Integer[5];
    Stream<Integer> stream = Arrays.stream(array);
  }

  @Test
  public void numCreate() {
    //todo  how to use?
    IntStream.of(1, 2, 3);
    IntStream.rangeClosed(1, 10);
  }


  //2.  mid operation 中间操作
  String s = "my name is 007";

  //  2.1无状态操作

  //过滤 转化
  @Test
  public void filter() {
    Arrays.stream(s.split(" "))
        .map(String::length).forEach(System.out::println);
    System.out.println("---------");
    Arrays.stream(s.split(" ")).filter(new Predicate<String>() {
      @Override
      public boolean test(String s) {
        return s.length() > 3;
      }
    }).mapToDouble(new ToDoubleFunction<String>() {
      @Override
      public double applyAsDouble(String value) {
        return value.length();
      }
    }).forEach(System.out::println);

    System.out.println("--------");


    //todo flatmap
    List<String> collect = Arrays.stream(s.split(" "))
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList());
    collect.forEach(s -> System.out.println(s));

    System.out.println("------");
    IntStream.of(new int[]{1, 2, 3, 4, 5}).peek(System.out::println);

    System.out.println("----------");
    //解除 有序限制
    IntStream.of(new int[]{1, 2, 3, 4, 5}).unordered().forEach(System.out::println);
  }


  //  2.2有状态操作
  public void status() {
    IntStream.of(new int[]{1, 2, 7, 5, 4, 8, 6})
        .sorted()
        .skip(3) //跳过几个
        .limit(2)  //输出几个
        .forEach(System.out::println);
  }

  //3.  close 终止操作
  // 3.1 非短路操作 即返回所有数据进行计算
  @Test
  public void foreachOrderes() {
    IntStream.of(new int[]{1, 2, 3, 4, 5, 6, 7}).parallel().forEach(System.out::println);
    System.out.println("-----------");
    IntStream.of(new int[]{1, 2, 3, 4, 5, 6, 7}).parallel().forEachOrdered(System.out::println);
  }

  @Test
  public void reduce() {
    Integer[] intArr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int result = Arrays.stream(intArr).reduce(new BinaryOperator<Integer>() {
      @Override
      public Integer apply(Integer integer, Integer integer2) {
        return integer + integer2;
      }
    }).get();
    System.out.println(result);
  }

  @Test
  public void max() {
    Integer[] intArr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    //返回比较器中最小（下标）的元素  比较器为大到小排序
    System.out.println(Arrays.stream(intArr).min(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    }).get());

  }

  // 3.2 短路操作 只需要一部分
  @Test
  public void findFirst() {
    Integer[] intArr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(Arrays.stream(intArr).findFirst().get());

    //findany 也会返回第一个，因为流是有顺序的
    System.out.println(Arrays.stream(intArr).findAny().get());
  }

  //并行流


}
