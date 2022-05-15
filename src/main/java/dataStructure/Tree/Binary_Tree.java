package Tree;

/**
 * @author: gameLike
 * @Date: 2020/5/27
 * 二叉树
 */
public class Binary_Tree {
    public static void main(String[] args) {
        binaryTree binaryTree = new binaryTree();
        Tree tree1 = new Tree(1, "刘备");
        Tree tree2 = new Tree(2, "关羽");
        Tree tree3 = new Tree(3, "张飞");
        Tree tree4 = new Tree(4, "马超");

        tree1.setLeft(tree2);
        tree1.setRight(tree3);
        tree3.setLeft(tree4);

        binaryTree.setRoot(tree1);
        binaryTree.preTraversal(); // 1 2 3 4

        System.out.println("=========");

        binaryTree.infixTraversal(); // 2 1 4 3

        System.out.println("=========");

        binaryTree.postTraversal(); // 2 4 3 1

        System.out.println("==========");
        System.out.println(binaryTree.postFind(2));

        System.out.println("=========");
        binaryTree.deleteTree(3);
        binaryTree.preTraversal();
    }
}

// 创建二叉树
class binaryTree{
    private Tree root;

    public void setRoot(Tree root){
        this.root = root;
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
    public Tree preFind(int id){
        Tree result = null;
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
    public Tree infixFind(int id){
        Tree result = null;
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
    public Tree postFind(int id){
        Tree result = null;
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
class Tree{
    public int sno;
    public String name;
    private Tree left;
    private Tree right;

    public Tree(int sno, String name) {
        this.sno = sno;
        this.name = name;
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

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
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
    public Tree preFind(int id){
        // 用于观察比较了几次
        System.out.println("进入前序遍历");
        Tree result = null;
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
    public Tree infixFind(int id){
        Tree result = null;
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
    public Tree postFind(int id){
        Tree result = null;
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
