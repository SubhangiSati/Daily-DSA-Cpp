class Solution {
    public int reverseDegree(String s) {
        HashMap<Character, Integer> mp = new HashMap<>();
        int l = 26;
        for (int i = 1; i <= 26; i++) {
            char z = (char) (96 + i);
            if (z == 'z')
                mp.put(z, 1);
            else
                mp.put(z, l);
            l--;
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int ind = mp.get(s.charAt(i));
            sum += ind * (i + 1);
        }
        return sum;
    }
}