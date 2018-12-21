package presentation.uicomponents.playercontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import structure.Mp3Player;

public class PlayerControl extends HBox {
    private Mp3Player player;

    private Button play;
    private Button stop;
    private Button skip;
    private Button back;
    private Button shuffle;
    private Button repeat;

    private Slider volume;
    private Label volumelabel;

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
        volumelabel = new Label("0.75");

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(back, play, stop, skip, shuffle, repeat, volume, volumelabel);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());
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

    public Label getVolumelabel(){return this.volumelabel;}

    public void setVolumelabel(float value){
        volumelabel= new Label(Float.toString(value));
    }
}
