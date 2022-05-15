package Recursion;
/**
 * @author: gameLike
 * @Date: 2020/5/20
 */
public class Recursion {
    public static void main(String[] args) {
        int factorial = factorial(5);
        System.out.println(factorial);
    }

    public static int factorial(int n){
        if (n > 0) {
            return n * factorial(n-1);
        }else {
            return 1;
        }
    }
}
