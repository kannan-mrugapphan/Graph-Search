// 323.
class Solution {
    public int countComponents(int n, int[][] edges) {
        //edge
        if(n <= 0)
        {
            return 0; //no nodes
        }
        if(edges == null || edges.length == 0)
        {
            return n; //no edges
        }
        
        HashMap<Integer, List<Integer>> graph = buildGraph(edges);
        int components = 0; //return value
        
        //visited[i] tracks whether node i is visited in current traversal
        boolean[] visited = new boolean[n];
        
        for(int node = 0; node < n; node++)
        {
            //unvisited current node - sink the whole component
            if(!visited[node])
            {
                bfs(graph, node, visited);
                components++;
            }
        }
        
        return components;
    }
    
    //time - O(E)
    //space - O(V+E) for map to store the graph 
    private HashMap<Integer, List<Integer>> buildGraph(int[][] edges)
    {
        //key - node, value - list of nodes connected to key
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] edge: edges)
        {
            //undirected graph - bidirectional edge
            if(!graph.containsKey(edge[0]))
            {
                graph.put(edge[0], new ArrayList<Integer>());
            }
            if(!graph.containsKey(edge[1]))
            {
                graph.put(edge[1], new ArrayList<Integer>());
            }
            
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        return graph;
    }
    
    //time - O(V+E)
    //space - O(V) - all nodes are in same component - max stack size = V
    private void dfs(HashMap<Integer, List<Integer>> graph, int node, boolean[] visited)
    {
        visited[node] = true; //avoid re-traversing nodes
        if(graph.containsKey(node))
        {
            //recurse on unvisited neighbors
            List<Integer> neighbors = graph.get(node);
            for(Integer neighbor : neighbors)
            {
                if(!visited[neighbor])
                {
                    dfs(graph, neighbor, visited);
                }
            }
        }
        return;
    }

    //time - O(V+E)
    //space - O(V) - queue size is max is when node i is connected to all nodes in graph 
    private void bfs(HashMap<Integer, List<Integer>> graph, int node, boolean[] visited)
    {
        Queue<Integer> support = new LinkedList<>(); 
        support.offer(node); //start bfs from node after marking it as visited
        visited[node] = true;

        while(!support.isEmpty())
        {
            int levelSize = support.size(); //number of numbes in current level
            //process all nodes in current level
            for(int i = 0; i < levelSize; i++)
            {
                int front = support.poll();
                if(graph.containsKey(front))
                {
                    //process unvisited neighbors
                    List<Integer> neighbors = graph.get(front);
                    for(Integer neighbor : neighbors)
                    {
                        if(!visited[neighbor])
                        {
                            visited[neighbor] = true;
                            support.offer(neighbor); //neighbors are marked as visited and will be processed in next level
                        }
                    }
                }
            }
        }

        return;
    }
}
