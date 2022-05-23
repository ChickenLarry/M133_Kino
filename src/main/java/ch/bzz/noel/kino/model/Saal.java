package ch.bzz.noel.kino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/**
 * @author noel
 * @version 1.0
 */
public class Saal {

    /**
     * @see Saal
     */
    @JsonIgnore
    private String saalUUID;
    private int saalNummer;
    private int plaetze;
    private int reihen;
    private int anzahlPlaetzeProReihe;

    /**
     * empty Constructor
     */
    public Saal() {
    }

    /**
     * Constructor
     *
     * @param saalUUID
     * @param saalNummer
     * @param plaetze
     * @param reihen
     * @param anzahlPlaetzeProReihe
     */
    public Saal(int saalNummer, int plaetze, int reihen, int anzahlPlaetzeProReihe) {
        this.saalUUID = UUID.randomUUID().toString();
        this.saalNummer = saalNummer;
        this.plaetze = plaetze;
        this.reihen = reihen;
        this.anzahlPlaetzeProReihe = anzahlPlaetzeProReihe;
    }

    public String getSaalUUID() {
        return saalUUID;
    }

    public void setSaalUUID(String saeleUUID) {
        this.saalUUID = saeleUUID;
    }

    public int getSaalNummer() {
        return saalNummer;
    }

    public void setSaalNummer(int saalNummer) {
        this.saalNummer = saalNummer;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public int getReihen() {
        return reihen;
    }

    public void setReihen(int reihen) {
        this.reihen = reihen;
    }

    public int getAnzahlPlaetzeProReihe() {
        return anzahlPlaetzeProReihe;
    }

    public void setAnzahlPlaetzeProReihe(int anzahlPlaetzeProReihe) {
        this.anzahlPlaetzeProReihe = anzahlPlaetzeProReihe;
    }
}
