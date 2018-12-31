package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

/**
 * Ein Trackobjekt kennt die eigentliche .mp3-Datei + schon herausgefilterte Informationen zur jeweiligen .mp3
 */
public class Track {

    private final Mp3File song;

    private final String filename;
    private String title;
    private String artist;
    private String album;
    private String comment;
    private String year;
    private String track;
    private String key;
    private int genre, length, bpm;

    /**
     * Es wird zuerst versucht die wesentlichen Zusatzinformationen aus dem id3v2-Tag zu lesen, da dieses zuverlässiger ist
     * @param song die .mp3 muss beim Erstellen eines Tracks mitgegeben werden
     */
    public Track(Mp3File song) {
        this.song = song;
        this.filename = song.getFilename();

        if (song.hasId3v2Tag()) {
            ID3v2 id3v2 = song.getId3v2Tag();
            this.title = id3v2.getTitle();
            this.artist = id3v2.getArtist();
            this.album = id3v2.getAlbum();
            this.comment = id3v2.getComment();
            this.year = id3v2.getYear();
            this.genre = id3v2.getGenre();
            this.track = id3v2.getTrack();
            this.length = id3v2.getLength();
            this.bpm = id3v2.getBPM();
            this.key = id3v2.getKey();
        }

        if (song.hasId3v1Tag() && !song.hasId3v2Tag()) {
            ID3v1 id3v1 = song.getId3v1Tag();
            this.title = id3v1.getTitle();
            this.artist = id3v1.getArtist();
            this.album = id3v1.getAlbum();
            this.comment = id3v1.getComment();
            this.year = id3v1.getYear();
            this.genre = id3v1.getGenre();
            this.track = id3v1.getTrack();
        }

    }

    public String getYear() {
        if (year != null) {
            return this.year;
        } else return "-";    }

    public String getTrack() {
        if (track != null) {
            return this.track;
        } else return "-";    }

    public String getKey() {
        if (key != null) {
            return this.key;
        } else return "-";    }

    public int getGenre() {
        if (genre != 0) {
            return this.genre;
        } else return 0;    }

    public int getBpm() {
        if (bpm != 0) {
            return this.bpm;
        } else return 0;    }

    String getFilename() {
        return this.filename;
    }

    Mp3File getSong() {
        return this.song;
    }

    public String getTitle() {
        if (title != null) {
            return this.title;
        } else return this.filename;
    }

    public String getArtist() {
        if (artist != null) {
            return this.artist;
        } else return "-";
    }

    public String getAlbum() {
        if (album != null) {
            return this.album;
        } else return "-";
    }

    public String getComment(){
        if (comment != null) {
            return this.comment;
        } else return "-";
    }

    /**
     * Liefert das Cover direkt als Imageobjekt (statt Byte-Array)
     * @return
     */
    public Image getCover() {
        if (song.getId3v2Tag().getAlbumImage() != null) {
            try {
                Image image =new Image(new ByteArrayInputStream(song.getId3v2Tag().getAlbumImage()));
                if (!image.isError()){
                    return image;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } return null;
    }

    /**
     * Nicht in Benutzung, wird nun direkt über player.length() im Player abgerufen
     * (Fehleranfällig)
     *
     * @return
     */
    public int getLength() {
        return length;
    }

}




