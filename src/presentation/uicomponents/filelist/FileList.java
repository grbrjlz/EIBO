package presentation.uicomponents.filelist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import structure.Playlist;


public class FileList extends StackPane {

    public FileList(Playlist playlist){

        ListView<String> list = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList(playlist.getSongNames());

        list.setItems(items);

        this.getChildren().addAll(list);
    }

}
