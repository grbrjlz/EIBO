package presentation.uicomponents.list;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import structure.Mp3Player;
import structure.Track;

class TrackListCell extends ListCell<Track> {
    private HBox root;
    private VBox infoPane;
    private Label titleLabel;
    private Label artistLabel;
    private Label albumLabel;
    Mp3Player player;

    TrackListCell() {
        infoPane = new VBox();
        titleLabel = new Label();
        artistLabel = new Label();
        albumLabel = new Label();
        infoPane.getChildren().addAll(titleLabel, artistLabel, albumLabel);
        infoPane.setAlignment(Pos.CENTER);
        root = new HBox();
        root.getChildren().addAll(infoPane);
        root.setAlignment(Pos.CENTER);
    }

    @Override
    protected void updateItem(Track x, boolean empty) {
        super.updateItem(x,empty);
        setText(null);
        setGraphic(null);
        if (x != null) {
            titleLabel.setText(x.getName());
            artistLabel.setText(x.getArtist());
            albumLabel.setText(x.getAlbum());
            this.setGraphic(root);
        } else {
            setGraphic(null);
        }
    }

}
