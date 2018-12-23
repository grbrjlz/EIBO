package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.*;
import javafx.scene.image.Image;

public class Mp3Player {

    private SimpleMinim minim = new SimpleMinim(true);
    private SimpleAudioPlayer player;

    private IntegerProperty aktSongIndex = new SimpleIntegerProperty();
    private IntegerProperty playlistSize = new SimpleIntegerProperty();

    private IntegerProperty position = new SimpleIntegerProperty();

    //boolean shuffle, repeat, playing;

    private BooleanProperty shuffle = new SimpleBooleanProperty();
    private BooleanProperty repeat = new SimpleBooleanProperty();
    private BooleanProperty playing = new SimpleBooleanProperty();

    private Playlist aktPlaylist;
    private Track aktSong;

    private StringProperty aktSongName = new SimpleStringProperty();
    private StringProperty aktPlaylistName = new SimpleStringProperty();

    public Mp3Player(Playlist aktPlaylist){

        //Player merkt sich die aktuelle Playlist und den aktuellen Song
        this.aktPlaylist = aktPlaylist;
        this.aktSong = aktPlaylist.getAktSong();

        //Properties des Players für die GUI
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSongIndex.setValue(aktPlaylist.getAktSongNumber());
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());

        //Initialisierung des Players
        this.player = minim.loadMP3File(aktSong.getFilename());
        this.playing.setValue(false);

    }

    public SimpleAudioPlayer getAudioPlayer(){
        return this.player;
    }
    public void setAktPlaylist(Playlist aktPlaylist){

        //Beim Aktualisieren der Playlist werden Player & Properties neu geladen
        this.aktPlaylist = aktPlaylist;
        this.aktSong = aktPlaylist.getAktSong();
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.aktSongIndex.setValue(aktPlaylist.getAktSongNumber());
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());
        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSongIndex.get()));
        this.playing.setValue(false);
    }

    //PLAYERCONTROL-METHODEN
    public void play(){
        player.play();
        playing.setValue(true);

    }

    public void play(int index){
        player.pause();
        playing.setValue(false);

        aktSong = aktPlaylist.getSong(index);
        aktSongIndex.setValue(index);
        player = minim.loadMP3File(aktSong.getFilename());
        aktSongName.setValue(aktSong.getFilename());

        player.play();
        playing.setValue(true);
    }

    public void pause() {
        player.pause();
        playing.setValue(false);

    }

    public void stop() {
        player.pause();
        player.rewind();
        playing.setValue(false);

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



    //Property: boolean playing
    public boolean isPlaying(){
        return playing.get();
    }
    public void setPlaying(boolean value){
        playing.setValue(value);
    }
    public BooleanProperty playingProperty(){
        return playing;
    }


    //Property: boolean shuffle
    public boolean isShuffle(){
        return shuffle.get();
    }
    public void setShuffle(boolean value){
        shuffle.setValue(value);
    }
    public BooleanProperty shuffleProperty(){
        return shuffle;
    }


    //Property: boolean repeat
    public boolean isRepeat(){
        return repeat.get();
    }
    public void setRepeat(boolean value){
        repeat.setValue(value);
    }
    public BooleanProperty repeatProperty(){
        return repeat;
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

    //Property: int aktSongIndex
    public int getAktSongIndex(){
        return aktSongIndex.get();
    }
    public void setAktSongIndex(int value){
        aktSongIndex.setValue(value);
    }
    public IntegerProperty aktSongIndexProperty(){
        return aktSongIndex;
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
        return aktPlaylist.getSong(aktSongIndex.get()).getFilename();
    }

    public String getAktTitle() {
        return aktPlaylist.getSong(aktSongIndex.get()).getTitle();
    }

    public String getAktArtist() {
        return aktPlaylist.getSong(aktSongIndex.get()).getArtist();
    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSongIndex.get());
    }

    public Image getCover() {
        return aktPlaylist.getSong(aktSongIndex.get()).getCover();

    }

    public void setVolume(float value) {
        player.setGain(value);
    }

    public float getVolume(){
        return player.getGain();
    }


}


