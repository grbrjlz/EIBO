package presentation.scenes.playerview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;

public class PlayerViewController {

    private PlayerView view;

    private PlayerControl playerControl;
    private ViewControl viewControl;
    private PlayerContent content;

    private Mp3Player player;
    private Main application;
    private boolean isPlaying;


    public PlayerViewController(Mp3Player player, Main application){

        isPlaying = false;

        this.player = player;
        this.view = new PlayerView(player);

        this.content = view.getPlayerContent();
        this.playerControl = view.getBottomPanel().getPlayercontrol();
        this.viewControl = view.getTopPanel().getViewcontrol();

        this.application = application;
        initialize();
    }

    private void initialize(){
        playerControl.getPlay().addEventHandler(ActionEvent.ACTION, e -> {
            if (!isPlaying) {
                player.play();
                isPlaying = true;
            } else {
                player.pause();
                isPlaying = false;
            }
        });

        playerControl.getStop().addEventHandler(ActionEvent.ACTION, e -> {
            player.stop();
            isPlaying = false;
        });

        playerControl.getSkip().addEventHandler(ActionEvent.ACTION, e -> {
            player.skip();
            view.setPlayerContent(new PlayerContent(player));
        });

        playerControl.getBack().addEventHandler(ActionEvent.ACTION, e -> {
            player.back();
            view.setPlayerContent(new PlayerContent(player));
        });

        playerControl.getShuffle().addEventHandler(ActionEvent.ACTION, e -> {
            player.shuffle();
        });

        playerControl.getRepeat().addEventHandler(ActionEvent.ACTION, e -> {
            player.repeat();
        });


       playerControl.getVolume().valueProperty().addListener((observable, oldValue, newValue) -> {

            player.setVolume(newValue.floatValue());
           view.getBottomPanel().getPlayercontrol().getVolumelabel().setText(newValue.toString());
        });



        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));



    }

    public PlayerView getView() {
        return this.view;
    }


}

