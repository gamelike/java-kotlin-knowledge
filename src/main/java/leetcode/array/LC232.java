package leetcode.array;

import java.util.Stack;

/*
请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：

实现 MyQueue 类：

void push(int x) 将元素 x 推到队列的末尾
int pop() 从队列的开头移除并返回元素
int peek() 返回队列开头的元素
boolean empty() 如果队列为空，返回 true ；否则，返回 false

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/implement-queue-using-stacks
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC232 {

  class MyQueue {

    Stack<Integer> stackLeft = new Stack<>();
    Stack<Integer> stackRight = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
      stackLeft.push(x);
    }

    public int pop() {
      //右栈如果有数据，就直接出，没有的话，将左边数据移过来
      if (stackRight.empty()) {
        while (!stackLeft.empty()) {
          stackRight.push(stackLeft.pop());
        }
      }
      return stackRight.pop();
    }

    public int peek() {
      if (stackRight.empty()) {
        while (!stackLeft.empty()) {
          stackRight.push(stackLeft.pop());
        }
      }
      return stackRight.peek();
    }

    public boolean empty() {
      return stackLeft.empty() && stackRight.empty();
    }
  }
}
