package Solution;

import java.util.ArrayList;
import java.util.List;

public class convert {
    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean direction = true;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) direction = !direction;
            curRow += direction ? -1 : 1;
        }

        StringBuilder ref = new StringBuilder();
        for (StringBuilder row : rows) {
            ref.append(row);
        }

        return ref.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("AB",1));
    }
}
