import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

class Taxi {
    List<String> table_destination;
    int nombre_place_disponible;
    // List<String> Cp;
    Map<String, Integer> Cp = new HashMap<>(); // Cp dictionnaire ayant comme clé les points de destinations et comme
                                               // valeur correspondante le nombre de place
    public static Map<Integer, List<Object>> propositions = new HashMap<>(); // Pour le stockage des propositions des
                                                                             // clients présents au point de ramassage.
                                                                             // Elle prendra comme valeurs les vecteurs
                                                                             // clients.
    List<String> S = new ArrayList<>();

    public Taxi(int placesDisponibles) {
        this.nombre_place_disponible = placesDisponibles;
        this.table_destination = new ArrayList<>();
        this.Cp = new HashMap<>();
        this.S = new ArrayList<>();
    }

    public void choixClient(String Sn, String Sm, int Pi, int Ni) {
        for (int i = 1; i <= 3; i++) {
            for (Map.Entry<Integer, List<Object>> entry : Client.C.entrySet()) {
                int key = entry.getKey();
                List<Object> parametre = entry.getValue();
                // Object prixProposé = parametre.get(3);
                // Object nbreplaceDemandé = parametre.get(2);
                int prixProposé = ((Number) parametre.get(3)).intValue();
                int nbreplaceDemandé = ((Number) parametre.get(2)).intValue();

                choixPrix(prixProposé, nbreplaceDemandé);

            }
        }

        clientPris(propositions);

        // List<String> chemin = calculDjikstra(Sn, Sm);
        // if ((double) Pi / Ni >= calculerPrix(chemin)) {
        // nombre_place_disponible -= Ni;
        // table_destination.add(chemin.toString());
        // Cp.put(Sm,Ni);
        // // Cp.add(Sm);
        // return "Accepte";
        // } else {
        // return "Refuse";
        // }
    }

    private double choixPrix(int Pi, int Ni) {
        return 0;
    }

    public static Map<Integer, List<Object>> clientPris(Map<Integer, List<Object>> propositions) {

        return propositions;
    }

    // Logique pour décider d'accepter ou de refuser un client et mettre à jour
    // table_destination, nombre_place_disponible, etc.
    private List<String> calculDjikstra(String sn, String sm) {
        return null;
    }

    public void choixClientSuivant() {
        // Logique pour choisir le prochain client à prendre en charge en fonction de
        // l'algorithme décrit
    }

    public void depotClient(String destination) {

        for (Map.Entry<String, Integer> entry : Cp.entrySet()) {
            for (String Si : S) {
                String cpi = entry.getKey();
                if (Si.equals(cpi)) {
                    // Supprimer cpi de Cp
                    Cp.remove(cpi);
                    // Supprimer Djikstra(., cpi) de tableDestination
                    table_destination.remove(cpi);
                    // Mise à jour de nombrePlaceDisponible

                    nombre_place_disponible += getNombrePlacesClient(cpi); // Obtenez le nombre de places du client cpi
                }
            }
        }
    }

    private int getNombrePlacesClient(String cpi) {
        Integer Place_Prises = Cp.get(cpi);
        return Place_Prises;
    }
}

class Client {
    // C étant le dictionnaire de tous les clients
    public static Map<Integer, List<Object>> C = new HashMap<>();
    String pointDepart;
    String destination;
    int nombrePlacesVoulu;
    int prixPropose;
    int autoIncrementKey = 1;

    public Client(String pointDepart, String destination, int nombrePlacesVoulu, int prixPropose) {
        this.C = new HashMap<>();
        this.pointDepart = pointDepart;
        this.destination = destination;
        this.nombrePlacesVoulu = nombrePlacesVoulu;
        this.prixPropose = prixPropose;
    }

    public void ajoutClient(String Sn, String Sm, int Pi, int Ni) {
        List<Object> parametreClient = new ArrayList<>();
        parametreClient.add(pointDepart);
        parametreClient.add(destination);
        parametreClient.add(nombrePlacesVoulu);
        parametreClient.add(prixPropose);
        C.put(autoIncrementKey, parametreClient);
        autoIncrementKey++;

    }

}
