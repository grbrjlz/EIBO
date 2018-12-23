package presentation.scenes.playlistview;

import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;
import structure.PlaylistManager;

class PlaylistView extends BorderPane {
    private final TopPanel topPanel;
    private final PlaylistContent playlistContent;


    PlaylistView(Mp3Player player, PlaylistManager manager) {
        this.topPanel = new TopPanel();
        BottomPanel bottomPanel = new BottomPanel(player);
        SidePanel sidePanelLeft = new SidePanel();
        SidePanel sidePanelRight = new SidePanel();
        this.playlistContent = new PlaylistContent(player, manager  );
        this.setTop(topPanel);
        this.setBottom(bottomPanel);
        this.setLeft(sidePanelLeft);
        this.setRight(sidePanelRight);
        this.setCenter(playlistContent);
    }

    TopPanel getTopPanel(){
        return this.topPanel;
    }

    PlaylistContent getPlaylistContent() {
        return this.playlistContent;
    }

}
