class Solution {
    private int m, n;
    private int totalCells;
    private int maxMask;
    private int topPower;
    private int[][][][] memo;
    private final int[][] pairScore = {
        {0, 0, 0},
        {0, -60, -10},
        {0, -10, 40}
    };
    public int getMaxGridHappiness(
        int m, int n, int introvertsCount, int extrovertsCount) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        this.m = m;
        this.n = n;
        this.totalCells = m * n;
        maxMask = 1;
        for (int i = 0; i < n; i++) 
            maxMask *= 3;
        topPower = 1;
        for (int i = 1; i < n; i++) 
            topPower *= 3;
        memo = new int[totalCells][introvertsCount + 1][extrovertsCount + 1][maxMask];
        for (int pos = 0; pos < totalCells; pos++) {
            for (int intro = 0; intro <= introvertsCount; intro++) {
                for (int extro = 0; extro <= extrovertsCount; extro++) 
                    Arrays.fill(memo[pos][intro][extro], -1);
            }
        }
        return dfs(0, introvertsCount, extrovertsCount, 0);
    }
    private int dfs(int pos, int introLeft, int extroLeft, int mask) {
        if (pos == totalCells) return 0;
        if (memo[pos][introLeft][extroLeft][mask] != -1) return memo[pos][introLeft][extroLeft][mask];
        int col = pos % n;
        int top = mask / topPower;
        int left = (col == 0) ? 0 : mask % 3;
        int shiftedMask = (mask % topPower) * 3;
        int best = dfs(pos + 1, introLeft, extroLeft, shiftedMask);
        if (introLeft > 0) {
            int gain = 120 + pairScore[1][top] + pairScore[1][left];
            best = Math.max(best, gain + dfs(pos + 1, introLeft - 1, extroLeft, shiftedMask + 1)
            );
        }
        if (extroLeft > 0) {
            int gain = 40 + pairScore[2][top] + pairScore[2][left];
            best = Math.max( best, gain + dfs(pos + 1, introLeft, extroLeft - 1, shiftedMask + 2)
            );
        }
        memo[pos][introLeft][extroLeft][mask] = best;
        return best;
    }
}