package presentation.scenes.playlistview;

import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;

class PlaylistView extends BorderPane {
    private TopPanel topPanel;
    private SidePanel sidePanelLeft;
    private SidePanel sidePanelRight;
    private PlaylistContent playlistContent;
    private BottomPanel bottomPanel;
    private Mp3Player player;


    PlaylistView(Mp3Player player) {
        this.player = player;
        this.topPanel = new TopPanel();
        this.bottomPanel = new BottomPanel(player);
        this.sidePanelLeft = new SidePanel();
        this.sidePanelRight = new SidePanel();
        this.playlistContent = new PlaylistContent(player);

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
