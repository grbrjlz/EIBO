package presentation.uicomponents.cover;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import structure.Track;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Cover extends StackPane {
    private Track aktTrack;
    private Image img;
    File defCoverPath = new File("./src/assets/images/nocover.jpg");

    BufferedImage defCoverImage;
    {
        try {
            defCoverImage = ImageIO.read(defCoverPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    WritableRaster raster = defCoverImage.getRaster();
    DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
    byte[] defCover = data.getData();

    public Cover(Track aktTrack) {
        this.aktTrack = aktTrack;
        setCover(aktTrack);

    }

    public void setCover(Track aktTrack) {
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
        this.getChildren().addAll(iv1);

    }

    public void setDefaultCover(){

        this.getChildren().clear();
        ImageView iv1 = new ImageView();
        try {
            img = new Image(new ByteArrayInputStream(defCover));
            iv1.setImage(img);
        } catch (Exception e){
            e.printStackTrace();
        }

        iv1.setFitHeight(350);
        iv1.setFitWidth(350);
        this.getChildren().addAll(iv1);
    }
}
