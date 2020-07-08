
public class Solution {
    
    //time - O(min(x, y))
    public static int gcd(int x, int y) {
        //gcd or hcf is the largest positive number that divides both x and y
        int result = 1; //gcd - lowest possible number to divide both x and y
        // from i = 2 to min(x, y) find largest i which divides both x and y and return
        for(int i = 2; i <= x && i <= y; i++)
        {
            if(x % i == 0 && y % i == 0)
            {
                result = i;
            }
        }
        return result;
    }
    
    //time - O(n^3) including gcd()
    public static HashMap<Integer, List<Integer>> buildGraph(int n, int g) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                //no self edges 
                if(i == j)
                {
                    continue;
                }
                //an edge between i and j is present if gcd(i, j) > g
                if(gcd(i, j) > g)
                {
                    if(!graph.containsKey(i))
                    {
                        graph.put(i, new ArrayList<Integer>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
        return graph;
    }
    
    public static int bfs(HashMap<Integer, List<Integer>> graph, int n, int src, int dest) {
        Queue<Integer> support = new LinkedList<>(); //for bfs
        boolean[] visited = new boolean[n]; 
        visited[src - 1] = true; //mark src as visited and insert into queue
        support.offer(src);
        
        while(!support.isEmpty())
        {
            Integer front = support.poll(); //if front is dest, return true (1)
            if(front == dest)
            {
                return 1;
            }
            //insert the unvisited neighbors into queue after marked as visited
            List<Integer> neighbors = graph.get(front); 
            if(neighbors != null)
            {
                for(Integer neighbor : neighbors)
                {
                    if(!visited[neighbor - 1])
                    {
                        visited[neighbor - 1] = true;
                        support.offer(neighbor);
                    }
                }
            }
        }
        
        return 0;
    }
    
    static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
        // Complete this function
        HashMap<Integer, List<Integer>> graph = buildGraph(n, g);
        int[] result = new int[originCities.length];
        for(int i = 0; i < originCities.length; i++)
        {
            result[i] = bfs(graph, n, originCities[i], destinationCities[i]); //if path is present set to 1 else 0
        }
        return result;
    }
}
