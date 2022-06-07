# Tree DFS

## Example Problems

- Same Tree
- Path Sum
- Diameter of Binary Tree

## Template

```java
dfs(node) {
    // 1. Check stopping conditions.
    if node is null return

    // 2. Call DFS on child nodes.
    dfs(node.left)
    dfs(node.right)

    // 3. Return appropriate value.
    return value
}
```

## Same Tree

```java
class Solution {
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 1. Check stopping conditions.
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        
        // 2. Call DFS on child nodes.
        boolean l = isSameTree(p.left, q.left);
        boolean r = isSameTree(p.right, q.right);

        // 3. Return value.
        return l && r;
    }
}
```

## Path Sum

```java
class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 1. Check stopping conditions.
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        
        // 2. Call DFS on child nodes.
        boolean l = hasPathSum(root.left, targetSum - root.val);
        boolean r = hasPathSum(root.right, targetSum - root.val);

        // 3. Return value.
        return l || r;
    }
}
```

## Diameter of Binary Tree

```java
class Solution {

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // 1. Check stopping conditions.
        if (root == null)
            return 0;

        // 2. Call DFS on child nodes.
        int l = dfs(root.left);
        int r = dfs(root.right);

        res = Math.max(res, l + r);

        // 3. Return value.
        return Math.max(l, r) + 1;
    }
}
```