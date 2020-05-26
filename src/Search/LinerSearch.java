package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gameLike
 * @Date: 2020/5/25
 * 线性查找/顺序查找
 */
public class LinerSearch {
    public static void main(String[] args) {
        int[] arr = {5,12,1,63,77,3,3};
        List<Integer> list = linearSearch(arr, 4);
        for (int index : list) {
            System.out.print(index + " ");
        }
    }
    public static List<Integer> linearSearch(int[] arr, int value){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                list.add(i);
            }
        }
        return list;
    }
}
