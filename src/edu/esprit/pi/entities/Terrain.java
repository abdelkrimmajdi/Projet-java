/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.entities;

/**
 *
 * @author Sadok
 */
public class Terrain {

    private int id_terrain ; 
    private int prix ; 
    private String adress;
    private int status ; 
    
    

    public Terrain() {
    }
    
    public Terrain(int prix, String adress, int status){
        this.prix = prix;
        this.adress = adress;
        this.status = status;
    }

    public Terrain(int id_terrain, int prix, String adress, int status) {
        this.id_terrain = id_terrain;
        this.prix = prix;
        this.adress = adress;
        this.status = status;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Terrain{" + "id_terrain=" + id_terrain + ", prix=" + prix + ", adress=" + adress + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_terrain;
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
        final Terrain other = (Terrain) obj;
        if (this.id_terrain != other.id_terrain) {
            return false;
        }
        return true;
    }

    
   
}
