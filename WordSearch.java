// 79.
//time - O(m * n)
//space - O(m * n) -> max call stack size when whole board is word -> eg : matrix -['s','e','e'], word - see

class Solution {
    public boolean exist(char[][] board, String word) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return false; //emppty board -> word cant be found
        }
        
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j] == word.charAt(0)) //1st letter found
                {
                    if(word.length() == 1) //word length 1
                    {
                        return true;
                    }
                    //found 1st letter -> recurse to check if this is a vlid start position of word in board
                    else if(findWord(board, word, 0, i, j))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false; //word not foundd
    }
    
    //time - O(m * n)
    //space - O(m * n) -> max call stack size when whole board is word -> eg : matrix -['s','e','e'], word - see
    private boolean findWord(char[][] board, String word, int index, int row, int col) {
        //base
        if(index == word.length()) //word found
        {
            return true;
        }
        if(word.charAt(index) != board[row][col]) //char mismatch
        {
            return false;
        }
        //logic
        board[row][col] = '-'; //marking as visited - to avoid using same letter again
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //4 possible neighbors
        for(int[] dir : dirs)
        {
            int nRow = row + dir[0];
            int nCol = col + dir[1];
            if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length)
            {
                //neighbour within bounds
                if(findWord(board, word, index + 1, nRow, nCol))
                {
                    return true;
                }
            }
        }
        board[row][col] = word.charAt(index); //updating the cell with original letter
        return false;
    }
}
