package presentation.uicomponents.playercontrol;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.util.Duration;
import structure.Mp3Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PlayerControl extends HBox {
    private Mp3Player player;

    private Button play;
    private Button stop;
    private Button skip;
    private Button back;
    private Button shuffle;
    private Button repeat;

    private Slider volume;

    private boolean isPlaying;


    public PlayerControl(Mp3Player player){
        this.player = player;

        back = new Button();
        back.setId("back");

        play = new Button();
        play.setId("play");

        stop = new Button();
        stop.setId("stop");

        skip = new Button();
        skip.setId("skip");

        shuffle = new Button();
        shuffle.setId("shuffle");

        repeat = new Button();
        repeat.setId("repeat");

        volume = new Slider(0.0, 1.0, 0.75);

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(back, play, stop, skip, shuffle, repeat, volume);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
        initialize();
    }

    private void initialize(){
        play.addEventHandler(ActionEvent.ACTION, e -> {
            if (!isPlaying) {
                player.play();
                isPlaying = true;
            } else {
                player.pause();
                isPlaying = false;
            }
        });
        stop.addEventHandler(ActionEvent.ACTION, e -> {
            player.stop();
            isPlaying = false;
        });
        skip.addEventHandler(ActionEvent.ACTION, e -> {
            player.skip();
            //view.setPlayerContent(new PlayerContent(player));
        });
        back.addEventHandler(ActionEvent.ACTION, e -> {
            player.back();
            //view.setPlayerContent(new PlayerContent(player));
        });
        shuffle.addEventHandler(ActionEvent.ACTION, e -> {
            player.shuffle();
        });
        repeat.addEventHandler(ActionEvent.ACTION, e -> {
            player.repeat();
        });

        volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume(newValue.floatValue());
        });



        player.positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                //positionlabel.setText(newValue.toString());
            }
        });

    }
    public Button getBack() {
        return this.back;
    }

    public Button getPlay() {
        return this.play;
    }

    public Button getSkip() {
        return this.skip;
    }

    public Button getRepeat() {
        return this.repeat;
    }

    public Button getShuffle() {
        return this.shuffle;
    }

    public Button getStop() {
        return this.stop;
    }

    public Slider getVolume(){return this.volume;}
    
}
