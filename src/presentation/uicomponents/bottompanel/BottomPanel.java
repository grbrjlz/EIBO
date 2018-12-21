package presentation.uicomponents.bottompanel;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.playercontrol.PlayerControl;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

public class BottomPanel extends StackPane {

    private Mp3Player player;
    public PlayerControl playercontrol;

    public BottomPanel(Mp3Player player) {
        this.player = player;
        playercontrol = new PlayerControl(player);

        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");
        this.getChildren().add(playercontrol);

        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public PlayerControl getPlayercontrol(){
        return this.playercontrol;
    }

}

