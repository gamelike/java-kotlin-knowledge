package leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。 int pop() 移除并返回栈顶元素。 int top() 返回栈顶元素。 boolean empty() 如果栈是空的，返回
 * true ；否则，返回 false 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC225 {

  class MyStack2 {

    Deque<Integer> deque = new ArrayDeque<>();

    public MyStack2() {

    }

    public void push(int x) {
      deque.offer(x);
    }

    public int pop() {
      int size = deque.size();
      size--;
      while (size-- > 0) {
        deque.offerLast(deque.pollFirst());
      }
      return deque.pollFirst();
    }

    public int top() {
      return deque.peekLast();
    }

    public boolean empty() {
      return deque.isEmpty();
    }
  }


  class MyStack {

    Queue<Integer> queueLeft = new LinkedBlockingQueue<>();
    Queue<Integer> queueRight = new LinkedBlockingQueue<>();

    public MyStack() {

    }

    public void push(int x) {
      queueLeft.offer(x);
    }

    public int pop() {
      while (queueLeft.size() > 1) {
        queueRight.offer(queueLeft.poll());
      }
      int top = queueLeft.poll();
      while (!queueRight.isEmpty()) {
        queueLeft.offer(queueRight.poll());
      }
      queueRight = new LinkedBlockingQueue<>();
      return top;
    }

    public int top() {
      while (queueLeft.size() > 1) {
        queueRight.offer(queueLeft.poll());
      }
      int top = queueLeft.peek(); //拿到最后一个
      queueRight.offer(queueLeft.poll());
      while (!queueRight.isEmpty()) {
        queueLeft.offer(queueRight.poll());
      }
      queueRight = new LinkedBlockingQueue<>();
      return top;
    }

    public boolean empty() {
      return queueLeft.isEmpty() && queueRight.isEmpty();
    }
  }
}
