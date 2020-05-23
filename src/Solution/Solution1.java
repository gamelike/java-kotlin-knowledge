package Solution;

public class Solution1 {
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reverseNumber = 0;
        while (x > reverseNumber){
            reverseNumber = reverseNumber * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNumber || x == reverseNumber / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
