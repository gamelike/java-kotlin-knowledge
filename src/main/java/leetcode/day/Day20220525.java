package leetcode.day;

import java.util.Arrays;

/**
 * @author violet.
 */
public class Day20220525 {


    /**
     * recursion.
     * <p>
     * s = "..abcdefghigklmnopqrstuvwxyz.."
     *
     * @param p give a string.
     * @return sub string count.
     */
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) { // 字符之差为 1 或 -25
                ++k;
            } else {
                k = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();
    }

    public static void main(String[] args) {
        Day20220525 day20220525 = new Day20220525();
        // expect: 2
        System.out.println(day20220525.findSubstringInWraproundString("cac"));
        // expect: 6
        System.out.println(day20220525.findSubstringInWraproundString("zab"));
    }
}
