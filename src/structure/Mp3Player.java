package structure;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;
import java.util.Arrays;

public class Mp3Player {

    private MediaPlayer player;
    private Playlist aktPlaylist;
    private Track aktSong;

    private final double DROPDOWN = 0;
    private static final double INTERVAL = 0;


    private final IntegerProperty aktSongIndex = new SimpleIntegerProperty();
    private final IntegerProperty playlistSize = new SimpleIntegerProperty();
    private final IntegerProperty aktSongLength = new SimpleIntegerProperty();
    private final IntegerProperty position = new SimpleIntegerProperty();
    private final BooleanProperty shuffle = new SimpleBooleanProperty();
    private final BooleanProperty repeat = new SimpleBooleanProperty();
    private final BooleanProperty playing = new SimpleBooleanProperty();
    private final StringProperty aktSongName = new SimpleStringProperty();
    private final StringProperty aktPlaylistName = new SimpleStringProperty();

    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final AreaChart<String, Number> spektrum = new AreaChart<String, Number>(xAxis,yAxis);
    private int bands;
    private XYChart.Data[] series1Data;

    public Mp3Player(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());

        loadSong(0);

        this.playing.setValue(false);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1),
                ae -> refreshPos()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void loadSong(int index){
        spektrum.getData().clear();
        this.aktSong = aktPlaylist.getSong(index);
        this.aktSongIndex.setValue(index);
        this.aktSongName.setValue(aktSong.getFilename());
        this.playlistSize.setValue(aktPlaylist.getSize());
        this.player = new MediaPlayer(new Media(new File(aktSong.getFilename()).toURI().toString()));
        this.bands = player.getAudioSpectrumNumBands();
        this.aktSongLength.setValue(player.getTotalDuration().toMillis());
        player.setAudioSpectrumListener(new SpektrumListener());
        //player.setAudioSpectrumInterval(INTERVAL);
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1Data = new XYChart.Data[bands + 2];
        for (int i = 0; i < series1Data.length; i++) {
            series1Data[i] = new XYChart.Data<>(Integer.toString(i + 1), 0);
            series1.getData().add(series1Data[i]);
        }
        spektrum.getData().add(series1);
    }

    private class SpektrumListener implements AudioSpectrumListener {
        float[] buffer = createFilledBuffer(bands, player.getAudioSpectrumThreshold());

        @Override
        public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
            Platform.runLater(() -> {
                series1Data[0].setYValue(0);
                series1Data[bands + 1].setYValue(0);
                for (int i = 0; i < magnitudes.length; i++) {
                    if (magnitudes[i] >= buffer[i]) {
                        buffer[i] = magnitudes[i];
                        series1Data[i + 1].setYValue(magnitudes[i] - player.getAudioSpectrumThreshold());
                    } else {
                        series1Data[i + 1].setYValue(buffer[i] - player.getAudioSpectrumThreshold());
                        buffer[i] -= DROPDOWN;
                    }
                }
            });
        }
    }

    private float[] createFilledBuffer(int size, float fillValue) {
        float[] floats = new float[size];
        Arrays.fill(floats, fillValue);
        return floats;
    }

    public AreaChart<String, Number> getSpektrum(){
        return this.spektrum;
    }

    public void play(){
        player.play();
        playing.setValue(true);
    }

    public void play(int ms){
        player.setStartTime(new Duration(ms));
        player.play();
        playing.setValue(true);
    }

    public void playTrack(int index){
        player.pause();
        playing.setValue(false);

        loadSong(index);

        player.play();
        playing.setValue(true);
    }

    public void pause() {
        player.pause();
        playing.setValue(false);
    }

    public void stop() {
        playing.setValue(false);
        player.stop();
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

        loadSong(s);

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

        loadSong(s);

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
        position.setValue(player.getCurrentTime().toMillis());
    }

    public int getAktSongLength() {
        return aktSongLength.get();
    }

    public Track getAktTrack(){
        return aktPlaylist.getSong(aktSongIndex.get());
    }

    public MediaPlayer getAudioPlayer(){
        return this.player;
    }

    public IntegerProperty aktSongLengthProperty() {
        return aktSongLength;
    }

    public IntegerProperty aktSongIndexProperty() {
        return aktSongIndex;
    }

    public BooleanProperty shuffleProperty(){
        return shuffle;
    }

    public BooleanProperty repeatProperty(){
        return repeat;
    }

    public IntegerProperty positionProperty(){
        position.setValue(player.getCurrentTime().toMillis());
        return position;
    }

    public StringProperty aktSongNameProperty(){
        return aktSongName;
    }

    public void setAktPlaylist(Playlist aktPlaylist){
        this.aktPlaylist = aktPlaylist;
        this.aktPlaylistName.setValue(aktPlaylist.getName());
        this.playlistSize.setValue(aktPlaylist.getSize());

        loadSong(0);

        this.playing.setValue(false);
    }

    public void setVolume(float value) {
        player.setVolume(value);
    }

}


