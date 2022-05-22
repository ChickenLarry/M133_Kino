package ch.bzz.noel.kino.data;

import ch.bzz.noel.kino.model.Film;
import ch.bzz.noel.kino.model.Kino;
import ch.bzz.noel.kino.model.Saal;

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
     * */
    public DataHandler() {
        setFilmList(new ArrayList<>());
        setKinoList(new ArrayList<>());
        setSaalList(new ArrayList<>());
    }

    /**
     * Getter for instance
     * @return instance
     * */
    public static DataHandler getInstance() {
        if (instance == null) {
            instance = new DataHandler();
        }
        return instance;
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
