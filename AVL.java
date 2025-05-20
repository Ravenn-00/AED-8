public class AVL<T extends Comparable<T>> {
    private Branch<T> root;
    private Branch<T> father;

    public AVL() {
        this.root = null;
        this.father = null;
    }
    public AVL(Branch<T> root) {
        this.root = root;
        this.father = root;
    }
    public void insert(T data) {
    
    }
    public int balanceLeft(Branch<T> y) {
        return 0;
    }
    public int balanceRight(Branch<T> y) {
        return 0;
    }
    public void rotateRight(Branch<T> y) {
        
    }
    public void rotateLeft(Branch<T> x) {
        
    }    
}
