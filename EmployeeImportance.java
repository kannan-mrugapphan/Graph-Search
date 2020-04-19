// 690.

//alternative bfs - go level by level and add importance of employees in current level

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
//time - O(n)
//space - O(n) for the graph
class Solution {
    int importance = 0;
    
    public int getImportance(List<Employee> employees, int id) {
        //build the graph
        //emp id - emp object 
        HashMap<Integer, Employee> graph = new HashMap<>();
        for(Employee emp : employees)
        {
            graph.put(emp.id, emp);
        }
        
        dfs(graph, id);
        
        return importance;
    }
    
    //time - O(n) - number of employees if dfs is called on the employee at highest level
    //space- O(n) - recursive stack
    private void dfs(HashMap<Integer, Employee> graph, int id) {
        //similar to preorder tree traversal
        //add importance of current employee to result and recurse on his sub ordinates
        //visisted array not needed as the manager-subordiante relation is 1-1
        importance += graph.get(id).importance;
        List<Integer> subOrdinates = graph.get(id).subordinates;
        if(subOrdinates != null)
        {
            for(Integer sub : subOrdinates) 
            {
                dfs(graph, sub);
            }

        }
        return;
    }
}
