// 839.
// time - O(length of strs * length of word in strs)
// space - O(length of strs for call stack)
class Solution {
    public int numSimilarGroups(String[] strs) {
        //edge
        if(strs == null || strs.length == 0)
        {
            return 0;
        }
        
        boolean[] visited =  new boolean[strs.length]; //visited array to avoid reprocessing
        int result = 0; //return value
        
        for(int i = 0; i < strs.length; i++)
        {
            if(!visited[i])
            {
                //ith word is unvisited
                //call dfs on this word and sink all the similar words
                //increment result for this group
                dfs(strs, visited, i); 
                result++;
            }
        }
        
        return result;
    }
    
    //time - O(length of word)
    //space - const
    private boolean isSimilar(String word1, String word2) {
        if(word1.length() != word2.length())
        {
            return false; //words of unequal length are dissimilar
        }
        
        List<Integer> mismatchIndices = new ArrayList<>(); //tracks indices at which char mismatch occurs
        
        for(int i = 0; i < word1.length(); i++)
        {
            if(word1.charAt(i) != word2.charAt(i))
            {
                mismatchIndices.add(i); //track i in the list
                if(mismatchIndices.size() > 2)
                {
                    return false; //only 2 chars can be swapped at max
                }
            }
        }
        
        if(mismatchIndices.isEmpty() || (mismatchIndices.size() == 2 && word1.charAt(mismatchIndices.get(0)) == word2.charAt(mismatchIndices.get(1)) && word1.charAt(mismatchIndices.get(1)) == word2.charAt(mismatchIndices.get(0))))
        {
            return true; //1 swap or word1 = word2
        }
        
        return false; //mismatch at only index
    }
    
    private void dfs(String[] strs, boolean[] visited, int index) {
        //base
        if(visited[index])
        {
            return;
        }
        //logic
        visited[index] = true; //mark current as visited
        for(int i = 0; i < strs.length; i++)
        {
            if(i == index)
            {
                continue;
            }
            if(isSimilar(strs[index], strs[i]))
            {
                //i is similar to index - recurse
                dfs(strs, visited, i);
            }
        }
        return;
    }
}
