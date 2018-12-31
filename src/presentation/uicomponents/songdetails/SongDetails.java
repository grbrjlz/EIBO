package presentation.uicomponents.songdetails;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Track;

/**
 * Enthält weitere Songinformationen
 */
public class SongDetails extends VBox {

    private Track song;

    private final Label album, year, track, key, length, bpm;

    public SongDetails (Mp3Player player){
        this.song = player.getAktTrack();

        album = new Label();
        year = new Label();
        key = new Label();
        length = new Label();
        bpm = new Label();
        track = new Label();


        album.setAlignment(Pos.CENTER);
        year.setAlignment(Pos.CENTER);
        key.setAlignment(Pos.CENTER);
        length.setAlignment(Pos.CENTER);
        bpm.setAlignment(Pos.CENTER);
        track.setAlignment(Pos.CENTER);


        HBox col1 = new HBox();
        HBox col2 = new HBox();

        col1.getChildren().addAll(album, track, year);
        col2.getChildren().addAll(length, bpm, key);
        col1.setAlignment(Pos.CENTER);
        col2.setAlignment(Pos.CENTER);

        setLabels(player);

        this.setPadding(new Insets(30, 0, 30, 0));
        this.getChildren().addAll(col1, col2);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    /**
     * Methode, um im nachhinein die Labels neu zu setzen
     * @param player um Informationen abzurufen
     */
    public void setLabels(Mp3Player player){
        this.song = player.getAktTrack();

        album.setText("Album: "+player.getAktTrack().getAlbum());
        year.setText("Jahr: "+song.getYear());
        key.setText("Key: "+song.getKey());
        length.setText("Länge: "+(player.getAktSongLength())/1000+"s");
        bpm.setText("BPM: "+song.getBpm());
        track.setText("TrackNr: "+song.getTrack());
    }
}
