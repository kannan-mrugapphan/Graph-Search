// 818.
//approach - init position = 0, target position = target
//based on speed init positions changes

//target 6
// (0, 1) -> A -> (1, 2) -> A -> (3, 4) -> A -> (7, 8) -> overshot the target so R -> (7, -1) -> A -> (6, -2)

// during the traversal, if a node with same pos,speed is repeated, skip it

//time -> each node pos gets added by speed and speed is multiplied by 2 => O(log target)
//space -> O(log target)
class Solution {
    public int racecar(int target) {
        Queue<int[]> support = new LinkedList<>(); //for bfs
        HashSet<String> visited = new HashSet<>(); //strings of pos,speed pairs
        
        support.offer(new int[]{0, 1}); //init speed is 1 and init pos is 0
        visited.add("0:1");
        
        int moves = 0; //initially no moves made
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            //process all nodes in current level 
            for(int i = 0; i < layerSize; i++)
            {
                int[] current = support.poll();
                //check if target is reached
                if(current[0] == target)
                {
                    return moves;
                }
                
                //accelertae at every step
                int newPos = current[0] + current[1];
                int newSpeed = current[1] * 2;

                if(!visited.contains(newPos + ":" + newSpeed))
                {
                    support.offer(new int[]{newPos, newSpeed});
                    visited.add(newPos + ":" + newSpeed);
                }
                
                //check if reverse is possible
                //reverse possible if next pos breaches target and speed is +ve
                //or if next pos i within target and speed is -ve
                if((current[0] + current[1] > target && current[1] > 0) || (current[0] + current[1] < target && current[1] < 0))
                {
                    newPos = current[0];
                    newSpeed = (current[1] > 0 ? -1 : 1);
                    
                    if(!visited.contains(newPos + ":" + newSpeed))
                    {
                        support.offer(new int[]{newPos, newSpeed});
                        visited.add(newPos + ":" + newSpeed);
                    }
                }
            }
            moves++; //1 move is made
        }
        
        return -1; //code never reaches here
    }
}
