// 1559.
class Solution {

    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};

    public boolean containsCycle(char[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return false; //empty graph - no cycles
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                //unvisited cell
                if(!visited[i][j])
                {
                    if(isCyclicUsingBFS(grid, i, j, -1, -1, visited))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isCyclic(char[][] grid, int row, int col, int parentRow, int parentCol, boolean[][] visited)
    {

        visited[row][col]  = true; //marking cell as visited
        for(int i = 0; i < 4; i++)
        {
            //compute neighbor co-ordinates
            int nRow = row + deltaRow[i];
            int nCol = col + deltaCol[i];

            //neighbor within bounds and is valid with same value
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] == grid[row][col])
            {   
                //neighbor same as parent
                if(nRow == parentRow && nCol == parentCol)
                {
                    continue;
                }

                //neighbor already visited
                if(visited[nRow][nCol])
                {
                    return true;
                }
                //neighbor unvisited and results in cycle
                if(!visited[nRow][nCol] && isCyclic(grid, nRow, nCol, row, col, visited))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCyclicUsingBFS(char[][] grid, int row, int col, int parentRow, int parentCol, boolean[][] visited)
    {
        Queue<int[]> support = new LinkedList<>();
        support.offer(new int[]{row, col, parentRow, parentCol}); //insert src into queue
        visited[row][col] = true; //mark src as visited

        //as long as queue has more nodes to process
        while(!support.isEmpty())
        {
            int[] front = support.poll();
            int currentRow = front[0];
            int currentCol = front[1];
            int pRow = front[2];
            int pCol = front[3];

            for(int i = 0; i < 4; i++)
            {
                int nRow = currentRow + deltaRow[i];
                int nCol = currentCol + deltaCol[i];

                //if neighbor is valid and within bounds
                if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length && grid[nRow][nCol] == grid[row][col])
                {   
                    //neighbor same as parent
                    if(nRow == pRow && nCol == pCol)
                    {
                        continue;
                    }

                    //neighbor already visited - cyclic 
                    if(visited[nRow][nCol])
                    {
                        return true;
                    }
                    else
                    {
                        visited[nRow][nCol] = true; //mark neighbor as visited and insert to queue
                        support.offer(new int[]{nRow, nCol, currentRow, currentCol});
                    }
                }
            }
        }

        return false;
    }
}
