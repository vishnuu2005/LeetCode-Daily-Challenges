class Solution {
    private long[] sum;
    private long[] sqSum;
    private long[] lazy;
    private final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        // Segment tree arrays (4 * n is standard size)
        sum = new long[4 * n + 1];
        sqSum = new long[4 * n + 1];
        lazy = new long[4 * n + 1];
        
        // Array to store the 1-based index of the last occurrence of each number
        int[] last = new int[100001];
        long totalResult = 0;
        
        for (int i = 0; i < n; i++) {
            // Find where the current element last appeared (0 if it hasn't appeared yet)
            int l = last[nums[i]]; 
            
            // We update the segment tree for ranges starting from last occurrence + 1 up to current index + 1
            // (Using 1-based indexing for the segment tree to match bounds naturally)
            update(1, 1, n, l + 1, i + 1, 1);
            
            // Add the sum of squares of all subarrays ending at current index `i`
            totalResult = (totalResult + sqSum[1]) % MOD;
            
            // Update the last seen position of the current element to 1-based index
            last[nums[i]] = i + 1; 
        }
        
        return (int) totalResult;
    }
    
    private void apply(int node, int l, int r, long v) {
        long len = r - l + 1;
        // (x + v)^2 = x^2 + 2*x*v + v^2
        // So new_sqSum = old_sqSum + 2 * v * old_sum + v^2 * length
        sqSum[node] = (sqSum[node] + 2 * v * sum[node] + v * v % MOD * len) % MOD;
        
        // new_sum = old_sum + v * length
        sum[node] = (sum[node] + v * len) % MOD;
        
        // Accumulate the lazy value
        lazy[node] = (lazy[node] + v) % MOD;
    }
    
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }
    
    private void pushUp(int node) {
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sqSum[node] = (sqSum[node * 2] + sqSum[node * 2 + 1]) % MOD;
    }
    
    private void update(int node, int l, int r, int ql, int qr, long v) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, v);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) {
            update(node * 2, l, mid, ql, qr, v);
        }
        if (qr > mid) {
            update(node * 2 + 1, mid + 1, r, ql, qr, v);
        }
        pushUp(node);
    }
}