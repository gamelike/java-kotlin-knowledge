package LinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
class Solution {
    // 反转打印
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] result = new int[size];
        for(int i = 0;i < size;i++){
            result[i] = stack.pop().val;
        }
        return result;
    }

    // 合并俩个有序的链表,结果为降序
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode cur;
        while(l1 != null && l2 != null){
            if (l1.val > l2.val){
                cur = l2.next;
                l2.next = result;
                result = l2;
                l2 = cur;
            }else {
                cur = l1.next;
                l1.next = result;
                result = l1;
                l1 = cur;
            }
        }

        while (l1 != null){
            cur = l1.next;
            l1.next = result;
            result = l1;
            l1 = cur;
        }
        while (l2 != null){
            cur = l2.next;
            l2.next = result;
            result = l2;
            l2 = cur;
        }
        return result;
    }

    // 合并俩个有序的链表,结果为升序
    public static ListNode mergeTwoListsUp(ListNode l1,ListNode l2){
        ListNode head = new ListNode(-1);

        ListNode result = head;
        while(l1 != null && l2 != null){
            if (l1.val <= l2.val){
                result.next = l1;
                l1 = l1.next;
            }else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }

        result.next = l1 == null ? l2 : l1;
        return head.next;
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        addSortedListNode(head1,new ListNode(3));
        addSortedListNode(head1,new ListNode(2));

        ListNode head2 = new ListNode(1);
        addSortedListNode(head2,new ListNode(4));
        addSortedListNode(head2,new ListNode(3));
        addSortedListNode(head2,new ListNode(2));
        forEach(head1);
        forEach(head2);

/*
        System.out.println("=========");
        ListNode listNode = mergeTwoLists(head1, head2);
        forEach(listNode);
*/

        System.out.println("=========");
        ListNode merge = mergeTwoListsUp(head1,head2);
        forEach(merge);
    }

    public static void addListNode(ListNode head,ListNode ListNode){
        ListNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = ListNode;
    }

    public static void addSortedListNode(ListNode head,ListNode ListNode){
        ListNode temp = head;
        while(temp.next != null){
            if (temp.next.val > ListNode.val){
                break;
            }
            temp = temp.next;
        }
        ListNode.next = temp.next;
        temp.next = ListNode;
    }

    public static void forEach(ListNode head){
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }
}