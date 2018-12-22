package presentation.scenes.playlistview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.playercontent.PlayerContent;
import presentation.uicomponents.playercontrol.PlayerControl;
import presentation.uicomponents.playlistcontent.PlaylistContent;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;
import structure.PlaylistManager;

public class PlaylistViewController {

    private PlaylistView view;

    private ViewControl viewControl;
    private PlaylistContent content;

    private Mp3Player player;
    private Main application;

    public PlaylistViewController(Mp3Player player, PlaylistManager manager,  Main application){

        this.player = player;
        this.view = new PlaylistView(player, manager);

        this.content = view.getPlaylistContent();
        this.viewControl = view.getTopPanel().getViewcontrol();

        this.application = application;
        initialize();
    }

    private void initialize(){

        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("PLAYER"));



    }

    public PlaylistView getView() {
        return this.view;
    }


}

