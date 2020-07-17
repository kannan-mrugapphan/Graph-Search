// 301.
// time - O(2^n)  -> worst case -> s = "((("
//space - O(2^n)
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        //edge
        if(s == null)
        {
            return new ArrayList<>();
        }
        
        List<String> result = new ArrayList<>();
        Queue<String> support = new LinkedList<>(); //for bfs
        HashSet<String> visited = new HashSet<>(); //equal strings can be formed at different levels, so mark visited to avoid re-processing
        support.offer(s); //start with i/p s by adding to queue and marking as visited
        visited.add(s);
        boolean flag = false; //tracks whether a valid string is formed at a particular level or not
        
        while(!support.isEmpty() && !flag)
        {
            int levelSize = support.size(); //process all nodes in the current level
            for(int x = 0; x < levelSize; x++)
            {
                String current = support.poll(); 
                if(isValid(current))
                {
                    //if current string is valid
                    result.add(current);
                    flag = true; //indicates that a valid string is formed at current level
                }
                if(!flag)
                {
                    //no valid string in this level so far, add child nodes into queue to process in next level
                    //so remove 1 bracket from current string andd add to queue if not visited
                    for(int i = 0; i < current.length(); i++)
                    {
                        char ch = current.charAt(i); 
                        if(Character.isLetter(ch)) //skip if char at i is letter
                        {
                            continue;
                        }
                        else //create a child string without char ch and add to queue if not visited
                        {
                            String child = current.substring(0, i) + current.substring(i + 1);
                            if(!visited.contains(child))
                            {
                                support.offer(child);
                                visited.add(child);
                            }
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    //for each ( increase count by 1, for each ) reduce count by 1 if count is already positive, at the end check if count = 0 to return true
    //time - O(n)
    //space - O(1)
    private boolean isValid(String current) {
        int count = 0;
        for(int i = 0; i < current.length(); i++)
        {
            char ch = current.charAt(i); 
            if(Character.isLetter(ch))
            {
                continue;
            }
            else if(ch == '(')
            {
                count++;
            }
            else //ch = )
            {
                if(count > 0)
                {
                    count--;
                }
                else
                {
                    return false;
                }
            }
        }
        return (count == 0);
    }
}
