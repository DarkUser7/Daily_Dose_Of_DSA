class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    // Create node for one element
    Node makeNode(int value) {
        Node node = new Node(k);

        int p = value % k;
        node.prod = p;

        // The only prefix is the element itself
        node.cnt[p] = 1;

        return node;
    }

    // Merge two nodes
    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);

        // Product of the complete segment
        res.prod = (int) ((long) left.prod * right.prod % k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }

        // Prefixes that start in left and continue into right
        for (int r = 0; r < k; r++) {

            int count = right.cnt[r];

            if (count == 0) continue;

            int newRemainder =
                    (int) ((long) left.prod * r % k);

            res.cnt[newRemainder] += count;
        }

        return res;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = makeNode(nums[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = makeNode(value);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (qr < l || r < ql) {
            return null;
        }

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.n = nums.length;
        this.k = k;
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            answer[i] = res.cnt[x];
        }

        return answer;
    }
}