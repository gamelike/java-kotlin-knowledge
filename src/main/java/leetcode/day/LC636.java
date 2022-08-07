package leetcode.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author gjd
 */
public class LC636 {
  public int[] exclusiveTime(int n, List<String> logs) {
    int preTime = 0;//记录前一片的时间
    Stack<Integer> stack = new Stack<>();
    int[] ans = new int[n];
    for (String temp : logs) {
      String[] str = temp.split(":");
      //当前时间
      int curTime = Integer.parseInt(str[2]);
      //当前的线程号
      int threadId = Integer.parseInt(str[0]);
      if (str[1].equals("start")) {
        if (!stack.isEmpty()) {
          //计算上一个线程的执行时间
          int preThread = stack.peek();
          ans[preThread] = ans[preThread] + (curTime - preTime);
          //当前线程
          stack.push(threadId);
          preTime = curTime;
        } else {
          //当前线程
          stack.push(threadId);
          preTime = curTime;
        }
      } else {
        int preThreadId = stack.pop();
        ans[preThreadId] = ans[preThreadId] + (curTime - preTime + 1); // +1算上当前时间片
        preTime = curTime + 1;
      }
    }
    return ans;
  }

  @Test
  public void run() {
    System.out.println(Arrays.toString(exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"))));
  }
}
