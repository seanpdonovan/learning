import java.util.LinkedList;
import java.util.Queue;

/*
 * Undirected graph definition using adjacency list implementation.
 */
class Graph {

    private Map<Integer, Set<Integer>> graph;
    private int v;

    public Graph(int v) {
        this.graph = new HashMap<>();
        this.v = v;
        for (int i = 0; i < v; i++) {
            this.graph.put(i, new HashSet<>());
        }
    }

    public void addEdge(int src, int dest) {
        graph.get(src).add(dest);

        // Remove if directed.
        graph.get(dest).add(src);
    }

    public void printGraph() {
        for (int i = 0; i < v; i++) {
            System.out.println("Adjacency list of vertex " + i);

            Iterator set = graph.get(i).iterator();
            while (set.hasNext()) {
                System.out.println(set.next() + " ");
            }
        }
    }

    public boolean containsEdge(int src, int dest) {
        return graph.get(src).contains(dest);
    }

    public boolean bfs(int src, int dest) {

        // Keep track of visited nodes.
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();

        // Mark source node as visited.
        visited.add(src);
        queue.add(src);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == dest) {
                return true;
            }

            for (Integer neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    // Traverse graph from starting node using recursive DFS.
    public void dfsRecursive(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursiveHelper(start, visited);
    }

    private void dfsRecursiveHelper(int current, Set<Integer> visited) {
        
        // Mark as visited.
        visited.add(current);
        
        // Process node.
        System.out.println(current);
        
        // Make recursive call for each unvisited neighbor.
        for (Integer neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursiveHelper(neighbor, visited);
            }
        }
    }

    // Traverse graph from starting node using iterative DFS.
    public void dfsIterative(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println(current);
                for (Integer neighbor : graph.get(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }
}
