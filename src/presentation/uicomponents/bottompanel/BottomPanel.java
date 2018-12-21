package presentation.uicomponents.bottompanel;

import javafx.scene.layout.StackPane;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;

public class BottomPanel extends StackPane {

    private Mp3Player player;
    public PlayerControl playercontrol;

    public BottomPanel(Mp3Player player) {
        this.player = player;
        playercontrol = new PlayerControl(player);

        this.setMinSize(1000, 80);
        this.setMaxHeight(80);
        this.setStyle("-fx-background-color: #3E3E3E;");
        this.getChildren().add(playercontrol);

        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public PlayerControl getPlayercontrol(){
        return this.playercontrol;
    }

    public void setPlayercontrol(PlayerControl playercontrol){
        this.playercontrol = playercontrol;
    }

}

