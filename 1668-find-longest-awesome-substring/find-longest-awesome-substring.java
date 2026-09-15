class Solution {
    public int longestAwesome(String s) {
        int n = s.length();
        int[] dp = new int[1 << 10]; 
        for (int i = 0; i < (1 << 10); i++) 
            dp[i] = n;
        dp[0] = -1; 

        int ans = 1; 
        int currentMask = 0; 

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            currentMask ^= (1 << digit); 
            ans = Math.max(ans, i - dp[currentMask]);
            for (int k = 0; k < 10; k++) {
                int targetMask = currentMask ^ (1 << k);
                ans = Math.max(ans, i - dp[targetMask]);
            }
            if (dp[currentMask] == n) 
                dp[currentMask] = i;
        }
        return ans;
    }
}