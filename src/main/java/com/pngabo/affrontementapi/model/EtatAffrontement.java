package com.pngabo.affrontementapi.model;

public enum EtatAffrontement {
    RECHERCHE("recherche"),
    ENCOURS("en cours"),
    TERMINE("terminé");

    public String etat;

    EtatAffrontement(String etat) {
        this.etat = etat;
    }
}
