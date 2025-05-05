package com.malik.docuflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocumentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String email;
    private String date;
    private String typeContrat;
    private String montant;
    private String duree;

    private String marque;
    private String modele;
    private String kilometrage;
    private String cylindree;
    private String garantiesSupplementaires;
    private String franchise;
    private String plafondRemboursement;
    private String periodicitePaiement;
    private String modeleContrat;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getCylindree() {
        return cylindree;
    }

    public void setCylindree(String cylindree) {
        this.cylindree = cylindree;
    }

    public String getGarantiesSupplementaires() {
        return garantiesSupplementaires;
    }

    public void setGarantiesSupplementaires(String garantiesSupplementaires) {
        this.garantiesSupplementaires = garantiesSupplementaires;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getPlafondRemboursement() {
        return plafondRemboursement;
    }

    public void setPlafondRemboursement(String plafondRemboursement) {
        this.plafondRemboursement = plafondRemboursement;
    }

    public String getPeriodicitePaiement() {
        return periodicitePaiement;
    }

    public void setPeriodicitePaiement(String periodicitePaiement) {
        this.periodicitePaiement = periodicitePaiement;
    }

    public String getModeleContrat() {
        return modeleContrat;
    }
    
    public void setModeleContrat(String modeleContrat) {
        this.modeleContrat = modeleContrat;
    }
}
