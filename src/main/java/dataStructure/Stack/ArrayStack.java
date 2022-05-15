package Stack;

public class ArrayStack {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<Integer>(10);

        stack.push(20);
        stack.push(10);
        stack.push(30);

        stack.forEach();
    }
}

class MyStack<T>{
    private int maxSize;
    private T[] stack;
    private int top = -1;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(T newStack){
        if (isFull()) return;

        stack[++top] = newStack;
    }

    public T pop(){
        if (isEmpty()) throw new RuntimeException("栈空");

        return stack[top--];
    }

    public void forEach(){
        if (isEmpty()) throw new RuntimeException("栈空");

        int length = top;
        for (int i = 0; i <= length; i++) {
            System.out.println("MyStack : " + pop());
        }
    }
}
