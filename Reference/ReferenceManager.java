package Reference;

class Node {
    public int val;
    public Node(int val) {
        this.val = val;
    }
}

public class ReferenceManager {
    public Node node;

    public void copyValue(Node obj) {
        if (obj == null) return;
        if (node == null) {
            node = new Node(obj.val);
        }
        node.val = obj.val;
    }

    public void copyReference(Node obj) {
        node = obj;
    }
}

