package leetcode.day;

/**
 * easy.
 */
public class Day20220608 {

    public boolean isBoomerang(int[][] points) {
        /*
            y1 = k x1 + b;
            y2 = k x2 + b;
            k = (y2 - y1)/(x2 - x1);
         */
        return (points[0][1] - points[1][1]) * (points[2][0] - points[0][0]) !=
                (points[2][1] - points[0][1]) * (points[0][0] - points[1][0]);
    }

}
