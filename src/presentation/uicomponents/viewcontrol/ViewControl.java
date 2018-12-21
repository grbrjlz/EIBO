package presentation.uicomponents.viewcontrol;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ViewControl extends HBox {

    private Button switchview;

    public ViewControl(){

        this.switchview = new Button();
        switchview.setUserData("switch");
        switchview.setId("switch-button");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(switchview);
        this.getStylesheets().add(getClass().
                getResource("style.css").toExternalForm());


    }
}
