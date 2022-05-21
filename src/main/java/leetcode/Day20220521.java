package leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author violet.
 */
public class Day20220521 {
    /**
     * @param nums a list of integers
     * @return the number of repeated elements in nums
     */
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }else{
                set.add(nums[i]);
            }
        }
        throw new RuntimeException("No answer");
    }
}
