import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.List;

public class LowestCommonAncestorDFS {
    public static Node lowestCommonAncestor (Node tree, int val1, int val2) {

        List<Node> path1 = pathFromRoot(tree, val1, new LinkedList<Node>());
        List<Node> path2 = pathFromRoot(tree, val2, new LinkedList<Node>());

        return endOfCommonPrefix(path1, path2);
    }

    private static Node endOfCommonPrefix (final List<Node> path1, final List<Node> path2) {
        Node lca = null;
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) == path2.get(i)) { lca = path1.get(i); }
        }
        return lca;
    }

    static List<Node> pathFromRoot (Node tree, int val, List<Node> path) {
        if (tree == null) { return ImmutableList.of(); }
        ImmutableList<Node> extendedPath = ImmutableList.<Node>builder().addAll(path).add(tree).build();
        if (tree.val == val) { return extendedPath; }

        List<Node> leftPath = pathFromRoot(tree.left, val, extendedPath);
        List<Node> rightPath = pathFromRoot(tree.right, val, extendedPath);

        return (!leftPath.isEmpty()) ? leftPath : rightPath;
    }
}
