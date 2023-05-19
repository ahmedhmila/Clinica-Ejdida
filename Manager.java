

public class Manager {
    private String nom;
    private String mdp;
    
    
    public Manager(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }
    


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return nom + ", " + ", " + mdp;
    }
}
