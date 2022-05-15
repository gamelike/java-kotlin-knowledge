package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gameLike
 * @Date: 2020/5/25
 * 折半查找/二分查找
 * 需要数组有序
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {4,5,5,7,135,235};
        int i = binarySearch(arr, 235, 0, arr.length - 1);
        System.out.println(i);

        List<Integer> list = binarySearchList(arr, 5, 0, arr.length - 1);

        for (int temp : list) {
            System.out.print(temp + " ");
        }
    }

    // 获得一个值 , 不考虑多个值
    public static int binarySearch(int[] arr,int value,int left,int right){
        if (left > right) return -1;
        int mid = (left +  right) / 2;

        if (arr[mid] > value) return binarySearch(arr, value, left, mid - 1);
        else if (arr[mid] < value) return binarySearch(arr, value, mid + 1, right);
        else return mid;
    }

    // 考虑相同数的情况
    public static List<Integer> binarySearchList(int[] arr,int value,int left,int right){
        if (left > right) return null;
        List<Integer> list = new ArrayList<>();
        int mid = (left + right) / 2;
        if (arr[mid] > value) return binarySearchList(arr, value, left, mid - 1);
        else if (arr[mid] < value) return binarySearchList(arr, value, mid + 1, right);
        else {
            int temp = mid - 1;
            while(temp > 0 && arr[temp] == value){
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while(temp < arr.length - 1 && arr[temp] == value){
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
