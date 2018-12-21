package presentation.scenes.playerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;

import java.io.IOException;

public class PlayerView extends BorderPane {
    TopPanel topPanel;
    SidePanel sidePanelLeft;
    SidePanel sidePanelRight;
    PlayerContent playerContent;
    BottomPanel bottomPanel;
    private Mp3Player player;


    public PlayerView(Mp3Player player) {
        this.player = player;
        this.topPanel = new TopPanel();
        this.bottomPanel = new BottomPanel(player);
        this.sidePanelLeft = new SidePanel();
        this.sidePanelRight = new SidePanel();
        this.playerContent = new PlayerContent(player);

        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        this.setLeft(sidePanelLeft);
        this.setRight(sidePanelRight);
        this.setCenter(playerContent);


        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    public TopPanel getTopPanel(){
        return this.topPanel;
    }

    public BottomPanel getBottomPanel(){
        return this.bottomPanel;
    }

    public PlayerContent getPlayerContent() {
        return this.playerContent;
    }

    public void setPlayerContent(PlayerContent content){
        this.playerContent = content;
        this.setCenter(playerContent);

    }
}
