package ch.bzz.noel.kino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

import java.util.UUID;

/**
 * @author noel
 * @version 1.0
 */
public class Film {

    /**
     * @see Film
     */
    @JsonIgnore

    private String filmUUID;

    @FormParam("titel")
    @Size(min = 3, max = 50)
    private String titel;

    @FormParam("laenge")
    @Size(min = 1, max = 500)
    private int laenge;

    @FormParam("preis")
    @Size(min = 1, max = 500)
    private double preis;

    @FormParam("hauptdarsteller")
    @Size(min = 2, max = 30)
    private String hauptdarsteller;

    @FormParam("regisseur")
    @Size(min = 2, max = 30)
    private String regisseur;


    public Film() {

    }

    /**
     * @param filmUUID
     * @param titel
     * @param laenge
     * @param preis
     * @param hauptdarsteller
     * @param regisseur
     */
    public Film(String titel, int laenge, double preis, String hauptdarsteller, String regisseur) {
        this.filmUUID = UUID.randomUUID().toString();
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

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
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
