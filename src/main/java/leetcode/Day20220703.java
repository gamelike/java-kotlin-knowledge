package leetcode;

import org.junit.Test;

public class Day20220703 {

    public int nextGreaterElement(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        int i = chars.length - 2;
        // find that the num can be changed.
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = chars.length - 1;
        //
        while (j >= 0 && chars[i] >= chars[j]) {
            j--;
        }
        swap(chars, i, j);
        reverse(chars, i + 1);
        long ans = Long.parseLong(new String(chars));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private void swap(char[] num, int i, int j) {
        char tem = num[i];
        num[i] = num[j];
        num[j] = tem;
    }

    private void reverse(char[] nums, int begin) {
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    @Test
    public void test() {
        int element = nextGreaterElement(230241);
        System.out.println(element);
    }

}