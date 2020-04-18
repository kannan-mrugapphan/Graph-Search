// 1376.

//time - O(n) - process all employees to get max time
//space - O(n) - for the map

//time needed for news to reach an employee is the time needed for his manager to get the news + inform time of manager
//the employee with max time to get the news is the result

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //build a map with manager as key and list of his sub ordinates as value
        //manager[i] is the manager of employee i
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < manager.length; i++)
        {
            if(map.containsKey(manager[i]))
            {
                map.get(manager[i]).add(i); 
            }
            else
            {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(manager[i], temp);
            }
        }
        
        //start with the head
        Queue<timeInfo> support = new LinkedList<>();
        support.offer(new timeInfo(headID, 0)); // time needed for head to get news is 0
        
        //keep track of employee with max time to get the news
        int result = Integer.MIN_VALUE;
        
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            for(int i = 0; i < layerSize; i++)
            {
                timeInfo current = support.poll();
                result = Math.max(result, current.timeNeeded); //update result accordingly
                
                //get the subordinates of current
                List<Integer> subordinates = map.get(current.id);
                if(subordinates != null)
                {
                    //add each sub ordinate into queue with approriate id and time needed for that sub
                    for(Integer sub : subordinates)
                    {
                        int id = sub;
                        int timeNeeded = informTime[current.id] + current.timeNeeded;
                        support.offer(new timeInfo(id, timeNeeded));
                    }
                }
            }
        }
        
        return result;
    }
}

public class timeInfo {
    int id;
    int timeNeeded;
    
    public timeInfo(int id, int timeNeeded)
    {
        this.id = id;
        this.timeNeeded = timeNeeded;
    }
}
