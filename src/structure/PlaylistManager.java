package structure;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PlaylistManager {

    private int aktPlaylist;
    private final ObservableList<Playlist> playlists = FXCollections.observableArrayList(new ArrayList<>());

    public  PlaylistManager (Playlist playlist){
        this.playlists.add(playlist);
        this.aktPlaylist = 0;
    }

    public void addPlaylist (Playlist playlist){
        playlists.add(playlist);
    }

    public Playlist getPlaylist (int index){
        return playlists.get(index);
    }

    public void setAktPlaylist (int index){
        this.aktPlaylist = index;
    }

    public Playlist getAktPlaylist(){
        return this.playlists.get(aktPlaylist);
    }

    public ArrayList<String> getPlaylistNames(){
        ArrayList<String> playlistnames = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistnames.add(playlist.getName());
        }
        return playlistnames;
    }

    /*
    public void savePlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }
    public void deletePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }
    */
}
