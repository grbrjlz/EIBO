package presentation.uicomponents.songinfo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Track;

/**
 * Enthält Titel + Künstler
 */
public class SongInfo extends VBox {

    private Track track;
    private final Label title;
    private final Label artist;

    public SongInfo (Mp3Player player){
        this.track = player.getAktTrack();

        title = new Label();
        title.setId("title");
        artist = new Label();
        artist.setId("artist");

        setLabels(player);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(0, 0, 30, 0));
        this.getChildren().addAll(title, artist);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    /**
     * Methode, um im nachhinein die Labels neu zu setzen
     * @param player um Informationen abzurufen
     */
    public void setLabels(Mp3Player player){
        this.track = player.getAktTrack();
        title.setText(track.getTitle());
        artist.setText(track.getArtist());

    }

}
