package presentation.scenes.playlistview;

import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;
import structure.PlaylistManager;

public class PlaylistViewController {

    private final PlaylistView view;

    private final ViewControl viewControl;

    private final Main application;

    public PlaylistViewController(Mp3Player player, PlaylistManager manager,  Main application){
        this.view = new PlaylistView(player, manager);
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

