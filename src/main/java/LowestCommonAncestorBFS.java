import java.util.HashMap;
import java.util.LinkedList;

public class LowestCommonAncestorBFS {

    Integer lowestCommonAnestor (Node tree, int val1, int val2) {
        LinkedList<Integer> path1 = pathFromRoot(tree, val1);
        LinkedList<Integer> path2 = pathFromRoot(tree, val2);

        return endOfCommonPrefix(path1, path2);
    }

    private Integer endOfCommonPrefix (final LinkedList<Integer> path1, final LinkedList<Integer> path2) {
        Integer lca = null;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) == path2.get(i)) { lca = path1.get(i); }
        }
        return lca;
    }

    LinkedList<Integer> pathFromRoot (Node tree, int val) {
        HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();
        LinkedList<Node> queue = new LinkedList<Node>();

        queue.add(tree);
        parents.put(tree.val, null);

        while (!queue.isEmpty()) {
            Node head = queue.poll();
            if (head.val == val) { break; }
            if (head.right != null) {
                queue.add(head.right);
                parents.put(head.right.val, head.val);
            }
            if (head.left != null) {
                queue.add(head.left);
                parents.put(head.left.val, head.val);
            }
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        if (!parents.containsKey(val)) { return path; }
        path.addFirst(val);
        while (parents.get(val) != null) {
            path.addFirst(parents.get(val));
            val = parents.get(val);
        }
        return path;
    }
}
