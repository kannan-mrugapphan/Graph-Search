// 909.
// time - O(mn)
// space - O(mn) - flattened array
class Solution {
    public int snakesAndLadders(int[][] board) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return 0;
        }
        int[] flattenedBoard = new int[board.length * board[0].length]; //also serves as visited array
        int index = 0; //iterates over flattened array
        int i = board.length - 1; //start from last row 0th col
        int j = 0;
        int flag = 0; //tracks the next cell 
        
        while(i >= 0)
        {
            //if cell has -1(no snake or ladder copy -1 to flattened array) otherwise copy corresponding index location
            flattenedBoard[index] = (board[i][j] == -1) ? -1 : board[i][j] - 1; 
            if(flag % 2 == 0) //in the even row
            {
                j++; //go to the right (as in last row)
            }
            else
            {
                j--; //go the the left (as in second last row)
            }
            if(j == board[0].length)//out of bound on the right, go to prev row, prev coloum & increase flag(row changes)
            {
                j--;
                i--;
                flag++;
            }
            else if(j < 0)//out of bound on the left , go to prev row, prev coloum and increase flag (row changes)
            {
                j++;
                i--;
                flag++;
            }
            index++;
        }
        
        Queue<Integer> support = new LinkedList<>(); //for bfs
        support.offer(0); //start from 0th location
        flattenedBoard[0] = -2; //marking as visited
        int moves = 0;
        
        while(!support.isEmpty())
        {
            int size = support.size(); //process layer by layer
            for(j = 0; j < size; j++)
            {
                Integer front = support.poll(); //remove the front node
                if(front == flattenedBoard.length - 1)
                {
                    return moves;
                }
                for(i = 1; i < 7; i++) //6 possible place to move to in the next move
                {
                    int nextCell = front + i;
                    if(nextCell < board.length * board[0].length && flattenedBoard[nextCell] != -2)
                    {
                        //next cell is unvisited
                        //if there is no ladder or snake at this cell, insert its index else enter that value
                        if(flattenedBoard[nextCell] == -1) 
                        {
                            support.offer(nextCell);
                        }
                        else
                        {
                            support.offer(flattenedBoard[nextCell]);
                        }
                        flattenedBoard[nextCell] = -2; //marking as visited
                    }
                }
            }
            moves++;
        }
        
        return -1; //invalid case
    }
}
