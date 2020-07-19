// 277.
//time - O(n)
//space - constant
public class Solution extends Relation {
    public int findCelebrity(int n) {
        //edge
        if(n <= 0)
        {
            return -1;
        }
        
        int potentialCelebrity = 0; //assume 0th person as celeb initially
        //for all others
        for(int i = 1; i < n; i++)
        {
            //check if i doesnt know the potential answer or the potential answer knows i
            //if so potentail is made as i
            if(!knows(i, potentialCelebrity) || knows(potentialCelebrity, i))
            {
                potentialCelebrity = i;
            }
        }
        
        //in the next pass verify that potentail celebrity doesnt know any one else and everyone knows celeb
        for(int i = 0; i < n; i++)
        {
            if(i == potentialCelebrity)
            {
                continue;
            }
            if(knows(potentialCelebrity, i) || !knows(i, potentialCelebrity))
            {
                return -1;
            }
        }
        
        return potentialCelebrity;
    }
}

// 997.
// time - O(V + E)
// space - O(V)
class Solution {
    public int findJudge(int N, int[][] trust) {    
        int[] inDegree = new int[N]; //index i tracks the # of people who trust the i th person
        int[] outDegree = new int[N]; //index i tracks the number of people whom the ith person trust
        
        //the town judge should have inDegree n - 1 i.e every trust the judge and outDegree 0 i.e judge trusts no one
        for(int[] edge : trust) 
        {
            int src = edge[0]; //a trusts b is given as [a, b] - outgoing for a and incoming for b
            int dest = edge[1];
            outDegree[src - 1] += 1;
            inDegree[dest - 1] += 1;
        }
        
        for(int i = 0; i < N; i++)
        {
            if(inDegree[i] == N - 1 && outDegree[i] == 0)
            {
                return i + 1; //0 indexing on people id
            }
        }
        
        return -1;
    }
}
