//473.
// time - O(4^n) each match stick can be placed in 4 possible sides
class Solution {
    public boolean makesquare(int[] matchsticks) {
        
        //edge - atleast 4 matchsticks needed
        if(matchsticks == null || matchsticks.length < 4)
        {
            return false;
        }
        
        int sum = 0;
        for(int stick : matchsticks)
        {
            sum += stick;
        }
        //edge - total length of all matchsticks given must be divisible by 4
        if(sum % 4 != 0)
        {
            return false;
        }
        
        int sideLength = sum / 4; //required side length of square
        int[] sides = new int[4]; //dummy array to mock placement of sticks
        return dfs(matchsticks, sides, sideLength, 0);
    }
    
    private boolean dfs(int[] matchsticks, int[] sides, int sideLength, int index) {
        //base
        //all sticks consumed
        if(index == matchsticks.length)
        {
            //square formed
            if(sides[1] == sides[0] && sides[1] == sides[2] && sides[2] == sides[3] && sides[0] == sideLength)
            {
                return true;
            }
            return false; 
        }
        //logic
        //current match stick at index can be placed at any of the 4 sides
        for(int i = 0; i < 4; i++)
        {
            //if the side can accomodate the current stick
            if(sides[i] + matchsticks[index] <= sideLength)
            {
                //choose the stick
                sides[i] += matchsticks[index];
                if(dfs(matchsticks, sides, sideLength, index + 1)) //recurse
                {
                    return true;
                }
                sides[i] -= matchsticks[index]; //backtrack
            }
        }
        return false;
    }
}
