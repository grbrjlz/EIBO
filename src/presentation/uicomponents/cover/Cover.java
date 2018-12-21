package presentation.uicomponents.cover;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import structure.Mp3Player;
import structure.Playlist;
import structure.PlaylistManager;
import structure.Track;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Cover extends StackPane {
    private Track aktTrack;
    private Image img;

    public Cover(Track aktTrack) {
        this.aktTrack = aktTrack;
        ImageView iv1 = new ImageView();

        try {
            if (aktTrack.getCover() != null){
                byte[] cover = aktTrack.getCover();
                img = new Image(new ByteArrayInputStream(cover));
                iv1.setImage(img);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        iv1.setFitHeight(350);
        iv1.setFitWidth(350);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(iv1);

    }

}
