// 797.
// time - O(2^n) -> worst case -> total number of paths in a ist of size n
// space - O(n) -> worst case call stack size
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        //edge
        if(graph == null || graph.length == 0)
        {
            return result;
        }
        
        List<Integer> path = new ArrayList<>();
        dfs(graph, result, path, 0, graph.length - 1, new boolean[graph.length]);
        return result;
    }
    
    public void dfs(int[][] graph, List<List<Integer>> result, List<Integer> path, int node, int dest, boolean[] visited) {
        //base
        //reached dest
        if(node == dest)
        {
            //create copy of path, add dest to copy and add copy to result
            List<Integer> validPath = new ArrayList<>(path);
            validPath.add(dest);
            result.add(validPath);
            return;
        }
        //logic
        path.add(node); //include node in current path
        visited[node] = true; //mark as visited so currennt node will not be visited again in current path
        for(int neighbor : graph[node])
        {
            if(!visited[neighbor]) //for each unvisited neighbor, recurse
            {
                dfs(graph, result, path, neighbor, dest, visited);
            }
        }
        path.remove(path.size() - 1); //backtrack to return to prev state
        visited[node] = false;
        return;
    } 
}
