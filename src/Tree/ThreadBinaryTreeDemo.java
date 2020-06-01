package Tree;

/**
 * @author: gameLike
 * @Date: 2020/5/28
 * 线索化二叉树
 */
public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {
        Node a = new Node(1,"a");
        Node b = new Node(3, "b");
        Node c = new Node(6, "c");
        Node d = new Node(8, "d");
        Node e = new Node(10, "e");
        Node f = new Node(14, "b");
        threadBinaryTree binaryTree = new threadBinaryTree();
        binaryTree.setRoot(a);
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);

        binaryTree.threadPreTree(a);
        binaryTree.forEachPreTree();
    }
}

// 线索化二叉树
// 创建二叉树
class threadBinaryTree{
    private Node root;
    private Node pre = null;

    public void setRoot(Node root){
        this.root = root;
    }
    // 二叉树线索化
    // node当前需要线索化的节点
    // 中序线索化
    public void threadTree(Node node){
        if (node == null) {
            return;
        }

        // 先线索化左子树
        threadTree(node.getLeft());

        // 中序线索化当前节点
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(false);
        }
        if (pre != null && pre.getRight() == null){
            // 前驱节点右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针
            pre.setRightType(false);
        }
        // 处理后，当前节点就是下一个节点的前驱节点
        pre = node;

        // 线索化右子树
        threadTree(node.getRight());
    }
    // 遍历线索化二叉树
    public void forEachTree(){
        Node node = root;
        while (node != null){
            while (node.isLeftType()){
                node = node.getLeft();
            }
            System.out.println(node);
            while (!node.isRightType()){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    // 前序线索化
    public void threadPreTree(Node node){
        if (node == null){
            return;
        }
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(false);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(false);
        }
        pre = node;

        if (node.isLeftType())
        threadPreTree(node.getLeft());
        if (node.isRightType())
        threadPreTree(node.getRight());
    }
    // 前序遍历线索化二叉树
    public void forEachPreTree(){
        Node node = root;
        while(node != null){
            while (node.isLeftType()){
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            while (!node.isRightType()){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    // 后序线索化
    public void threadPostTree(Node node){
        if (node == null){
            return;
        }

        if (node.isLeftType())
        threadPostTree(node.getLeft());
        if (node.isRightType())
        threadPostTree(node.getRight());

        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(false);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(false);
        }
        pre = node;
    }
    // 后序遍历线索化二叉树
    public void foreachPostTree(){
        Node node = root;
        while (node != null && !node.isLeftType()){
            node = node.getLeft();
        }
        Node pre = null;
        while (node != null){
            if (!node.isRightType()){
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if(node.getRight() == pre) {
                    System.out.println(node);
                    if(node == root) {
                        return;
                    }

                    pre = node;
                    // 后续线索化需要加入父节点；
//                    node = node.getParent();

                } else {    //如果从左节点的进入则找到有子树的最左节点
                    node = node.getRight();
                    while(node != null && node.isLeftType()) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }
    // 前序遍历
    public void preTraversal(){
        if (this.root != null){
            this.root.preTraversal();
        }else {
            System.out.println("二叉树为null");
        }
    }
    // 中序遍历
    public void infixTraversal(){
        if (this.root != null){
            this.root.infixTraversal();
        }else {
            System.out.println("二叉树为空");
        }
    }
    // 后序遍历
    public void postTraversal(){
        if (this.root != null){
            this.root.postTraversal();
        }else {
            System.out.println("二叉树为空");
        }
    }
    // 前序查找
    public Node preFind(int id){
        Node result = null;
        if (this.root != null){
            result = this.root.preFind(id);
        }else {
            System.out.println("二叉树为空");
        }
        if (result == null){
            System.out.println("未找到学号是" + id + "的学生");
        }
        return result;
    }
    // 中序查找
    public Node infixFind(int id){
        Node result = null;
        if (this.root != null){
            result = this.root.infixFind(id);
        }else {
            System.out.println("二叉树为空");
        }
        if (result == null){
            System.out.println("未找到学号是" + id + "的学生");
        }
        return result;
    }
    // 后续查找
    public Node postFind(int id){
        Node result = null;
        if (this.root != null){
            result = this.root.postFind(id);
        }else {
            System.out.println("二叉树为空");
        }
        if (result == null){
            System.out.println("未找到学号是" + id + "的学生");
        }
        return result;
    }
    // 删除节点
    public void deleteTree(int id){
        if (root == null){
            System.out.println("树为空");
        }else if (root.sno == id){
            root = null;
        }else {
            root.deleteTree(id);
        }
    }
}

// 创建节点
class Node{
    public int sno;
    public String name;
    // left 可能为左子树，也可能为前驱节点
    private Node left;
    // right 可能为右子树，也可能是后继节点
    private Node right;
    // 用来判断节点类型，true为左子树，false为前驱节点
    private boolean leftType = true;
    // 同理
    private boolean rightType = true;

    public Node(int sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    public boolean isLeftType() {
        return leftType;
    }

    public void setLeftType(boolean leftType) {
        this.leftType = leftType;
    }

    public boolean isRightType() {
        return rightType;
    }

    public void setRightType(boolean rightType) {
        this.rightType = rightType;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历 先输出根节点，左边不为空，则递归遍历左节点，最后右边不为空，递归遍历右节点
    public void preTraversal(){
        System.out.println(this);

        if (this.left != null){
            this.left.preTraversal();
        }
        if (this.right != null){
            this.right.preTraversal();
        }
    }
    // 中序遍历 先遍历左节点，在输出根节点，最后遍历右节点
    public void infixTraversal(){
        if (this.left != null){
            this.left.infixTraversal();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixTraversal();
        }
    }
    // 后序遍历 先左，后右，最后输出根节点
    public void postTraversal(){
        if (this.left != null){
            this.left.postTraversal();
        }
        if (this.right != null){
            this.right.postTraversal();
        }
        System.out.println(this);
    }
    // 前序遍历查找
    public Node preFind(int id){
        // 用于观察比较了几次
        System.out.println("进入前序遍历");
        Node result = null;
        if (this.sno == id) return this;

        if (this.left != null){
            result = this.left.preFind(id);
        }
        if (result != null){
            return result;
        }
        if (this.right != null){
            result = this.right.preFind(id);
        }
        return result;
    }
    // 中序遍历查找
    public Node infixFind(int id){
        Node result = null;
        if (this.left != null){
            result = this.left.infixFind(id);
        }
        // 用于观察比较了几次
        System.out.println("进入中序遍历");
        if (result != null){
            return result;
        }
        if (this.sno == id) return this;
        if (this.right != null){
            result = this.right.infixFind(id);
        }
        return result;
    }
    // 后序遍历查找
    public Node postFind(int id){
        Node result = null;
        if (this.left != null){
            result = this.left.postFind(id);
        }
        if (result != null){
            return result;
        }
        if (this.right != null){
            result = this.right.postFind(id);
        }
        // 用于观察比较了几次
        System.out.println("进入后序遍历");
        if (result != null){
            return result;
        }
        if (this.sno == id) return this;
        else return null;
    }
    // 删除节点
    public void deleteTree(int id){
        if (this.left != null){
            if (this.left.sno == id) {
                this.left = null;
                return;
            }
            else this.left.deleteTree(id);
        }
        if (this.right != null){
            if (this.right.sno == id) {
                this.right = null;
                return;
            }
            else this.right.deleteTree(id);
        }
    }
}