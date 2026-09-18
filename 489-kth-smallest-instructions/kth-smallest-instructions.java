class Solution {
    public String kthSmallestPath(int[] destination, int k) {
        StringBuilder sb = new StringBuilder();
        int v = destination[0];
        int h = destination[1];
        final int totalSteps = v + h;
        final int[][] combination = getCombination(totalSteps - 1, v);

        for (int i = 0; i < totalSteps; ++i) {
            final int currRank = combination[h + v - 1][v];
            if (currRank >= k) { 
                sb.append('H');
                --h;
            } else { 
                k -= currRank;
                sb.append('V');
                --v;
            }
        }

        return sb.toString();
    }

    private int[][] getCombination(int n, int k) {
        int[][] combination = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i)
            combination[i][0] = 1;
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= k; ++j)
                combination[i][j] = combination[i - 1][j] + combination[i - 1][j - 1];
        return combination;
    }
}
