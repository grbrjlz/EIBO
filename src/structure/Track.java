package structure;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class Track {

    private Mp3File song;
    private String name;

    private boolean hasid3v1, hasid3v2;

    private ID3v1 id3v1;
    private ID3v2 id3v2;


    public Track(Mp3File song) {
        this.song = song;
        this.name = song.getFilename();

        if (song.hasId3v1Tag()) {
            hasid3v1 = true;
            this.id3v1 = song.getId3v1Tag();
        } else hasid3v1 = false;

        if (song.hasId3v2Tag()) {
            hasid3v2 = true;
            this.id3v2 = song.getId3v2Tag();
        } else hasid3v2 = false;
    }

    public String getName() {
        return this.name;
    }
    public Mp3File getSong() {
        return song;
    }

    public String getSongName() {
        return song.getFilename();
    }

    public String getTitle() {
        if (hasid3v1) {
            return this.id3v1.getTitle();
        } else return null;
    }

    public String getArtist() {
        if (hasid3v1) {
            return this.id3v1.getArtist();
        } else return null;    }

    public String getAlbum() {
        if (hasid3v1) {
            return this.id3v1.getAlbum();
        } else return null;    }

    public byte[] getCover() {
        if (hasid3v2) {
            return this.id3v2.getAlbumImage();
        } else return null;    }
    }


