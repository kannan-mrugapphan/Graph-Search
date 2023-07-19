// 269.
class Solution {
    public String alienOrder(String[] words) {
        //edge
        if(words == null || words.length == 0)
        {
            return "";
        }
        if(words.length == 1)
        {
            //only 1 word, all unique chars present will be part of result
            //eg: [abcd] -> abcd
            //eg: [aba] -> ab
            HashSet<Character> letters = new HashSet<>();
            for(int i = 0; i < words[0].length(); i++)
            {
                letters.add(words[0].charAt(i));
            }

            StringBuilder result = new StringBuilder();
            for(Character ch : letters)
            {
                result.append(ch);
            }

            return result.toString();
        }

        int[] indegrees = new int[26]; //all letters in english alphabet can be part of result order
        HashMap<Character, List<Character>> graph = buildGraph(words, indegrees);
        StringBuilder result = findTopologicalSort(graph, indegrees);
        return result.toString();
    }

    //time - O(n) where n is length of words[]
    //space - constant
    private HashMap<Character, List<Character>> buildGraph(String[] words, int[] indegrees)
    {
        HashMap<Character, List<Character>> graph = new HashMap<>(); 

        for(int i = 0; i < words.length - 1; i++)
        {
            //returns false if invalid case is found, return empty graph
            if(!processPair(words[i], words[i + 1], graph, indegrees))
            {
                return new HashMap<>();
            }
        }

        return graph;
    }

    //time - O(m+n) where m is length of word1 and n is length of word2
    //space - constant
    private boolean processPair(String current, String next, HashMap<Character, List<Character>> graph, int[] indegrees)
    {
        //eg: abc, ab -> invalid test case
        //whole test case is corrupted, return topological order should be empty string
        //so graph should be cleared out
        if(current.length() > next.length() && current.startsWith(next))
        {
            return false;
        }
        
        //all letters present in both words should be part of topological order i.e should be included in graph
        for(int i = 0; i < current.length(); i++)
        {
            if(!graph.containsKey(current.charAt(i)))
            {
                graph.put(current.charAt(i), new ArrayList<>());
            }
        }
        for(int i = 0; i < next.length(); i++)
        {
            if(!graph.containsKey(next.charAt(i)))
            {
                graph.put(next.charAt(i), new ArrayList<>());
            }
        }

        //edge exists between characters that mismatch for the first time
        //eg: ab, abc -> no edge
        //eg: wrt, wrf -> t -> f
        //eg: er, ett -> r -> t
        int minLength = Math.min(current.length(), next.length());
        for(int i = 0; i < minLength; i++)
        {
            //1st mismatch found
            if(current.charAt(i) != next.charAt(i))
            {
                graph.get(current.charAt(i)).add(next.charAt(i));
                indegrees[next.charAt(i) - 'a']++; //directed edge between current[i] -> next[i]
                break;
            }
        }

        return true;
    }

    //time - O(v+e)
    //space - O(v)
    private StringBuilder findTopologicalSort(HashMap<Character, List<Character>> graph, int[] indegrees)
    {
        Queue<Character> support = new LinkedList<>(); //to do topological sort
        for(char ch = 'a'; ch <= 'z'; ch++)
        {
            //if current char is part of graph and has no depenedency (i.e indegree = 0)
            if(graph.containsKey(ch) && indegrees[ch - 'a'] == 0)
            {
                support.offer(ch); //process current char in level 0 
            }
        }

        StringBuilder result = new StringBuilder();
        while(!support.isEmpty())
        {
            Character current = support.poll();
            result.append(current); //all dependecies of current resolved

            if(graph.containsKey(current))
            {
                List<Character> neighbors = graph.get(current);
                for(Character neighbor : neighbors)
                {
                    indegrees[neighbor - 'a']--; //current -> neighbor edge resolved
                    if(indegrees[neighbor - 'a'] == 0)
                    {
                        //all incoming edges on neighbor resolved
                        support.offer(neighbor);
                    }
                }
            }
        }

        for(char ch = 'a'; ch <= 'z'; ch++)
        {
            //if current char is part of graph and has all depenedencies are not resolved (i.e indegree = 0)
            if(graph.containsKey(ch) && indegrees[ch - 'a'] != 0)
            {
                return new StringBuilder(); //no order possible
            }
        }

        return result;
    }
}
