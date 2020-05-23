package Solution;

import org.junit.Test;

public class longestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(common) != 0) {
                System.out.println(strs[i].indexOf(common));
                common = common.substring(0, common.length() - 1);
                if (strs[i].isEmpty()) return "";
            }
        }

        return common;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abab","aba","ab"}));
    }
}
