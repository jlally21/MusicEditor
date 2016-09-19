package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import cs3500.music.model.Ending;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelRepeats;
import cs3500.music.model.Repeat;
import cs3500.music.view.MusicEditorView;
import cs3500.music.view.RepeatView;

/**
 * A music controller which supports repeats.
 */
public class MusicEditorRepeatController extends MusicEditorController {
  private MusicModelRepeats model;
  private RepeatView view;
  private Sequencer sequencer;

  /**
   * Constructs a controller with the given model and view
   *
   * @param model the model
   * @param view  the view
   */
  public MusicEditorRepeatController(MusicModelRepeats model, RepeatView view) {
    super(model, view);

    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Add repeat")) {
      //create repeat
      Repeat repeat = this.view.createRepeat();
      model.addRepeat(repeat);
      view.updateUI(model.getAllNotes(), model.getToneMap(), model.getTempo(), model.getRepeats());
    } else {
      super.actionPerformed(e);
    }
  }

  @Override
  public void playMusic() {

    //configure the view's keyboard listener
    configureKeyBoardListener();
    //configure the view's mouse listener
    configureMouseListener();
    view.initView(model.getAllNotes(), model.getToneMap(), model.getTempo(), model
            .getRepeats());

    view.addActionListener(this);
    view.getGui().setVisible(true);
//    view.getGui().addKeyListener(this);

    MidiDevice midiDevice = view.getMidiView().getMidiDevice();

   this.sequencer = (Sequencer)midiDevice;
  }

}
