package leetcode.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LC349 {

  public int[] intersection(int[] nums1, int[] nums2) {
    List<String> s = new ArrayList<>();

    Set<Integer> num1Set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    Set<Integer> result = new HashSet<>();
    for (int num : nums2) {
      if (num1Set.contains(num)) {
        result.add(num);
      }
    }
    return result.stream().mapToInt(num -> Integer.valueOf(num)).toArray();
  }
}
