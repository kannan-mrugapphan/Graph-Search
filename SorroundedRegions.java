// 130.

//time - O(m * n)
//space - O(m * n) - call stack size when all Os

//Os in the borders are not fully sorrounded by X
//sink all Os connected to any O in the border
//the remaining Os are sorrounded by Xs so flip them and return
class Solution {
    public void solve(char[][] board) {
        //edge
        if(board == null || board.length == 0|| board[0].length == 0)
        {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        //sink Os in the 1st and last row
        for(int i = 0; i < n; i++)
        {
            if(board[0][i] == 'O')
            {
                dfs(board, 0, i);
            }
            if(board[m - 1][i] == 'O')
            {
                dfs(board, m - 1, i);
            }
        }
        
        //sink all Os in 1st and last column
        for(int i = 0; i < m; i++)
        {
            if(board[i][0] == 'O')
            {
                dfs(board, i, 0);
            }
            if(board[i][n - 1] == 'O')
            {
                dfs(board, i, n - 1);
            }
        }
        
        //flip other Os and change -s to Os
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(board[i][j] == 'O')
                {
                    board[i][j] = 'X';
                }
                if(board[i][j] == '-')
                {
                    board[i][j] = 'O';
                }
            }
        }
        
        return;
    }
    
    private void dfs(char[][] board, int row, int column) {
        board[row][column] = '-'; //change to - to mark cell as visited to avoid proessing again, can also have a visited matrix
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] direction : dirs) 
        {
            int nRow = row + direction[0];
            int nColumn = column + direction[1];
            
            if(nRow >= 0 && nRow < board.length && nColumn >= 0 && nColumn < board[0].length)
            {
                //neighbour within bounds
                if(board[nRow][nColumn] == 'O')
                {
                    //sink them by dfs
                    dfs(board, nRow, nColumn);
                }
            }
        }
        
        return;
    }
}
