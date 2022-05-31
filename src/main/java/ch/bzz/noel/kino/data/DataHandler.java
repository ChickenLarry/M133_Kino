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

    //write kinoJSON
    public void writeKinoJSON() {
        try {
            String path = Config.getProperty("kinoJSON");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(getKinoList());
            Files.write(Paths.get(path), jsonString.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //write saalJSON
    public void writeSaalJSON() {
        try {
            String path = Config.getProperty("saalJSON");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(getSaalList());
            Files.write(Paths.get(path), jsonString.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //write filmJSON
    public void writeFilmJSON() {
        try {
            String path = Config.getProperty("filmJSON");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(getFilmList());
            Files.write(Paths.get(path), jsonString.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public void insertKino(Kino kino) {
        getKinoList().add(kino);
        writeKinoJSON();
    }

    public void updateKino(Kino kino) {
        writeSaalJSON();
    }

    public boolean deleteKino(String kinoUUID) {
        Kino kino = readKinoByUUID(kinoUUID);
        if (kino != null) {
            getSaalList().remove(kino);
            writeSaalJSON();
            return true;
        }else
            return false;
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

    public void insertSaal(Saal saal) {
        getSaalList().add(saal);
        writeSaalJSON();
    }

    public void updateSaal(Saal saal) {
        writeSaalJSON();
    }

    public boolean deleteSaal(String saalUUID) {
        Saal saal = readSaalByUUID(saalUUID);
        if (saal != null) {
            getSaalList().remove(saal);
            writeSaalJSON();
            return true;
        }else
            return false;
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

    public void insertFilm(Film film){
        getFilmList().add(film);
        writeFilmJSON();
    }

    public void updateFilm(){
        writeFilmJSON();
    }

    public boolean deleteFilm(String filmUUID) {
        Film film = readFilmByUUID(filmUUID);
        if (film != null) {
            getFilmList().remove(film);
            writeFilmJSON();
            return true;
        }else
            return false;
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


}
