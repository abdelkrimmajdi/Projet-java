package edu.esprit.pi.entities;


public class joueur {
    private int id_joueur;
    private int num_joueur;
    private String nom_joueur;
    private int age_joueur;
    private String Position_joueur;

    public joueur() {
    }

    public joueur(int num_joueur, String nom_joueur, int age_joueur, String Postion_joueur) {
        this.num_joueur = num_joueur;
        this.nom_joueur = nom_joueur;
        this.age_joueur = age_joueur;
        this.Position_joueur = Postion_joueur;
    }

    public joueur(int id_joueur, int num_joueur, String nom_joueur, int age_joueur, String Postion_joueur) {
        this.id_joueur = id_joueur;
        this.num_joueur = num_joueur;
        this.nom_joueur = nom_joueur;
        this.age_joueur = age_joueur;
        this.Position_joueur = Postion_joueur;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    public int getNum_joueur() {
        return num_joueur;
    }

    public void setNum_joueur(int num_joueur) {
        this.num_joueur = num_joueur;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public int getAge_joueur() {
        return age_joueur;
    }

    public void setAge_joueur(int age_joueur) {
        this.age_joueur = age_joueur;
    }

    public String getPosition_joueur() {
        return Position_joueur;
    }

    public void setPosition_joueur(String Postion_joueur) {
        this.Position_joueur = Postion_joueur;
    }

    @Override
    public String toString() {
        return "joueur{" + "id_joueur=" + id_joueur + ", num_joueur=" + num_joueur + ", nom_joueur=" + nom_joueur + ", age_joueur=" + age_joueur + ", Postion_joueur=" + Position_joueur + '}';
    }
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_joueur;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final joueur other = (joueur) obj;
        if (this.id_joueur != other.id_joueur) {
            return false;
        }
        return true;
    } }

  
    

