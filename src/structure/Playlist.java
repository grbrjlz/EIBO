package structure;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Playlist {
    private String name;
    //private HashMap<Integer, Track> songs;
    private ArrayList<Track> songs;

    private int aktSong, size;
    //private Track aktSong;
    //private int size;

    private boolean shuffle, repeat;

    //KONSTRUKTOREN

    public Playlist (){
        //Erstellt default-list mit allen vorhandenen Songs in default Directory
        songs = new ArrayList<>();

        File file = new File("default.m3u");
        File directory = new File("./songs");
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

        if (content!=null) Arrays.sort(content);
        //songs = fileToMp3Hash(content);
        songs = fileToTrackArrayList(content);

        try {
            file.createNewFile();
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = file.getName();

        this.aktSong = 0;
        this.size = songs.size();
        this.shuffle = false;
        this.repeat = false;

    }

    /*
    public Backend.list (String name, String directory){

        //Erstellt neue Backend.list mit gegebenem Namen und Verzeichnis
        this.file = new File(name);
        this.directory = new File(directory);

        try {
            createFile(file);
            writePlaylist();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

    //ERSTELLEN + FÜLLEN DER DATEI

    private void writePlaylist(File file) throws IOException{
        if (songs !=null){
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Track song : songs) {
                writer.write(song.toString());
                writer.newLine();
            }
            writer.close();
        }
    }

    private ArrayList<Track> fileToTrackArrayList(File[] files){
        //HashMap<Integer, Mp3File> content = new HashMap<>();
        ArrayList<Track> content = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                try {
                    content.add(new Track(new Mp3File(file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            /*try {
                content.put(i, new Mp3File(files[i]));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }*/
            }
            return content;
        }
        return null;
    }

    void skipToNextSong(){
        if (repeat)  {
            return;
        }
        else if (shuffle) {
            aktSong = (int)(Math.random()* songs.size());
            //aktSong = songs.get((int)(Math.random()* size));

            return;
        }
        else {
            if (aktSong<(size-1)){
                aktSong++;
            } else if (aktSong == (size-1)){
                aktSong = 0;
            }
        }

    }

    void skipToPrevSong(){
        if (repeat)  {
        }
        else if (shuffle) {
            aktSong = (int)(Math.random()* songs.size());
            return;
        }
        else {
            if (aktSong == (0)) {
                aktSong = (size - 1);
            }
            else if (aktSong<size){
                aktSong--;
            }
        }
    }

    //SETTER + TOGGLER

    public void toggleShuffle(){
        shuffle = !shuffle;
    }

    public void toggleRepeat(){
        repeat = !repeat;
    }

    public void setAktSong(int i){
        this.aktSong = i;
    }

    //GETTER

    public String getName(){
        return this.name;
    }

    public Track getAktSong(){
        return songs.get(aktSong);
    }

    public ArrayList<Track> getAll() {
        return this.songs;
    }

    public ArrayList<Track> getSongs() {
        return this.songs;
    }

    public Track getSong(int i){
        return songs.get(i);
    }


    public boolean isShuffle(){
        return shuffle;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
