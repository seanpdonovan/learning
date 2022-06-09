import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Topological sort of a directed graph is a linear ordering of its vertices such that
 * for every directed edge (U, V) from vertex U to V, U comes before V in the ordering.
 * 
 * Only works on a directed acyclic graph (DAG).
 * 
 * Time complexity: O(V + E) since each vertex and each edge will be processed once.
 * Space complexity: O(V + E) since we are storing all of the edges for each vertex in a list.
 */
class GraphTopologicalSort {
    
    /**
     * Given list of dependencies (e.g. {1,0} ==> 0 depends on 1) return the list of nodes in order.
     * @param vertices The number of vertices
     * @param edges The pairs of nodes. First is parent, second is child.
     */
    public static List<Integer> topologicalSort(int vertices, int[][] edges) {

        List<Integer> sortedOrder = new ArrayList<>();
        if (vertices <= 0) return sortedOrder;

        // Keep track of how many incoming edges a vertex has.
        Map<Integer, Integer> inDegree = new HashMap<>();
        // Adjacency list implementation of graph.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Initialize maps with empty values.
        for (int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }

        // Populate inDegree and graph.
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        // Add initial source values to queue.
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        while (!sources.isEmpty()) {
            
            // Add vertex to sorted order.
            int vertex = sources.poll();
            sortedOrder.add(vertex);

            // Decrement inDegree for child nodes.
            // Add to queue if inDegree becomes 0.
            List<Integer> children = graph.get(vertex);
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }

        // A cycle exists, so topological sort is not possible.
        if (sortedOrder.size() != vertices) {
            return new ArrayList<>();
        }

        return sortedOrder;
    }
}
