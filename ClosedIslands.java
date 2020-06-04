// 1254.
class Solution {
    public int closedIsland(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0; //empty grid
        }
        //capturing border islands
        //capturing all 0s in the 1st and last row - after recursive call - border 0s are turned to 10s
        for(int i = 0; i < grid[0].length; i++)
        {
            if(grid[0][i] == 0)
            {
                captureBorder0s(grid, 0, i);
            }
            if(grid[grid.length - 1][i] == 0)
            {
                captureBorder0s(grid, grid.length - 1, i);
            }
        }
        //capturing all 0s in the 1st and last col - after recursive call - border 0s are turned to 10s
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i][0] == 0)
            {
                captureBorder0s(grid, i, 0);
            }
            if(grid[i][grid[0].length - 1] == 0)
            {
                captureBorder0s(grid, i, grid[0].length - 1);
            }
        }
        int result = 0;
        //except the perimeter - i.e 1st and last row and 1st and last col
        for(int i = 1; i < grid.length - 1; i++)
        {
            for(int j = 1; j < grid[0].length - 1; j++)
            {
                if(grid[i][j] == 0) //this cell is part of closed island - sink the whole island and add 1 to result
                {
                    sinkIsland(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }
    
    //time - O(n * m)
    //space - O(n * m) - max call satck size when all 0s in grid
    private void captureBorder0s(int[][] grid, int row, int col) {
        //base
        if(grid[row][col] != 0) //either the cell is visited(10) or is a water(1)
        {
            return;
        }
        //logic
        grid[row][col] = 10; //finally all cells in grid with val 10 are part of border 0 islands
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //4 neighbors
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                captureBorder0s(grid, nRow, nCol);
            }
        }
        return;
    }
    
    //time - O(n * m)
    //space - O(n * m) - max call satck size when all 0s in grid
    private void sinkIsland(int[][] grid, int row, int col) {
        //base
        if(grid[row][col] != 0) //either the cell is border island(10) or is a water(1)
        {
            return;
        }
        //logic
        grid[row][col] = 1; //marking as visited
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //4 neighbors
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                sinkIsland(grid, nRow, nCol);
            }
        }
        return;
    }
}
