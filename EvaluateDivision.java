// 399.
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<NeighborInfo>> graph = new HashMap<>(); //tracks the graph
        buildGraph(graph, equations, values);
        
        double[] result = new double[queries.size()]; //result array
        int index = 0;
        for(List<String> query : queries)
        {
            String src = query.get(0);
            String dest = query.get(1);
            
            //"a"/"a" = 1.0 if a is seen in equations (presnt in graph) and -1.0 if a is unseen
            if(src.equals(dest))
            {
                if(graph.containsKey(src))
                {
                    result[index++] = 1.0;
                }
                else
                {
                    result[index++] = -1.0;
                }
                continue;
            }
            
            result[index++] = dfs(graph, src, dest, new HashSet<>());
        }
        
        return result;
    }
    
    //time - O(E)
    private void buildGraph(HashMap<String, List<NeighborInfo>> graph, List<List<String>> equations, double[] values){
        for(int i = 0; i < equations.size(); i++)
        {
            //for each edge, extract src, dest and weight
            List<String> edge = equations.get(i);
            String src = edge.get(0);
            String dest = edge.get(1);
            double weight = values[i];
            
            //update entries in graph
            graph.putIfAbsent(src, new ArrayList<>());
            graph.putIfAbsent(dest, new ArrayList<>());
            
            graph.get(src).add(new NeighborInfo(dest, weight));
            graph.get(dest).add(new NeighborInfo(src, 1 / weight));
        }
        return;
    }
    
    //time - O(v+e)
    private double dfs(HashMap<String, List<NeighborInfo>> graph, String src, String dest, HashSet<String> visited) {
        //base
        //current src is already visited
        if(visited.contains(src))
        {
            return -1.0;
        }
        //dest found 
        if(src.equals(dest))
        {
            return 1.0;
        }
        //can't continue traversing from current src
        if(!graph.containsKey(src))
        {
            return -1.0;
        }
        
        //mark current node as visited
        visited.add(src);
        for(NeighborInfo neighbor : graph.get(src))
        {
            String neighborNode = neighbor.neighbor;
            double weight = neighbor.weight;
            //traverse accross edge and reach neighbor node, so current result is weight
            double result = weight;
            double neighborValue = dfs(graph, neighborNode, dest, visited); //recurse on neighbor
            if(neighborValue != -1.0)
            {
                return result * neighborValue; //valid path
            }
        }
        return -1.0;
    }
}

//object of this class has 2 fields, dest of an edge and weight of an edge
class NeighborInfo
{
    String neighbor;
    double weight;
    
    public NeighborInfo(String neighbor, double weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }
}
