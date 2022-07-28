package leetcode.day;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 * <p>
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC592 {

  public String fractionAddition(String expression) {
    String[] expressSub = expression.split("/");
    List<Integer> son = new LinkedList<>();
    List<Integer> mum = new LinkedList<>();
    for (int i = 0; i < expressSub.length; i++) {
      if (i != expressSub.length - 1) {
        if (expressSub[i].contains("+")) {
          String[] beforeMumAndAfterSon = expressSub[i].split("\\+");
          mum.add(Integer.valueOf(beforeMumAndAfterSon[0]));
          son.add(Integer.valueOf(beforeMumAndAfterSon[1]));
        } else if (expressSub[i].contains("-") && !expressSub[i].startsWith("-")) {
          String[] beforeMumAndAfterSon = expressSub[i].split("-");
          mum.add(Integer.valueOf(beforeMumAndAfterSon[0]));
          son.add(Integer.valueOf("-" + beforeMumAndAfterSon[1]));
        } else {
          son.add(Integer.valueOf(expressSub[i]));
        }
      } else {
        mum.add(Integer.valueOf(expressSub[i]));
      }

    }
    int lcmNum = 1;
    for (int i = 0; i < mum.size(); i++) {
      lcmNum = lcm(mum.get(i), lcmNum);
    }

    int sonNum = 0;
    for (int i = 0; i < mum.size(); i++) {
      int plus = lcmNum / mum.get(i);
      sonNum = sonNum + son.get(i) * plus;
    }
    if (sonNum == 0) {
      return "0/1";
    }
    int gcbNum = Math.abs(gcb(sonNum, lcmNum));
    return String.format("%s/%s", sonNum / gcbNum, lcmNum / gcbNum);
  }

  public int lcm(int a, int b) {
    return a * b / (gcb(a, b));
  }

  public int gcb(int a, int b) {
    int remainder = a % b;
    while (remainder != 0) {
      a = b;
      b = remainder;
      remainder = a % b;
    }
    return b;
  }

  @Test
  public void run() {
    System.out.println(fractionAddition("-4/7-3/4+2/3"));
  }
}
