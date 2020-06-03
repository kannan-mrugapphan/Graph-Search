// 733.
//time - O(m * n)
//space - O(m * n)
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //edge
        if(image == null || image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor)
        {
            return image; //return same image in case of empty image or src color is same as new color
        }
        dfs(image, sr, sc, newColor, image[sr][sc]); //call recrsive helper to flood fill image
        return image;
    }
    
    //time - O(n * m)
    //space - O(n * m) - max call stack size when all pixels have same src color
    private void dfs(int[][] image, int sr, int sc, int newColor, int oldColor) {
        //base
        if(image[sr][sc] != oldColor) //return if neighbor pixel has a different color than src pixel
        {
            return;
        }
        //logic
        image[sr][sc] = newColor; //update color of src pixel
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //4 possible neighbors
        for(int[] dir : dirs)
        {
            int nRow = sr + dir[0];
            int nCol = sc + dir[1];
            if(nRow >= 0 && nRow < image.length && nCol >= 0 && nCol < image[0].length)
            {
                //neighbor within bounds
                dfs(image, nRow, nCol, newColor, oldColor); //recurse on neighbors
            }
        }
        return;
    }
}
