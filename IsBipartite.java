// 785.
// time - O(n^2) - due to adjacency matrix 
// space - O(n) - n = # of nodes
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        
        for(int i = 0; i < graph.length; i++)
        {
            //if currrent node is unprocessed & after running a search starting from here, if 2 colouring isn't possible return false
            if(colors[i] == 0 && !bfs(i, graph, colors, 1))
            {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(int node, int[][] graph, int[] colors, int color) {
        //base
        if(colors[node] != 0)
        {
            return colors[node] == color; //if the node is already processed return true if its color is same as incoming 
        }
        //logic
        colors[node] = color; //assign color to node to mark it as visited
        for(int neighbor : graph[node])
        {
            //for each neighbour recurse when its unprocessed
            if(!dfs(neighbor, graph, colors, -color))
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean bfs(int node, int[][] graph, int[] colors, int color) {
        Queue<Integer> support = new LinkedList<>(); //for bfs
        support.offer(node); //push the src into queue and mark it as visited by assigning its color
        colors[node] = color; 
        
        while(!support.isEmpty())
        {
            Integer front = support.poll();
            for(int neighbor : graph[front])
            {
                //for each neighbour of polled node, assign colour and push into queue if unprocessed, if same colour as polled node for neighbour is present return false
                if(colors[neighbor] == colors[front])
                {
                    return false;
                }
                if(colors[neighbor] == 0)
                {
                    colors[neighbor] = -colors[front];
                    support.offer(neighbor);
                }
            }
        }
        
        return true;
    }
}
