// 200.

//time - O(m * n)
//space -O(m * n) - max when all 1s - call stack size
class Solution {
    public int numIslands(char[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        int result = 0;
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == '1')
                {
                    //sink all the 1s and increase island count
                    result += dfs(grid, i, j);
                }
            }
        }
        
        return result;
    }
    
    private int dfs(char[][] grid, int row, int col) {
        grid[row][col] = '0'; //done to mark cell as visited - can use a visited matrix also
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        
        for(int[] direction : dirs) 
        {
            int nRow = direction[0] + row;
            int nCol = direction[1] + col;
            
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbour within bounds
                if(grid[nRow][nCol] == '1')
                {
                    //sink the neighbour
                    dfs(grid, nRow, nCol);
                }
            }
        }
        
        return 1; //1 island obtained by sinking
    }
}
