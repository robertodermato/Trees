public class AnderssonTreeOfInteger {
    class Node {

        Integer element;
        Integer level;
        Node left;
        Node right;

        Node(Integer e, Integer l) {
            level = l;
            element = e;
            left = null;
            right = null;
        }
    }

    Node root;
    Node nil;
    int count;

    // Construtor
    public AnderssonTreeOfInteger() {
        count = 0;
        nil = new Node(0, 0);
        root = nil;
    }

    private Node skew(Node root) {
        if (root.level != 0) {
            if (root.left.level.equals(root.level)) {
                Node save = root;
                root = root.left;
                save.left = root.right;
                root.right = save;
            }
            root.right = skew(root.right);
        }
        return root;
    }

    private Node split(Node root) {
        if (root.right != nil) {
            if (root.right.right.level.equals(root.level)) {
                Node save = root;
                root = root.right;
                save.right = root.left;
                root.left = save;
                ++root.level;
                root.right = split(root.right);
            }
        }
        return root;
    }

    public void add(Integer element) {
        root = add(root, element);
    }

    private Node add(Node root, Integer element) {
        if (root == nil)
            root = new Node(element, 1);
        else {
            if (root.element < element)
                root.right = add(root.right, element);
            else
                root.left = add(root.left, element);
            root = skew(root);
            root = split(root);
        }
        return root;
    }

}
