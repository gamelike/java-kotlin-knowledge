package Solution;

import java.util.ArrayList;
import java.util.List;

public class PalindromeList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();

        ListNode currentNode = head;
        while(currentNode != null){
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while(right > left) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
