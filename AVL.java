public class AVL<T extends Comparable<T>> extends BST<T> {
    private Branch<T> root;
    // private Branch<T> father;

    public AVL() {
        this.root = null;
        // this.father = null;
    }
    public AVL(Branch<T> root) {
        this.root = root;
        // this.father = root;
    }
    public void insert(T data) throws ItemDuplicated {
        insertRec(root, new Branch<T>(data));    
    }
    private void insertRec(Branch<T> root, Branch<T> elem) throws ItemDuplicated {
        int dir = root.getData().compareTo(elem.getData());
        if(dir == 0 ) 
            throw new ItemDuplicated ("ya existe");
        if(dir < 0 ) {
            if(root.getLeft() == null) {
                root.setLeft(elem);
            } else {
                insertRec(root.getLeft(), elem);                
            }
        }
        if(dir > 0 ) {            
            if(root.getRight() == null) {
                root.setRight(elem);
            } else {
                insertRec(root.getRight(), elem);                
            }
        }
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
