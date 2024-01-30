import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Taxi {
    private List<Client> clients;
    private List<Client> clientsPotentiels;
    private List<Client> clientsEmbarques;
    private List<String> table_destination;
    private int nombre_place_disponible = 4;
    private List<Integer> sommets;
    private List<Integer> pointArret;

    public Taxi(List<Client> clients, List<Client> clientsPotentiels, List<Client> clientsEmbarques,
            List<String> table_destination, int nombre_place_disponible, List<Integer> sommets,
            List<Integer> pointArret) {
        this.clients = clients;
        this.clientsPotentiels = clientsPotentiels;
        this.clientsEmbarques = clientsEmbarques;
        this.table_destination = table_destination;
        this.nombre_place_disponible = nombre_place_disponible;
        this.sommets = sommets;
        this.pointArret = pointArret;
    }

    public List<Client> getClientsPotentiels() {
        return clientsPotentiels;
    }

    public void setClientsPotentiels(List<Client> clientsPotentiels) {
        this.clientsPotentiels = clientsPotentiels;
    }

    public List<Client> getClientsEmbarques() {
        return clientsEmbarques;
    }

    public void setClientsEmbarques(List<Client> clientsEmbarques) {
        this.clientsEmbarques = clientsEmbarques;
    }

    public List<Integer> getPointArret() {
        return pointArret;
    }

    public void setPointArret(List<Integer> pointArret) {
        this.pointArret = pointArret;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<String> getTable_destination() {
        return table_destination;
    }

    public void setTable_destination(List<String> table_destination) {
        this.table_destination = table_destination;
    }

    public int getNombre_place_disponible() {
        return nombre_place_disponible;
    }

    public void setNombre_place_disponible(int nombre_place_disponible) {
        this.nombre_place_disponible = nombre_place_disponible;
    }

    public List<Integer> getSommets() {
        return sommets;
    }

    public void setSommets(List<Integer> sommets) {
        this.sommets = sommets;
    }

    public Taxi(List<Client> clients, List<String> table_destination, int nombre_place_disponible,
            List<Integer> sommets) {
        this.clients = clients;
        this.table_destination = table_destination;
        this.nombre_place_disponible = nombre_place_disponible;
        this.sommets = sommets;
    }

    public Taxi() {
    }

    public void ajoutClient(Client client) {
        clients.add(client);
    }

    private void choixPrix(Client client) {
        Graph.DijkstraResult result = createGraph(client.getPointDepart(), client.getDestination());
        String plusCourtChemin = result.getPath();
        int poidsPlusCourtChemin = result.getWeight();
        client.setBeneficeClient((client.getPrixPropose() / client.getNombrePlacesVoulu()) - poidsPlusCourtChemin);
        if (client.getBeneficeClient() >= 0) {
            clientsPotentiels.add(client);
        } else {
            System.out.println("refuse");
        }

    }

    public List<Client> clientPris(List<Client> clientsPotentiels) {

        Collections.sort(clientsPotentiels, Comparator.comparingInt(Client::getBeneficeClient).reversed());
        for (Client client : clientsPotentiels) {
            if (clientsEmbarques.size() == 0) {
                Client clientEmbarque = clientsPotentiels.get(0);
                clientsEmbarques.add(clientEmbarque);
                Graph.DijkstraResult result = createGraph(clientEmbarque.getPointDepart(),
                        clientEmbarque.getDestination());
                String plusCourtChemin = result.getPath();
                table_destination.add(plusCourtChemin);
                pointArret.add(clientEmbarque.getDestination());
                nombre_place_disponible = nombre_place_disponible - clientEmbarque.getNombrePlacesVoulu();
                clientsPotentiels.remove(client);

            } else {
                String plusLongChemin = max(table_destination);
                Graph.DijkstraResult result = createGraph(client.getPointDepart(), client.getDestination());
                String plusCourtChemin = result.getPath();
                if (client.getNombrePlacesVoulu() > nombre_place_disponible) {
                    if (plusLongChemin.contains(plusCourtChemin)) {
                        clientsEmbarques.add(client);
                        clientsPotentiels.remove(client);
                        nombre_place_disponible = nombre_place_disponible - client.getNombrePlacesVoulu();
                    } else if (plusCourtChemin.contains(plusLongChemin)) {
                        clientsPotentiels.remove(client);
                        clientsEmbarques.add(client);
                        nombre_place_disponible = nombre_place_disponible - client.getNombrePlacesVoulu();
                    } else {
                        System.out.println("Error");
                    }
                } else {
                    System.out.println("Error");
                }
            }
        }

        return clientsEmbarques;

        // } else {
        // if (nombre_place_disponible == 0) {
        // System.out.println("Error");
        // } else {
        // String plusLongChemin = max(table_destination);
        // for (Client client : clientsPotentiels) {
        // if (client.getNombrePlacesVoulu() > nombre_place_disponible) {
        // System.out.println("Error");
        // } else {
        // Graph.DijkstraResult result = createGraph(client.getPointDepart(),
        // client.getDestination());
        // String plusCourtChemin = result.getPath();
        // if (plusLongChemin.contains(plusCourtChemin)) {
        // clientsEmbarques.add(client);
        // nombre_place_disponible = nombre_place_disponible -
        // client.getNombrePlacesVoulu();
        // } else if (plusCourtChemin.contains(plusLongChemin)) {
        // clientsEmbarques.add(client);
        // nombre_place_disponible = nombre_place_disponible -
        // client.getNombrePlacesVoulu();
        // } else {
        // System.out.println("Error");

        // }

        // }
        // }
        // }
        // }

    }

    public Graph.DijkstraResult createGraph(int pointSource, int pointDestination) {
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

        Graph.DijkstraResult result = graph.calculateDijkstra(pointSource, pointDestination);
        return result;
    }

    public void choixClient() {
            for (Client client : clients) {
                choixPrix(client);
            }
            clientPris(clientsPotentiels);
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

    
}
