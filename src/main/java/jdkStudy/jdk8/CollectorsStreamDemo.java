package jdkStudy.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gjd3
 */
public class CollectorsStreamDemo {
  List<Student> students = new ArrayList<>();

  @Before
  public void before() {

    students.add(new Student("一号", 7, true, "一年级"));
    students.add(new Student("二号", 8, true, "二年级"));
    students.add(new Student("三号", 8, false, "二年级"));
    students.add(new Student("四号", 9, true, "三年级"));
    students.add(new Student("五号", 7, false, "一年级"));
    students.add(new Student("六号", 8, true, "二年级"));
    students.add(new Student("七号", 10, true, "四年级"));

  }

  //获取统计值
  @Test
  public void summary() {
    IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(Student::getAge));
    System.out.println(collect.getMax());
    System.out.println(collect.getAverage());
    System.out.println(collect.getCount());
    System.out.println(collect.getSum());
  }

  //分类 partitioningBy 要求的函数的predicate类型 boolean
  @Test
  public void partitioning() {
    //
    Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(x -> x.isBoy));
  }

  //分组
  @Test
  public void group() {
    Map<String, Long> collect = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
    Map<String, List<Student>> collect2 = students.stream().collect(Collectors.groupingBy(Student::getGrade));
  }

  @Data
  @AllArgsConstructor
  private class Student {
    private String name;
    private Integer age;
    private boolean isBoy;
    private String grade;
  }
}
