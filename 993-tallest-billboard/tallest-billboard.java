class Solution {
    public int tallestBillboard(int[] rods) {
        int m = 1;
        for (int n : rods) m += n;
        int n = rods.length;
        int[] dp1 = new int[m];
        int[] dp2 = new int[m];
        for (int i = 1; i < m; i++) dp2[i] = Integer.MIN_VALUE;
        dp2[rods[0]] = 0;
        for (int i = 1; i < n; i++) {
            int[] temp = dp1;
            dp1 = dp2;
            dp2 = temp;
            int rod = rods[i];
            for (int j = 0; j < m; j++) 
                dp2[j] = dp1[j];
            for (int j = 0; j < m; j++) {
                if (dp1[j] == Integer.MIN_VALUE) continue;
                int newDiff = j + rod;
                int newShortest = dp1[j];
                dp2[newDiff] = Math.max(dp2[newDiff], newShortest);
                newDiff = j - rod;
                newShortest = dp1[j] + rod;
                if (newDiff < 0) {
                    newShortest += newDiff;
                    newDiff *= -1;
                }
                dp2[newDiff] = Math.max(dp2[newDiff], newShortest);
            }
        }
        return dp2[0];
    }
}