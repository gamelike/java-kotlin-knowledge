package leetcode.chainTable;

import leetcode.chainTable.LC206.ListNode;

public class LC24 {

  public ListNode swapPairs(ListNode head) {
    ListNode cur = new ListNode(0, head);
    ListNode dumyNode = cur;
    ListNode left = null;
    ListNode right = null;
    while (cur.next != null && cur.next.next != null) {
      left = cur.next;
      right = left.next;
      ListNode temp = right.next;
      cur.next = right;
      right.next = left;
      left.next = temp;
      cur = left;
    }
    return dumyNode.next;
  }
}
