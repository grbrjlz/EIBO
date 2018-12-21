package presentation.uicomponents.playlistcontent;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import presentation.uicomponents.cover.Cover;
import presentation.uicomponents.songinfo.SongInfo;
import structure.Mp3Player;
import structure.PlaylistManager;

public class PlaylistContent extends VBox {


    private Mp3Player player;
    private PlaylistManager manager;

    public PlaylistContent(Mp3Player player,PlaylistManager manager){
        this.player = player;
        this.manager = manager;

        Label aktPlaylistName = new Label(manager.getAktPlaylistName());

        this.setAlignment(Pos.CENTER);
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.setStyle("-fx-background-color: #F2F2F2;");
        this.getChildren().addAll(aktPlaylistName);
       // this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


    }


}
