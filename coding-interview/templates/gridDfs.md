# Grid DFS

## Example Problems

- Number of Islands
- Pacific Atlantic Water Flow

## Template

```java
main() {
    // Call DFS method with starting point.
    dfs(cell);
    return value;
}

dfs(cell) {
    // 1. Check stopping conditions.
    if cell out of bounds, visited, or not target value return

    // 2. Process the current cell.
    increment count
    mark cell as visited

    // 3. Call DFS on adjacent cells.
    dfs(top)
    dfs(right)
    dfs(bottom)
    dfs(left)
}
```

## Number of Islands

```java
class Solution {
    
    public int numIslands(char[][] grid) {
        
        int count = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        
        // 1. Check stopping conditions.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;

        // 2. Process current cell.
        grid[i][j] = '0';

        // 3. Call DFS on adjacent cells.
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
```

## Pacific Atlantic Water Flow

```java
class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        // Cell is reachable from pacific.
        boolean[][] pacific = new boolean[rows][cols];

        // Cell is reachable from atlantic.
        boolean[][] atlantic = new boolean[rows][cols];

        // Process top and bottom rows of grid.
        for (int c = 0; c < cols; c++) {
            dfs(0, c, pacific, heights, heights[0][c]);
            dfs(rows - 1, c, atlantic, heights, heights[rows - 1][c]);
        }

        // Process left and right columns of grid.
        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pacific, heights, heights[r][0]);
            dfs(r, cols - 1, atlantic, heights, heights[r][cols - 1]);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Cell is reachable from both pacific and atlantic.
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<>();
                    foo.add(i);
                    foo.add(j);
                    result.add(cell);
                }
            }
        }

        return result;
    }

    private void dfs(int r, int c, boolean[][] visited, int[][] heights, int prevHeight) {
        // 1. Check stopping conditions.
        // Cell is out of bounds.
        if (r < 0 || r >= heights.length || c < 0 || c >= heights[0].length)
            return;
        // Cell is already visited.
        if (visited[r][c])
            return;
        // Cell's height is less than previous cell's height.
        if (heights[r][c] < prevHeight)
            return;

        // 2. Process the current cell.
        visited[r][c] = true;

        // 3. Call DFS on adjacent cells.
        dfs(r + 1, c, visited, heights, heights[r][c]);
        dfs(r - 1, c, visited, heights, heights[r][c]);
        dfs(r, c + 1, visited, heights, heights[r][c]);
        dfs(r, c - 1, visited, heights, heights[r][c]);
    }
}
```