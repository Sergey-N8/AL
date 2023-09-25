class BinaryTree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color {Red, Black};

    public boolean insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
            root.color = Color.Black;
            return true;
        }else{     
            root = rebalance(root);
            root.color = Color.Black;
            return insert(root, value);
        }
        
    }

    private boolean insert(Node node, int value){
        if(node.value == value){
            return false;
        }else{
            if(node.value < value){
                if(node.right != null){
                    insert(node.right, value);
                    node.right = rebalance(node.right);
                    return insert(node.right, value);
                }else{
                    node.right = new Node();
                    node.right.value = value;
                    node.color = Color.Red;
                    return true;
                }
            }else{
                if(node.left != null){
                    node.left = rebalance(node.left);
                    return insert(node.left, value);
                }else{
                    node.left = new Node();
                    node.left.value = value;
                    node.color = Color.Red;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.Red
                    && (result.left == null || result.left.color == Color.Black)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.Red
                    && result.left.left != null && result.left.left.color == Color.Red) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.Red
                    && result.right != null && result.right.color == Color.Red) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.Black;
        node.left.color = Color.Black;
        node.color = Color.Red;
    }

    private Node leftSwap(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.Red;
        return left;
    }

    private Node rightSwap(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.Red;
        return right;
    }

    public boolean find(int value){
        return find(root, value);
    }

    private boolean find(Node node, int value){
        if(node == null){
            return false;
        }
        if(node.value == value){
            return true;
        }

        if(node.value < value){
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(5);
        tree.insert(3);
        tree.insert(4);
        tree.insert(1);
        tree.insert(2);
        tree.insert(7);
        tree.insert(8);
        tree.insert(6);

        System.out.println(tree.find(7));
        System.out.println(tree.find(9));

    }
}

