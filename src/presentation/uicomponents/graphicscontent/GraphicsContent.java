package presentation.uicomponents.graphicscontent;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import presentation.uicomponents.songdetails.SongDetails;
import presentation.uicomponents.songinfo.SongInfo;
import structure.Mp3Player;



public class GraphicsContent extends BorderPane {
    private final Mp3Player player;
    private final SongInfo songinfo;
    private final SongDetails songdetails;
    private final Button color1, color2, color3, color4, color5, color6;

    private int currentstyle=1;


    AreaChart<String, Number> spektrum;



    public GraphicsContent(Mp3Player player) {
        this.player = player;
        this.songinfo = new SongInfo(player);
        this.songdetails = new SongDetails(player);
        this.spektrum = player.getSpektrum();

        GridPane leftpanel = new GridPane();
        GridPane rightpanel = new GridPane();

        this.color1 = new Button();
        color1.setId("color1");
        this.color2 = new Button();
        color2.setId("color2");
        this.color3 = new Button();
        color3.setId("color3");
        this.color4 = new Button();
        color4.setId("color4");
        this.color5 = new Button();
        color5.setId("color5");
        this.color6 = new Button();
        color6.setId("color6");

        leftpanel.setHgap(5);
        leftpanel.setVgap(5);
        leftpanel.add(color1, 0, 0);
        leftpanel.add(color2, 1, 0);
        leftpanel.add(color3, 0, 1);
        leftpanel.add(color4, 1, 1);
        leftpanel.add(color5, 0, 2);
        leftpanel.add(color6, 1, 2);
        leftpanel.setAlignment(Pos.CENTER);
        leftpanel.setPadding(new Insets(20));

        songinfo.setPadding(new Insets(20));
        songdetails.setPadding(new Insets(20));


        //spektrum.setMaxWidth(1000);
        //spektrum.setMaxHeight(750);

        this.setTop(songinfo);
        this.setBottom(songdetails);
        this.setCenter(spektrum);
        this.setLeft(leftpanel);
        //this.setMinHeight(600);
        //this.setMinWidth(800);
        //this.setPrefWidth(1200);
        //this.setPrefHeight(900);

        this.setStyle("-fx-background-color: #2E2E30;");
        //this.getChildren().addAll(songinfo, spektrum);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        initialize();
    }


    public void initialize(){
        player.aktSongNameProperty().addListener((observable, oldValue, newValue) -> {
            songinfo.setLabels(player);
            songdetails.setLabels(player);
        });


        color1.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 1) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle1.css").toExternalForm());
                currentstyle = 1;
            }
        });

        color2.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 2) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle2.css").toExternalForm());
                currentstyle = 2;
            }});

        color3.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 3) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle3.css").toExternalForm());
                currentstyle = 3;
            }});

        color4.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 4) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle4.css").toExternalForm());
                currentstyle = 4;
            }});

        color5.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 5) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle5.css").toExternalForm());
                currentstyle = 5;
            }});

        color6.addEventHandler(ActionEvent.ACTION, e -> {
            if (currentstyle != 6) {
                spektrum.getStylesheets().clear();
                spektrum.getStylesheets().add(getClass().getResource("Chartstyles/cstyle6.css").toExternalForm());
                currentstyle = 6;
            }});


    }




}
