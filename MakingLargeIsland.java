// 827.
// brute force - find max area island - time - n * m - for each zero change to 1 - find max area again - total time - (n*m) ^ 2
//time - O(n * m)
//space - O(n * m) - max call stack size when all 1s in matrix apart from hash map
class Solution {
    public int largestIsland(int[][] grid) {
        //edge
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        {
            return 0; //empty grid;
        }
        HashMap<Integer, Integer> support = new HashMap<>(); //maps island id to area of that island
        int islandId = 2; //starting from 2 coz 0 is water and 1 is land
        int maxArea = 0; //return value
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1) //found an island
                {
                    //find area of this island and updat map with id and area
                    int currentArea = dfs(grid, i, j, islandId); //update area in global result if needed
                    support.put(islandId, currentArea); //update map
                    islandId++; //increase id
                    maxArea = Math.max(currentArea, maxArea);
                }
            }
        }
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 0)
                {
                    int currentArea = 1; //initailly after changing this 0 to 1 area is 1
                    //change this 0 to 1 and find max area
                    HashSet<Integer> islandIds = new HashSet<>(); //possible islands neighboring current 0
                    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
                    for(int[] dir : dirs) //4 neighbors
                    {
                        //find neighbor co ordinates
                        int nRow = dir[0] + i;
                        int nCol = dir[1] + j;
                        if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
                        {
                            //neigbour within bounds
                            if(support.containsKey(grid[nRow][nCol]))
                            {
                                //add to set if neighbor is not a 0
                                islandIds.add(grid[nRow][nCol]);
                            }
                        }
                    }
                    //for each island id found, add its area to current arae and update max area if needed
                    for(Integer id : islandIds)
                    {
                        currentArea += support.get(id);
                    }
                    maxArea = Math.max(currentArea, maxArea);
                }
            }
        }
        return maxArea;
    }
    
    //normal method to find max arae
    //time - O(n * m)
    //space - O(n * m) - max call stack size when all 1s in matrix
    private int dfs(int[][] grid, int row, int col, int islandId) {
        //base
        if(grid[row][col] != 1)
        {
            //either in a visited cell or in water
            return 0;
        }
        //logic
        grid[row][col] = islandId; //marking cell as visited by changing val to island id
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int currentArea = 1; //initialliy area is 1 coz of the current 1 in row, col in parent method
        for(int[] dir : dirs)
        {
            //find neighbor co ordinates
            int nRow = dir[0] + row;
            int nCol = dir[1] + col;
            if(nRow >= 0 && nRow < grid.length && nCol >= 0 && nCol < grid[0].length)
            {
                //neighbor within bounds
                currentArea += dfs(grid, nRow, nCol, islandId);
            }
        }
        return currentArea;
    }
}
