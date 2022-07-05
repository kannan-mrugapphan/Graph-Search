// 2328.
// time - O(nm) -> each cell is touched once and future calls are retreived from cache
// space - O(mn) -> cache and stack size
class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int mod = 1000000007;
    
    public int countPaths(int[][] grid) {
        long result = 0L; //return value
        int[][] dp = new int[grid.length][grid[0].length]; //cache
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                result += dfs(grid, i, j, dp); 
            }
        }
        
        return (int)(result % mod);
    }
    
    private int dfs(int[][] grid, int r, int c, int[][] dp) {
        //base
        if(dp[r][c] != 0)
        {
            return dp[r][c]; //return from cache
        }
        //logic
        int numberOfPaths = 1; //the cell r,c is one increasing path by itslef
        //marking as visited isn't needed because we go to a neighbor with a larger value and there is no way to reach the current cell as it has a smmaler value than its neighbor (if recursed)
        for(int[] dir : dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] > grid[r][c])
            {
                //neighbor within bounds and has a larger value than current cell
                numberOfPaths += dfs(grid, nr, nc, dp); //update result with neighbor contribution
            }
        }
        
        dp[r][c] = numberOfPaths % mod; //update cache
        return numberOfPaths % mod;
    }
}
