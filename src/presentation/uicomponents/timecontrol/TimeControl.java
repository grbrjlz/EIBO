package presentation.uicomponents.timecontrol;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import structure.Mp3Player;

import java.util.Calendar;

public class TimeControl extends HBox {

    Mp3Player player;
    Slider slider;
    Label postion, length;


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

    public void initialize() {
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
