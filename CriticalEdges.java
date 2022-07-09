// 1192.
class Solution {
    
    int time = 1; //global timer -> increases by 1 after visiting every node
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        int[] disc = new int[n]; //tracks the discovery time of each node, if node is unvistied, discovery time of it is 0
        int[] low = new int[n]; //low[i] is smallest discovery time reachable from i
        
        buildGraph(connections);
        
        dfs(0, -1, disc, low);
        
        return result;
    }
    
    //time - O(E)
    private void buildGraph(List<List<Integer>> connections) {
        for(List<Integer> edge : connections)
        {
            //bidirectional edge
            int src = edge.get(0);
            int dest = edge.get(1);
            if(!graph.containsKey(src))
            {
                graph.put(src, new ArrayList<>());
            }
            if(!graph.containsKey(dest))
            {
                graph.put(dest, new ArrayList<>());
            }
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }
        return;
    }
    
    //time - O(V+E)
    private void dfs(int node, int parent, int[] disc, int[] low) {
        //logic
        //visit node
        disc[node] = time;
        low[node] = time;
        time++; //time increases by 1 for next node
        for(int neighbor : graph.get(node))
        {
            if(neighbor == parent)
            {
                continue;
            }
            if(disc[neighbor] == 0)
            {
                //unvisited neighbor - recurse
                dfs(neighbor, node, disc, low);
                //after coming back, update low due to a potential back edge found at neighbor
                low[node] = Math.min(low[node], low[neighbor]);
                if(disc[node] < low[neighbor])
                {
                    //no back edge for neighbor -- critical edge
                    List<Integer> cEdge = new ArrayList<>();
                    cEdge.add(node);
                    cEdge.add(neighbor);
                    result.add(cEdge);
                }
                
            }
            else //base case
            {
                //back edge found (there is a path to reach node other than coming from parent i.e. by coming through neighbor) -> low of node is updated as we find another node with small disc time
                low[node] = Math.min(low[node], disc[neighbor]);
            }
        }
    }
}
