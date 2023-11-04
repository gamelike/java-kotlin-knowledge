package leetcode.dp;

public class LC213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robDp(nums, 0, n - 2), robDp(nums, 1, n - 1));
    }

    public int robDp(int[] nums, int start, int end) {
        int f = nums[start], s = Math.max(f, nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            int t = s;
            s = Math.max(f + nums[i], s);
            f = t;
        }
        return s;
    }
}
