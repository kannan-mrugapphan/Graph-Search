// 1168.
// time - O(n logn) -> n is the number of edges
// space - O(number of villages) - parent[]

class Solution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //consider a dummy node 0 - an edge between dummy node 0 and a village i indicates a well in village i
        int[] parent = new int[n + 1]; //n villages 1 dummy node
        
        List<int[]> edges = new ArrayList<>(); //tracks wells and pipes
        for(int i = 1; i <= n; i++)
        {
            edges.add(new int[]{0, i, wells[i - 1]}); //an edge between dummy, node i with cost as wells[i - 1]
            parent[i] = i; //initially all nodes are part of different groups
        }
        for(int[] pipe : pipes)
        {
            edges.add(pipe); //add pipes to edge list
        }
        Collections.sort(edges, (int[] a, int[] b) -> a[2] - b[2]); //process the edges in increasing order of costs
        
        int totalCost = 0; //return value
        for(int[] edge : edges)
        {
            int parentSrc = find(edge[0], parent); //find the representative of src and dest of each edge
            int parentDest = find(edge[1], parent);
            if(parentSrc != parentDest)
            {
                //if they are unequal, they are of different sets, add this edge to result and union them
                totalCost += edge[2];
                parent[parentSrc] = parentDest; 
            }
        }
        
        return totalCost;
    }
    
    private int find(int node, int[] parent) {
        if(parent[node] != node) //if parent of node is not same as node recurse on the parent
        {
            parent[node] = find(parent[node], parent);
        }
        return parent[node]; //return parent of node
    }
}
