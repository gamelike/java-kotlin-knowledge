package Solution;

public class MaxArea {
    public static int maxArea(int[] height) {
        int result = 0;
        int i = 0,k = height.length - 1;
        int maxArea = 0;

        while(i < k){
            if (height[i] > height[k])
            {
                maxArea = height[k] * (k - i);
                k--;
            }else{
                maxArea = height[i] * (k - i);
                i++;
            }
            if (result < maxArea){
                result = maxArea;
            }
        }

        return result;
    }
    // 暴力
    public static int trap(int[] height) {
        int i = 0;
        int result = 0;
        for (i = 1; i < height.length - 1; i++) {
            int left_max = 0,right_max = 0;
            for (int j = i; j < height.length; j++) {
                right_max = Math.max(right_max,height[j]);
            }
            for (int j = i; j > 0; j--) {
                left_max = Math.max(left_max,height[j]);
            }
            result += Math.min(left_max,right_max) - height[i];
        }
        return result;
    }

    // 双指针
    public static int trap1(int[] height) {
        int i = 1,j = height.length - 1;
        int result = 0;
        int left_max = 0,right_max = 0;
        while(i < j){
            left_max = Math.max(left_max,height[i]);
            right_max = Math.max(right_max,height[j]);
            if (left_max < right_max){
                result += left_max - height[i];
                i++;
            }else{
                result += right_max - height[j];
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(arr));
    }
}
