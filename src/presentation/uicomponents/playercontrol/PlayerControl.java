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
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.util.Duration;
import structure.Mp3Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PlayerControl extends BorderPane {
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

        HBox left = new HBox();
        HBox center = new HBox();
        HBox right = new HBox();

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
        shuffle.setStyle("-fx-border-color: #ee1200");


        repeat = new Button();
        repeat.setId("repeat");
        repeat.setStyle("-fx-border-color: #ee1200");


        volume = new Slider(-50, 50, 25);

        center.getChildren().addAll(back, play, stop, skip);
        left.getChildren().addAll(shuffle, repeat);
        right.getChildren().addAll(volume);

        left.setPadding(new Insets(0, 0, 0, 30));
        right.setPadding(new Insets(0, 30, 0, 0));

        center.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER);
        left.setAlignment(Pos.CENTER);

        center.setSpacing(5);
        left.setSpacing(5);

        this.setLeft(left);
        this.setRight(right);
        this.setCenter(center);

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

        player.shuffleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    shuffle.setStyle("-fx-border-color: #88ee00");
                } else {
                    shuffle.setStyle("-fx-border-color: #ee1200");
                }
            }
        });

        player.repeatProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    repeat.setStyle("-fx-border-color: #88ee00");
                } else {
                    repeat.setStyle("-fx-border-color: #ee1200");
                }
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
