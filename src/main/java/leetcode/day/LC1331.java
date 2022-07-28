package leetcode.day;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class LC1331 {

  public int[] arrayRankTransform(int[] arr) {
    //数组 排序一下 会有重复
    int[] copdArr = new int[arr.length];
    System.arraycopy(arr, 0, copdArr, 0, arr.length);
    Arrays.sort(copdArr);
    Map<Integer, Integer> sortNum = new LinkedHashMap<>();
    int count = 0;
    for (int num : copdArr) {
      if (!sortNum.containsKey(num)) {
        sortNum.put(num, ++count);
      }
    }

    int[] ans = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      ans[i] = sortNum.get(arr[i]);
    }
    return ans;
  }

  @Test
  public void run() {
    arrayRankTransform(new int[]{40, 10, 20, 30});
  }
}
