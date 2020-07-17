// 1192.
//time - O(V + E)
//space - O(V)

class Solution {
    int time = 0;
    List<List<Integer>> criticalEdges;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(graph, connections, n);
        
        int[] discovery = new int[n]; //each index in discover[] tracks the order of flow of dfs to reach node i
        Arrays.fill(discovery, -1); //initially fill discovery[] with -1s since no node is visited
        int[] lowest = new int[n]; //lowest[i] tracks the earliest discovered node that can be reached from i
        
        criticalEdges = new ArrayList<>(); //return val
        dfs(graph, 0, 0, discovery, lowest); //start from 0th node - parent of this is itself
       
        return criticalEdges;
    }
    
    //time - O(number of edges)
    //space - O(1)
    private void buildGraph(HashMap<Integer, List<Integer>> graph, List<List<Integer>> connections, int numberOfNodes) {
        for(int i = 0; i < numberOfNodes; i++)
        {
            graph.put(i, new ArrayList<>()); //creating an empty array list to each node and inserting it to map
        }
        for(List<Integer> edge : connections)
        {
            int from = edge.get(0); //for each edge, get the source and dest vertex
            int to = edge.get(1);
            graph.get(from).add(to); //add to to neighber list of from
            graph.get(to).add(from); //add from to neighbor list of to
            //both are added, since edge is undirected
        }
        return;
    }
    
    //time - O(V + E)
    //space - O(V)
    private void dfs(HashMap<Integer, List<Integer>> graph, int current, int parent, int[] discovery, int[] lowest) {
        //base
        if(discovery[current] != -1)
        {
            return;
        }
        //logic
        discovery[current] = time; //setting time and marking current as visited
        lowest[current] = time;
        time++;
        //recurse on neighbors
        for(Integer neighbor : graph.get(current)) 
        {
            if(neighbor == parent) //dont recurse if neighbor is parrent of current
            {
                continue;
            }
            dfs(graph, neighbor, current, discovery, lowest);
            if(lowest[neighbor] > discovery[current]) //add edge to result conditionally
            {
                criticalEdges.add(Arrays.asList(current, neighbor));
            }
            lowest[current] = Math.min(lowest[current], lowest[neighbor]); //update lowest of current
        }
        return;
    }
}
