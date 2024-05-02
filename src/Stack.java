class Stack<T> {
    private Node<T> top;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Stack() {
        this.top = null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = this.top;
        this.top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = this.top.data;
        this.top = this.top.next;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return this.top.data;
    }

    public boolean isEmpty() {
        return this.top == null;
    }
}