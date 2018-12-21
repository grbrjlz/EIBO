package presentation.uicomponents.toppanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import presentation.uicomponents.viewcontrol.ViewControl;

public class TopPanel extends StackPane {

    private String toggleString;

    private ViewControl viewcontrol;


    public TopPanel() {

        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");

        this.viewcontrol = new ViewControl();
        this.getChildren().addAll(viewcontrol);

        /*switchPlayer = new Button("PLAYER");
        switchPlayer.setUserData("PLAYER");
        switchList = new Button("LIST");
        this.getChildren().addAll(switchPlayer,switchList);*/
    }

    public ViewControl getViewcontrol(){
        return this.viewcontrol;
    }

}
