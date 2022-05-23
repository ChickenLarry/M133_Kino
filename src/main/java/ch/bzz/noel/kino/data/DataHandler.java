package ch.bzz.noel.kino.data;

import ch.bzz.noel.kino.model.Film;
import ch.bzz.noel.kino.model.Kino;
import ch.bzz.noel.kino.model.Saal;
import ch.bzz.noel.kino.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * DataHandler Class
 *
 * @author noel
 * @version 1.0
 * @see DataHandler
 */

public class DataHandler {
    private static DataHandler instance = null;
    private List<Film> filmList;
    private List<Kino> kinoList;
    private List<Saal> SaalList;

    /**
     * Constructor
     */
    public DataHandler() {
        setFilmList(new ArrayList<>());
        readFilmJSON();
        setKinoList(new ArrayList<>());
        readKinoJSON();
        setSaalList(new ArrayList<>());
        readSaalJSON();
    }

    /**
     * Getter for instance
     *
     * @return instance
     */
    public static DataHandler getInstance() {
        if (instance == null) {
            instance = new DataHandler();
        }
        return instance;
    }

    private void readKinoJSON() {
        try {
            String path = Config.getProperty("kinoJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Kino[] kinos = objectMapper.readValue(jsonData, Kino[].class);
            for (Kino kino : kinos) {
                getKinoList().add(kino);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readSaalJSON() {
        try {
            String path = Config.getProperty("saalJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Saal[] saele = objectMapper.readValue(jsonData, Saal[].class);
            for (Saal saal : saele) {
                getSaalList().add(saal);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readFilmJSON() {
        try {
            String path = Config.getProperty("filmJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Film[] filme = objectMapper.readValue(jsonData, Film[].class);
            for (Film film : filme) {
                getFilmList().add(film);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    public List<Kino> getKinoList() {
        return kinoList;
    }

    public void setKinoList(List<Kino> kinoList) {
        this.kinoList = kinoList;
    }

    public List<Saal> getSaalList() {
        return SaalList;
    }

    public void setSaalList(List<Saal> saalList) {
        SaalList = saalList;
    }

    public List<Film> readallFilme() {
        return getFilmList();
    }


    public Film readFilmByUUID(String filmUUID) {
        for (Film film : getFilmList()) {
            if (film.getFilmUUID().equals(filmUUID)) {
                return film;
            }
        }
        return null;
    }

    public List<Kino> readallKinos() {
        return getKinoList();
    }

    public Kino readKinoByUUID(String kinoUUID) {
        for (Kino kino : getKinoList()) {
            if (kino.getKinoUUID().equals(kinoUUID)) {
                return kino;
            }
        }
        return null;
    }

    public List<Saal> readallSaele() {
        return getSaalList();
    }

    public Saal readSaalByUUID(String saalUUID) {
        for (Saal saal : getSaalList()) {
            if (saal.getSaalUUID().equals(saalUUID)) {
                return saal;
            }
        }
        return null;
    }
}
