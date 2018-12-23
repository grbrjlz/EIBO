package presentation.scenes.playerview;

import javafx.scene.layout.*;
import presentation.uicomponents.bottompanel.BottomPanel;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.sidepanel.SidePanel;
import presentation.uicomponents.toppanel.TopPanel;
import structure.Mp3Player;

class PlayerView extends BorderPane {
    private final TopPanel topPanel;
    private final PlayerContent playerContent;


    PlayerView(Mp3Player player) {
        this.topPanel = new TopPanel();
        BottomPanel bottomPanel = new BottomPanel(player);
        SidePanel sidePanelLeft = new SidePanel();
        SidePanel sidePanelRight = new SidePanel();
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

}
