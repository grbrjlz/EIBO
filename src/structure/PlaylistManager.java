package structure;

import java.util.ArrayList;

public class PlaylistManager {

    private Playlist aktuell;
    //private HashMap<String, Playlist> playlists = new HashMap<>();

    private ArrayList<Playlist> playlists = new ArrayList<>();

/*
    public Backend.PlaylistManager (){
        this.aktuell = new Backend.Playlist();
    }
*/

    //KONSTRUKTOREN

    public  PlaylistManager (Playlist playlist){
        this.aktuell=playlist;
        this.playlists.add(playlist);
    }

    //GETTER

    public Playlist getPlaylist (String playlistname){
        for (Playlist playlist : playlists) {
            if (playlist.toString().equalsIgnoreCase(playlistname)) {
                return playlist;
            }
        }
        return null;
    }

    public Playlist getAktPlaylist(){
        return this.aktuell;
    }

    public void settingsInfo(){
        String s, r;
        if (aktuell.isShuffle()) s="ON";
        else s="OFF";
        if (aktuell.isRepeat()) r="ON";
        else r="OFF";
        System.out.println("Aktuelle Einstellungen: Repeat: "+r+" | "+"Shuffle: "+s);
    }

    public void playlistInfo(){
        System.out.println("Aktuelle Backend.Playlist: "+aktuell.getName());
    }

    public void songInfo(){
        System.out.println("Aktueller Song: "+aktuell.getAktSong().getName());
    }



    public void savePlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
}
