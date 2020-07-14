// 339.
// time - total number of nested elements
// space - longest nested child - depth of call stack
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        //edge
        if(nestedList == null || nestedList.size() == 0)
        {
            return 0;
        }
        int[] result = new int[1]; //return value
        for(NestedInteger current : nestedList)
        {
            dfs(current, 1, result); //depth starts from 1
        }
        return result[0];
    }
    
    private void dfs(NestedInteger current, int depth, int[] result) {
        //base - isInteger() is a terminating condition
        //logic
        if(current.isInteger())
        {
            result[0] += (current.getInteger() * depth);
            return;
        }
        else //recurse on each nested integer in the list at this level
        {
            for(NestedInteger child : current.getList())
            {
                dfs(child, depth + 1, result); //nested element is at a higher depth (1 higher than caller)
            }
        }
        return;
    }
}
