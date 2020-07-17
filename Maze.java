// 490.
// time - O(mn)
// space - O(mn)
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //edge
        if(maze == null || maze.length == 0 || maze[0].length == 0)
        {
            return false;
        }
        
        int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //4 possible neighbours
        Queue<int[]> support = new LinkedList<>(); //for bfs
        support.offer(start); //insert start location into queue
        maze[start[0]][start[1]] = 2; //marking src cell as visited
        int moves = 0;
        
        while(!support.isEmpty())
        {
            int size = support.size(); //process cells as distinct groups
            for(int i = 0; i < size; i++)
            {
                int[] front = support.poll(); 
                //check for dest and return if reached
                if(front[0] == destination[0] && front[1] == destination[1])
                {
                    return true; //return moves if # of moves is asked
                }
                //add neighbours
                for(int[] dir : dirs) 
                {
                    int nRow = front[0] + dir[0]; //neighbour co-ordinates
                    int nCol = front[1] + dir[1];
                    //keep rolling until wall is hit
                    while(nRow >= 0 && nRow < maze.length && nCol >= 0 && nCol < maze[0].length && maze[nRow][nCol] != 1)
                    {
                        nRow += dir[0];
                        nCol += dir[1];
                    }
                    //ball stops when obstacle is hit - revert back to prev pos
                    nRow -= dir[0];
                    nCol -= dir[1];
                    if(maze[nRow][nCol] != 2) //if this resultant neighbour cell is unvisited
                    {
                        support.offer(new int[]{nRow, nCol}); //mark as visited and insert into queue
                        maze[nRow][nCol] = 2;
                    }
                } 
            }
            moves++;
        }
        
        return false; //dest unreachable
    }
}
