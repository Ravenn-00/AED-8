public class BST <T extends Comparable<T>> {
    private Branch<T> root;
    public BST() {
        this.root = null;
    }
    public BST(Branch<T> root) {
        this.root = root;
    }
    public void insert(T data) throws ItemDuplicated {
        Branch<T> nuevo = new Branch<T>(data);
        if (root == null) {
            root = nuevo;
            return;
        }
        if (insertRe(root, nuevo) == null) {
            throw new ItemDuplicated("item duplicado");
        }
    }
    private Branch<T> insertRe(Branch<T> current, Branch<T> nuevo) {
        if (nuevo.getData().compareTo(current.getData()) == 0) {
            return null;
        } else if (nuevo.getData().compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                current.setLeft(nuevo);
                return nuevo;
            } else {
                return insertRe(current.getLeft(), nuevo);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(nuevo);
                return nuevo;
            } else {
                return insertRe(current.getRight(), nuevo);
            }
        }
    }
    public void delete(T data) throws ExceptionIsEmpty {
        if (root == null)
            throw new ExceptionIsEmpty("arbol vacio");
        root = deleteRec(root, data);
    }
    private Branch<T> deleteRec(Branch<T> node, T data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(deleteRec(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(deleteRec(node.getRight(), data));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            Branch<T> min = minValueNode(node.getRight());
            node.setData(min.getData());
            node.setRight(deleteRec(node.getRight(), min.getData()));
        }
        return node;
    }
    private Branch<T> minValueNode(Branch<T> node) {
        Branch<T> current = node;
        while (current.getLeft() != null) current = current.getLeft();
        return current;
    }
    public T search(T data) throws ItemNotFound {
        Branch<T> res = searchRec(root, data);
        if (res == null)
            throw new ItemNotFound("No encontrado: " + data);
        return res.getData();
    }
    private Branch<T> searchRec(Branch<T> node, T data) {
        if (node == null)
            return null;
        int cmp = data.compareTo(node.getData());
        if (cmp == 0)
            return node;
        else if (cmp < 0)
            return searchRec(node.getLeft(), data);
        else
            return searchRec(node.getRight(), data);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb);
        return sb.toString().trim();
    }
    private void toStringRec(Branch<T> node, StringBuilder sb) {
        if (node != null) {
            toStringRec(node.getLeft(), sb);
            sb.append(node.getData()).append(" ");
            toStringRec(node.getRight(), sb);
        }
    }
    public void destroyNodes() {
        root = null;
    }
    public int countAllNodes() {
        return countAllNodesRec(root);
    }
    private int countAllNodesRec(Branch<T> node) {
        if (node == null)
            return 0;
        return 1 + countAllNodesRec(node.getLeft()) + countAllNodesRec(node.getRight());
    }
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Branch<T> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(heightRec(node.getLeft()), heightRec(node.getRight()));
    }

    public int amplitude() {
        if (root == null)
            return 0;
        java.util.Queue<Branch<T>> queue = new java.util.LinkedList<>();
        queue.add(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            maxWidth = Math.max(maxWidth, count);
            for (int i = 0; i < count; i++) {
                Branch<T> n = queue.poll();
                if (n.getLeft() != null) queue.add(n.getLeft());
                if (n.getRight() != null) queue.add(n.getRight());
            }
        }
        return maxWidth;
    }

    public int areaBTS() {
        return countLeaves(root);
    }

    private int countLeaves(Branch<T> node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    public void drawBST() {
        drawBSTRec(root, 0);
    }

    private void drawBSTRec(Branch<T> node, int level) {
        if (node == null) return;
        drawBSTRec(node.getRight(), level + 1);
        for (int i = 0; i < level; i++) System.out.print("    ");
        System.out.println(node.getData());
        drawBSTRec(node.getLeft(), level + 1);
    }

    public boolean sameArea(BST<T> other) {
        if (root == null && (other == null || other.root == null)) {
            return true;
        }
        if (root == null || other == null || other.root == null) {
            return false;
        }
        return countLeaves(this.root) == countLeaves(other.root);
    }

    public void parenthesize() {
        parenthesizeRec(root);
    }

    private void parenthesizeRec(Branch<T> node) {
        if (node == null) return;
        System.out.print(node.getData());
        if (node.getLeft() != null || node.getRight() != null) {
            System.out.print("(");
            if (node.getLeft() != null) parenthesizeRec(node.getLeft());
            else System.out.print("null");
            System.out.print(",");
            if (node.getRight() != null) parenthesizeRec(node.getRight());
            else System.out.print("null");
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        try {
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);

            System.out.println("arbol BST:");
            bst.drawBST();

            System.out.println("inorden:");
            System.out.println(bst.toString());

            System.out.println("buscar 40: " + bst.search(40));

            System.out.println("total de nodos: " + bst.countAllNodes());

            System.out.println("altura: " + bst.height());

            System.out.println("amplitud: " + bst.amplitude());

            System.out.println("area hojas: " + bst.areaBTS());

            System.out.print("parentizado: ");
            bst.parenthesize();
            System.out.println();

            BST<Integer> bst2 = new BST<>();
            bst2.insert(10);
            bst2.insert(5);
            bst2.insert(15);
            bst2.drawBST();
            System.out.println("compara: " + bst.sameArea(bst2));

            bst.delete(70);
            System.out.println("eliminar 70:");
            bst.drawBST();

        } catch (ItemDuplicated | ItemNotFound | ExceptionIsEmpty e) {
            System.out.println("excp: " + e.getMessage());
        }
    }
}