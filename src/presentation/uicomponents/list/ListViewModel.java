package presentation.uicomponents.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.list.*;
import structure.Playlist;
import structure.Track;
import java.util.List;

public class ListViewModel extends StackPane {


    private ListView<Track> playlist;
    private List<Track> songs;


    public ListViewModel(Playlist defPlayList){

        ObservableList<Track> items = FXCollections.observableArrayList();
        songs = defPlayList.getSongs();
        items.setAll(songs);

        playlist = new ListView<>();
        playlist.setItems(items);
        playlist.setEditable(true);
        playlist.setCellFactory(v -> new TrackListCell());

        //????
        //playlist.getSelectionModel().selectedItemProperty().addListener();

        this.setMaxWidth(300);
        this.setMaxHeight(500);
        this.getChildren().addAll(playlist);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());

    }
}
