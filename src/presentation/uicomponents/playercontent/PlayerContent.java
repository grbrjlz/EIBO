package presentation.uicomponents.playercontent;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songinfo.SongInfo;
import structure.Mp3Player;

public class PlayerContent extends VBox {
    private Cover cover;
    private SongInfo songinfo;

    private Mp3Player player;

    public PlayerContent(Mp3Player player){
        this.player = player;
        this.cover = new Cover(player.getAktTrack());
        this.songinfo = new SongInfo(player.getAktTrack());

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #2E2E30;");
        this.getChildren().addAll(cover, songinfo);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();
    }

    public void initialize(){
        //player.getAktTrack().
    }


}
