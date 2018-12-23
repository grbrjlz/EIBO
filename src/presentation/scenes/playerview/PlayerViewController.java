package presentation.scenes.playerview;

import javafx.event.ActionEvent;
import presentation.application.Main;
import presentation.uicomponents.viewcontrol.ViewControl;
import structure.Mp3Player;

public class PlayerViewController {

    private final PlayerView view;

    private final ViewControl viewControl;

    private final Main application;

    public PlayerViewController(Mp3Player player, Main application){

        this.view = new PlayerView(player);
        this.viewControl = view.getTopPanel().getViewcontrol();
        this.application = application;
        initialize();
    }

    private void initialize(){
        viewControl.addEventFilter(ActionEvent.ACTION, e -> application.switchView("LIST"));
    }

    public PlayerView getView() {
        return this.view;
    }

}

