import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by ending point
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r) {
                return Integer.compare(a.r, b.r);
            }
            return Integer.compare(a.idx, b.idx);
        });

        // previous[i] = last interval whose end < arr[i].start
        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {
            previous[i] = findPrevious(arr, i, arr[i].l);
        }

        State[][] dp = new State[n + 1][5];

        // Empty solution
        for (int k = 0; k <= 4; k++) {
            dp[0][k] = new State(
                0,
                new ArrayList<>()
            );
        }

        for (int i = 1; i <= n; i++) {

            Interval current = arr[i - 1];

            for (int k = 0; k <= 4; k++) {

                // Don't take current interval
                State skip = dp[i - 1][k];

                State best = skip;

                // Take current interval
                if (k > 0) {

                    int p = previous[i - 1];

                    State prev = dp[p + 1][k - 1];

                    List<Integer> newIndices =
                        new ArrayList<>(prev.indices);

                    newIndices.add(current.idx);

                    // VERY IMPORTANT:
                    // Lexicographical order is based on
                    // original interval indices.
                    Collections.sort(newIndices);

                    State take = new State(
                        prev.score + current.w,
                        newIndices
                    );

                    best = better(best, take);
                }

                dp[i][k] = best;
            }
        }

        // At most 4 intervals
        State answer = dp[n][0];

        for (int k = 1; k <= 4; k++) {
            answer = better(answer, dp[n][k]);
        }

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < answer.indices.size(); i++) {
            result[i] = answer.indices.get(i);
        }

        return result;
    }

    // Find last interval j < i such that:
    //
    // arr[j].r < start
    //
    // Strictly less because touching boundaries overlap.
    static int findPrevious(
        Interval[] arr,
        int i,
        int start
    ) {

        int left = 0;
        int right = i - 1;
        int ans = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].r < start) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    // Choose:
    // 1. Larger score
    // 2. If same score, lexicographically smaller indices
    static State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        if (lexicographicallySmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    static boolean lexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is a prefix of the other,
        // shorter one is lexicographically smaller.
        return a.size() < b.size();
    }
}