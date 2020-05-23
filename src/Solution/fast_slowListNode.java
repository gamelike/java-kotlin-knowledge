package Solution;

public class fast_slowListNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head){
        if(head == null) return true;
        ListNode frontNode = head;

        ListNode firstNode = getListNode(head);
        ListNode reverseList = reverseList(firstNode);

        boolean flag = true;
        while(flag && reverseList.next != null){
            if (reverseList.val != frontNode.val) flag = false;
            frontNode = frontNode.next;
            reverseList = reverseList.next;
        }

        //恢复链表
        firstNode.next = reverseList(reverseList);
        return flag;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            // 链表
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public ListNode getListNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
