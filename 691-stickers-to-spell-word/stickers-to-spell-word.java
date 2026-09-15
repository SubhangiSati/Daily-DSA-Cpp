class Solution {
    Integer[] dp;
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] freq = new int[n][26];
        for(int i=0;i<n;i++){
            for(char ch : stickers[i].toCharArray()){
                freq[i][ch-'a']++;
            }
        }

        dp = new Integer[1<<target.length()];
        int targetBitMask = (1<<target.length()) - 1;
        int ans =  helper(freq, target, targetBitMask, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int helper(int[][] freq, String target, int targetMask, int currMask){
        if(currMask == targetMask) return 0;
        if(dp[currMask] != null) return dp[currMask];

        int firstUncovered = -1;
        for (int i = 0; i < target.length(); i++) {
            if ((currMask & (1 << i)) == 0) {
                firstUncovered = i;
                break;
            }
        }

        int res = Integer.MAX_VALUE;
        char targetChar = target.charAt(firstUncovered);

        for(int i=0;i<freq.length;i++){
            if (freq[i][targetChar - 'a'] == 0) continue;
            
            int[] tempFreq = new int[26];
            int newMask = currMask;
            
            for(int b=0;b<target.length();b++){
                if((newMask & (1<<b)) == 0){
                    int t = target.charAt(b)-'a';
                    if(tempFreq[t] < freq[i][t]){
                        tempFreq[t]++;
                        newMask |= (1<<b);
                    }
                }
            }

            int subProblem = helper(freq, target, targetMask, newMask);
            if (subProblem != Integer.MAX_VALUE) 
                res = Math.min(res, 1 + subProblem);
        }

        return dp[currMask] = res;
    }
}