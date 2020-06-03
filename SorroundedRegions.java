// 130.
// time - O(m * n)
// space - O(m * n)
//Os in the borders are not fully sorrounded by X
//sink all Os connected to any O in the border
//the remaining Os are sorrounded by Xs so flip them and return
class Solution {
    public void solve(char[][] board) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return;
        }
        
        //capture all Os in the borders
        for(int i = 0; i < board[0].length; i++)
        {
            //capturing all Os in the 1st and last row
            if(board[0][i] == 'O')
            {
                //capture this O
                dfs(board, 0, i);
            }
            if(board[board.length - 1][i] == 'O')
            {
                //capture this O
                dfs(board, board.length - 1, i);
            }
        }
        for(int i = 0; i < board.length; i++)
        {
            //capturing all Os in the 1st and last column
            if(board[i][0] == 'O')
            {
                //capture this O
                dfs(board, i, 0);
            }
            if(board[i][board[0].length - 1] == 'O')
            {
                //capture this O
                dfs(board, i, board[0].length - 1);
            }
        }
        
        //iterate through board, if O is found then its is not part of a border O if so it would have been flipped to B -> all neighbors of this O should be X -> if not it would have been captured and flipped to B
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j] == 'O')
                {
                    board[i][j] = 'X';
                }
            }
        }
        
        //in second pass flip Bs back to Os
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j] == 'B')
                {
                    board[i][j] = 'O';
                }
            }
        }
        
        return;
    }
    
    //time - O(n * m)
    //space - O(n * m) - max call stack size when all Os in matrix
    private void dfs(char[][] board, int row, int col) {
        //base
        if(board[row][col] != 'O')
        {
            return; //this cell cant be captured -> because the cell doesnt have O
        }
        //logic
        board[row][col] = 'B'; //change border O to B to indicate all regions in all border O
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 4 directions
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0]; //neighbor co ordinates
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length)
            {
                //neighbor within bounds
                dfs(board, nRow, nCol);
            }
        }
        return;
    }
}
