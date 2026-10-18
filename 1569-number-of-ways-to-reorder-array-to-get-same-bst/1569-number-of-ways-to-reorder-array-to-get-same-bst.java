import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1000000007;
    private long[][] combinations;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute combinations using Pascal's triangle
        combinations = new long[n][n];
        for (int i = 0; i < n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                combinations[i][j] = (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 because the original array ordering is included in the total combinations
        return (int) ((dfs(list) - 1) % MOD);
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Separate elements into left and right subtrees based on the root
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        // Recursively find the number of ways for left and right subtrees
        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int leftSize = left.size();
        int rightSize = right.size();
        int totalSize = leftSize + rightSize;

        // ways = (combinations(total, left) * leftWays * rightWays) % MOD
        long ways = combinations[totalSize][leftSize];
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}