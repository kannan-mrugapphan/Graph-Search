//542.

//time - O(m * n)
//space - O(m * n) - max when all 0s in matrix

class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        //edge
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0)
        {
            return matrix;
        }
        
        Queue<int[]> support = new LinkedList<>();
        
        //the cells with 0s will have 0s because the shortest 0 to them are themselves
        //replace all 1s with infinities to find shotest distance later
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                if(matrix[i][j] == 0)
                {
                    //process all 0s initially
                    support.offer(new int[]{i, j});
                }
                if(matrix[i][j] == 1)
                {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        while(!support.isEmpty())
        {
            //poll the front and try to relax edge of its 4 neighbours
            //if relaxtion occurs for any neighbour, process the neighbour in next layer
            int[] current = support.poll();
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for(int[] direction : dirs)
            {
                int row = current[0] + direction[0];
                int col = current[1] + direction[1];
                if(row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length)
                {
                    //neighbour cell within bounds
                    if(matrix[row][col] > 1 + matrix[current[0]][current[1]])
                    {
                        matrix[row][col] = 1 + matrix[current[0]][current[1]]; //relax the edge
                        support.offer(new int[]{row, col}); //process the neighbour in next levl
                    }
                }
            }
        }
        
        return matrix;
    }
}
