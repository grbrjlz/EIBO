package presentation.uicomponents.timecontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import structure.Mp3Player;

public class TimeControl extends HBox {

    Mp3Player player;
    Slider slider;
    Label postion, length;


    public TimeControl (Mp3Player player){
        this.player = player;
        this.slider = new Slider(0.0, 1.0, 0);
        this.postion = new Label("0");
        this.length = new Label(Integer.toString(player.getAktTrack().getLength()));

        postion.setPadding(new Insets(0,10,0,0));
        length.setPadding(new Insets(0,0,0,10));

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(30,0,20,0));
        this.getChildren().addAll(postion, slider, length);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

}
