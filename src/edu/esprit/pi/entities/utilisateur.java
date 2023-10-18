/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.entities;

import java.sql.Date;

/**
 *
 * @author aziz
 */
public class utilisateur {

    public static utilisateur current_user;

    public static void add(utilisateur u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id_user;
    private int cin;
    private String prenom;
    private String nom;
    private String num;
    private String mail;
    private int role;
    private String password;
    private Date date_naissance;

    public utilisateur() {
    }

    public utilisateur(int cin, String prenom, String nom, String num, String mail, int role, String password, Date date_naissance) {

        this.cin = cin;
        this.prenom = prenom;
        this.nom = nom;
        this.num = num;
        this.mail = mail;
        this.role = role;
        this.password = password;
        this.date_naissance = date_naissance;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "id_user=" + id_user + ", cin=" + cin + ", prenom=" + prenom + ", nom=" + nom + ", num=" + num + ", mail=" + mail + ", role=" + role + ", password=" + password + ", date_naissance=" + date_naissance + '}';
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
        final utilisateur other = (utilisateur) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    public static utilisateur getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(utilisateur current_user) {
        utilisateur.current_user = current_user;
    }

}
