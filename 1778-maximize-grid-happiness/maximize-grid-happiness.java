class Solution {
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        final int twoToThePowerOfN = (int) Math.pow(2, n);
        int[][][][][] mem = new int[m * n][twoToThePowerOfN][twoToThePowerOfN][introvertsCount + 1][extrovertsCount
                + 1];
        return getMaxGridHappiness(m, n, 0, 0, 0, introvertsCount, extrovertsCount, mem);
    }

    private int getPlacementCost(int n, int i, int j, int inMask, int exMask, int diff) {
        int cost = 0;
        if (i > 0) {
            if (((1 << (n - 1)) & inMask) > 0)
                cost += diff - 30;
            if (((1 << (n - 1)) & exMask) > 0)
                cost += diff + 20;
        }
        if (j > 0) {
            if ((1 & inMask) > 0)
                cost += diff - 30;
            if ((1 & exMask) > 0)
                cost += diff + 20;
        }
        return cost;
    }

    private int getMaxGridHappiness(int m, int n, int pos, int inMask, int exMask, int inCount,
            int exCount, int[][][][][] mem) {
        final int i = pos / n;
        final int j = pos % n;
        if (i == m)
            return 0;
        if (mem[pos][inMask][exMask][inCount][exCount] > 0)
            return mem[pos][inMask][exMask][inCount][exCount];

        final int shiftedInMask = (inMask << 1) & ((1 << n) - 1);
        final int shiftedExMask = (exMask << 1) & ((1 << n) - 1);

        final int skip = getMaxGridHappiness(m, n, pos + 1, shiftedInMask, shiftedExMask, inCount, exCount, mem);
        final int placeIntrovert = inCount > 0 ? 120 + getPlacementCost(n, i, j, inMask, exMask, -30) +
                getMaxGridHappiness(m, n, pos + 1, shiftedInMask | 1, shiftedExMask,
                        inCount - 1, exCount, mem)
                : Integer.MIN_VALUE;
        final int placeExtrovert = exCount > 0 ? 40 + getPlacementCost(n, i, j, inMask, exMask, 20) +
                getMaxGridHappiness(m, n, pos + 1, shiftedInMask, shiftedExMask | 1,
                        inCount, exCount - 1, mem)
                : Integer.MIN_VALUE;
        return mem[pos][inMask][exMask][inCount][exCount] = Math.max(skip, Math.max(placeIntrovert, placeExtrovert));
    }
}