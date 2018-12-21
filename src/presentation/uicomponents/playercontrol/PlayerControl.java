package presentation.uicomponents.playercontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(back, play, stop, skip, shuffle, repeat);
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
}
