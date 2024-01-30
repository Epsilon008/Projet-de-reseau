import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Taxi {
    String accepter = "Accepté";
    String refuse = "Refusé";
    List<String> table_destination;
    int nombre_place_disponible;

    Map<Integer, Integer> Cp = new HashMap<>(); // Cp dictionnaire ayant comme clé les points de destinations et comme
                                                // valeur correspondante le nombre de place
    public static Map<Integer, List<Integer>> propositions = new HashMap<>(); // Pour le stockage des propositions des
                                                                              // clients présents au point de ramassage.
                                                                              // Elle prendra comme valeurs les vecteurs
                                                                              // clients.

    List<Integer> S = new ArrayList<>();
    int placesDisponibles = 4;

    public Taxi(int placesDisponibles) {
        this.nombre_place_disponible = placesDisponibles;
        this.table_destination = new ArrayList<>();
        this.Cp = new HashMap<>();
        this.S = new ArrayList<>();
    }

    int autoIncrementKey = 1;

    public void choixClient(int Sn, int Sm, int Pi, int Ni) {
        for (int i = 1; i <= 3; i++) {
            Iterator<Map.Entry<Integer, List<Object>>> iterator = Client.C.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, List<Object>> entry = iterator.next();
                int key = entry.getKey();
                List<Object> parametre = entry.getValue();
                int pointDepart = ((Number) parametre.get(0)).intValue();
                int pointDestination = ((Number) parametre.get(1)).intValue();
                int prixProposé = ((Number) parametre.get(3)).intValue();
                int nbreplaceDemandé = ((Number) parametre.get(2)).intValue();
    
                choixPrix(prixProposé, nbreplaceDemandé, pointDepart, pointDestination);
            }
        }
    
        clientPris(propositions);
    }


    private void choixPrix(int Pi, int Ni, int Sn, int Sm) {
        Graph graph = new Graph(11);
        graph.addEdge(0, 1, 300);
        graph.addEdge(0, 2, 300);
        graph.addEdge(1, 2, 300);
        graph.addEdge(1, 3, 300);
        graph.addEdge(2, 4, 300);
        graph.addEdge(2, 3, 300);
        graph.addEdge(3, 4, 300);
        graph.addEdge(3, 5, 300);
        graph.addEdge(4, 5, 300);
        graph.addEdge(5, 6, 300);
        graph.addEdge(5, 7, 300);
        graph.addEdge(6, 7, 300);
        graph.addEdge(6, 2, 300);
        graph.addEdge(7, 8, 300);
        graph.addEdge(8, 9, 300);
        graph.addEdge(9, 10, 300);
        graph.addEdge(10, 8, 300);

        Graph.DijkstraResult result = graph.calculateDijkstra(Sn, Sm);
        String path = result.getPath();
        int P = result.getWeight();
        if ((P / Pi) >= P) {
            // Insérons dans propositions tous les paramètres du client correspondant
            List<Integer> parametreClient = new ArrayList<>();
            parametreClient.add(Pi);
            parametreClient.add(Ni);
            parametreClient.add(Sn);
            parametreClient.add(Sm);
            propositions.put(autoIncrementKey, parametreClient);
            autoIncrementKey++;

        } else {
            System.out.println(refuse);
        }

    }

    public String clientPris(Map<Integer, List<Integer>> propositions) {

        // classer par ordre décroissant de Pi le dictionnaire proposition

        // Convertir le dictionnaire en une liste d'objets Map.Entry
        List<Map.Entry<Integer, List<Integer>>> list = new ArrayList<>(propositions.entrySet());

        // Trier la liste en ordre décroissant en se basant sur le premier élément de
        // chaque objet Map.Entry
        Collections.sort(list, new Comparator<Map.Entry<Integer, List<Integer>>>() {
            public int compare(Map.Entry<Integer, List<Integer>> o1, Map.Entry<Integer, List<Integer>> o2) {
                return o2.getValue().get(0).compareTo(o1.getValue().get(0));
            }
        });

        // Convertir la liste triée en un nouveau dictionnaire
        Map<Integer, List<Integer>> propositionsTrié = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : list) {
            propositionsTrié.put(entry.getKey(), entry.getValue());
        }

        // Afficher le dictionnaire trié
        // System.out.println(propositionsTrié);

        // Retournons le premier du dictionnaire propositions trié
        List<Integer> premierElement = new ArrayList<>(propositionsTrié.values()).get(0);
        // System.out.println("Premier élément : " + premierElement);

        this.placesDisponibles = this.placesDisponibles - premierElement.get(1);

        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 300);
        graph.addEdge(0, 2, 300);
        graph.addEdge(1, 2, 300);
        graph.addEdge(1, 3, 300);
        graph.addEdge(2, 4, 300);
        graph.addEdge(2, 3, 300);
        graph.addEdge(3, 4, 300);
        graph.addEdge(3, 5, 300);
        graph.addEdge(4, 5, 300);
        graph.addEdge(5, 6, 300);
        graph.addEdge(5, 7, 300);
        graph.addEdge(6, 7, 300);
        graph.addEdge(6, 2, 300);
        graph.addEdge(7, 8, 300);
        graph.addEdge(8, 9, 300);
        graph.addEdge(9, 10, 300);
        graph.addEdge(10, 8, 300);

        Graph.DijkstraResult result = graph.calculateDijkstra(premierElement.get(2), premierElement.get(3));
        String path = result.getPath();
        table_destination.add(path);
        Cp.put(premierElement.get(3), premierElement.get(1));
        propositions.clear();
        propositionsTrié.clear();
        return accepter;

        // return premierElement;
    }

    

    

        public void choixClientSuivant(int Sn, int Sm, int Pi, int Ni) {
 // Logique pour choisir le prochain client à prendre en charge en fonction de
        // l'algorithme décrit

            // int nombrePlaceDisponible;
            // String[] tableDestination;
    
            while (table_destination.size() > 0 && placesDisponibles > 0) {
                max(table_destination); // Représente le plus long chemin à parcourir dans la table de destination
                for (int i = 1; i <= 3; i++) {
                    for (Map.Entry<Integer, List<Object>> entry : Client.C.entrySet()) {
                        if (Ni > placesDisponibles) {
                            System.out.println(refuse);   
                        } else {
                            // Calculer Djikstra et faire une comparaison
                        
                            Graph graph = new Graph(11);
                            graph.addEdge(0, 1, 300);
                            graph.addEdge(0, 2, 300);
                            graph.addEdge(1, 2, 300);
                            graph.addEdge(1, 3, 300);
                            graph.addEdge(2, 4, 300);
                            graph.addEdge(2, 3, 300);
                            graph.addEdge(3, 4, 300);
                            graph.addEdge(3, 5, 300);
                            graph.addEdge(4, 5, 300);
                            graph.addEdge(5, 6, 300);
                            graph.addEdge(5, 7, 300);
                            graph.addEdge(6, 7, 300);
                            graph.addEdge(6, 2, 300);
                            graph.addEdge(7, 8, 300);
                            graph.addEdge(8, 9, 300);
                            graph.addEdge(9, 10, 300);
                            graph.addEdge(10, 8, 300);
                    
                            Graph.DijkstraResult result = graph.calculateDijkstra(Sn, Sm);
                            String shortestPath = result.getPath();
                            //String shortestPath = calculDjikstra(Sn, Sm);
                            if (max(table_destination).contains(shortestPath)) {
                                if (max(table_destination) == shortestPath) {
                                    //choixPrix(Pi, Ni);
                                    choixPrix( Pi, Ni, Sn, Sm);
                                } else {
                                    System.out.println(refuse) ;
                                }
                            } else {
                                if (shortestPath == max(table_destination)) {
                                    choixPrix(Pi, Ni, Sn, Sm);
                                } else {
                                    System.out.println( refuse);
                                }
                            }
                        }
                    }
                }
                clientPris(propositions);
    
                // Véhicule plein
                if (placesDisponibles == 0) {
                    depotClient(min(table_destination)); // On dépose les clients à partir du client ayant la plus petite destination
                }
            }
        }
    
        private String max(List<String> listeChaine) {
            if (listeChaine == null || listeChaine.size() == 0) {
                // Vérification pour un tableau vide ou nul
                return null;
            } else {
                String plusLongueChaine = listeChaine.get(0);
                for (String chaine : listeChaine) {
                    if (chaine.length() > plusLongueChaine.length()) {
                        plusLongueChaine = chaine;
                    }
                }
                return plusLongueChaine;
            }       
    }

    private String min(List<String> listeChaine) {
        if (listeChaine == null || listeChaine.size() == 0) {
            // Vérification pour un tableau vide ou nul
            return null;
        } else {
            String plusPetiteChaine = listeChaine.get(0);
            for (String chaine : listeChaine) {
                if (chaine.length() < plusPetiteChaine.length()) {
                    plusPetiteChaine = chaine;
                }
            }
            return plusPetiteChaine;
        }       
}
//     private String min(List<String> listeDeChaines) {
//         String plusPetit = listeDeChaines.get(0);
//         for (String chaine : listeDeChaines) {
//             if (chaine.length() < plusPetit.length()) {
//                 plusPetit = chaine;
//             }
//         }
//         return plusPetit;
//     }

    public void depotClient(String destination) {

        for (Map.Entry<Integer, Integer> entry : Cp.entrySet()) {
            for (Integer Si : S) {
                Integer cpi = entry.getKey();
                if (Si.equals(cpi)) {
                    // Supprimer cpi de Cp
                    Cp.remove(cpi);
                    // Supprimer Djikstra(., cpi) de tableDestination
                    for (int i = table_destination.size() - 1; i >= 0; i--) {
                        String chaine = table_destination.get(i);
                        char dernierCaractere = chaine.charAt(chaine.length() - 1);
                        if (dernierCaractere==cpi) {
                            table_destination.remove(i);
                        }
                    }
                    // Mise à jour de nombrePlaceDisponible
                    nombre_place_disponible += getNombrePlacesClient(cpi); // Obtenez le nombre de places du client cpi
                }
            }
        }
    }

    private int getNombrePlacesClient(Integer cpi) {
        Integer Place_Prises = Cp.get(cpi);
        return Place_Prises;
    }
}

class Client {
    // C étant le dictionnaire de tous les clients
    public static Map<Integer, List<Object>> C = new HashMap<>();
    int pointDepart;
    int destination;
    int nombrePlacesVoulu;
    int prixPropose;
    int autoIncrementKey = 1;

    public Client(int pointDepart, int destination, int nombrePlacesVoulu, int prixPropose) {
        this.C = new HashMap<>();
        this.pointDepart = pointDepart;
        this.destination = destination;
        this.nombrePlacesVoulu = nombrePlacesVoulu;
        this.prixPropose = prixPropose;
    }

    public void ajoutClient(Integer Sn, Integer Sm, int Pi, int Ni) {
        List<Object> parametreClient = new ArrayList<>();
        parametreClient.add(pointDepart);
        parametreClient.add(destination);
        parametreClient.add(nombrePlacesVoulu);
        parametreClient.add(prixPropose);
        C.put(autoIncrementKey, parametreClient);
        autoIncrementKey++;

    }

}


