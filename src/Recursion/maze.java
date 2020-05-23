package Recursion;
/**
 * @author: gameLike
 * @Date: 2020/5/20
 */
public class maze {
    public static void main(String[] args) {
        // 二维数组模拟数组
        int[][] maze = new int[8][7];
        // 使用 1 表示墙体
        int i = 0,j = 0;

        maze[3][1] = 1;
        maze[3][2] = 1;

        while(i < maze[0].length){
            maze[0][i] = 1;
            maze[7][i] = 1;
            i++;
        }

        while(j < maze.length){
            maze[j][0] = 1;
            maze[j][6] = 1;
            j++;
        }

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 7; l++) {
                System.out.print(maze[k][l] + " ");
            }
            System.out.println();
        }

        setWay(maze,1,1);
        System.out.println("===============");

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 7; l++) {
                System.out.print(maze[k][l] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param map 表示地图
     * @param i 从哪个位置开始行动
     * @param j .
     * @return 找到通路返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][1] == 2){
            System.out.println("找到");
            return true;
        }else {
            if (map[i][j] == 0){ // 表示没有走过这条路
                map[i][j] = 2;
                if (setWay(map,i + 1,j)){ // 先向下走，在右，在左，在上
                    return true;
                }else if (setWay(map,i,j + 1)){
                    return true;
                }else if(setWay(map,i,j - 1)){
                    return true;
                }else if (setWay(map,i - 1,j)){
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
