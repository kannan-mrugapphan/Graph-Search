// 1219.
// time - O(2^mn) -> each cell in matrix can be part of the optimal path or not
// space - O(mn) -> visited array and call stack
class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    
    public int getMaximumGold(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int maxGold = 0; //return value
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                //find path with i,j as start cell
                if(grid[i][j] > 0)
                {
                    maxGold = Math.max(maxGold, dfs(grid, i, j, new boolean[grid.length][grid[0].length]));
                }
            }
        }
        
        return maxGold;
    }
    
    private int dfs(int[][] grid, int r, int c, boolean[][] visited) {
        //base
        if(visited[r][c] || grid[r][c] == 0)
        {
            return 0;
        }
        //logic
        int currentPathGold = 0; //start path with r, c as start cell -> set to 0 as we need max of 4 neighbors
        visited[r][c] = true; //mark as visited
        for(int[] dir : dirs)
        {
            int nr = r + dir[0]; //find neighbor co-ordinates
            int nc = c + dir[1];
            //neighbor within bounds
            if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length)
            {
                //try adding neighbor to currentPath
                currentPathGold = Math.max(currentPathGold, dfs(grid, nr, nc, visited));
            }
        }
        visited[r][c] = false; //mark r,c as unvisited so it could be used by other paths where r, c is not start cell
        return currentPathGold + grid[r][c]; //add cell value to value from neighbors
    }
}
