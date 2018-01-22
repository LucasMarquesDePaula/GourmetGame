
/**
 * This class implements a binary tree
 *
 * @author Lucas
 * @see https://en.wikipedia.org/wiki/Binary_tree
 */
public class Tree<T> {

    private T data;
    private Tree<T> left;
    private Tree<T> right;

    /**
     * @param data
     */
    public Tree(T data) {
        this.data = data;
    }

    /**
     * @param data
     * @param left
     * @param rigth
     */
    public Tree(T data, T left, T right) {
        this.data = data;
        this.setLeft(left);
        this.setRight(right);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @return true if tree has no child
     * @see https://en.wikipedia.org/wiki/Tree_(data_structure)#leaf
     */
    public boolean isLeaf() {
        return getLeft() == null && getRight() == null;
    }

    public Tree<T> getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.setLeft(new Tree<T>(left));
    }

    public void setLeft(Tree<T> left) {
        this.left = left;
    }

    public Tree<T> getRight() {
        return right;
    }

    public void setRight(T right) {
        this.setRight(new Tree<T>(right));
    }

    public void setRight(Tree<T> right) {
        this.right = right;
    }
}
