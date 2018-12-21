package presentation.uicomponents.filelist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import structure.Playlist;
import structure.PlaylistManager;


public class FileList extends VBox {

    public FileList(PlaylistManager manager){

        ListView<String> playlists = new ListView<>();
        ObservableList<String> playlistitems = FXCollections.observableArrayList(manager.getPlaylistNames());
        playlists.setItems(playlistitems);
        playlists.setMaxHeight(160);
        playlists.setMaxWidth(650);


        ListView<String> songs = new ListView<>();
        ObservableList<String> songitems = FXCollections.observableArrayList(manager.getAktPlaylist().getSongNames());
        songs.setItems(songitems);
        songs.setMaxHeight(240);
        songs.setMaxWidth(650);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playlists, songs);
        this.setSpacing(5);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

}
