package presentation.scenes.playlistview;

import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;

public class PlayListViewController {

    private PlaylistView view;

    private PlayerControl playerControl;
    private ViewControl viewControl;
    private PlaylistContent content;

    private Mp3Player player;
    private Main application;
    private boolean isPlaying;


    public PlayListViewController(Mp3Player player, Main application){

        isPlaying = false;

        this.player = player;
        this.view = new PlaylistView(player);

        this.content = view.getPlaylistContent();
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
            view.setPlaylistContent(new PlaylistContent(player));
        });

        playerControl.getBack().addEventHandler(ActionEvent.ACTION, e -> {
            player.back();
            view.setPlaylistContent(new PlaylistContent(player));
        });

        playerControl.getShuffle().addEventHandler(ActionEvent.ACTION, e -> {
            player.shuffle();
        });

        playerControl.getRepeat().addEventHandler(ActionEvent.ACTION, e -> {
            player.repeat();
        });

        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));



    }

    public PlaylistView getView() {
        return this.view;
    }


}

