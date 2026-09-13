class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> pts1 = new ArrayList<>();
        List<int[]> pts2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) pts1.add(new int[]{i, j});
                if (img2[i][j] == 1) pts2.add(new int[]{i, j});
            }
        }

        Map<Long, Integer> count = new HashMap<>();
        int best = 0;

        for (int[] p1 : pts1) {
            for (int[] p2 : pts2) {
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                long key = ((long)(dx + n) << 32) | (dy + n);
                int c = count.getOrDefault(key, 0) + 1;
                count.put(key, c);
                best = Math.max(best, c);
            }
        }

        return best;
    }
}