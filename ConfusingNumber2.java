// 1088.
class Solution {
    
    //map to track the equivalent rotated number for single digit numbers
    HashMap<Integer, Integer> mapping = new HashMap<>(); 
    
    public int confusingNumberII(int n) {
        //edge
        if(n < 0)
        {
            return 0;
        }
        
        mapping.put(1, 1);
        mapping.put(6, 9);
        mapping.put(8, 8);
        mapping.put(9, 6);
        mapping.put(0, 0);
        
        //edge
        if(n <= 9)
        {
            return mapping.size();
        }
        
        int result = 0;
        
        Queue<Integer> support = new LinkedList<>();
        for(int startPoint : mapping.keySet())
        {
            if(startPoint == 0)
            {
                continue; //we can ignore all trees starting with 0s as they will be explored with other starts
            }
            //do bfs/dfs with this start point
            // else
            // {
            //     result += dfs(startPoint, n);
            // }
            support.offer(startPoint);
        }
        
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            for(int i = 0; i < layerSize; i++)
            {
                //if polled number is confusing, add to result set
                int number = support.poll();
                if(isConfusingNumber(number))
                {
                    result++;
                }
                
                //generate all suffixes
                for(int suffix : mapping.keySet())
                {
                    //current = 1
                    //suffixes -> 10, 16, 18, 19
                    int neighbor = (number * 10) + suffix; 
                    if(neighbor <= n) //if neighbor within limits, process it in next level (push into queue)
                    {
                        support.offer(neighbor);
                    }
                }
            }
        }
        
        return result;
    }
    
    // a number is confusing, if the screen is turned upside down (mapping digits) and different number is obtained
    private boolean isConfusingNumber(int number) {
        int mappedNumber = 0; //tracks the upside number
        int numberCopy = number; //copy to do comparison at end
        while(number > 0)
        {
            int digit = number % 10; //get last digit
            if(!mapping.containsKey(digit))
            {
                return false; //if digit isn't confusing return false
            }
            int eqDigit = mapping.get(digit); //get eqDigit from map
            mappedNumber = mappedNumber * 10 + eqDigit; //build reversed number
            number /= 10;
        }
        return mappedNumber != numberCopy; 
    }
    
    private int dfs(int number, int limit) {
        //base
        if(number > limit)
        {
            return 0;
        }
        
        int result = 0;
        //check if incoming number is confusing, if so increase result by 1
        if(isConfusingNumber(number))
        {
            result += 1;
        }
        
        //generate all neighbors and recurse
        for(int suffix : mapping.keySet())
        {
            int neighbor = (number * 10) + suffix;
            result += dfs(neighbor, limit);
        }
        
        return result;
    }
}
