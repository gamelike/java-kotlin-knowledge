package leetcode;

import java.util.Comparator;
import java.util.TreeSet;
import org.junit.Test;

public class LC729 {

  //维护一个treeset,按照第一个元素 从小到大排序
  TreeSet<int[]> booked = new TreeSet<>(new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
      return o1[0] - o2[0];
    }
  });

  public boolean book(int start, int end) {
    if (booked.isEmpty()) {
      booked.add(new int[]{start, end});
      return true;
    }
    int[] tmp = {end, 0};
    int[] big = booked.ceiling(tmp);// 获取到比end大的值
    int[] small =
        (big == null) ? booked.last() : booked.lower(big); //获取到比big小的,如果big是null，则说明是拍在了最后一个
    if (big == booked.first() || small == null || small[1] <= start) {
      booked.add(new int[]{start, end});
      return true;
    }
    return false;
  }

  @Test
  public void run() {
    System.out.println(book(10, 20));
  }
}
