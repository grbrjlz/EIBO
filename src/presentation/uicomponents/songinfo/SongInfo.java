package presentation.uicomponents.songinfo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import structure.Track;

public class SongInfo extends VBox {

    private Track track;

    public SongInfo (Track track){
        this.track = track;

        Label title = new Label(track.getTitle());
        title.setId("title");

        Label artist = new Label(track.getArtist());
        artist.setId("artist");

        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(title, artist);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }
}
