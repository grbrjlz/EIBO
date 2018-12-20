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
    Pane toppanel, bottompanel, sidepanelLeft, sidepanelRight, playercontent;

    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;


    public PlayerView(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;

        toppanel = new TopPanel();
        bottompanel = new BottomPanel(defPlaylist, player, manager);
        sidepanelLeft = new SidePanel();
        sidepanelRight = new SidePanel();
        playercontent = new PlayerContent(defPlaylist, player, manager);


        this.setTop(toppanel);
        this.setBottom(bottompanel);
        this.setLeft(sidepanelLeft);
        this.setRight(sidepanelRight);
        this.setCenter(playercontent);


        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }

}
