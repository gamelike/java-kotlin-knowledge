package Solution;

public class myAtoi {
    public static int myAtoi(String str){
        char[] chars = str.toCharArray();
        boolean flag = true;
        int index = 0;
        while(index < chars.length && chars[index] == ' '){
            index++;
        }
        if (index == chars.length) return 0;
        if (chars[index] == '+') {
            index++;
        }else if (chars[index] == '-'){
            flag = false;
            index++;
        }else if(!Character.isDigit(chars[index])){
            return 0;
        }
        int result = 0;
        while(index < chars.length && Character.isDigit(chars[index])){
            int digit = chars[index] - '0';
            if(result > (Integer.MAX_VALUE - digit) / 10){
                return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = digit + result*10;
            index++;
        }
        return flag ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1L + Integer.MAX_VALUE);
        System.out.println(myAtoi("+1"));
    }
}
