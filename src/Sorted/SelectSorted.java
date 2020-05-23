package Sorted;

/**
 * @author: gameLike
 * @Date: 2020/5/22
 */
public class SelectSorted {

    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        selectSorted(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] random = new int[80000];
        for (int i = 0; i < 80000; i++) {
            random[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        selectSorted(random); //4236ms
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");

    }
    /**
     * @author: gameLike
     * @Date: 2020/5/22
     *   选择排序
     *   先找出最小的，再进行交换
     */
    public static void selectSorted(int[] arr){
        int temp;
        int j;
        for (int i = 0; i < arr.length; i++) {
            j = i;
            for (int k = j + 1; k < arr.length; k++) {
                if (arr[j] > arr[k]) j = k;
            }
            if (j != i) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
