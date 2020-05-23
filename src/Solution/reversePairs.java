package Solution;

public class reversePairs {
    public static int reversePairs(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] > nums[j]) result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {7,5,6,4};

        System.out.println(reversePairs(nums));
    }
}
