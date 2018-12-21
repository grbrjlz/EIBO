package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class Mp3Player {

    private SimpleMinim minim = new SimpleMinim(true);
    private SimpleAudioPlayer player;

    private int aktSong;
    private int playlistSize;
    boolean shuffle, repeat;

    private Playlist aktPlaylist;

    public Mp3Player(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktSong = 0;
        this.playlistSize = aktPlaylist.getSize();
        this.player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));
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

        if (!repeat){
            if (!shuffle) {
                if (aktSong == (playlistSize-1)) aktSong = 0;
                else aktSong++;
            } else {
                aktSong = (int)(Math.random()*playlistSize);
            }
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));
        player.play();
    }

    public void back() {
        player.pause();

        if (!repeat){
            if (aktSong == 0) aktSong = (playlistSize-1);
            else aktSong--;
        }

        player = minim.loadMP3File(aktPlaylist.getSongName(aktSong));
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

    public String getAktName() {
        return aktPlaylist.getSong(aktSong).getName();
    }

    public String getAktTitle() {
        return aktPlaylist.getSong(aktSong).getTitle();
    }

    public String getAktArtist() {
        return aktPlaylist.getSong(aktSong).getArtist();

    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSong);
    }

    public void setAktSong(int song){
        this.aktSong = song;
    }

    public String getAktPlaylistName(){
        return this.aktPlaylist.getName();
    }

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


