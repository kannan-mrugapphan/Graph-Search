// 417
// time - O(nm)
// space - O(nm)
class Solution {
    
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; //directions unit vectors
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        HashSet<String> pacificOceanCells = new HashSet<>(); //cells where pacific ocean water can flow into
        HashSet<String> atlanticOceanCells = new HashSet<>(); //cells where atlantic ocean water can flow into
        
        //top left touch pacific
        checkIfOceanWaterCanFlowIntoCell(heights, 0, 0, pacificOceanCells);
        
        //top right touch both oceans
        checkIfOceanWaterCanFlowIntoCell(heights, 0, heights[0].length - 1, pacificOceanCells);
        checkIfOceanWaterCanFlowIntoCell(heights, 0, heights[0].length - 1, atlanticOceanCells);
        
        //bottom left touch both oceans
        checkIfOceanWaterCanFlowIntoCell(heights, heights.length - 1, 0, pacificOceanCells);
        checkIfOceanWaterCanFlowIntoCell(heights, heights.length - 1, 0, atlanticOceanCells);
        
        //bottom right touch atlantic
        checkIfOceanWaterCanFlowIntoCell(heights, heights.length - 1, heights[0].length - 1, atlanticOceanCells);

        for(int i = 1; i < heights.length - 1; i++)
        {
            //0th col touches pacific
            checkIfOceanWaterCanFlowIntoCell(heights, i, 0, pacificOceanCells);
            //last col touches atlantic
            checkIfOceanWaterCanFlowIntoCell(heights, i, heights[0].length - 1, atlanticOceanCells);
        }
        
        for(int i = 1; i < heights[0].length - 1; i++)
        {
            //0th row touches pacific
            checkIfOceanWaterCanFlowIntoCell(heights, 0, i, pacificOceanCells);
            //last row touches atlantic
            checkIfOceanWaterCanFlowIntoCell(heights, heights.length - 1, i, atlanticOceanCells);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i = 0; i < heights.length; i++)
        {
            for(int j = 0; j < heights[0].length; j++)
            {
                String key = i + "-" + j;
                if(atlanticOceanCells.contains(key) && pacificOceanCells.contains(key))
                {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        
        return result;
    }
    
    //time - O(n*m)
    //space - O(n*m) worst case stack size when water can flow into all cells
    private void checkIfOceanWaterCanFlowIntoCell(int[][] heights, int r, int c, HashSet<String> visited) {
        String key = r + "-" + c;
        //logic
        visited.add(key); //marking cell as visited and also tracking that ocean water can flow into this cell
        for(int[] dir : dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            //check if neighbor within bounds
            if(nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length)
            {
                //check if water can flow into neighbor cell - base case
                if(heights[nr][nc] >= heights[r][c] && !visited.contains(nr + "-" + nc))
                {
                    checkIfOceanWaterCanFlowIntoCell(heights, nr, nc, visited);
                }
            }
        }
        return;
    }
}
