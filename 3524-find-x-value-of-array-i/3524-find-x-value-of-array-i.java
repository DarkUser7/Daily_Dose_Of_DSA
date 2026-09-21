class Solution {

    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at
        // previous index having product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray [num]
            int rem = num % k;
            newDp[rem]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] != 0) {

                    int newRem = (int) ((long) r * num % k);

                    newDp[newRem] += dp[r];
                }
            }

            // Add current subarrays to answer
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}