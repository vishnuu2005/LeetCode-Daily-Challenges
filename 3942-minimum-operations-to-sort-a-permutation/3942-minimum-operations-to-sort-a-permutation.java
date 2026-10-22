class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        // Check if the array is a cyclic shift of an increasing sequence
        boolean isInc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(i + 1) % n] != (nums[i] + 1) % n) {
                isInc = false;
                break;
            }
        }
        
        // Check if the array is a cyclic shift of a decreasing sequence
        boolean isDec = true;
        for (int i = 0; i < n; i++) {
            if (nums[(i + 1) % n] != (nums[i] - 1 + n) % n) {
                isDec = false;
                break;
            }
        }
        
        int minOps = Integer.MAX_VALUE;
        
        if (isInc) {
            if (zeroIdx == 0) {
                minOps = Math.min(minOps, 0);
            } else {
                // Either left rotate `zeroIdx` times OR reverse, rotate, and reverse (right rotate equivalent)
                minOps = Math.min(minOps, Math.min(zeroIdx, n - zeroIdx + 2));
            }
        }
        
        if (isDec) {
            // Either reverse first then rotate OR rotate first then reverse
            minOps = Math.min(minOps, Math.min(zeroIdx + 2, n - zeroIdx));
        }
        
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}