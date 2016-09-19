package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Repeat;
import cs3500.music.view.MusicEditorView;

/**
 * A controller for a combined gui-midi player and music editor.
 */
public class MusicEditorController implements MusicController, ActionListener{

  private final MusicModel model;
  private final MusicEditorView view;

  /**
   * Constructs a controller with the given model and view
   *
   * @param model the model
   * @param view the view
   */
  public MusicEditorController(MusicModel model, MusicEditorView view) {
    this.model = model;
    this.view = view;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Add note":
        ANote note = view.showAddNotePopup();
        if(!(note == null)) {
          //add it to the model, update the view
          model.addNote(note);
          view.updateUI(model.getAllNotes(), model.getToneMap(), model.getTempo(), new
                  ArrayList<Repeat>());
        }
        break;
    }
    view.resetFocus();
  }

  /**
   * Create and set a mouse listener for the view
   */
  protected void configureMouseListener() {
    Map<Integer, Runnable> mouseEventTypes = new HashMap<>();
    //a left click event
    mouseEventTypes.put(MouseEvent.MOUSE_CLICKED, () -> {
      //user has selected something

        try {
          view.getSelectedNote();

          ANote selectedNote = view.getSelectedNote();
          if(selectedNote == null) {
            throw new NullPointerException();
          }
        if(view.showDeleteDialog()) {
          //delete the note
          model.removeNote(selectedNote);
          view.updateUI(model.getAllNotes(), model.getToneMap(), model.getTempo(), new
                  ArrayList<Repeat>());
        }
        else {
          //don't delete the note
        }
      }
      catch (NullPointerException e) {
          ANote tempNote = view.getTempNote();
          //ask to add a note
          //preset the tone and start beat
          view.setToneFieldText(tempNote.getTone().toString());
          view.setStartBeatFieldText(Integer.toString(tempNote.getStartBeat()));
          ANote addedNote = view.showAddNotePopup();

          //if user confirmed the addition
          if(addedNote != null) {
            model.addNote(addedNote);
            view.updateUI(model.getAllNotes(), model.getToneMap(), model.getTempo(),
                    new ArrayList<Repeat>());
          }

        }
    });

    MouseEventListener mel = new MouseEventListener();
    mel.setMouseEventMap(mouseEventTypes);
    view.getGui().addMouseListener(mel);
  }

  /**
   * Create and set a keyboard listener for the view.
   */
  protected void configureKeyBoardListener() {
    //map key types to runnables
    Map<Integer, Runnable> keyTypes = new HashMap<>();

    //define the functions to map to the keys

    //home scroll
    keyTypes.put(KeyEvent.VK_HOME, () -> {
      //scroll to beginning
      view.scrollToBeginning();
    });

    //end scroll
    keyTypes.put(KeyEvent.VK_END, () -> {
      view.scrollToEnd();
    });

    //pause
    keyTypes.put(KeyEvent.VK_P, () -> {
      view.togglePlay();
    });

    //move forward
    keyTypes.put(KeyEvent.VK_RIGHT, () -> {
      view.moveRight();
    });

    //move backward
    keyTypes.put(KeyEvent.VK_LEFT, () -> {
      view.moveLeft();
    });

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    view.getGui().addKeyListener(kbd);
  }

  @Override
  public void playMusic() {

    //configure the view's keyboard listener
    configureKeyBoardListener();
    //configure the view's mouse listener
    configureMouseListener();
    view.initView(model.getAllNotes(), model.getToneMap(), model.getTempo(), new
            ArrayList<Repeat>());

    view.addActionListener(this);
    view.getGui().setVisible(true);
  }
}
