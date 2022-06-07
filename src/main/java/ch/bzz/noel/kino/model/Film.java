package ch.bzz.noel.kino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;

import java.math.BigDecimal;
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

    @FormParam("filmUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String filmUUID;

    @FormParam("titel")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String titel;

    @FormParam("laenge")
    @NotEmpty
    @Size(min = 1, max = 500)
    private int laenge;

    @FormParam("preis")
    @DecimalMax(value="40.00")
    @DecimalMin(value="19.95")
    private BigDecimal preis;

    @FormParam("hauptdarsteller")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String hauptdarsteller;

    @FormParam("regisseur")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String regisseur;

    public Film(String film1, int laenge, double v, String harry, String potter) {

    }

    /**
     * @param filmUUID
     * @param titel
     * @param laenge
     * @param preis
     * @param hauptdarsteller
     * @param regisseur
     */
    public Film(String titel, int laenge, BigDecimal preis, String hauptdarsteller, String regisseur) {
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

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
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
