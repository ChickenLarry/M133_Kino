package ch.bzz.noel.kino.model;

public class Film {

    private String filmUUID;
    private String titel;
    private int laenge;
    private int preis;
    private String hauptdarsteller;
    private String regisseur;

    public Film() {
    }

    public Film(String filmUUID, String titel, int laenge, int preis, String hauptdarsteller, String regisseur) {
        this.filmUUID = filmUUID;
        this.titel = titel;
        this.laenge = laenge;
        this.preis = preis;
        this.hauptdarsteller = hauptdarsteller;
        this.regisseur = regisseur;
    }

    public String getFilmUUID() {
        return filmUUID;
    }

    public void setFilmUUID(String filmUUID) {
        this.filmUUID = filmUUID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public String getHauptdarsteller() {
        return hauptdarsteller;
    }

    public void setHauptdarsteller(String hauptdarsteller) {
        this.hauptdarsteller = hauptdarsteller;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }
}
