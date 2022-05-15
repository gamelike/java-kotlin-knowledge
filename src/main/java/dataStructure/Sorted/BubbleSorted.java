package Sorted;
/**
 * @author: gameLike
 * @Date: 2020/5/22
 */
public class BubbleSorted {
    /**
     * @author: gameLike
     * @Date: 2020/5/22
     *  冒泡排序
     *  时间复杂度 O(n^2)
     */
    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        sorted(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] random = new int[80000];
        for (int i = 0; i < 80000; i++) {
            random[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        sorted(random); //13759ms
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }

    public static void sorted(int[] arr){
        boolean flag;
        for (int i = 0; i < arr.length; i++) {
            flag = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] >= arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if (flag) break;
        }
    }
}
