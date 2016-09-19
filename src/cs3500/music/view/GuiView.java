package cs3500.music.view;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.Sequencer;
import javax.swing.*;

/**
 * A GUI view interface.
 */
public interface GuiView extends MusicView {

  /**
   * Sets the sequencer for the view.
   * @param sequencer the sequencer to set.
   */
  void setSequencer(Sequencer sequencer);

}
