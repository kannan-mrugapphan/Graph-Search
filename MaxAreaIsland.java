// 695.
// time - O(n * m)
// space - O(n * m) - max call stack size when all 1s in grid
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0; //max area in case of an empty grid is 0
        }
        int maxArea = 0; //return value
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                //count the # of 1s in this island, update result if needed
                int currentArea = dfs(grid, i, j);
                maxArea = Math.max(maxArea, currentArea);
            }
        }
        return maxArea;
    }
    
    private int dfs(int[][] grid, int row, int col) {
        //base
        if(grid[row][col] == 0)
        {
            return 0; //no island here - so area is 0
        }
        //logic
        grid[row][col] = 0; //marking as visited as to avoid reprocessing
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //4 possible neighbors
        int currentArea = 1; //initially current area is 1 account the initail incoming 1
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0]; //next neighbor co ordinates
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                currentArea += dfs(grid, nRow, nCol); 
            }
        }
        return currentArea;
    }
}
