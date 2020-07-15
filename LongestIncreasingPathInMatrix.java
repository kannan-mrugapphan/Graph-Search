// 329.
// time - O(nm)
// space - O(nm) - for dp matrix
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        //edge
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }
        
        int[][] result = new int[matrix.length][matrix[0].length]; //each cell memoizes the length of longest increasing path in matrix starting from this cell (at the end dfs each cell in result[][] should have atleast 1)
        int maxPathLength = 0; //return value
        
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                if(result[i][j] == 0)
                {
                    //longest increasing path from this cell is still uncalculated
                    int pathLength = dfs(matrix, result, i, j); //compute that value and update max if needed
                    maxPathLength = Math.max(maxPathLength, pathLength);
                }
            }
        }
        
        return maxPathLength;
    }
    
    //time - O(nm)
    //space - O(nm)
    private int dfs(int[][] matrix, int[][] result, int row, int col) {
        //base
        if(result[row][col] != 0)
        {
            //already calculated
            return result[row][col];
        }
        //logic
        int length = 1;
        int[][] dirs = {{0 ,1}, {-1, 0}, {0, -1}, {1, 0}};
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0]; //find neighbor co-ordinates
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < matrix.length && nCol >= 0 && nCol < matrix[0].length)
            {
                //neighbor within bounds
                if(matrix[row][col] < matrix[nRow][nCol])
                {
                    //neighbour has higher value
                    int nLength = 1 + dfs(matrix, result, nRow, nCol); //path length in this path is 1 + dfs(neighbor)
                    length = Math.max(length, nLength); //update global length if needed
                }
            }
        }
        result[row][col] = length; //updating the memo table
        return length;
    }
}
