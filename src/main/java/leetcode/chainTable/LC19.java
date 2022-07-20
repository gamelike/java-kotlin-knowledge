package leetcode.chainTable;

import leetcode.chainTable.LC206.ListNode;

public class LC19 {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dumyNode = new ListNode(0, head);
    ListNode slow = dumyNode;
    ListNode quick = dumyNode;
    //快指针移动n位，然后同时移动快慢指针，直到快指针到了最后一位
    for (int i = 1; i <= n; i++) {
      quick = quick.next;
    }
    while (quick.next != null) {
      quick = quick.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return dumyNode.next;
  }

}
