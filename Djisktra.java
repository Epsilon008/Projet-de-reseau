import java.util.*;

class Graph {
    private int V;
    private List<List<Edge>> adjList;

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(destination, weight);
        adjList.get(source).add(edge);
    }

    public class DijkstraResult {
        private String path;
        private int weight;

        public DijkstraResult(String path, int weight) {
            this.path = path;
            this.weight = weight;
        }

        public String getPath() {
            return path;
        }

        public int getWeight() {
            return weight;
        }
    }

    public DijkstraResult calculateDijkstra(int source, int destination) {
        int[] distances = new int[V];
        int[] previous = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);

        distances[source] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
        pq.add(new Vertex(source, 0));

        while (!pq.isEmpty()) {
            Vertex currentVertex = pq.poll();
            int current = currentVertex.vertex;

            if (current == destination) {
                break;  // Arrêter lorsque la destination est atteinte
            }

            if (currentVertex.distance > distances[current]) {
                continue;  // Ignorer les sommets déjà traités avec une distance plus petite
            }

            for (Edge edge : adjList.get(current)) {
                int next = edge.destination;
                int newDistance = distances[current] + edge.weight;

                if (newDistance < distances[next]) {
                    distances[next] = newDistance;
                    previous[next] = current;
                    pq.add(new Vertex(next, newDistance));
                }
            }
        }

        if (previous[destination] == -1) {
            return new DijkstraResult("", -1); // Aucun chemin trouvé
        } else {
            String path = reconstructPath(previous, source, destination);
            int weight = distances[destination];
            return new DijkstraResult(path, weight);
        }
    }

    private String reconstructPath(int[] previous, int source, int destination) {
        StringBuilder path = new StringBuilder();
        int current = destination;

        while (current != source) {
            path.insert(0, current + " -> ");
            current = previous[current];
        }

        path.insert(0, source + " -> ");
        path.delete(path.length() - 4, path.length()); // Supprimer la flèche et l'espace après la destination
        return path.toString();
    }

    private static class Edge {
        private int destination;
        private int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Vertex {
        private int vertex;
        private int distance;

        public Vertex(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}