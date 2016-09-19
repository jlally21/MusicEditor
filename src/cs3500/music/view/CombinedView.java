package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.*;

import cs3500.music.controller.MusicController;
import cs3500.music.model.ANote;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

import static java.lang.Thread.sleep;

/**
 * A combined GUI and MIDI view.
 */
public class CombinedView extends AMusicView implements MusicEditorView {

  //has a gui view and a midi view.
  protected GuiViewFrame guiView;
  protected MidiViewImpl midiView;

  //the sequencer for the midi view.
  protected Sequencer sequencer;

  //button to add note
  protected JButton addNoteButton;

  //the tempo of the sequencer.
  protected float seqTempo;

  /**
   * Set the text
   *
   * @param text the text
   */
  @Override
  public void setToneFieldText(String text) {
    this.toneField.setText(text);
  }

  /**
   * Set the text
   *
   * @param text the text
   */
  @Override
  public void setStartBeatFieldText(String text) {
    this.startBeatField.setText(text);
  }

  //dialog text views
  private JTextField toneField = new JTextField();
  private JTextField startBeatField = new JTextField();
  private JTextField durationField = new JTextField();

  /**
   * Default constructor.
   */
  public CombinedView() {
    this.guiView = new GuiViewFrame();
  }

  @Override
  public void scrollToBeginning() {
    sequencer.setMicrosecondPosition(0);
    guiView.setVisibleBeat(0);
  }

  @Override
  public void scrollToEnd() {
    sequencer.setMicrosecondPosition(sequencer.getLoopEndPoint());

    //the beat is the tick divided by the resolution.
    guiView.setVisibleBeat((int) sequencer.getLoopEndPoint() /
            sequencer.getSequence().getResolution());
  }

  @Override
  public void togglePlay() {
    sequencer.setTempoInMPQ(seqTempo);
    if (sequencer.isRunning()) {
      sequencer.stop();
      guiView.repaint();
      sequencer.setTempoInMPQ(seqTempo);
    } else {
      sequencer.start();
      guiView.repaint();
      sequencer.setTempoInMPQ(seqTempo);
    }
  }

  @Override
  public void moveRight() {
    sequencer.setTickPosition(sequencer.getTickPosition() +
            sequencer.getSequence().getResolution());

    guiView.setVisibleBeat((int) (sequencer.getTickPosition() /
            sequencer.getSequence().getResolution()));
  }

  @Override
  public void moveLeft() {
    sequencer.setTickPosition(sequencer.getTickPosition() -
            sequencer.getSequence()
            .getResolution());

    guiView.setVisibleBeat((int) (sequencer.getTickPosition() /
            sequencer.getSequence().getResolution()));

    System.out.println(sequencer.getTickPosition());
  }

  @Override
  public boolean showDeleteDialog() {
    JPanel panel = new JPanel(new GridLayout(0, 1));
    int result = JOptionPane.showConfirmDialog(null, panel, "Delete this note?",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      return true;


    } else {
      return false;
    }
  }

  @Override
  public ANote getSelectedNote() {
    return guiView.getSelectedNote();
  }

  @Override
  public void resetFocus() {
    guiView.getContentPane().setFocusable(true);
    guiView.getContentPane().requestFocus();

    guiView.gridPanel.setFocusable(true);
    guiView.gridPanel.requestFocus();
  }


  @Override
  public void addActionListener(ActionListener listener) {
    this.addNoteButton.addActionListener(listener);
  }

  @Override
  public ANote showAddNotePopup() {
    sequencer.stop();
    ANote newNote = null;

    //init dialog
    JPanel panel = new JPanel(new GridLayout(0, 1));
    panel.add(new JLabel("Enter Tone (ex. C#2):"));
    panel.add(toneField);
    panel.add(new JLabel("Enter integer starting beat:"));
    panel.add(startBeatField);
    panel.add(new JLabel("Enter note duration:"));
    panel.add(durationField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Create a note",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      //validate note
      String tone = toneField.getText();
      String startBeatString = startBeatField.getText();
      String durationString = durationField.getText();

      try {
        int startBeat = Integer.parseInt(startBeatString);
        int duration = Integer.parseInt(durationString);

        String pitch = "";
        int octave = 0;

        //look for integer, split there
        for (int i = 0; i < tone.length(); i++) {
          if (Character.isDigit(tone.charAt(i))) {
            //split, break out of the loop
            pitch = tone.substring(0, i);
            octave = Integer.parseInt(tone.substring(i, tone.length()));
          }
        }

        if (!pitch.equals("")) {
          //find which pitch it is
          Pitch[] pitches = Pitch.values();
          ArrayList<String> pitchNames = new ArrayList<>();
          for (Pitch p : pitches) {
            pitchNames.add(p.toString());
          }

          if (pitchNames.contains(pitch)) {
            //valid pitch.
            int indexOfPitch = pitchNames.indexOf(pitch);

            Pitch newPitch = pitches[indexOfPitch];

            //make the note
            newNote = new Note(new Tone(newPitch, octave), duration, startBeat, 1);
          }
        }
      } catch (Exception e) {
        System.err.println("Failed note creation.");
      }
    } else {
      //user cancelled the new note
    }

    toneField.setText("");
    durationField.setText("");
    startBeatField.setText("");

    return newNote;
  }

  @Override
  public void updateUI(List<ANote> allNotes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {
    this.guiView.initView(allNotes, toneMap, tempo, repeats);
    this.midiView.initView(allNotes, toneMap, tempo, repeats);
    guiView.repaint();
  }

  @Override
  public JFrame getGui() {
    return this.guiView;
  }

  @Override
  public ANote getTempNote() {
    return guiView.getTempSelectedNote();
  }

  @Override
  public void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {


    try {
      this.sequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.midiView = MidiViewImpl.builder().midiDevice(sequencer).build();
    this.midiView.initView(notes, toneMap, tempo, repeats);


    this.addNoteButton = new JButton();
    addNoteButton.setText("Add a note");
    addNoteButton.setActionCommand("Add note");
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(addNoteButton);
    guiView.setJMenuBar(menuBar);
    guiView.setSequencer(sequencer);
    this.guiView.initView(notes, toneMap, tempo, repeats);
    seqTempo = sequencer.getTempoInMPQ();
  }
}