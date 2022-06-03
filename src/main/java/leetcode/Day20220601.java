package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author violet.
 */
public class Day20220601 {

    /**
     * @param nums 火柴棍数组
     * @return 是否可以组成square
     */
    public boolean makesquare(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        return dfs(nums, dp, 0, sum / 4);
    }

    // dfs
    public boolean dfs(int[] nums, int[] dp, int index, int target) {
        if (index == nums.length) {
            return target == 0;
        }
        if (dp[index] != 0) {
            return dp[index] == 1;
        }
        for (int i = 0; i < 4; i++) {
            // 如果当前火柴棍的值加上剩余的值大于0，则可以组成正方形
            if (nums[index] + target - i * nums[index] >= 0) {
                if (dfs(nums, dp, index + 1, target - i * nums[index])) {
                    dp[index] = i + 1;
                    return true;
                }
            }
        }
        dp[index] = 0;
        return false;
    }

    @Test
    public void make_square_test() {
        int[] nums = {10, 6, 5, 5, 5, 3, 3, 3, 2, 2, 2, 2};
        Assert.assertTrue(makesquare(nums));
    }

}
