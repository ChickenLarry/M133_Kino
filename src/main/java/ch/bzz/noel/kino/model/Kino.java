package ch.bzz.noel.kino.model;

public class Kino {

        private String kinoUUID;
        private String name;
        private String ort;

        public Kino() {
        }

        public Kino(String kinoUUID, String name, String ort) {
            this.kinoUUID = kinoUUID;
            this.name = name;
            this.ort = ort;
        }

    public String getKinoUUID() {
        return kinoUUID;
    }

    public void setKinoUUID(String kinoUUID) {
        this.kinoUUID = kinoUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
