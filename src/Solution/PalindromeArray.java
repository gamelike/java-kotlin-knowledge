package Solution;

public class PalindromeArray {
    // 去掉其他字符，判断不考虑大小写字符串是否回文
    public static boolean isPalindrome(String s) {
        String lowerCase = s.toLowerCase().replaceAll("[^0-9a-z]", "");
        int left = 0;
        int right = lowerCase.length() - 1;
        while (left < right){
            if (lowerCase.charAt(left) != lowerCase.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    // 随意去掉一个字符,判断字符串是否回文
    public static boolean isPalindrome01(String s) {
        int left = 0;
        int right = s.length() - 1;
        int index = 0;
        while (right > left){
            if (s.charAt(left) != s.charAt(right)){
                // 跳过左指针一个字符
                if (index == 0){
                    left++;
                    index++;
                    continue;
                }
                // 跳过右指针一个字符
                if (index == 1){
                    right--;
                    left--;
                    index++;
                    continue;
                }
                // 跳过之后仍然失败
                if (index == 2){
                    return false;
                }
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "i am nn,nn aai";
        String s1 = "ambaaa";
        System.out.println(isPalindrome01(s1));
        System.out.println(isPalindrome(s));
    }
}
