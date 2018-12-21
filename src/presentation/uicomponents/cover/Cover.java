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

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Cover extends StackPane {
    private Playlist defPlaylist;
    private Mp3Player player;
    private PlaylistManager manager;
    private Image img;

    public Cover(Playlist defPlaylist, Mp3Player player, PlaylistManager manager) {
        this.defPlaylist = defPlaylist;
        this.player = player;
        this.manager = manager;
        ImageView iv1 = new ImageView();

        try {
            if (defPlaylist.getAktSong().getSong().hasId3v2Tag()){
                ID3v2 id3v2Tag = defPlaylist.getAktSong().getSong().getId3v2Tag();
                byte[] cover = id3v2Tag.getAlbumImage();

                if (cover!=null) {
                    img = new Image(new ByteArrayInputStream(cover));
                }
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
