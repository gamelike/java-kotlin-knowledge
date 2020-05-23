package Sorted;

/**
 * @author: gameLike
 * @Date: 2020/5/23
 */
public class QuickSorted {
    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        quickSorted(arr,0,arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void quickSorted(int[] arr,int start,int end){
        if(start < end){
            int temp = getIndex(arr,start,end);

            // 分治
            // 递归分别再把基准值左右俩边数组进行快排
            quickSorted(arr,start,temp - 1);
            quickSorted(arr,temp + 1,end);
        }
    }

    public static int getIndex(int[] arr, int left, int right){
        // 一个基准，挖一个坑
        int temp = arr[left];
        while (left < right){
            // 从另一端开始扫描，如果小于基准值，就把小的数放在另外一边
            while(left < right && arr[right] >= temp){
                right--;
            }
            arr[left] = arr[right];
            // 反之，从left端扫描
            while(left < right && arr[left] <= temp){
                left++;
            }
            arr[right] = arr[left];
        }
        // 把坑填进中间
        arr[left] = temp;
        return left;
    }
}
