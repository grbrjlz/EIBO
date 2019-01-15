package presentation.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presentation.scenes.graphicsview.GraphicsView;
import presentation.scenes.playerview.PlayerView;
import presentation.scenes.playlistview.PlaylistView;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Views;

/**
 * Main-Applikation
 */
public class Main extends Application {

    private PlaylistView playlistview;
    private PlayerView playerview;
    private GraphicsView graphicsview;

    private Stage primaryStage;

    @Override
    /**
     * Hier werden alle im Backend benötigten Klassen, plus die Controller der benötigten Views initialisiert
     */
    public void init(){

        Playlist defPlaylist = new Playlist();
        PlaylistManager manager = new PlaylistManager(defPlaylist);
        Mp3Player player = new Mp3Player(defPlaylist);
        manager.addPlaylist(new Playlist("eigenePlaylist", "./newsongs"));

        playlistview = new PlaylistView(player, manager, this);
        playerview = new PlayerView(player, this);
        graphicsview = new GraphicsView(player, this);
    }

    @Override
    /**
     * Hier kann die default-Ansicht (was angezeigt werden soll, sobald sich das Programm öffnet) angepasst werden
     * Momentan: Playerview
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root, 1000, 750);
        scene.setRoot(graphicsview);

        scene.getStylesheets().add(getClass().
                getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setTitle("Player");
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public void switchView(Views view) {
        Scene scene = primaryStage.getScene();
        switch (view) {
            case player:
                scene.setRoot(playerview);
                break;
            case playlist:
                scene.setRoot(playlistview);
                break;
            case graphics:
                scene.setRoot(graphicsview);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
