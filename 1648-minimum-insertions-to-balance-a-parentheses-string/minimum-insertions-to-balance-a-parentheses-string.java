class Solution {
    public int minInsertions(String s) {
        int neededRight = 0;
        int missingLeft = 0;
        int missingRight = 0;

        for (final char c : s.toCharArray())
            if (c == '(') {
                if (neededRight % 2 == 1) {
                    ++missingRight;
                    --neededRight;
                }
                neededRight += 2;
            } else if (--neededRight < 0) {
                ++missingLeft;
                neededRight += 2;
            }
        return neededRight + missingLeft + missingRight;
    }
}
