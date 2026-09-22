class Solution {
    static class Node {
        int[] remain;
        int prod = 1;

        Node(int k) {
            remain = new int[k];
        }
    }

    static class SegmentTree {
        private final int n;
        private final int k;
        private final Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];

            build(nums, 0, 0, n - 1);
        }

        void update(int i, int val) {
            update(0, 0, n - 1, i, val);
        }

        Node query(int i, int j) {
            return query(0, 0, n - 1, i, j);
        }

        private void build(int[] nums, int cur, int left, int right) {
            if (left == right) {
                tree[cur] = new Node(k);
                tree[cur].remain[nums[left]] = 1;
                tree[cur].prod = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(nums, 2 * cur + 1, left, mid);
            build(nums, 2 * cur + 2, mid + 1, right);
            tree[cur] = merge(
                    tree[2 * cur + 1],
                    tree[2 * cur + 2]);
        }

        private void update(int treeIndex, int lo, int hi,
                int i, int val) {
            if (lo == hi) {
                tree[treeIndex] = new Node(k);
                tree[treeIndex].remain[val] = 1;
                tree[treeIndex].prod = val;
                return;
            }
            int mid = (lo + hi) / 2;

            if (i <= mid)
                update(2 * treeIndex + 1, lo, mid, i, val);
            else
                update(2 * treeIndex + 2, mid + 1, hi, i, val);

            tree[treeIndex] = merge(
                    tree[2 * treeIndex + 1],
                    tree[2 * treeIndex + 2]);
        }

        private Node query(int treeIndex, int lo, int hi,
                int i, int j) {
            if (i <= lo && hi <= j)
                return tree[treeIndex];
            if (j < lo || hi < i)
                return new Node(k);
            int mid = (lo + hi) / 2;
            return merge(
                    query(2 * treeIndex + 1, lo, mid, i, j),
                    query(2 * treeIndex + 2, mid + 1, hi, i, j));
        }

        private Node merge(Node left, Node right) {
            Node node = new Node(k);
            node.prod = (left.prod * right.prod) % k;
            for (int i = 0; i < k; i++)
                node.remain[i] = left.remain[i];
            for (int i = 0; i < k; i++) {
                int index = (i * left.prod) % k;
                node.remain[index] += right.remain[i];
            }
            return node;
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        for (int i = 0; i < nums.length; i++)
            nums[i] %= k;
        for (int[] query : queries)
            query[1] %= k;
        int n = nums.length;
        int[] ans = new int[queries.length];
        SegmentTree tree = new SegmentTree(nums, k);
        for (int q = 0; q < queries.length; q++) {
            int[] query = queries[q];
            int index = query[0];
            int value = query[1];
            int start = query[2];
            int x = query[3];
            tree.update(index, value);
            ans[q] = tree.query(start, n - 1).remain[x];
        }
        return ans;
    }
}
