package presentation.uicomponents.toppanel;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class TopPanel extends HBox {


    public TopPanel() {
        //this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(1000, 60);
        this.setMaxHeight(60);
        this.setStyle("-fx-background-color: #2E2E30;");
        //this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

}
