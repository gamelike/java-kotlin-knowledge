package HashTable;

import java.util.Scanner;

public class hashTableDemo {
    public static void main(String[] args) {
        // 创建hashTable
        HashTable hashTable = new HashTable(7);

        int num;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1.添加雇员");
            System.out.println("2.显示雇员");
            System.out.println("3.查找雇员");
            System.out.println("4.删除雇员");
            System.out.println("任意键退出系统");

            num = scanner.nextInt();
            switch (num){
                case 1:
                    System.out.println("输入雇员id");
                    int id = scanner.nextInt();
                    System.out.println("输入雇员名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.addEmp(emp);
                    break;
                case 2:
                    hashTable.forEach();
                    break;
                case 3:
                    System.out.println("输入要查找的雇员id");
                    id = scanner.nextInt();
                    hashTable.findEmp(id);
                    break;
                case 4:
                    System.out.println("输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.deleteEmp(id);
                    break;
                default:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}

// 创建哈希表
class HashTable{
    private EmployLinkedList[] linkedList;
    private int size;

    public HashTable(int size) {
        this.size = size;
        linkedList = new EmployLinkedList[size];
        for (int i = 0; i < size; i++) {
            linkedList[i] = new EmployLinkedList();
        }
    }

    // 编写散列函数 , 取模法
    public int hashFun(int id){
        return id % size;
    }

    // 添加雇员
    public void addEmp(Emp emp){
        // 判断应该加入到哪条链表
        int EmployLinkedListNo = hashFun(emp.id);
        linkedList[EmployLinkedListNo].addEmp(emp);
    }

    // 查找雇员
    public void findEmp(int id){
        int EmployLinkedListNo = hashFun(id);
        Emp emp = linkedList[EmployLinkedListNo].findEmp(id);
        if (emp != null){
            System.out.println("第" + EmployLinkedListNo + "条链表中，" + " id : " + emp.id + " -> name : " + emp.name);
        }else {
            System.out.println("未找到");
        }
    }

    // 删除雇员
    public void deleteEmp(int id){
        int EmployLinkedListNo = hashFun(id);
        boolean flag = linkedList[EmployLinkedListNo].deleteEmp(id);
        if (flag){
            System.out.println("在第" + EmployLinkedListNo + "条链表中删除成功,id = " + id);
        }else {
            System.out.println("未找到，删除失败");
        }
    }

    // 遍历
    public void forEach(){
        for (int i = 0; i < linkedList.length; i++) {
            linkedList[i].forEach(i);
            System.out.println();
        }
    }
}

// 创建一个链表
class EmployLinkedList{
    // 头指针，指向第一个节点,直接指向第一个Emp
    private Emp head;

    // 添加雇员到链表
    // id 从小到大
    public void addEmp(Emp emp){
        if (head == null) head = emp;
        else {
            Emp temp = head;
            while (temp.next != null) {
                if (temp.next.id > emp.id) {
                    break;
                } else {
                    temp = temp.next;
                }
            }

            emp.next = temp.next;
            temp.next = emp;
        }
    }

    // 查找雇员
    public Emp findEmp(int id){
        Emp temp = head;
        boolean flag = false;
        while(temp != null){
            if (temp.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            return temp;
        }else {
            return null;
        }
    }

    // 删除雇员
    public boolean deleteEmp(int id){
        if (head.id == id) {
            head = head.next;
            return true;
        }
        Emp temp = head;
        while (temp.next != null){
            if (temp.next.id == id){
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void forEach(int no){
        if (head == null) System.out.print("第" + (no + 1) + "条链表 -> null");
        else {
            Emp temp = head;
            System.out.print("第" + (no + 1) + "条链表 -> ");
            while (temp != null) {
                System.out.print("id=" + temp.id + " -> " + temp.name + "- >");
                temp = temp.next;
            }
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next; // 默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
