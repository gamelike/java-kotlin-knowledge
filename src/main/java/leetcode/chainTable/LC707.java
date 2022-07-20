package leetcode.chainTable;


import org.junit.Test;


public class LC707 {

  private class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }
  }

  @Test
  public void run() {
    LC707 lc707 = new LC707();
    lc707.addAtHead(1);
    lc707.addAtTail(3);
    lc707.addAtIndex(1, 2);
    System.out.println(lc707.get(1));
    lc707.deleteAtIndex(1);
    System.out.println(lc707.get(1));
  }

  ListNode head;
  int size = 0;

  public LC707() {
    //链表从0开始，size是里面元素个数 类似与 数组的length
    size = 0;
    head = new ListNode();//head
  }

  public int get(int index) {
    if (index >= size || index < 0) {
      return -1;
    }
    ListNode node = head;
    for (int i = 0; i <= index; i++) { //判断避免超界
      node = node.next;
    }
    return node.val;
  }

  public void addAtHead(int val) {
    addAtIndex(0, val);
  }

  public void addAtTail(int val) {
    ListNode tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    tail.next = new ListNode(val);
    size++;
  }

  public void addAtIndex(int index, int val) {
    ListNode addNode = new ListNode(val);
    ListNode pre = head;
    if (index < 0) {
      index = 0;
    }
    if (index > size) {
      return;
    }
    for (int i = 0; i <= index - 1; i++) {
      pre = pre.next;
    }
    addNode.next = pre.next;
    pre.next = addNode;
    size++;
  }

  public void deleteAtIndex(int index) {
    ListNode pre = head;
    if (index < 0 || index >= size) {
      return;
    }

    for (int i = 0; i <= index - 1; i++) { //找到前一位
      pre = pre.next;
    }
    pre.next = pre.next.next;
    size--;
  }
}
