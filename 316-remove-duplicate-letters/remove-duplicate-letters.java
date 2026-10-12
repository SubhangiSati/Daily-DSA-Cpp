class Solution {
    public String removeDuplicateLetters(String s) {
        StringBuilder strbldr = new StringBuilder();
        int[] count = new int[128];
        boolean[] used = new boolean[128];
        for (final char c : s.toCharArray())
            ++count[c];
        for (final char c : s.toCharArray()) {
            --count[c];
            if (used[c])
                continue;
            while (strbldr.length() > 0 && last(strbldr) > c && count[last(strbldr)] > 0) {
                used[last(strbldr)] = false;
                strbldr.setLength(strbldr.length() - 1);
            }
            used[c] = true;
            strbldr.append(c);
        }
        return strbldr.toString();
    }

    private char last(StringBuilder strbldr) {
        return strbldr.charAt(strbldr.length() - 1);
    }
}
