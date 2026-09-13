class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesImg1 = new ArrayList<>();
        List<int[]> onesImg2 = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (img1[i][j] == 1)
                    onesImg1.add(new int[] { i, j });
                if (img2[i][j] == 1)
                    onesImg2.add(new int[] { i, j });
            }
        }
        Map<String, Integer> count = new HashMap<>();
        int res = 0;
        for (int[] p1 : onesImg1) {
            for (int[] p2 : onesImg2) {
                int dx = p2[0] - p1[0], dy = p2[1] - p1[1];
                String key = dx + "," + dy;
                count.put(key, count.getOrDefault(key, 0) + 1);
                res = Math.max(res, count.get(key));
            }
        }
        return res;
    }
}