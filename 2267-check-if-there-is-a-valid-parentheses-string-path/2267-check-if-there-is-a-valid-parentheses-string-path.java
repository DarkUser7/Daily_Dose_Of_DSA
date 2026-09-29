class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')' ||
            grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBalance = (m + n) / 2;

        boolean[][][] dp =
            new boolean[m][n][maxBalance + 1];

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0;
                     balance <= maxBalance;
                     balance++) {

                    int prevBalance = balance - change;

                    if (prevBalance < 0 ||
                        prevBalance > maxBalance) {
                        continue;
                    }

                    if (i > 0 &&
                        dp[i - 1][j][prevBalance]) {
                        dp[i][j][balance] = true;
                    }

                    if (j > 0 &&
                        dp[i][j - 1][prevBalance]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}