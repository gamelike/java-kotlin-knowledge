package Solution;

import java.util.Scanner;

public class GetPassword {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入密匙");
        int k = scanner.nextInt();
        System.out.println("加密输入1,默认为解密");
        String n = scanner.next();
        if (n == "1") {
            System.out.println("输入明文");
            String s = scanner.next();
            System.out.println("密文为");
            System.out.println(getString(s, k));
        }
        else {
            System.out.println("输入密文");
            String s = scanner.next();
            System.out.println("明文为");
            System.out.println(outString(s, k));
        }
    }

    public static String getString(String s,int k){
        String get = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (ch[i] < 'a' || ch[i] > 'z'){
                get += ch[i];
                continue;
            }
            if (ch[i] >= ('a' + 26 - k) && ch[i] <= 'z') {
                ch[i] = (char) (ch[i] - 26 + k);
            } else {
                ch[i] = (char) (ch[i] + k);
            }
            get += ch[i];
        }
        return get;
    }

    public static String outString(String s,int k){
        String out = "";
        char[] ch = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (ch[i] < 'a' || ch[i] > 'z'){
                out += ch[i];
                continue;
            }
            if (ch[i] >= 'a' && ch[i] <= ('z' - 26 + k)){
                ch[i] = (char) (ch[i] + 26 - k);
            }else{
                ch[i] = (char) (ch[i] - k);
            }
            out += ch[i];
        }
        return out;
    }
}
