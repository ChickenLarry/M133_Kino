package ch.bzz.kino.data;


import ch.bzz.kino.model.Film;
import ch.bzz.kino.model.Kino;
import ch.bzz.kino.model.Saal;
import ch.bzz.kino.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Film> filmList;
    private List<Saal> saalList;
    private List<Kino> kinoList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setFilmList(new ArrayList<>());
        readFilmJSON();
        setSaalList(new ArrayList<>());
        readSaalJSON();
        setKinoList(new ArrayList<>());
        readKinoJSON();
    }


    /**
     * gets the only instance of this class
     *
     * @return Get and Set
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Film
     *
     * @return list of films
     */
    public List<Film> readAllFilme() {
        return getFilmList();
    }
    public List<Saal> readAllSaal() {
        return getSaalList();
    }
    public List<Kino> readAllKino() {
        return getKinoList();
    }



    /**
     * reads a film by its filmID
     *
     * @param filmUUID is filmUUID from Film Class
     * @return the Film (null=not found)
     */
    public Film readFilmByUUID(String filmUUID) {
        Film film = null;
        for (Film entry : getFilmList()) {
            if (entry.getFilmUUID() == (filmUUID)) {
                film = entry;
            }
        }
        return film;
    }

    /**
     * reads a Saal by its saalUUID
     *
     * @param saalUUID is saalUUID from Saal Class
     * @return the Saal (null=not found)
     */
    public Saal readSaalByUUID(String saalUUID) {
        Saal saal = null;
        for (Saal entry : getSaalList()) {
            if (entry.getSaalUUID() == (saalUUID)) {
                saal = entry;
            }
        }
        return saal;
    }

    /**
     * reads a Kino by its kinoUUID
     *
     * @param kinoUUID is kinoUUID from Kino Class
     * @return the Kino (null=not found)
     */
    public Kino readKinoByUUID(String kinoUUID) {
        Kino kino = null;
        for (Kino entry : getKinoList()) {
            if (entry.getKinoUUID() == (kinoUUID)) {
                kino = entry;
            }
        }
        return kino;
    }


    /**
     * inserts a new film into the filmList
     *
     * @param film the film to be saved
     */
    public void insertFilm(Film film) {
        getFilmList().add(film);
        writeFilmJSON();
    }

    /**
     * inserts a new saal into the saalList
     *
     * @param saal the film to be saved
     */
    public void insertSaal(Saal saal) {
        getSaalList().add(saal);
        writeSaalJSON();
    }

    /**
     * inserts a new kino into the kinoList
     *
     * @param kino the film to be saved
     */
    public void insertKino(Kino kino) {
        getKinoList().add(kino);
        writeKinoJSON();
    }

    /**
     * updates the filmList
     */
    public void updateFilm() {
        writeFilmJSON();
    }

    /**
     * updates the saalList
     */
    public void updateSaal() {
        writeSaalJSON();
    }

    /**
     * updates the kinoList
     */
    public void updateKino() {
        writeKinoJSON();
    }


    /**
     * deletes a film identified by the filmUUID
     *
     * @param filmUUID the key
     * @return success=true/false
     */
    public boolean deleteFilm(String filmUUID) {
        Film film = readFilmByUUID(filmUUID);
        if (film != null) {
            getFilmList().remove(film);
            writeFilmJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes a saal identified by the saalUUID
     *
     * @param saalUUID the key
     * @return success=true/false
     */
    public boolean deleteSaal(String saalUUID) {
        Saal saal = readSaalByUUID(saalUUID);
        if (saal != null) {
            getFilmList().remove(saal);
            writeFilmJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes a saal identified by the saalUUID
     *
     * @param kinoUUID the key
     * @return success=true/false
     */
    public boolean deleteKino(String kinoUUID) {
        Kino kino = readKinoByUUID(kinoUUID);
        if (kino != null) {
            getFilmList().remove(kino);
            writeFilmJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the Film from the JSON-file
     */
    private void readFilmJSON() {
        try {
            String path = Config.getProperty("filmJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Film[] films = objectMapper.readValue(jsonData, Film[].class);
            for (Film film : films) {
                getFilmList().add(film);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Saal from the JSON-file
     */
    private void readSaalJSON() {
        try {
            String path = Config.getProperty("saalJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Saal[] saals = objectMapper.readValue(jsonData, Saal[].class);
            for (Saal saal : saals) {
                getSaalList().add(saal);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Kino from the JSON-file
     */
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

    /**
     * writes the filmList to the JSON-file
     */
    private void writeFilmJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String filmPath = Config.getProperty("filmJSON");
        try {
            fileOutputStream = new FileOutputStream(filmPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFilmList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the saalList to the JSON-file
     */
    private void writeSaalJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String filmPath = Config.getProperty("saalJSON");
        try {
            fileOutputStream = new FileOutputStream(filmPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFilmList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the kinoList to the JSON-file
     */
    private void writeKinoJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String filmPath = Config.getProperty("kinoJSON");
        try {
            fileOutputStream = new FileOutputStream(filmPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFilmList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * get the filmList of the Json-file
     * */
    private List<Film> getFilmList() {
        if (filmList == null){
            setFilmList(new ArrayList<>());
            readFilmJSON();
        }
        return filmList;
    }

    /**
     * get the saalList of the Json-file
     * */
    private List<Saal> getSaalList() {
        if (saalList == null){
            setSaalList(new ArrayList<>());
            readSaalJSON();
        }
        return saalList;
    }

    /**
     * get the kinoList of the Json-file
     * */
    private List<Kino> getKinoList() {
        if (kinoList == null){
            setKinoList(new ArrayList<>());
            readKinoJSON();
        }
        return kinoList;
    }

    /**
     * set the filmList
     * */
    private void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    /**
     * set the saalList
     * */
    private void setSaalList(List<Saal> saalList) {
        this.saalList = saalList;
    }

    /**
     * set the kinoList
     * */
    private void setKinoList(List<Kino> kinoList) {
        this.kinoList = kinoList;
    }

}