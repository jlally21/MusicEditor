package cs3500.music.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sound.midi.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl extends AMusicView {

  private List<MidiMessage> midiMessages;
  private MidiDevice midiDevice;
  private List<MidiEvent> midiEvents;

  private MidiViewImpl() {
    this.midiMessages = new ArrayList<MidiMessage>();
    this.midiEvents = new ArrayList<MidiEvent>();
    try {
      this.midiDevice = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      System.err.println("Could not obtain sequencer");
    }

  }

  public MidiDevice getMidiDevice() {
    return this.midiDevice;
  }

  @Override
  public void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {

    //convert the notes to midi messages
    try {
      this.midiMessages = this.convertToMidiMessages(notes);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }


    Sequence sequence = null;
    try {

      //resolution is ticks per beat: 1/tempo in seconds per beat
      double resolution = 1 / ((double) tempo / 1000000);
      sequence = new Sequence(Sequence.PPQ, (int) resolution, 1);
      Track track = sequence.getTracks()[0];

      //for each pair of notes, turn them into midi events
      for (int i = 0; i < this.midiMessages.size() - 1; i++) {
        //note
        ANote currentNote = notes.get(i / 2);
        int startBeat = currentNote.getStartBeat();
        int duration = currentNote.getBeatLength();

        //onMessage
        MidiMessage on = this.midiMessages.get(i);
        MidiMessage off = this.midiMessages.get(i + 1);

        MidiEvent onEvent;
        MidiEvent offEvent;

//        System.out.println("start: " + startBeat);
        onEvent = new MidiEvent(on, sequence.getResolution() * startBeat);
        offEvent = new MidiEvent(off, sequence.getResolution() * startBeat + sequence
                .getResolution() * duration);

//        System.out.println("ontick: " + onEvent.getTick());
//        System.out.println("offtick: " + offEvent.getTick());

        track.add(onEvent);
        track.add(offEvent);
        midiEvents.add(onEvent);
        midiEvents.add(offEvent);
        i++;
      }

    } catch (Exception e) {
      System.err.println("Could not play.");
    }

    if (midiDevice instanceof Sequencer) {
      try {
        midiDevice.open();
        ((Sequencer) midiDevice).setSequence(sequence);
        ((Sequencer) midiDevice).setTempoInMPQ(tempo);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      //invalid. must be a sequencer.
      throw new IllegalArgumentException("Midi device is not a Sequencer.");
    }
  }

  /**
   * Converts ANotes to MidiMessages.
   *
   * @param notes the ANotes
   * @return the list of MidiMessages
   */
  private List<MidiMessage> convertToMidiMessages(List<ANote> notes)
          throws InvalidMidiDataException {
    List<MidiMessage> messages = new ArrayList<MidiMessage>();

    for (ANote note : notes) {
      MidiMessage messageOn = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument
              () - 1
              , this.convertToIntegerPitch(note.getTone()), 64);
      MidiMessage messageOff = new ShortMessage(ShortMessage.NOTE_OFF, note
              .getInstrument() - 1
              , this.convertToIntegerPitch(note.getTone()), 64);
      messages.add(messageOn);
      messages.add(messageOff);

    }
    return messages;
  }

  /**
   * Converts Tones to integer pitches
   *
   * @param tone the Tone to convert
   */
  private int convertToIntegerPitch(Tone tone) {
    int octave = tone.getOctave();
    int pitchValue = tone.getPitch().ordinal();

    return octave * 12 + pitchValue;
  }

  /**
   * Constructs a builder for configuring the view. Default midi device is a sequencer.
   *
   * @return the new builder.
   */
  public static MidiViewBuilder builder() {
    return new MidiViewBuilder();
  }


  /**
   * A midi view builder
   */
  public static final class MidiViewBuilder {
    private static MidiViewImpl midiView = new MidiViewImpl();

    /**
     * Construct a Midi View Builder.
     */
    private MidiViewBuilder() {
      this.midiView = new MidiViewImpl();
    }

    /**
     * Build with a midi device
     *
     * @param device the midi device
     * @return the builder
     */
    public MidiViewBuilder midiDevice(MidiDevice device) {
      midiView.midiDevice = device;
      return this;
    }


    /**
     * Build the midiview
     *
     * @return the midiview
     */
    public MidiViewImpl build() {
      return midiView;
    }
  }
}
