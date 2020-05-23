package LinkedList;

import java.util.Stack;

public class SingleLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        HeroNode heroNode = new HeroNode(1, "刘备");
        linkedList.sortedAdd(new HeroNode(2,"张飞"));
        linkedList.sortedAdd(heroNode);
        linkedList.sortedAdd(new HeroNode(3,"关羽"));
        linkedList.sortedAdd(new HeroNode(4,"马超"));
        linkedList.sortedAdd(new HeroNode(4,"黄忠"));

        linkedList.forEach();

        System.out.println("==========");

        linkedList.update(new HeroNode(4,"黄忠"));
        linkedList.forEach();

        System.out.println("===========");
        linkedList.delete(4);
        linkedList.forEach();

        System.out.println("==========");
        System.out.println(linkedList.findLastIndex(2));

        System.out.println("=======");
        linkedList.reverseLinkedList();
        linkedList.forEach();

        System.out.println("===========");
    }
}

class LinkedList{
    private HeroNode head = new HeroNode();

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    public void sortedAdd(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next != null){
            if (temp.next.sno > heroNode.sno){
                break;
            }else if(temp.next.sno == heroNode.sno){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            System.out.println("编号" + heroNode.sno + "存在，插入失败");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;

        while(temp.next != null){
            temp = temp.next;
            if (temp.next.sno == heroNode.sno){
                flag = true;
                break;
            }
        }

        if (flag){
            temp.name = heroNode.name;
        }else {
            System.out.println("未找到");
        }
    }

    public void delete(int sno){
        HeroNode temp = head;
        boolean flag = false;
        while(temp.next != null){
            if (temp.next.sno == sno){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("未找到");
        }
    }

    public void forEach(){
        // 因为head节点不能移动，因此我们需要一个辅助遍历(指针)
        HeroNode temp = head;
        while(temp.next != null){
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public int getLength(){
        HeroNode temp = head;
        int i = 0;
        while(temp.next != null){
            i++;
            temp = temp.next;
        }
        return i;
    }

    // 获得倒数第K个节点
    public HeroNode findLastIndex(int index){
        if (head.next == null) return null;
        int length = getLength();
        if (index > length || index < 0) return null;
        HeroNode temp = head;
        int i = 0;
        while(head.next != null){
            temp = temp.next;
            i++;
            if (i == (length - index + 1)){
                break;
            }
        }
        return temp;
    }

    // 反转链表
    public void reverseLinkedList(){
        HeroNode temp = head.next;
        HeroNode reverseLinked = null;
        while(temp != null){
            HeroNode pro = temp.next;
            temp.next = reverseLinked;
            reverseLinked = temp;
            temp = pro;
        }

        head.next = reverseLinked;
    }

    // 倒序输出链表
    public void StackForEach(HeroNode heroNode){
        if (heroNode == null)
            return;
        System.out.println(heroNode);
        StackForEach(heroNode.next);
    }
}

class HeroNode{
    public int sno;
    public String name;
    public HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                '}';
    }
}
