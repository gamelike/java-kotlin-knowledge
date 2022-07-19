package leetcode.chainTable;


public class LC203 {

  public ListNode removeElements(ListNode head, int val) {
    ListNode dump = new ListNode(-1, head);
    ListNode temp = dump;
    while (temp.next != null) {
      if (temp.next.val == val) {//删除元素
        temp.next = temp.next.next;
      } else {
        temp = temp.next;
      }
    }
    return dump.next;
  }

  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

}
