package Sorted;

/**
 * @author: gameLike
 * @Date: 2020/5/23
 * 希尔排序
 */
public class ShellSorted {
    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        shellSorted(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] random = new int[80000];
        for (int i = 0; i < 80000; i++) {
            random[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSorted(random); // 182ms
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
    public static void shellSorted(int[] arr){
        int gap = arr.length / 2;
        int k,temp;
        while(gap >= 1) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < arr.length; j = j + gap) {
                    temp = arr[j];
                    for (; j > i; j = j - gap) {
                        if (arr[j - gap] > temp) {
                            arr[j] = arr[j - gap];
                        }
                        else break;
                    }
                    arr[j] = temp;
                }
            }
            gap /= 2;
        }
    }
}
