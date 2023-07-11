import java.awt.*;

public class BinTree {
    Node root;

    public boolean add(int value) {

        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.black;
            return result;
        } else {
            root = new BinTree.Node();
            root.color = Color.black;
            root.value = value;
            return true;
        }
    }
    private boolean addNode(BinTree.Node node, int value){
        if (node.value == value){
            return false;
        } else {
            if (node.value > value){
                if (node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new BinTree.Node();
                    node.left.color = Color.red;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new BinTree.Node();
                    node.right.color = Color.black;
                    node.right.value = value;
                    return true;
                }

            }
        }
    }
    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    private BinTree.Node rebalance(BinTree.Node node) {
        BinTree.Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.red &&
                    (result.left == null || result.left.color == Color.black)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.red &&
                    result.left.left != null && result.left.left.color == Color.red){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.red &&
                    result.right != null && result.right.color == Color.red){
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    private BinTree.Node rightSwap(BinTree.Node node) {
        BinTree.Node right = node.right;
        BinTree.Node betweebChild = right.right;
        right.left = node;
        node.right = betweebChild;
        right.color = node.color;
        node.color = Color.red;
        return right;
    }

    private BinTree.Node leftSwap(BinTree.Node node) {
        BinTree.Node left = node.left;
        BinTree.Node betweebChild = left.right;
        left.right = node;
        node.left = betweebChild;
        left.color = node.color;
        node.color = Color.red;
        return left;
    }

    private void colorSwap (BinTree.Node node){
        node.right.color = Color.black;
        node.left.color = Color.black;
        node.color = Color.red;
    }


    public class Node {
        int value;
        Node left;
        Node right;
        Color color;
        Node() {
            this.color = Color.red;
        }

        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }


    }
    enum Color {red, black}
}
