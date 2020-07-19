// 269.
// time - O(length of words[] * avg length of word in words[])
// space - O(length of o/p string)

class Solution {
    HashMap<Character, HashSet<Character>> graph;
    int[] inDegree;
    public String alienOrder(String[] words) {
        //edge
        if(words == null || words.length == 0)
        {
            return "";
        }
        graph = new HashMap<>();
        inDegree = new int[26];
        buildGraph(words);
        return bfs();
    }
    
    //time - O(length of words[] * avg length of word in words[])
    //space - constant
    private void buildGraph(String[] words) {
        //edge case - words['z', 'z'] - expected o/p - z
        //create an entry for every letter in words[]
        for(String word : words)
        {
            for(int i = 0; i < word.length(); i++)
            {
                if(!graph.containsKey(word.charAt(i)))
                {
                    graph.put(word.charAt(i), new HashSet<>());
                }
            }
        }
        for(int i = 0; i < words.length - 1; i++)
        {
            String first = words[i]; //for each pair of words
            String second = words[i + 1];
            //edge - i/p words[] = {'abc', 'ab'} - o/p = ""
            if(first.length() > second.length() && first.startsWith(second))
            {
                graph.clear(); //delete all entries so far
            }
            //get the first different character and create an edge between the first word and second word
            for(int j = 0; j < first.length() && j < second.length(); j++)
            {
                char firstChar = first.charAt(j);
                char secondChar = second.charAt(j);
                if(firstChar != secondChar)
                {
                    if(graph.get(firstChar).add(secondChar)) //build the edge if edge is not already present
                    {
                        inDegree[secondChar - 'a']++; //increase indegree of dest
                    }
                    break;
                }
            }
        }
        return;
    }
    
    //time - O(size of o/p string)
    //space - O(size of o/p string)
    private String bfs() {
        Queue<Character> support = new LinkedList<>(); //for bfs
        //for each character in keyset of graph, offer into queue if inDegree[character] = 0
        for(Character letter : graph.keySet())
        {
            if(inDegree[letter - 'a'] == 0)
            {
                support.offer(letter);
            }
        }
        
        StringBuilder result = new StringBuilder(); //return string
        while(!support.isEmpty())
        {
            Character front = support.poll(); //remove thr front and append to result
            result.append(front);
            HashSet<Character> neighbors = graph.get(front);
            if(neighbors != null)
            {
                //for each neighbor of polled element reduce indegree by 1 and add it to queue if indegree is 0
                for(Character neighbor : neighbors)
                {
                    inDegree[neighbor - 'a']--;
                    if(inDegree[neighbor - 'a'] == 0)
                    {
                        support.offer(neighbor);
                    }
                }
            }
        }
        
        //if indegree of any letter remains non zero return empty string
        for(int i = 0; i < 26; i++)
        {
            if(inDegree[i] != 0)
            {
                return "";
            }
        }
        
        return result.toString();
    }
}
