package presentation.scenes.playlistview;

import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.PlaylistManager;

class PlaylistView extends BorderPane {
    private TopPanel topPanel;
    private SidePanel sidePanelLeft;
    private SidePanel sidePanelRight;
    private PlaylistContent playlistContent;
    private BottomPanel bottomPanel;
    private Mp3Player player;
    private PlaylistManager manager;


    PlaylistView(Mp3Player player, PlaylistManager manager) {
        this.player = player;
        this.manager = manager;
        this.topPanel = new TopPanel();
        this.bottomPanel = new BottomPanel(player);
        this.sidePanelLeft = new SidePanel();
        this.sidePanelRight = new SidePanel();
        this.playlistContent = new PlaylistContent(player, manager  );

        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        this.setLeft(sidePanelLeft);
        this.setRight(sidePanelRight);
        this.setCenter(playlistContent);


        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    TopPanel getTopPanel(){
        return this.topPanel;
    }

    BottomPanel getBottomPanel(){
        return this.bottomPanel;
    }

    PlaylistContent getPlaylistContent() {
        return this.playlistContent;
    }

    void setPlaylistContent(PlaylistContent content){
        this.playlistContent = content;
        this.setCenter(playlistContent);
    }

}
