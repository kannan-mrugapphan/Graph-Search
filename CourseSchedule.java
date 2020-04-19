// 207.

//time - O(V + E) - V - num of courses and E size of prereqss
//space - O(V + E)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //pre req pair - [c1, c2] - c1 : main, c2 : prereq
        
        //the courses with in degree 0 i.e.e no dpenedency are finished(processed) first
        int[] inDegree = new int[numCourses];
        
        //graph as a hashmap(adjacency list)
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        
        for(int[] preReq : prerequisites)
        {
            int main = preReq[0];
            int pre = preReq[1];
            
            inDegree[main] += 1; //incrementing indegree of main course by 1 due to pre req
            //building the graph
            if(graph.containsKey(pre))
            {
                graph.get(pre).add(main);
            }
            else
            {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(main);
                graph.put(pre, temp);
            }
        }
        
        //start with all courses with 0 in degree
        Queue<Integer> support = new LinkedList<>();
        for(int i = 0; i < inDegree.length; i++)
        {
            if(inDegree[i] == 0)
            {
                support.offer(i);
            }
        }
        
        //break with false if size of queue is 0
        
        while(!support.isEmpty())
        {
            int current = support.poll();
            List<Integer> courses = graph.get(current);
            if(courses != null)
            {
                for(Integer course : courses)
                {
                    inDegree[course] -= 1; //reducing the in degree by 1 as current is processed 
                    if(inDegree[course] == 0)
                    {
                        support.offer(course); //process the course in next level
                    }
                }
            }
        }
        
        for(int in : inDegree)
        {
            if(in != 0)
            {
                return false; //this implies a course has more in coming edges, but all are processed
            }
        }
        
        return true;
    }
}
