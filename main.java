import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int V = 6;  // Nombre de sommets dans le graphe
        Graph graph = new Graph(V);

        // Ajouter les arêtes et les poids
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 1);
        graph.addEdge(3, 5, 5);
        graph.addEdge(4, 5, 2);


        
        int source = 0;
        int destination = 5;
        Graph.DijkstraResult result = graph.calculateDijkstra(source, destination);
        
        if (result.getPath().isEmpty()) {
            System.out.println("Aucun chemin trouvé de " + source + " à " + destination);
        } else {
            System.out.println("Chemin le plus court de " + source + " à " + destination + ": " + String.join(" -> ", result.getPath()));
            System.out.println("Poids du chemin le plus court: " + result.getWeight());
        }

        // Taxi taxi = new Taxi(4); // Créer une instance de Taxi avec 4 places disponibles
        //         int n = 5;
        //          Set<String> sommet = new HashSet<>();
        //          for (int i = 1; i <= n; i++) {
        //              sommet.add("S" + i);
        //          }
        
        //         // Ajouter des clients avec leurs détails
        //          Client client1 = new Client("A", "B", 2, 20);
        //          Client client2 = new Client("C", "D", 3, 25);
        
        //          // Gérer l'interaction avec les clients
        //          taxi.choixClient("A", "B", 2, 20); // Appliquer votre logique pour choisir d'accepter ou de refuser ce client
        //          taxi.choixClient("C", "D", 3, 25); // Appliquer votre logique pour choisir d'accepter ou de refuser ce client
        
        //         // Appel des méthodes pour gérer les clients suivants selon votre algorithme
        //          taxi.choixClientSuivant();
        //          taxi.choixClientSuivant();
        
        //          // Déposer un client à sa destination
        //         taxi.depotClient("B");
        //         taxi.depotClient("D");


    }
}