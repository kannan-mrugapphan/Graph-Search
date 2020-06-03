// 695.
// time - O(m * n)
// space - O(m * n) - max call stack size when all cells have 1s
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0; //max area of island in an empty grid is 0
        }
        int maxArea = 0; //initial result
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1) // 1 is found i.e an island is found - find area of this island
                {
                    int currentArea = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, currentArea); //update max if needed
                }
            }
        }
        return maxArea;
    }
    
    private int dfs(int[][] grid, int row, int col) {
        //base
        if(grid[row][col] == 0)
        {
            return 0; //returning as cell is visited
        }
        //logic
        int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        grid[row][col] = 0;  //marking as visited to avoid reprocessing
        int currentArea = 1; //initially area is 1 as the current row, col cell has 1
        for(int[] direction : dirs)
        {
            int nRow = row + direction[0]; //nRow, nCol is the next cell that could possibly be visited
            int nCol = col + direction[1];
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                currentArea += dfs(grid, nRow, nCol); //recurse on neighbor to add to currentArea
            }
        }
        return currentArea;
    }
}
