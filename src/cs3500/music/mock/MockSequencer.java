package cs3500.music.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

/**
 * A mock sequencer. Construct with a mock receiver to test output.
 */
public class MockSequencer implements Sequencer {
  private Sequence sequence;
  private Receiver receiver;

  /**
   * Construct a mock sequencer with a mock receiver
   *
   * @param r the receiver
   * @throws IllegalArgumentException if the receiver is not a mock.
   */
  public MockSequencer(Receiver r) throws IllegalArgumentException {
    if (r instanceof MockReceiver) {
      this.receiver = r;
    } else {
      throw new IllegalArgumentException("Must specify Mock Receiver.");
    }
  }

  @Override
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    this.sequence = sequence;
  }

  @Override
  public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public Sequence getSequence() {
    return this.sequence;
  }

  @Override
  public void start() {
    //send the events to the receiver.
    Track track = this.sequence.getTracks()[0];

    for (int i = 0; i < track.size() - 1; i++) {
      MidiMessage m1 = track.get(i).getMessage();
      MidiMessage m2 = track.get(i + 1).getMessage();
      long mTick1 = track.get(i).getTick();
      long mTick2 = track.get(i + 1).getTick();

      //send to receiver
      receiver.send(m1, mTick1);
      receiver.send(m2, mTick2);
      i++;
    }
  }

  @Override
  public void stop() {
    //do nothing, but no exception thrown
  }

  @Override
  public boolean isRunning() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void startRecording() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void stopRecording() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public boolean isRecording() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void recordEnable(Track track, int channel) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void recordDisable(Track track) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public float getTempoInBPM() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setTempoInBPM(float bpm) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public float getTempoInMPQ() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setTempoInMPQ(float mpq) {
    //do nothing, but no exception thrown.
  }

  @Override
  public void setTempoFactor(float factor) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public float getTempoFactor() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getTickLength() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getTickPosition() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setTickPosition(long tick) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getMicrosecondLength() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public Info getDeviceInfo() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void open() throws MidiUnavailableException {
    //do nothing, but no exception thrown.
  }

  @Override
  public void close() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public boolean isOpen() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getMicrosecondPosition() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public int getMaxReceivers() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public int getMaxTransmitters() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return this.receiver;
  }

  @Override
  public List<Receiver> getReceivers() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public List<Transmitter> getTransmitters() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setMicrosecondPosition(long microseconds) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setMasterSyncMode(SyncMode sync) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public SyncMode getMasterSyncMode() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setSlaveSyncMode(SyncMode sync) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setTrackMute(int track, boolean mute) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public boolean getTrackMute(int track) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setTrackSolo(int track, boolean solo) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public boolean getTrackSolo(int track) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener listener) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void removeMetaEventListener(MetaEventListener listener) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setLoopStartPoint(long tick) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getLoopStartPoint() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setLoopEndPoint(long tick) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public long getLoopEndPoint() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public void setLoopCount(int count) {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }

  @Override
  public int getLoopCount() {
    throw new IllegalArgumentException("Unsupported by mock sequencer.");
  }
}
