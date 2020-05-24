package Sorted;

/**
 * @author: gameLike
 * @Date: 2020/5/24
 * 归并排序，相当于有序链表的添加
 */
public class MergeSorted {

    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        mergeSorted(arr,0,arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void mergeSorted(int[] arr,int left,int right){
        if (left < right){
            int mid = (left + right) / 2;

            mergeSorted(arr,left,mid);
            mergeSorted(arr,mid + 1,right);
            merge(arr,left,right,mid);
        }
    }

    public static void merge(int[] arr,int left,int right,int mid){
        int[] temp = new int[right - left + 1];

        int l = left;
        int r = mid + 1;
        int index = 0;
        // 像俩个有序链表添加一样
        while (l <= mid && r <= right){
            if (arr[l] <= arr[r]){
                temp[index++] = arr[l++];
            }else {
                temp[index++] = arr[r++];
            }
        }

        while(l <= mid){
            temp[index++] = arr[l++];
        }

        while(r <= right){
            temp[index++] = arr[r++];
        }

        if (temp.length >= 0) System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
