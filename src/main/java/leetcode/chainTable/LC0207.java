package leetcode.chainTable;

import leetcode.chainTable.LC206.ListNode;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC0207 {

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int aSize = 0;
    int bSize = 0;
    ListNode temp = headA;
    while (temp != null) {
      aSize++;
      temp = temp.next;
    }
    temp = headB;
    while (temp != null) {
      bSize++;
      temp = temp.next;
    }
    System.out.println(aSize);
    System.out.println(bSize);
    ListNode pre = new ListNode();
    if (aSize > bSize) {
      for (int i = 1; i <= aSize - bSize; i++) {
        headA = headA.next;
      }
    } else {
      for (int i = 1; i <= bSize - aSize; i++) {
        headB = headB.next;
      }
    }
    while (headA != null && headB != null) {
      if (headA == headB) {
        return headA;
      }
      headA = headA.next;
      headB = headB.next;
    }
    return null;
  }
}
