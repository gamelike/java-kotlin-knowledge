package Solution;

public class centerExpansion {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        String result = Integer.toString(x);
        if (result.length() % 2 != 0) return isPalindrome(result,result.length()/2,result.length()/2);
        else return isPalindrome(result,result.length()/2 - 1,result.length()/2);
    }

    public static Boolean isPalindrome(String x,int left,int right){
        boolean flag = true;
        while(left >= 0 && right < x.length()){
            if (x.charAt(left) != x.charAt(right)) {
                flag = false;
                break;
            }
            left--;
            right++;
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }
}
