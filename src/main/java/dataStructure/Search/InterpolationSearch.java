package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gameLike
 * @Date: 2020/5/25
 * 插值算法的 优劣性
 * 数组有序
 * 1.对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 * 2.关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        List<Integer> list = interpolationSearch(arr, 2, 0, arr.length - 1);

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * @author: gameLike
     * @Date: 2020/5/25
     *
     */
    public static List<Integer> interpolationSearch(int[] arr,int value,int left,int right){
        if (left > right) return null;
                                            // value在整序列中的占比
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);

        List<Integer> list = new ArrayList<>();
        if (arr[mid] > value) return interpolationSearch(arr,value,left,mid - 1);
        else if (arr[mid] < value) return interpolationSearch(arr,value,mid + 1,right);
        else {
            int temp = mid - 1;
            while (temp > 0 && arr[temp] == value){
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
