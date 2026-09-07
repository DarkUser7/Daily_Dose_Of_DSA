class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1_000_000_007;

        int[] dp = new int[s.length() + 1];

        // dp[0] = empty subsequence
        dp[0] = 1;

        int[] last = new int[26];

        for (int i = 1; i <= s.length(); i++) {

            int ch = s.charAt(i - 1) - 'a';

            // Normally double the subsequences
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates caused by previous same character
            dp[i] = (dp[i] - last[ch] + MOD) % MOD;

            // Store current dp value
            last[ch] = dp[i - 1];
        }

        return (dp[s.length()] - 1 + MOD) % MOD;
    }
}