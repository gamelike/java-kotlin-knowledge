package Sorted;

/**
 * @author: gameLike
 * @Date: 2020/5/31
 * 堆排序，利用顺序存储二叉树
 * 升序用大顶堆，降序小顶堆
 */
public class HeapSorted {

    public static void main(String[] args) {
        int[] arr = {5,7,8,1,3,6,2};
        sorted(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    // 堆排序
    public static void sorted(int[] arr){
        // 1.构建大顶堆
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            adjustTree(arr,arr.length,i);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustTree(arr,j,0);//重新对堆进行调整
        }
    }

    // 调整大顶堆

    /**
     * @param arr 数组（二叉树）
     * @param length 数组长度，会逐渐减少
     * @param i 非叶子节点的索引
     */
    public static void adjustTree(int[] arr,int length,int i){
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]){
                j++;
            }if (arr[j] > temp){
                arr[i] = arr[j];
                i = j;
            }else {
                break;
            }
        }
        // for循环结束时，只是调整了将以i为节点的值放在了最顶（局部调整）
        arr[i] = temp;
    }
}
