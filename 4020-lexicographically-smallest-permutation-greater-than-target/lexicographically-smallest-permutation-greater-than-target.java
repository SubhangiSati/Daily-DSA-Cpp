class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] sCount = new int[26];
        for(int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }
        int sIndex = 0;
        while(sIndex < target.length() && sCount[target.charAt(sIndex) - 'a'] > 0) {
            sCount[target.charAt(sIndex) - 'a'] -= 1;
            sIndex++;
        }
        for(int i = sIndex; i >= 0; i--) {
            if(i < sIndex) {
                sCount[target.charAt(i) - 'a']++;
            }
            if(i < s.length()) {
                int targetChar = target.charAt(i) - 'a';
                for(int c = targetChar + 1; c < 26; c++) {
                    if(sCount[c] > 0) {
                        StringBuilder result = new StringBuilder();
                        result.append(target.substring(0, i)); 
                        result.append((char)(c + 'a'));
                        sCount[c]--;
                        for(int j = 0; j < 26; j++) {
                            while(sCount[j] > 0) {
                                result.append((char)(j + 'a'));
                                sCount[j]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }
        }
        return "";
    }
}