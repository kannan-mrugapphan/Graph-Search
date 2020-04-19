// 733.

//rum time - O(m * n) - all pixels have same color as image[sr][sc]
//space - O(m * n)
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //edge
        if(image == null || image.length == 0 || image[0].length == 0)
        {
            return image;
        }
        
        //edge
        if(image[sr][sc] == newColor)
        {
            return image;
        }
        
        dfs(image, sr, sc, newColor);
        
        return image;
    }
    
    private void dfs(int[][] image, int row, int col, int newColor) {
        
        //store the old colour and flip to new colour
        int oldColor = image[row][col];
        image[row][col] = newColor;
        
        //recurse on neighbours within bounds
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] direction : dirs)
        {
            int nRow = direction[0] + row;
            int nCol = direction[1] + col;
            
            if(nRow >= 0 && nRow < image.length && nCol >= 0 && nCol < image[0].length)
            {
                //neighbour pixel within bounds
                if(image[nRow][nCol] == oldColor)
                {
                    dfs(image, nRow, nCol, newColor);
                }
            }
        }
        
        return;
    }
}
