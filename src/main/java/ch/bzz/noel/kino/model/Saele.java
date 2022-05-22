package ch.bzz.noel.kino.model;

public class Saele {

    private String saeleUUID;
    private String saalNummer;
    private int plaetze;
    private int reihen;
    private int anzahlPlaetzeProReihe;

    public Saele() {
    }

    public Saele(String saeleUUID, String saalNummer, int plaetze, int reihen, int anzahlPlaetzeProReihe) {
        this.saeleUUID = saeleUUID;
        this.saalNummer = saalNummer;
        this.plaetze = plaetze;
        this.reihen = reihen;
        this.anzahlPlaetzeProReihe = anzahlPlaetzeProReihe;
    }

    public String getSaeleUUID() {
        return saeleUUID;
    }

    public void setSaeleUUID(String saeleUUID) {
        this.saeleUUID = saeleUUID;
    }

    public String getSaalNummer() {
        return saalNummer;
    }

    public void setSaalNummer(String saalNummer) {
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
