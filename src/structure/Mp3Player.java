package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.util.Duration;


public class Mp3Player {

    private final SimpleMinim minim = new SimpleMinim(true);
    private SimpleAudioPlayer player;
    private Playlist aktPlaylist;
    private Track aktSong;

    private final IntegerProperty aktSongIndex = new SimpleIntegerProperty();
    private final IntegerProperty playlistSize = new SimpleIntegerProperty();
    private final IntegerProperty aktSongLength = new SimpleIntegerProperty();
    private final IntegerProperty position = new SimpleIntegerProperty();
    private final BooleanProperty shuffle = new SimpleBooleanProperty();
    private final BooleanProperty repeat = new SimpleBooleanProperty();
    private final BooleanProperty playing = new SimpleBooleanProperty();
    private final StringProperty aktSongName = new SimpleStringProperty();
    private final StringProperty aktPlaylistName = new SimpleStringProperty();

    public Mp3Player(Playlist aktPlaylist){

        //Player merkt sich die aktuelle Playlist und den aktuellen Song
        this.aktPlaylist = aktPlaylist;
        this.aktSong = aktPlaylist.getSong(0);

        //Properties des Players für die GUI
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSongIndex.setValue(0);
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());

        //Initialisierung des Players
        this.player = minim.loadMP3File(aktSong.getFilename());
        this.aktSongLength.setValue(player.length());
        this.playing.setValue(false);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> refreshPos()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void play(){
        player.play();
        playing.setValue(true);
    }

    public void play(int ms){
        player.play(ms);
        playing.setValue(true);
    }

    public void playTrack(int index){
        player.pause();
        playing.setValue(false);
        aktSong = aktPlaylist.getSong(index);
        aktSongIndex.setValue(index);
        player = minim.loadMP3File(aktSong.getFilename());
        aktSongName.setValue(aktSong.getFilename());
        aktSongLength.setValue(player.length());
        player.play();
        playing.setValue(true);
    }

    public void pause() {
        player.pause();
        playing.setValue(false);
    }

    public void stop() {
        playing.setValue(false);
        player.pause();
        player.rewind();
    }

    public void skip() {
        player.pause();
        playing.setValue(false);

        int s = aktSongIndex.get();
        int size = playlistSize.get();
        if (!repeat.getValue()){
            if (!shuffle.getValue()) {
                if (s == (size-1)) s = 0;
                else s++;
            } else {
                s = (int)(Math.random()*size);
            }
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSongLength.setValue(player.length());
        aktSongIndex.setValue(s);
        aktSong = aktPlaylist.getSong(aktSongIndex.get());
        aktSongName.setValue(aktSong.getFilename());
        player.play();
        playing.setValue(true);

    }

    public void back() {
        player.pause();
        playing.setValue(false);

        int s = aktSongIndex.get();
        int size = playlistSize.get();
        if (!repeat.getValue()){
            if (s == 0) s = (size-1);
            else s--;
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(s));
        aktSongIndex.setValue(s);
        aktSong = aktPlaylist.getSong(aktSongIndex.get());
        aktSongName.setValue(aktSong.getFilename());
        aktSongLength.setValue(player.length());
        player.play();
        playing.setValue(true);
    }

    public void shuffle(){
        if (shuffle.getValue()) shuffle.setValue(false);
        else shuffle.setValue(true);
    }

    public void repeat(){
        if (repeat.getValue()) repeat.setValue(false);
        else repeat.setValue(true);
    }

    private void refreshPos(){
        position.setValue(player.position());
    }

    public int getAktSongLength() {
        return aktSongLength.get();
    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSongIndex.get());
    }

    public SimpleAudioPlayer getAudioPlayer(){
        return this.player;
    }

    public IntegerProperty aktSongLengthProperty() {
        return aktSongLength;
    }

    public BooleanProperty shuffleProperty(){
        return shuffle;
    }

    public BooleanProperty repeatProperty(){
        return repeat;
    }

    public IntegerProperty positionProperty(){
        position.setValue(player.position());
        return position;
    }

    public StringProperty aktSongNameProperty(){
        return aktSongName;
    }

    public void setAktPlaylist(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktSong = aktPlaylist.getSong(0);
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSongIndex.setValue(0);
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());
        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSongIndex.get()));
        this.aktSongLength.setValue(player.length());
        this.playing.setValue(false);
    }

    public void setVolume(float value) {
        player.setGain(value);
    }

}


