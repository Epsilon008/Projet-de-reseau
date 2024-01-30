public class Client {
    private int pointDepart;
    private int destination;
    private int nombrePlacesVoulu;
    private int prixPropose;
    private int beneficeClient = 0;

    
    public Client(int pointDepart, int destination, int nombrePlacesVoulu, int prixPropose, int beneficeClient) {
        this.pointDepart = pointDepart;
        this.destination = destination;
        this.nombrePlacesVoulu = nombrePlacesVoulu;
        this.prixPropose = prixPropose;
        this.beneficeClient = beneficeClient;
    }
    public int getPointDepart() {
        return pointDepart;
    }
    public void setPointDepart(int pointDepart) {
        this.pointDepart = pointDepart;
    }
    public int getDestination() {
        return destination;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    public int getNombrePlacesVoulu() {
        return nombrePlacesVoulu;
    }
    public void setNombrePlacesVoulu(int nombrePlacesVoulu) {
        this.nombrePlacesVoulu = nombrePlacesVoulu;
    }
    public int getPrixPropose() {
        return prixPropose;
    }
    public void setPrixPropose(int prixPropose) {
        this.prixPropose = prixPropose;
    }
    public Client(int pointDepart, int destination, int nombrePlacesVoulu, int prixPropose) {
        this.pointDepart = pointDepart;
        this.destination = destination;
        this.nombrePlacesVoulu = nombrePlacesVoulu;
        this.prixPropose = prixPropose;
    }
    public Client() {
    }
    public int getBeneficeClient() {
        return beneficeClient;
    }
    public void setBeneficeClient(int beneficeClient) {
        this.beneficeClient = beneficeClient;
    }

    

}