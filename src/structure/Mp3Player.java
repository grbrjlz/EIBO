package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Mp3Player {

    private SimpleMinim minim = new SimpleMinim(true);
    private SimpleAudioPlayer player;

    private IntegerProperty aktSong = new SimpleIntegerProperty();
    private int playlistSize;
    boolean shuffle, repeat;

    private Playlist aktPlaylist;

    public Mp3Player(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktSong.setValue(0);
        this.playlistSize = aktPlaylist.getSize();
        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSong.get()));
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
        int s = aktSong.get();

        if (!repeat){
            if (!shuffle) {
                if (s == (playlistSize-1)) s = 0;
                else s++;
            } else {
                s = (int)(Math.random()*playlistSize);
            }
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSong.setValue(s);
        player.play();
    }

    public void back() {
        player.pause();
        int s = aktSong.get();

        if (!repeat){
            if (s == 0) s = (playlistSize-1);
            else s--;
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSong.setValue(s);
        player.play();

    }

    public void shuffle(){
        if (shuffle) shuffle = false;
        else shuffle = true;
    }

    public void repeat(){
        if (repeat) repeat = false;
        else repeat = true;
    }

    public final int getAktSong(){
        return aktSong.get();
    }

    public String getAktName() {
        return aktPlaylist.getSong(aktSong.get()).getName();
    }

    public String getAktTitle() {
        return aktPlaylist.getSong(aktSong.get()).getTitle();
    }

    public String getAktArtist() {
        return aktPlaylist.getSong(aktSong.get()).getArtist();

    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSong.get());
    }

    public void setAktSong(int song){
        this.aktSong.setValue(song);
    }

    public String getAktPlaylistName(){
        return this.aktPlaylist.getName();
    }

    public byte[] getCover() {
        return aktPlaylist.getSong(aktSong.get()).getCover();

    }

    public void setVolume(float value) {
        player.setGain(value);
    }

    public float getVolume(){
        return player.getGain();
    }


}


