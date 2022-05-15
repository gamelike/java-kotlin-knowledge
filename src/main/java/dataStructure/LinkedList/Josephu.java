package LinkedList;

import java.util.HashSet;

public class Josephu {
    private ListNode1 head = new ListNode1(0);
    public static void main(String[] args) {
        ListNode1 head = new ListNode1(0);
        Test test = new Test();
        head.next = head;
        ListNode1 a1 = new ListNode1(1);
        ListNode1 a2 = new ListNode1(2);
        ListNode1 a3 = new ListNode1(3);
        ListNode1 a4 = new ListNode1(4);
        ListNode1 a5 = new ListNode1(5);
        test.add(head,a1);
        test.add(head,a2);
        test.add(head,a3);
        test.add(head,a4);
        test.add(head,a5);
        test.forEach(head);

    }
}

class Test{
    public void add(ListNode1 head,ListNode1 newList){
        ListNode1 temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        temp.next = newList;
        newList.next = head;
    }

    public void forEach(ListNode1 head){
        ListNode1 temp = head;
        while(temp.next != head){
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode1{" +
                "val=" + val +
                '}';
    }
}