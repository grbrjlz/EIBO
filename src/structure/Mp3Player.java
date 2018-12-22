package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mp3Player {

    private SimpleMinim minim = new SimpleMinim(true);
    private SimpleAudioPlayer player;

    private IntegerProperty aktSong = new SimpleIntegerProperty();
    private IntegerProperty playlistSize = new SimpleIntegerProperty();

    private IntegerProperty position = new SimpleIntegerProperty();

    boolean shuffle, repeat;

    private Playlist aktPlaylist;
    private StringProperty aktSongName = new SimpleStringProperty();
    private StringProperty aktPlaylistName = new SimpleStringProperty();

    public Mp3Player(Playlist aktPlaylist){

        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSong.setValue(0);
        this.aktSongName.setValue(aktPlaylist.getSongName(aktSong.get()));
        this.playlistSize.setValue(aktPlaylist.getSize());

        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSong.get()));
        this.position.setValue(player.position());

    }

    public void setAktPlaylist(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSong.setValue(0);
        this.aktSongName.setValue(aktPlaylist.getSongName(aktSong.get()));
        this.playlistSize.setValue(aktPlaylist.getSize());

        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSong.get()));
        this.position.setValue(player.position());
    }

    //PLAYERCONTROL-METHODEN
    public void play(){
        player.play();
    }

    public void play(int index){
        player.pause();
        player = minim.loadMP3File(aktPlaylist.getSongName(index));
        aktSong.setValue(index);
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
        int size = playlistSize.get();

        if (!repeat){
            if (!shuffle) {
                if (s == (size-1)) s = 0;
                else s++;
            } else {
                s = (int)(Math.random()*size);
            }
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSong.setValue(s);
        aktSongName.setValue(aktPlaylist.getSongName(aktSong.get()));
        player.play();
    }

    public void back() {
        player.pause();
        int s = aktSong.get();
        int size = playlistSize.get();

        if (!repeat){
            if (s == 0) s = (size-1);
            else s--;
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSong.setValue(s);
        aktSongName.setValue(aktPlaylist.getSongName(aktSong.get()));
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


    //Property: int Position (bereits gespielte Zeit in ms)
    public int getPosition(){
        position.setValue(player.position());
        return position.get();
    }

    public void setPosition(int value){
        position.setValue(value);
    }
    public IntegerProperty positionProperty(){
        position.setValue(player.position());
        return position;
    }

    //Property: int aktSong
    public int getAktSong(){
        return aktSong.get();
    }
    public void setAktSong(int value){
        aktSong.setValue(value);
    }
    public IntegerProperty aktSongProperty(){
        return aktSong;
    }


    //Property: int playlistSize
    public int getPlaylistSize(){
        return playlistSize.get();
    }
    public void setPlaylistSize(int value){
        playlistSize.setValue(value);
    }
    public IntegerProperty playlistSizeProperty(){
        return playlistSize;
    }

    //Property: String aktSongName
    public String getAktSongName(){
        return aktSongName.get();
    }
    public void setAktSongName(String value){
        aktSongName.setValue(value);
    }
    public StringProperty aktSongNameProperty(){
        return aktSongName;
    }

    //Property: String aktPlaylistName
    public String getAktPlaylistName(){
        return aktPlaylistName.get();
    }
    public void setAktPlaylistName(String value){
        aktPlaylistName.setValue(value);
    }
    public StringProperty aktPlaylistNameProperty(){
        return aktPlaylistName;
    }

    public Playlist getAktPlaylist(){
        return this.aktPlaylist;
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


