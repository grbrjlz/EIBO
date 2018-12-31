package structure;

import com.mpatric.mp3agic.Mp3File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Eine Playlist besteht aus einer ArrayList der aktuellen Tracks, dem Playlistnamen und der Anzahl der Songs
 */
public class Playlist {
    private final String name;
    private ArrayList<Track> songs;

    private final int size;

    /**
     * Falls eine Playlist ohne mitgegebenen Parametern initialisiert wird, wird eine default-Playlist mit Standardsongs in /songs erstellt
     */
    public Playlist() {
        songs = new ArrayList<>();
        File file = new File("default.m3u");
        File directory = new File("./songs");
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (content != null) Arrays.sort(content);
        songs = fileToTrackArrayList(content);
        try {
            file.createNewFile();
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = file.getName();
        this.size = songs.size();
    }

    /**
     * Werden Parameter mitgegeben, wird eine neue Playlist erstellt
     * @param playlistname der dateiname der Playlist (ohne .m3u)
     * @param songdirectory der Ordner des Playlistinhalts
     */
    public Playlist(String playlistname, String songdirectory) {
        songs = new ArrayList<>();
        File file = new File(playlistname+".m3u");
        File directory = new File(songdirectory);
        File[] content = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (content != null) Arrays.sort(content);
        songs = fileToTrackArrayList(content);
        try {
            file.createNewFile();
            writePlaylist(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = file.getName();
        this.size = songs.size();

    }

    private void writePlaylist(File file) throws IOException {
        if (songs != null) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Track song : songs) {
                writer.write(song.getFilename());
                writer.newLine();
            }
            writer.close();
        }
    }

    private ArrayList<Track> fileToTrackArrayList(File[] files) {
        ArrayList<Track> content = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                try {
                    content.add(new Track(new Mp3File(file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getSongNames() {
        ArrayList<String> songnames = new ArrayList<>();
        if (songs != null) {
            for (int i = 0; i < size; i++) {
                songnames.add(songs.get(i).getTitle());
            }
            return songnames;
        }
        return null;
    }

    public String getSongName(int i) {
        return songs.get(i).getFilename();
    }

    public Track getSong(int i) {
        return songs.get(i);
    }

    int getSize() {
        return songs.size();
    }

}
