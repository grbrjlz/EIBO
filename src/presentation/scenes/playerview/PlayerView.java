package presentation.scenes.playerview;

import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;

class PlayerView extends BorderPane {
    private TopPanel topPanel;
    private SidePanel sidePanelLeft;
    private SidePanel sidePanelRight;
    private PlayerContent playerContent;
    private BottomPanel bottomPanel;
    private Mp3Player player;


    PlayerView(Mp3Player player) {
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

    TopPanel getTopPanel(){
        return this.topPanel;
    }

    BottomPanel getBottomPanel(){
        return this.bottomPanel;
    }

    PlayerContent getPlayerContent() {
        return this.playerContent;
    }

    void setPlayerContent(PlayerContent content){
        this.playerContent = content;
        this.setCenter(playerContent);

    }
}
