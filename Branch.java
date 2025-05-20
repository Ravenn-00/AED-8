public class Branch<T> {
    private T data;
    private Branch<T> left;
    private Branch<T> right;
    private int weight;
    private int height;
    public Branch(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Branch<T> getLeft() {
        return left;
    }
    public void setLeft(Branch<T> left) {
        this.left = left;
    }
    public Branch<T> getRight() {
        return right;
    }
    public void setRight(Branch<T> right) {
        this.right = right;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}