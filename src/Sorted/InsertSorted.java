package Sorted;
/**
 * @author: gameLike
 * @Date: 2020/5/23
 * 插入排序
 */
public class InsertSorted {
    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        insertSorted(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] random = new int[80000];
        for (int i = 0; i < 80000; i++) {
            random[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        insertSorted(random); // 712ms
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
    public static void insertSorted(int[] arr){
        int i = 0;
        int j,temp;
        while (i < arr.length - 1){
            j = i + 1;
            temp = arr[j];
            for (; j > 0; j--) {
                if (arr[j - 1] > temp){
                    arr[j] = arr[j - 1];
                }else {
                    break;
                }
            }
            arr[j] = temp;
            i++;
        }
    }
}
