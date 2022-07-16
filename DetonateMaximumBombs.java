// 2101.
// time - O(n^2)
class Solution {
    public int maximumDetonation(int[][] bombs) {
        //for each bomb, the list of bombs within its radius are tracked in graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        //nested loop runs for O(n^2)
        for(int i = 0; i < bombs.length; i++)
        {
            //get the current bomb co-ordinates and radius
            int currentBomb = i;
            int currentBombX = bombs[i][0];
            int currentBombY = bombs[i][1];
            long currentBombRadius = bombs[i][2];
            
            for(int j = 0; j < bombs.length; j++)
            {
                //if the neighbor is not current, get the neighbor co-ordinates and radius
                int neighbor = j;
                if(currentBomb == neighbor)
                {
                    continue;
                }
                int neighborX = bombs[j][0];
                int neighborY = bombs[j][1];
                
                //check if neighbor is with radius of current bomb
                long dx = (neighborX - currentBombX);
                long dy = (neighborY - currentBombY);
                long distance = (dx * dx) + (dy * dy);
                
                if(currentBombRadius * currentBombRadius >= distance)
                {
                    //neighbor within radius
                    graph.putIfAbsent(currentBomb, new ArrayList<>());
                    graph.get(currentBomb).add(neighbor);
                }
            }
        }
        
        int maxBombsExploded = 0; //return value
        // time - O(n^2) -> bfs for each n
        for(int i = 0; i < bombs.length; i++)
        {
            //try detonating each bomb and update result if possible
            int bombsExploded = bfs(i, graph, new boolean[bombs.length]);
            maxBombsExploded = Math.max(maxBombsExploded, bombsExploded);
        }
        return maxBombsExploded;
    }
    
    //worst case all bombs are exploded time - O(n)
    private int bfs(int node, HashMap<Integer, List<Integer>> graph, boolean[] visited) {
        Queue<Integer> support = new LinkedList<>();
        support.offer(node);
        visited[node] = true;
        int bombsExploded = 0;
        
        while(!support.isEmpty())
        {
            int currentBomb = support.poll();
            bombsExploded++; //current bomb explodes
            
            if(graph.containsKey(currentBomb))
            {
                //if there are any neighbors within radius of current bomb
                for(int neighbor : graph.get(currentBomb))
                {
                    if(!visited[neighbor])
                    {
                        //if neighbor bomb is unvisited, mark it as visited and insert into queue
                        visited[neighbor] = true;
                        support.offer(neighbor);
                    }
                }
            }
        }
        
        return bombsExploded;
    }
}
