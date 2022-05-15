package Solution;

public class Sliding {
    public static int SlidingWindows(String str1,String str2){
        int length = str2.length();
        int i = 0;
        int flag = -1;
        while(i < (str1.length() - length)){
            if (str2.equals(str1.substring(i,i + length)))
                flag = i;
            i++;
        }

        return flag;
    }

    public static void main(String[] args) {
        String str1 = "Thread1";
        String str2 = "Thread2";

        System.out.println(SlidingWindows(str1, str2));
    }
}
