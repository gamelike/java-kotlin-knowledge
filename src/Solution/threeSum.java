package Solution;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class threeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int i = 0,f = 0,s = 0;
        while (i < nums.length - 1) {
            for (int k = i + 1; k < nums.length; k++) {
                int j = nums.length - 1;
                while (k < j) {
                    if (nums[i] + nums[k] + nums[j] == 0) {
                        List<Integer> num = new ArrayList<>();
                        num.add(nums[i]);
                        num.add(nums[k]);
                        num.add(nums[j]);
                        result.add(num);
                    }
                    j--;
                }
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
