package LinkedList;

public class DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinked linked = new DoubleLinked();

/*        linked.addLastList(new DoubleListNode(3,"张飞"));
        linked.addLastList(new DoubleListNode(1,"刘备"));
        linked.addLastList(new DoubleListNode(2,"关羽"));
        linked.addLastList(new DoubleListNode(4,"马超"));
        linked.forEach(linked.getHead());*/
        System.out.println("========");

        linked.addSortedList(new DoubleListNode(3,"张飞"));
        linked.addSortedList(new DoubleListNode(1,"刘备"));
        linked.addSortedList(new DoubleListNode(2,"关羽"));
        linked.addSortedList(new DoubleListNode(4,"马超"));
        linked.forLastEach(linked.getLast());
    }
}

class DoubleLinked{
    private DoubleListNode head = new DoubleListNode();

    public DoubleListNode getHead() {
        return head;
    }

    public DoubleListNode getLast(){
        DoubleListNode last = getHead();

        while (last.next != null){
            last = last.next;
        }

        return last;
    }

    public void addLastList(DoubleListNode newDoubleNode){
        DoubleListNode temp = head;

        while (temp.next != null){
            temp = temp.next;
        }

        temp.next = newDoubleNode;
        newDoubleNode.pre = temp;
    }

    public void addSortedList(DoubleListNode newDoubleNode){
        DoubleListNode temp = head;

        while(temp.next != null && temp.next.val < newDoubleNode.val){
            temp = temp.next;
        }

        if (temp.next != null) {
            newDoubleNode.next = temp.next;
            temp.next.pre = newDoubleNode;
            temp.next = newDoubleNode;
            newDoubleNode.pre = temp;
        }else {
            temp.next = newDoubleNode;
            newDoubleNode.pre = temp;
        }
    }

    public void delete(int no){
        DoubleListNode temp = head;

        while(temp != null){
            if (temp.val == no){
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }

    public void forEach(DoubleListNode head){
        DoubleListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public void forLastEach(DoubleListNode last){
        DoubleListNode temp = last;

        while(temp.pre != null){
            System.out.println(temp);
            temp = temp.pre;
        }
    }
}

class DoubleListNode{
    public int val;
    public String name;
    public DoubleListNode pre;
    public DoubleListNode next;

    public DoubleListNode() {
    }

    public DoubleListNode(int val, String name) {
        this.val = val;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoubleListNode{" +
                "val=" + val +
                ", name='" + name + '\'' +
                '}';
    }
}