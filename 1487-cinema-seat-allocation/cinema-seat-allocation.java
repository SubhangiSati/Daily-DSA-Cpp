class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();
        for (int[] seat : reservedSeats) {
            reserved.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
	        }
	    int ans = 0;
        for (Set<Integer> seats : reserved.values()) {
            boolean left = true, right = true, middle = true;
            for (int s : new int[]{2,3,4,5}) if (seats.contains(s)) left = false;
            for (int s : new int[]{6,7,8,9}) if (seats.contains(s)) right = false;
            for (int s : new int[]{4,5,6,7}) if (seats.contains(s)) middle = false;
            if (left && right)
                ans += 2;
            else if (left || right || middle)
            ans += 1;
            }
            ans += 2 * (n - reserved.size());
            return ans;
            }
            }