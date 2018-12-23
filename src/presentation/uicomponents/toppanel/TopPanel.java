package presentation.uicomponents.toppanel;

import javafx.scene.layout.StackPane;
import presentation.uicomponents.viewcontrol.ViewControl;

public class TopPanel extends StackPane {

    private final ViewControl viewcontrol;

    public TopPanel() {
        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #3E3E3E;");
        this.viewcontrol = new ViewControl();
        this.getChildren().addAll(viewcontrol);
    }

    public ViewControl getViewcontrol(){
        return this.viewcontrol;
    }
}
