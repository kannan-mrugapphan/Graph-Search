// 133.
// cloning requires to visit all nodes - so dfs/bfs
// in a normal dfs, a deep copy of src is created and recursion continues on neighbor, if there is a back edge one more deep copy of src is created - to avoid this a visited[] (hashmap) is used - the map has a ref of original node mapping to ref of its clone
// time - O(V+E)
// space - O(V) - for the map
class Solution {
    public Node cloneGraph(Node node) {
        //edge
        if(node == null)
        {
            return null;
        }
        HashMap<Node, Node> clones = new HashMap<>(); //each node in original graph maps to its respective deep copy
        return bfs(node, clones); 
    }
    
    private Node dfs(Node node, HashMap<Node, Node> clones) {
        //base
        if(clones.containsKey(node)) //if clone exists return its ref
        {
            return clones.get(node);
        }
        //logic
        clones.put(node, new Node(node.val)); //deep copy of node
        for(Node neighbor : node.neighbors) //for each neighbor of src node
        {
            //add the clone of neighbor(obtained from dfs(neighbor)) to the neighbor list of clone of src
            clones.get(node).neighbors.add(dfs(neighbor, clones)); 
        }
        return clones.get(node); //return clone of src
    }
    
    private Node bfs(Node node, HashMap<Node, Node> clones) {
        //each node in the queue will already have a deep copy - create a copy before inserting into queue
        Queue<Node> support = new LinkedList<>(); //for bfs
        support.offer(node); //push src into queue
        clones.put(node, new Node(node.val)); //deep copy of src node
        
        while(!support.isEmpty()) 
        {
            Node current = support.poll(); //get the front node
            for(Node neighbor : current.neighbors) 
            {
                //for each neighbor of polled node
                //if the neighbor doesnt have a deep copy create one and insert into queue
                if(!clones.containsKey(neighbor))
                {
                    clones.put(neighbor, new Node(neighbor.val));
                    support.offer(neighbor);
                }
                //add the clone of this neighbor to the neighbor list of clone of polled node
                clones.get(current).neighbors.add(clones.get(neighbor));
            }
        }
        
        return clones.get(node);
    }
}
