package Recursion;

/**
 * @author: gameLike
 * @Date: 2020/5/21
 */
public class Queens {

    int max = 8;
    int[] queen = new int[max];
    int count = 0;
    public static void main(String[] args) {
        Queens queens = new Queens();
        queens.putQueen(0);
        System.out.println(queens.count);;
    }

    // n 表示第N个皇后
    private boolean Judge(int n){
        for (int i = 0; i < n; i++) {
            // 判断是否同一列                相当于y2 - y1 = k * (x2 - x1) 默认k = 1
            if (queen[i] == queen[n] || Math.abs(n-i) == Math.abs(queen[n] - queen[i])){
                return false;
            }
        }
        return true;
    }

    // 放置皇后
    public void putQueen(int n){
        if (n == max){
            print();
            return;
        }

       for (int i = 0; i < max; i++){
            queen[n] = i;
            if (Judge(n)){
                putQueen(n + 1);
            }
        }
    }

    // 输出
    public void print(){
        count++;
        for (int i = 0; i < queen.length; i++) {
            System.out.print(queen[i] + " ");
        }
        System.out.println();
        System.out.println();
    }
}
