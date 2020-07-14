// 565.
// time - O(n) - dfs for each unvisited element
// space - O(n) - hashset

//eg 
// nums = [5,4,0,3,1,6,2]
// dfs at 0th index  - 5 - 6 - 2 - 0
// dfs at 1st index - 4 - 1
// 2nd index - 0 - 5 - 6 - 2
// 3rd index - 3
// 4th index - 1 - 4 - 3
// 5th index - 6 - 2 - 0 - 5
// 6th index - 2 - 0 - 5 - 6
// o/p - longest possible - 4
// observation - sequences are cyclic  - if a number was already seen then no need to process again


class Solution {
    int maxSize;
    public int arrayNesting(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        Set<Integer> result = new HashSet<>(); //tracks the result set s
        maxSize = 0; //result
        HashSet<Integer> visited = new HashSet<>(); 
        for(int i = 0; i < nums.length; i++) //try each element as the first element in s
        {
            if(!visited.contains(nums[i])) //avoid processing elements already processed
            {
                dfs(nums, result, i); 
                for(Integer number : result) //after dfs returns add all elements in result set to visited set
                {
                    visited.add(number);
                }
                result.clear(); //clear result set after each recursive call
            }
        }
        return maxSize;
    }
    
    //time - O(n)
    //space - O(n) - max call stack size
    private void dfs(int[] nums, Set<Integer> result, int index) {
        //base
        if(result.contains(nums[index])) //if the result contains element at incoming index return after updating size
        {
            maxSize = Math.max(maxSize, result.size());
            return;
        }
        //logic
        result.add(nums[index]); //add number at incoming index into result and recurse with new index as number added
        dfs(nums, result, nums[index]);
        return;
    }
}
