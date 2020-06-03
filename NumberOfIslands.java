// 200.
// time - O(n * m)
// space - O(n * m) -> max call stack size when the grid has all 1s
class Solution {
    public int numIslands(char[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0; //# of islands is 0 in case of an empty grid
        }
        int result = 0; //return vvalue
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == '1')
                {
                    //when a 1 is seen, all adjacent land neighbors are sinked together in 1 dfs call
                    dfs(grid, i, j);
                    result++; //increase result by 1 
                }
            }
        }
        return result;
    }
    
    private void dfs(char[][] grid, int row, int col)
    {
        //base
        if(grid[row][col] == '0')
        {
            return; //return as cell as visited
        }
        //logic
        grid[row][col] = '0'; //marking incoming cell as visited
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //4 possible neighbors
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0]; //nRow, nCol -> ppossible cells where we can recurse on
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                dfs(grid, nRow, nCol); //recurse on neighbor
            }
        }
        return;
    }
}
