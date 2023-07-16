class Solution {

    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};

    public int numDistinctIslands(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }

        HashSet<String> islands = new HashSet<>();
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1)
                {
                    StringBuilder cells = new StringBuilder(); //track cells in this island
                    bfs(grid, i, j, i, j, cells);
                    islands.add(cells.toString()); //add to islands
                }
            }
        }

        return islands.size();
    }

    //dfs(i, j) returns the cells present in island which has the cell (i, j)
    //(srcRow, srcCol) - cell at which caller invokes the dfs
    //time - O(n^2) - all cells are 1
    //space - O(n^2) - all cells are 1
    private void dfs(int[][] grid, int row, int col, int srcRow, int srcCol, StringBuilder cells)
    {
        grid[row][col] = 0; //mark current cell as visited (turn to water to avoid usage of visited matrix)
        //compute row,col value w.r.to srcRow, srcCol
        int offsetRow = row - srcRow;
        int offsetCol = col - srcCol;
        //add offset cell to path
        String currentCell = Integer.toString(offsetRow) + " - " + Integer.toString(offsetCol) + ",";
        cells.append(currentCell);

        //recurse on unvisited neighbors
        for(int i = 0; i < 4; i++)
        {
            int nRow = row + deltaRow[i];
            int nCol = col + deltaCol[i];

            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] == 1)
            {
                dfs(grid, nRow, nCol, srcRow, srcCol, cells);
            }
        }

        return;
    }

    //time - O(n^2) - all cells are 1
    //space - O(n^2)
    private void bfs(int[][] grid, int row, int col, int srcRow, int srcCol, StringBuilder cells)
    {
        Queue<int[]> support = new LinkedList<>();
        support.offer(new int[]{row, col});

        grid[row][col] = 0; //mark current cell as visited (turn to water to avoid usage of visited matrix)
        
        while(!support.isEmpty())
        {
            int[] front = support.poll();

            //compute row,col value w.r.to srcRow, srcCol
            int offsetRow = front[0] - srcRow;
            int offsetCol = front[1] - srcCol;
            //add offset cell to path
            String currentCell = Integer.toString(offsetRow) + " - " + Integer.toString(offsetCol) + ",";
            cells.append(currentCell);

            //process unvisited neighbors
            for(int i = 0; i < 4; i++)
            {
                int nRow = front[0] + deltaRow[i];
                int nCol = front[1] + deltaCol[i];

                if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] == 1)
                {
                    grid[nRow][nCol] = 0;
                    support.offer(new int[]{nRow, nCol});
                }
            }
        }

        return;
    }
}
