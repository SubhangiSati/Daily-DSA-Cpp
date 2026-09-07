class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        long[] last = new long[27];

        long subSeqCount = 0;
        long count = 1;
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            int ch = (int) s.charAt(i) - 97;
            subSeqCount = (2 * count - last[ch] + mod) % mod;
            last[ch] = count;
            count = subSeqCount;
        }

        return (int) (subSeqCount - 1 + mod) % mod;
    }

}