class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] r = new boolean[n][n];
        for (var row : r) 
            Arrays.fill(row, true);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) 
                r[i][j] = s.charAt(i) == s.charAt(j) && r[i + 1][j - 1];
        }
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = f[i + 1];
            for (int j = i + k - 1; j < n; ++j) {
                if (r[i][j]) 
                    f[i] = Math.max(f[i], 1 + f[j + 1]);
            }
        }
        return f[0];
    }
}