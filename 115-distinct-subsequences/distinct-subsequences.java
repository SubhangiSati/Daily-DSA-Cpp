class Solution {
    public int numDistinct(String y, String x) {
        int s = x.length();
        int t = y.length();
        Integer [][] dp = new Integer [s][t];

        return solve(x,y,s-1,t-1,dp);

    }
    private int solve(String x, String y, int i, int j, Integer[][]dp){
        if(i<0) return 1;
        if(j<0) return 0;
        if(j<i) return 0;
        if(dp [i][j] != null) return dp [i][j];
        if(x.charAt(i) == y.charAt(j)){
            return dp [i][j] = solve(x,y,i-1,j-1,dp) + solve(x,y,i,j-1,dp);
        }
        return dp[i][j] = solve(x,y,i,j-1,dp);
    }
}