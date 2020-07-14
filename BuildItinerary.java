// 332.
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        //edge
        if(tickets == null || tickets.size() == 0)
        {
            return new ArrayList<>();
        }
        HashMap<String, PriorityQueue<String>> graph = buildGraph(tickets);
        List<String> result = new ArrayList<>();
        buildItineraryRecursive(graph, result, "JFK");
        return result;
    }
    
    //time - O(E) - number of tickets
    private HashMap<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> graph = new HashMap<>(); //graph
        for(List<String> ticket : tickets) //for each edge(ticket)
        {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if(!graph.containsKey(from)) //if from is absent in graph, create a new pq corresponding to from
            {
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to); //add TO to corresponding pq
        }
        return graph;
    }
    
    //time - O(E) - number of tickets
    //space - O(length of o/p list for stack)
    private List<String> buildItineraryIterative(HashMap<String, PriorityQueue<String>> graph) {
        Stack<String> support = new Stack<>();
        List<String> result = new ArrayList<>(); //return list
        support.push("JFK"); //src = jfk
        
        while(!support.isEmpty())
        {
            String top = support.peek();
            PriorityQueue<String> possibleDests = graph.get(top); //all possible places reachable from stack top 
            //if no more places to explore poll from stack and add to list
            if(possibleDests == null || possibleDests.isEmpty())
            {
                result.add(0, support.pop());
            }
            else //else the remove from pq and add to stack top and go to next iteration
            {
                support.push(possibleDests.poll());
            }
        }
        
        return result; //reverse list is not needed as popped elements are added to 0th index of list every time
    }
    
    //time - O(E) - number of tickets
    //space - O(length of o/p list for call stack)
    private void buildItineraryRecursive(HashMap<String, PriorityQueue<String>> graph, List<String> result, String src) {
        //logic
        while(graph.get(src) != null && !graph.get(src).isEmpty())
        {
            buildItineraryRecursive(graph, result, graph.get(src).poll());
        }
        //base
        result.add(0, src); 
    }
}
