package Sorted;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gameLike
 * @Date: 2020/5/24
 * 基数排序 || 桶排序
 */
public class radixSorted {
    public static void main(String[] args) {
            int[] arr = {5,7,8,1,342,60,2};
            radixSorted(arr);
    }
    public static void radixSorted(int[] arr){
        int max = 0; // 找出数组中最大的值
        for (int value : arr) {
            if (max < value) max = value;
        }

        // 通过最大的值，找出桶需要遍历的次数
        int KeyNum = 0;
        while (max % 10 != 0 ){
            max = max / 10;
            KeyNum++;
        }

        // 建立 10 个桶
        List<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < KeyNum; i++) { // 需要遍历的次数
            for (int value : arr) { // 遍历当前数组
                // 获取需要判断的位 先取余，再除
                int Key = (int) ((value % Math.pow(10, i + 1)) / Math.pow(10, i));
                // 把得到的位 放入相对的桶中
                buckets.get(Key).add(value);
            }
            // 计数器，用以放回原数组中时进行
            int count = 0;
            for (int j = 0; j < 10; j++) {
                // 得到当时的放入桶中的数组
                ArrayList<Integer> bucket = buckets.get(j);
                // 判断当时桶中数组是否为0
                while (bucket.size() > 0)
                    arr[count++] = bucket.remove(0);
            }
            System.out.println("第" + (i + 1) + "次基数排序");
            foreach(arr);
        }
    }

    public static void foreach(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
