//BFS approach as oranges rot in layes(4 adjacent neighbours)

//time - O(m * n) - nested for loops
//space - O(m * n) - max queue size when all oranges are rotten initially

// 994.
class Solution {
    public int orangesRotting(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0;
        }
        
        Queue<int[]> support = new LinkedList<>();
        int fresh = 0;
        
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 2)
                {
                    support.offer(new int[]{i, j}); //process all rotten oranges initially
                }
                if(grid[i][j] == 1)
                {
                    fresh++; //increment fresh orange count
                }
            }
        }
        
        if(fresh == 0)
        {
            return 0;
        }
        
        int layers = 0;
        
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            for(int i = 0; i < layerSize; i++)
            {
                int[] current = support.poll();
                int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                for(int[] direction : dirs)
                {
                    int row = direction[0] + current[0];
                    int col = direction[1] + current[1];

                    if(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length)
                    {
                        //neighbour within bounds
                        if(grid[row][col] == 1)
                        {
                            //make it rotten to avoid visisting same cell again - can also use visited 
                            grid[row][col] = 2;
                            //process the rotten orange
                            support.offer(new int[]{row, col});
                            //reduce fresh count
                            fresh--;
                        }
                    }
                }
            }
            layers++;
        }
        
        if(fresh > 0)
        {
            return -1; //more fresh oranges are still present
        }
        return layers - 1; //numners of layers = layers - 1
    }
}
