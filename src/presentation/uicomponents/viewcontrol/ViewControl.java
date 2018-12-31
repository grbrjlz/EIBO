package presentation.uicomponents.viewcontrol;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import presentation.application.Main;
import structure.Views;

public class ViewControl extends HBox {
    Main application;
    Button playlistview, playerview, graphicsview;

    public ViewControl(Main application){
        this.application = application;

        this.playlistview = new Button("list");
        playlistview.setUserData("playlistview");
        playlistview.setId("playlistview");

        this.playerview = new Button("player");
        playerview.setUserData("playerview");
        playerview.setId("playerview");



        this.graphicsview = new Button("graphics");
        graphicsview.setUserData("graphicsview");
        graphicsview.setId("graphicsview");


        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playlistview, playerview, graphicsview);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        initialize();

    }

    public void initialize(){
        playlistview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.playlist));
        playerview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.player));
        graphicsview.addEventFilter(ActionEvent.ACTION, e -> application.switchView(Views.graphics));

    }
}
