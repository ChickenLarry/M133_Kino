package ch.bzz.noel.kino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

import java.util.UUID;

/**
 * @author noel
 * @version 1.0
 */
public class Kino {

    /**
     * @see Kino
     */
    @JsonIgnore

    @FormParam("kinoUUID")
    @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
    private String kinoUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min = 5, max = 20)
    private String name;

    @FormParam("ort")
    @NotEmpty
    @Size(min = 10, max = 50)
    private String ort;

    /**
     * empty Constructor
     */
    public Kino() {
    }

    /**
     * Constructor
     *
     * @param name
     * @param ort
     */

    public Kino(String name, String ort) {
        this.kinoUUID = UUID.randomUUID().toString();
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
