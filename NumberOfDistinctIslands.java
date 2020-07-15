// 694.
// time - O(nm)
// space - O(nm) for call stack (all 1s) + hashset size
class Solution {
    public int numDistinctIslands(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        HashSet<String> islandIds = new HashSet<>(); //holds islandsIds of unique islands in grid
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1) //sink this island and return id as a string
                {
                    String path = dfs(grid, i, j, "X");
                    islandIds.add(path); //duplicates handled automatically in hashset
                }
            }
        }
        
        return islandIds.size(); //# of unique islands
    }
    
    private String dfs(int[][] grid, int row, int col, String path) {
        //base
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0)
        {
            //incoming cell not within bounds of grid or has water
            return "O";
        }
        //logic
        grid[row][col] = 0; //marking as visited
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        String[] nPaths = {"U", "D", "L", "R"};
        for(int i = 0; i < dirs.length; i++)
        {
            //find neighbor coordinates
            int nRow = row + dirs[i][0];
            int nCol = col + dirs[i][1];
            String nPath = nPaths[i];
            //recurse on neighbors directly and append the resultant string to path 
            path += dfs(grid, nRow, nCol, nPath);
        }
        return path;
    }
}
