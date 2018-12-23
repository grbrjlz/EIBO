package presentation.uicomponents.timecontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import structure.Mp3Player;

public class TimeControl extends HBox {

    private final Mp3Player player;
    private final Slider slider;
    private final Label postion;
    private final Label length;

    public TimeControl (Mp3Player player){
        this.player = player;
        this.slider = new Slider(0.0, player.getAudioPlayer().length(), 0);
        this.postion = new Label("0s");
        this.length = new Label();
        length.setText((player.getAudioPlayer().length())/1000+"s");
        postion.setPadding(new Insets(0,10,0,0));
        length.setPadding(new Insets(0,0,0,10));
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(30,0,20,0));
        this.getChildren().addAll(postion, slider, length);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        initialize();
    }

    private void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) player.pause();
            else player.play(newValue.intValue());
        });

        player.aktSongLengthProperty().addListener((observable, oldValue, newValue) -> {
            slider.setMax(newValue.intValue());
            length.setText((newValue.intValue())/1000+"s");
        });

        player.positionProperty().addListener((observable, oldValue, newValue) -> {
            postion.setText((newValue.intValue())/1000+"s");
            slider.setValue(newValue.intValue());
        });
    }
}
