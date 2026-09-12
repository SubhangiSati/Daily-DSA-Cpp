class Solution {
    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++)
            dp[i][i] = 1;   

        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;
                if(chs[i] == chs[j]){
                    int low = i + 1;
                    int high = j - 1;

                    while(low <= high && chs[low] != chs[j])
                        low++;
                    while(low <= high && chs[high] != chs[j])
                        high--;
                    if(low > high)
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;  
                    else if(low == high)
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;                  
                    else
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]; 
                }
                else
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }
}