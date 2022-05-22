package ch.bzz.noel.kino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ch.bzz.noel.kino.data.DataHandler;

/**
 * @author noel
 * @version 1.0
 */
public class Kino {

    /**
     * @see Kino
     * */
        @JsonIgnore
        private String kinoUUID;
        private String name;
        private String ort;

    /**
     * empty Constructor
     */
    public Kino() {
        }

    /**
     * Constructor
     *
     * @param kinoUUID
     * @param name
     * @param ort
     */

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
