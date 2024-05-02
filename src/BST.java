class BSTNode<T extends Comparable<T>> {
    T data;
    BSTNode<T> left;
    BSTNode<T> right;

    public BSTNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST<T extends Comparable<T>> {
    private BSTNode<T> root;

    public BST() {
        this.root = null;
    }

    public void insert(T data) {
        this.root = insertRec(this.root, data);
    }

    private BSTNode<T> insertRec(BSTNode<T> root, T data) {
        if (root == null) {
            return new BSTNode<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public T search(T data) {
        return searchRec(this.root, data);
    }

    private T searchRec(BSTNode<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) == 0) {
            return root.data;
        } else if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    private void inorderRec(BSTNode<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
        }
    }

    public void inorder() {
        inorderRec(this.root);
    }
}