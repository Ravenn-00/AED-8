public class BST <T extends Comparable<T>> {
    private Branch<T> root;

    public BST() {
        this.root = null;
    }
    public int getAltura() {
        return getAlturaRecursivo(root);
    }
    public int getAlturaRecursivo(Branch<T> Branch) {
        if (Branch == null) {
            return 0;
        }
        int alturootquierda = getAlturaRecursivo(Branch.getLeft());
        int alturaDerecha = getAlturaRecursivo(Branch.getRight());
        return Math.max(alturootquierda, alturaDerecha) + 1;
    }

    public void insertar(T data) {
        Branch<T> nuevo = new Branch<>(data);
        if (root == null) {
            root = nuevo;
        } else {
            insertarRecursivo(root, nuevo);
        }
    }
    private void insertarRecursivo(Branch<T> actual, Branch<T> nuevo) {
        if (nuevo.getData().compareTo(actual.getData()) < 0) {
            if (actual.getLeft() == null) {
                actual.setLeft(nuevo);
            } else {
                insertarRecursivo(actual.getLeft(), nuevo);
            }
        } else {
            if (actual.getRight() == null) {
                actual.setRight(nuevo);
            } else {
                insertarRecursivo(actual.getRight(), nuevo);
            }
        }
    }
    public void inOrden() {
        inOrdenRecursivo(root);
    }

    private void inOrdenRecursivo(Branch<T> Branch) {
        if (Branch != null) {
            inOrdenRecursivo(Branch.getLeft());
            System.out.print(Branch.getData() + " ");
            inOrdenRecursivo(Branch.getRight());
        }
    }

    public void preOrden() {
        preOrdenRecursivo(root);
    }

    private void preOrdenRecursivo(Branch<T> Branch) {
        if (Branch != null) {
            System.out.print(Branch.getData() + " ");
            preOrdenRecursivo(Branch.getLeft());
            preOrdenRecursivo(Branch.getRight());
        }
    }

    public void postOrden() {
        postOrdenRecursivo(root);
    }

    private void postOrdenRecursivo(Branch<T> Branch) {
        if (Branch != null) {
            postOrdenRecursivo(Branch.getLeft());
            postOrdenRecursivo(Branch.getRight());
            System.out.print(Branch.getData() + " ");
        }
    }
    public T search(T x) throws ItemNotFound {
        Branch<T> res = searchRec(x, this.root);
        if (res == null)
            throw new ItemNotFound ("El data "+ x + "no esta");
        return res.getData();
    }
    protected Branch<T> searchRec(T x, Branch<T> n){
        if (n == null) return null;
        else {
            int resC = n.getData().compareTo(x);
            if (resC < 0) return searchRec(x, n.getRight());
            else if (resC > 0) return searchRec(x, n.getLeft());
            else return n;
        }
    }
    public static void main(String[] args) {
        BST<Integer> arbol = new BST<>();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Recorrido InOrden:");
        arbol.inOrden();
        System.out.println();

        System.out.println("Recorrido PreOrden:");
        arbol.preOrden();
        System.out.println();

        System.out.println("Recorrido PostOrden:");
        arbol.postOrden();
        System.out.println("Árbol visual:");
        
    }
    
}
