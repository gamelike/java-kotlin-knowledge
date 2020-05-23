package Solution;

public class intToRoman {
    public static String intToRoman(int num) {
        StringBuilder str = new StringBuilder();
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] numsString = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int index = 0;

        while (index < 13){
            while(num > nums[index]){
                str.append(numsString[index]);
                num -= nums[index];
            }
            index++;
        }

        return str.toString();
    }

    public static void main(String[] args) {
        int num = 3999;
        System.out.println(intToRoman(num));
    }
}
