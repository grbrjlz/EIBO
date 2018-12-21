package structure;

import java.util.ArrayList;

public class PlaylistManager {

    private int aktPlaylist;
    private ArrayList<Playlist> playlists = new ArrayList<>();


    //KONSTRUKTOREN

    public  PlaylistManager (Playlist playlist){
        this.playlists.add(playlist);
        this.aktPlaylist = 0;
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
        return this.playlists.get(aktPlaylist);
    }


    public String getAktPlaylistName(){
        return this.playlists.get(aktPlaylist).getName();
    }

    public void savePlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
}
