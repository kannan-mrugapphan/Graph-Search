// 847.
// time - O(n * 2^n) -> each bfs in worst case runs for 2^n time as each node has 2 choices, include or don't include in path
// space - O(n) -> visited set, queue
class Solution {
    public int shortestPathLength(int[][] graph) {
        
        int result = Integer.MAX_VALUE; //return value
        for(int i = 0; i < graph.length; i++)
        {
            //start bfs from every node to get min result
            int currentPathLength = bfs(graph, i);
            result = Math.min(result, currentPathLength);
        }
        
        return result;
    }
    
    private int bfs(int[][] graph, int sourceNode) {
        int n = graph.length; //number of nodes
        //if n = 4, then inital mask = 10000 (2^n), the last 4 zeros track the visited info of each node
        int initialVisitedMask = (int) Math.pow(2, n); 
        
        int endState = (int) Math.pow(2, n + 1) - 1; //if n = 4, expected end state = 11111 = 2^5 - 1
        
        HashSet<String> visited = new HashSet<>(); //visited set t avoid infinite cycles
        Queue<int[]> support = new LinkedList<>(); //each element in queue tracks the current node and current mask
        
        //visited keys creation for source
        int sourceVisitedMask = initialVisitedMask | (1 << sourceNode);
        String sourceVisitedKey = sourceNode + "-" + sourceVisitedMask;
        
        support.offer(new int[]{sourceNode, sourceVisitedMask});
        //assuming source node is 0, insert pair {0, 0001} into the queue and "0-0001" into visited set
        visited.add(sourceVisitedKey);
        
        int pathLength = 0; //tracks the number of levels explored till whole graph is visited
        
        while(!support.isEmpty())
        {
            int layerSize = support.size(); //number of nodes in current level = number of elements in queue
            for(int i = 0; i < layerSize; i++)
            {
                //get the front node info
                int[] current = support.poll();
                int currentNode = current[0];
                int currentVisitedMask = current[1];
                String visitedKey = currentNode + "-" + currentVisitedMask;
                
                if(currentVisitedMask == endState)
                {
                    //if current visited mask becomes all 1s then all nodes are visited
                    return pathLength;
                }
                
                for(int neighbor : graph[currentNode])
                {
                    //for each neighbor
                    //get the neighbor mask
                    int neighborVisitedMask = currentVisitedMask | (1 << neighbor);
                    String neighborVisitedKey = neighbor + "-" + neighborVisitedMask;
                    
                    //for the 1st example if we start from 0 mask is 10001
                    //then visite 1 to get 1 with a mask of 10011
                    //then go to 0 to get a mask of 10011
                    //if we go to 1 again, then it is an infinite cycle and has to be skipped
                    //this is obtained from key 1,10011
                    
                    if(!visited.contains(neighborVisitedKey))
                    {
                        visited.add(neighborVisitedKey); //mark as visited
                        support.offer(new int[]{neighbor, neighborVisitedMask}); //insert into queue 
                    }
                    
                }
            }
            pathLength++;
        }
        
        return Integer.MAX_VALUE; //no valid path found
    }
}
