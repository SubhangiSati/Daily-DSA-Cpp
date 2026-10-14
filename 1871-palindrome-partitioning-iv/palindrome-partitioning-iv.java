class Solution {
    public boolean checkPartitioning(String s) {
        final int n = s.length();
        Boolean[][] memory = new Boolean[n][n];

        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j + 1 < n; ++j)
                if (isPalindrome(s, 0, i, memory) && 
                        isPalindrome(s, i + 1, j, memory) && 
                        isPalindrome(s, j + 1, n - 1, memory))
                    return true;

        return false;
    }
    private boolean isPalindrome(final String s, int i, int j, Boolean[][] memory) {
        if (i > j)
            return true;
        if (memory[i][j] != null)
            return memory[i][j];
        if (s.charAt(i) == s.charAt(j))
            return memory[i][j] = isPalindrome(s, i + 1, j - 1, memory);
        return memory[i][j] = false;
    }
}