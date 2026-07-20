public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        while (k > 0) {
            int[][] curr = new int[m][n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n - 1; c++) {
                    curr[r][c + 1] = grid[r][c];
                }
            }

            for (int r = 0; r < m; r++) {
                curr[(r + 1) % m][0] = grid[r][n - 1];
            }

            grid = curr;
            k--;
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(grid[r][i]);
            }
            result.add(temp);
        }
        return result;
    }
}