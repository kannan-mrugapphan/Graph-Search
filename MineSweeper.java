// 529.

class Solution {
    int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}}; //8 neighbors
    
    public char[][] updateBoard(char[][] board, int[] click) {
        //edge 
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return board;
        }
        //if stepped onto a mine initially
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }
    
    //time - O(mn)
    //space - O(mn) - for the queue
    private char[][] bfs(char[][] board, int[] click) {
        Queue<int[]> support = new LinkedList<>(); //for bfs
        support.offer(click); //insert src into queue
        board[click[0]][click[1]] = 'B'; //mark as B (visited) while inserting into queue & changed when polled(if needed)
        
        while(!support.isEmpty())
        {
            int size = support.size();
            for(int i = 0; i < size; i++)
            {
                int[] front = support.poll(); //front element
                int minesCount = countMines(board, front[0], front[1]); //# of mines neighbouring to the polled cell
                if(minesCount > 0)
                {
                    board[front[0]][front[1]] = (char) (minesCount + '0'); //insert the mines count into polled cell
                }
                else
                {
                    for(int[] dir : dirs)
                    {
                        int nRow = front[0] + dir[0]; //neighbor coordinates
                        int nCol = front[1] + dir[1];
                        if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length && board[nRow][nCol] == 'E') //neighbour within bounds and unvisited
                        {
                            support.offer(new int[]{nRow, nCol}); //insert neighbor into queue
                            board[nRow][nCol] = 'B'; //marking as visited
                        }
                    }
                }
                
            }
        }
        
        return board;
    }
    
    //time - constant
    private int countMines(char[][] board, int row, int col) {
        int count = 0; //# of mines in neighbourhood
        for(int[] dir : dirs)
        {
           int nRow = row + dir[0]; //neighbor coordinates
           int nCol = col + dir[1];
           if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length && board[nRow][nCol] == 'M')
           {
               count++;
           }
        }
        return count;
    }
    
    //time - O(mn)
    //space - O(mn) - max call stack when no mines in board
    private void dfs(char[][] board, int row, int col) {
        //base
        if(board[row][col] != 'E')
        {
            return;
        }
        //logic
        board[row][col] = 'B'; //marking as visited
        int minesCount = countMines(board, row, col); //find the neighbouring mines
        if(minesCount > 0)
        {
            board[row][col] = (char)(minesCount + '0'); //update the mines count and return
            return;
        }
        else
        {
            for(int[] dir : dirs)
            {
                int nRow = row + dir[0]; //neighbor coordinates
                int nCol = col + dir[1]; 
                if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length)
                {
                    //neighbour within bounds
                    dfs(board, nRow, nCol);
                }
            }
        }
        return;
    }
}
