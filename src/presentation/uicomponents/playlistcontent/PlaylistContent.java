package presentation.uicomponents.playlistcontent;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songinfo.SongInfo;
import structure.Mp3Player;

public class PlaylistContent extends VBox {
    private Cover cover;
    private SongInfo songinfo;

    private Mp3Player player;

    public PlaylistContent(Mp3Player player){
        this.player = player;
        this.cover = new Cover(player.getAktTrack());
        this.songinfo = new SongInfo(player.getAktTrack());

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.getChildren().addAll(songinfo);
       // this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }


}