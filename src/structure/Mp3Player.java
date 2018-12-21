package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class Mp3Player {

    SimpleMinim minim = new SimpleMinim(true);
    SimpleAudioPlayer player;
    //Mp3File aktSong;

    int aktSong, playlistSize;
    Playlist aktPlaylist;

    public Mp3Player(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktSong = 0;
        this.playlistSize = aktPlaylist.getSize();
        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));

    }

    public void setAktSong(int song){
        this.aktSong = song;
    }

    //PLAYERCONTROL-METHODEN
    public void play(){
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public void stop() {
        player.pause();
        player.rewind();
    }

    public void skip() {
        player.pause();

        if (aktSong == (playlistSize-1)) aktSong = 0;
        else aktSong++;

        player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));
        player.play();
    }

    public void back() {
        player.pause();

        if (aktSong == 0) aktSong = (playlistSize-1);
        else aktSong--;

        player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));
        player.play();

    }

    public String getAktName() {
        return aktPlaylist.getSong(aktSong).getName();
    }

    public String getAktTitle() {
        return aktPlaylist.getSong(aktSong).getTitle();
    }

    public String getAktArtist() {
        return aktPlaylist.getSong(aktSong).getArtist();

    }

    /*public void info() {
        System.out.println("Dateiname: " + aktSong.getFilename());
            System.out.println("Länge: " + aktSong.getLengthInSeconds() + " Sekunden");
            System.out.println("Bitrate: " + aktSong.getBitrate() + " kbps");
            if (aktSong.hasId3v1Tag()) {
                ID3v1 id3v1Tag = aktSong.getId3v1Tag();
                System.out.println("Track: " + id3v1Tag.getTrack());
                System.out.println("Artist: " + id3v1Tag.getArtist());
                System.out.println("Title: " + id3v1Tag.getTitle());
                System.out.println("Album: " + id3v1Tag.getAlbum());
                System.out.println("Year: " + id3v1Tag.getYear());
                System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
                System.out.println("Comment: " + id3v1Tag.getComment());
            } else System.out.println("keine Id3v1 Tags vorhanden!");
            if (aktSong.hasId3v2Tag()) {
                ID3v2 id3v2tag = aktSong.getId3v2Tag();
                System.out.println("BPM "+id3v2tag.getBPM());
                System.out.println("Key "+id3v2tag.getKey());

            }

    }*/

    public byte[] getCover() {
        return aktPlaylist.getSong(aktSong).getCover();

    }

    public void volume(float value) {
        player.setGain(value);
    }

    /*public String getAktSongInfo(){
        return aktSong.getFilename();
    }*/

}


