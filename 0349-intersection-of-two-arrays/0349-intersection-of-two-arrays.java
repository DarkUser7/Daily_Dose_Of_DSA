
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length];
        int count = 0;

        for (int i = 0; i < nums1.length; i++) {
            boolean found = false;

            // Check whether nums1[i] exists in nums2
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    found = true;
                    break;
                }
            }
            if (found) {
                boolean duplicate = false;

                for (int k = 0; k < count; k++) {
                    if (temp[k] == nums1[i]) {
                        duplicate = true;
                        break;
                    }
                }

                if (!duplicate) {
                    temp[count] = nums1[i];
                    count++;
                }
            }
        }

        return java.util.Arrays.copyOf(temp, count);
    }
}
