/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int result = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return result;
    }

    // Returns {sum, count}
    private int[] dfs(TreeNode node) {

        // Empty subtree
        if (node == null) {
            return new int[]{0, 0};
        }

        // Get information from left subtree
        int[] left = dfs(node.left);

        // Get information from right subtree
        int[] right = dfs(node.right);

        // Calculate current subtree
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        // Calculate average
        int average = sum / count;

        // Check condition
        if (node.val == average) {
            result++;
        }

        return new int[]{sum, count};
    }
}