
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    Client client = new Client();
    public static void main(String[] args) {
    
        // int V = 6;  // Nombre de sommets dans le graphe
        // Graph graph = new Graph(V);

        // // Ajouter les arêtes et les poids
        // graph.addEdge(0, 1, 2);
        // graph.addEdge(0, 2, 4);
        // graph.addEdge(1, 2, 1);
        // graph.addEdge(1, 3, 7);
        // graph.addEdge(2, 4, 3);
        // graph.addEdge(3, 4, 1);
        // graph.addEdge(3, 5, 5);
        // graph.addEdge(4, 5, 2);


        
        // int source = 0;
        // int destination = 5;
        // Graph.DijkstraResult result = graph.calculateDijkstra(source, destination);
        
        // if (result.getPath().isEmpty()) {
        //     System.out.println("Aucun chemin trouvé de " + source + " à " + destination);
        // } else {
        //     System.out.println("Chemin le plus court de " + source + " à " + destination + ": " + String.join(" -> ", result.getPath()));
        //     System.out.println("Poids du chemin le plus court: " + result.getWeight());
        // }

       
         Scanner scanner = new Scanner(System.in);
        Client client = null;
        Taxi taxi = new Taxi(4);
            System.out.println("Choisissez une option :");
            System.out.println("1. Client");
            System.out.println("2. Chauffeur");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            
            if (choix == 1) {
                System.out.print("Entrez votre point de départ : ");
                Integer pointDepart = scanner.nextInt();
                
                System.out.print("Entrez votre destination : ");
                Integer dest = scanner.nextInt();
                
                System.out.print("Entrez le nombre de places voulu : ");
                int nombrePlacesVoulu = scanner.nextInt();
                
                System.out.print("Entrez le prix proposé : ");
                int prixPropose = scanner.nextInt();
                
                client = new Client(pointDepart, dest, nombrePlacesVoulu, prixPropose);
                taxi.ajoutClient(pointDepart, dest, nombrePlacesVoulu, prixPropose);
                taxi.clients
                
                System.out.println("Vous avez été enregistré. Veuillez patienter en attendant qu'un taxi soit trouvé pour votre déplacement.");

                Taxi taxi1 = new Taxi(4);
                taxi1.choixClient(pointDepart, dest, prixPropose, nombrePlacesVoulu);
            } else if (choix == 2) {
                // Option chauffeur
                System.out.println("Fonctionnalité chauffeur non implémentée.");
            } else if (choix == 3) {
              
            } else {
                System.out.println("Option invalide. Veuillez choisir à nouveau.");
            }
        }
        
        


    
}