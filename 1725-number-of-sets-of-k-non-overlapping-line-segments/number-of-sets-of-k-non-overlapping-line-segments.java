class Solution {
    private static final long MOD = 1000000007L;
    private long quickPow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) != 0) result = (result * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return result;
    }

    public int numberOfSets(int n, int k) {
        int m = 2 * k;
        long num = 1, deno = 1;
        for (int i = 1; i <= m; i++) {
            num = (num * (n + k - i)) % MOD;
            deno = (deno * i) % MOD;
        }
        return (int) ((num * quickPow(deno, MOD - 2)) % MOD);
    }
}