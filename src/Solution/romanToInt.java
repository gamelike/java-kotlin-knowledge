package Solution;

import java.util.HashMap;
import java.util.Map;

public class romanToInt {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),i);
        }

        
        return 0;
    }

    public static void main(String[] args) {

    }
}
