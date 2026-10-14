class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int p = 0; p < 2; p++){
                int left = i;
                int right = i + p;
                while(left >= 0 && right<n && s.charAt(left)==s.charAt(right)){
                    isPalindrome[left][right]=true;
                    left--;
                    right++;
                }
            }
        }

        for(int i = 0; i < n-2; i++){
            for(int j = i + 1; j< n-1; j++){
                if(isPalindrome[0][i] && isPalindrome[i + 1][j] && isPalindrome[j + 1][n - 1])
                    return true;
            }
        }

        return false;
    }
}