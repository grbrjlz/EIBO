package presentation.uicomponents.filelist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.util.ArrayList;


public class FileList extends VBox {

    Mp3Player player;
    PlaylistManager manager;
    ListView<String> playlists, songs;

    public FileList(Mp3Player player, PlaylistManager manager) {
        this.manager = manager;
        this.player = player;
        this.playlists = new ListView<>();
        ObservableList<String> playlistitems = FXCollections.observableArrayList(manager.getPlaylistNames());
        playlists.setItems(playlistitems);
        playlists.setMaxHeight(160);
        playlists.setMaxWidth(650);
        playlists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        this.songs = new ListView<>();
        ObservableList<String> songitems = FXCollections.observableArrayList(manager.getAktPlaylist().getSongNames());
        songs.setItems(songitems);
        songs.setMaxHeight(240);
        songs.setMaxWidth(650);
        songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playlists, songs);
        this.setSpacing(5);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();
    }

    public void initialize() {

        playlists.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player.pause();
                int selected = playlists.getSelectionModel().getSelectedIndex();

                manager.setAktPlaylist(selected);
                player.setAktPlaylist(manager.getPlaylist(selected));

                ArrayList<String> newsongs = manager.getAktPlaylist().getSongNames();

                ObservableList<String> newsongitems = FXCollections.observableArrayList(newsongs);
                songs.setItems(newsongitems);
            }
        });

        songs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int selected = songs.getSelectionModel().getSelectedIndex();
                player.pause();
                player.play(selected);
            }
        });
    }
}