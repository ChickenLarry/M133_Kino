package ch.bzz.noel.kino.data;

import ch.bzz.noel.kino.model.Film;
import ch.bzz.noel.kino.model.Kino;
import ch.bzz.noel.kino.model.Saal;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Film> filmList;
    private List<Kino> kinoList;
    private List<Saal> SaalList;

    public DataHandler() {
        setFilmList(new ArrayList<>());
        setKinoList(new ArrayList<>());
        setSaalList(new ArrayList<>());
    }

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

/*    public void addFilm(Film film) {
        filmList.add(film);
    }

    public void addKino(Kino kino) {
        kinoList.add(kino);
    }

    public void addSaal(Saal saal) {
        SaalList.add(saal);
    }

    public Film getFilmByUUID(String uuid) {
        for (Film film : filmList) {
            if (film.getFilmUUID().equals(uuid)) {
                return film;
            }
        }
        return null;
    }

    public Kino getKinoByUUID(String uuid) {
        for (Kino kino : kinoList) {
            if (kino.getKinoUUID().equals(uuid)) {
                return kino;
            }
        }
        return null;
    }

    public Saal getSaalByUUID(String uuid) {
        for (Saal saal : SaalList) {
            if (saal.getSaalUUID().equals(uuid)) {
                return saal;
            }
        }
        return null;
    }

    public void removeFilm(Film film) {
        filmList.remove(film);
    }

    public void removeKino(Kino kino) {
        kinoList.remove(kino);
    }

    public void removeSaal(Saal saal) {
        SaalList.remove(saal);
    }*/
}
