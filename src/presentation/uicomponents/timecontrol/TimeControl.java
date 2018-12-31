package presentation.uicomponents.timecontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import structure.Mp3Player;

/**
 * Slider, der den aktuellen Track repräsentiert
 */
public class TimeControl extends HBox {

    private final Mp3Player player;
    private final Slider slider;
    private final Label postion;
    private final Label length;

    public TimeControl (Mp3Player player){
        this.player = player;
        this.slider = new Slider(0.0, player.getAudioPlayer().getTotalDuration().toMillis(), 0);
        this.postion = new Label("0s");
        this.length = new Label();

        length.setText((player.getAudioPlayer().getTotalDuration().toMillis())/1000+"s");
        postion.setPadding(new Insets(0,10,0,0));
        length.setPadding(new Insets(0,0,0,10));
        postion.setAlignment(Pos.CENTER);
        length.setAlignment(Pos.CENTER);

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5,0,0,0));
        this.getChildren().addAll(postion, slider, length);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        initialize();
    }

    private void initialize() {

        /**
         * Falls per Maus etwas geändert wird
         */
        slider.setOnMouseReleased(event -> player.play((int)slider.getValue()));

        /**
         * Falls sich die aktuelle Songlänge ändert
         */
        player.aktSongLengthProperty().addListener((observable, oldValue, newValue) -> {
            slider.setMax(newValue.intValue());
            length.setText((newValue.intValue())/1000+"s");
        });

        /**
         * Falls sich die aktuelle position() im Player ändert
         */
        player.positionProperty().addListener((observable, oldValue, newValue) -> {
            postion.setText((newValue.intValue())/1000+"s");
            slider.setValue(newValue.intValue());
        });
    }
}
