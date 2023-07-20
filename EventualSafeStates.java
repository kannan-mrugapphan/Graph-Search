// 802.
// time - O(n^2) to build reverse graph and indegrees[] + O(v+e) for topoligical sort
//space - O(n) for indegrees list O(v+e) for reverse graph O(v) for queue in topological sort
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        //nodes with no outgoing edges (outdegree = 0) are terminal nodes and are safe
        //terminal nodes thus will be part of the result set
        //thus all the edges which are incident on these terminal nodes could be removed as they lead to 
        //a terminal node and are eventually safe

        //thus source of these edges will have an outdegree reduced by 1 accounting for this current edge
        //if the outdegree of these nodes become 0, then they are safe and should be part of result set

        //the above steps should be repeated till more safe nodes are found

        //when processing a node with outdegree 0, then this node is safe and the outdegree of all nodes 
        //connected to this node should reduce by 1
        //to easily find all nodes connected to this node, the graph is reversed 
        //in reversed graph, all nodes with indegree 0 are terminal nodes

        //a key in reverse is a node to which all nodes in value list are connected 
        HashMap<Integer, List<Integer>> reverse = new HashMap<>();
        int[] indegrees = new int[graph.length];

        for(int node = 0; node < graph.length; node++)
        {
            int[] neighbors = graph[node];
            //original graph - node -> neighbors[i]
            //reverse graph - neighbors[i] -> node

            //build edge for reverse graph
            for(int neighbor : neighbors)
            {
                if(!reverse.containsKey(neighbor))
                {
                    reverse.put(neighbor, new ArrayList<>());
                }
                reverse.get(neighbor).add(node);
            }

            indegrees[node] = neighbors.length; //all neighbors are connected to node
        }

        Queue<Integer> support = new LinkedList<>(); //to perform topological sort
        List<Integer> result = new ArrayList<>(); //return list

        for(int i = 0; i < graph.length; i++)
        {
            if(indegrees[i] == 0)
            {
                support.offer(i); //terminal node, processed first
            }
        }

        while(!support.isEmpty())
        {
            Integer current = support.poll(); 
            result.add(current); //all nodes in queue are either teminal nodes or safe nodes

            //if node has any incoming edges in original graph or outgoing edges in reversed graph
            //reduce of outdegree in original graph or indegree in reverse graph
            if(reverse.containsKey(current))
            {
                List<Integer> neighbors = reverse.get(current);
                for(Integer neighbor : neighbors)
                {
                    indegrees[neighbor]--; 
                    if(indegrees[neighbor] == 0)
                    {
                        //neighbor is safe
                        support.offer(neighbor);
                    }
                }
            }
        }

        Collections.sort(result);//result list in sorted order
        return result;
    }
}
