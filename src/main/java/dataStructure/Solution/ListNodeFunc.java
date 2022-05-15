package Solution;

public class ListNodeFunc {
    private ListNode frontNode;

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 主方法
    public boolean isPalindrome(ListNode head){
        frontNode = head;
        return recursivelyCheck(head);
    }

    // 递归检查
    public boolean recursivelyCheck(ListNode currentNode){
        while(currentNode != null){
            if(!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontNode.val) return false;
            frontNode = frontNode.next;
        }
        return true;
    }
}
