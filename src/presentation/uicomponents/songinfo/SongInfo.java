package presentation.uicomponents.songinfo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class SongInfo extends VBox {

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;

    public SongInfo (Playlist defPlaylist, Mp3Player player, PlaylistManager manager){
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        Label songname = new Label(player.getAktSongName());
        songname.setId("song");

        Label artistname = new Label(player.getAktArtistName());
        artistname.setId("artist");

        Label playlistname = new Label(manager.getAktPlaylist().getName());
        playlistname.setId("playlist");


        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(songname, artistname, playlistname);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }
}
