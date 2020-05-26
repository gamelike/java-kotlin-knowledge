package Search;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: gameLike
 * @Date: 2020/5/26
 * Fibonacci查找法
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {4,5,5,7,135,235};
        int i = fibonacciSearch(arr, 5);
        System.out.println(i);
    }

    // 斐波那契数列获取
    public static int[] FibonacciArray(){
        int[] fibonacci = new int[30];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int k = 2; k < fibonacci.length; k++) {
            fibonacci[k] = fibonacci[k - 1] + fibonacci[k - 2];
        }
        return fibonacci;
    }

    public static int fibonacciSearch(int[] arr,int value){
        int left = 0;
        int right = arr.length - 1;

        int[] F = FibonacciArray();
        // Fibonacci数值的分割下标
        int k = 0;
        while (right > F[k] - 1){
            k++;
        }
        // 存放返回下标值
        int mid = 0;
        // 创建临时数组
        int[] temp = new int[F[k] - 1];

        // 创建临时数组赋值，大于原数组使用 最后一个下标的值进行赋值
        for (int i = 0; i < temp.length; i++) {
            if (i < right + 1){
                temp[i] = arr[i];
            }else {
                temp[i] = arr[right];
            }
        }
        while (left <= right){
            // 找到的黄金分割点
            mid = left + F[k - 1] - 1;
            if (temp[mid] > value){
                right = mid - 1;
                // （全部元素） = （前半部分）+（后半部分）
                // f[k] = f[k-1] + f[k-2]
                // 因为前半部分有f[k-1]个元素，所以 k = k-1
                k = k - 1;
            }else if (temp[mid] < value){
                left = mid + 1;
                // （全部元素） = （前半部分）+（后半部分）
                // f[k] = f[k-1] + f[k-2]
                // 因为后半部分有f[k-1]个元素，所以 k = k-2
                k = k - 2;
            }else {
                if (mid <= right){
                    return mid;
                }else {
                    return right;
                }
            }
        }
        return -1;
    }
}
