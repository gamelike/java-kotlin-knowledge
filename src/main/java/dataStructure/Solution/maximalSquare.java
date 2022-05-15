package Solution;

public class maximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) return 0;
        int length = matrix.length;
        int colum = matrix[0].length;
        int i = 0,j = 1,start = 0,end = 1;
        int result = 0;
        int area = 0;
        while(end < length && j < colum){
            if (matrix[i][end] != 1){
                start = end + 1;
                continue;
            }else if (matrix[i][start] != 1){
                start++;
                continue;
            }else if (matrix[j][end] != 1){
                start = end + 1;
                continue;
            }else if (matrix[j][start] != 1){
                start++;
                continue;
            }
            area = (end - start + 1) * (j - i + 1);
            if (result < area) result = area;
            j++;
        }
        return result;
    }
}
