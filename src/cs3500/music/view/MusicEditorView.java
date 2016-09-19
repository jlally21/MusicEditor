package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * An interface for music editors. Extends music view interface to offer gui-specific
 * functionality.
 */
public interface MusicEditorView extends MusicView {

  /**
   * Set the text of the tone field.
   * @param text the text to set
   */
  void setToneFieldText(String text);

  /**
   * Set the text of the start beat
   * @param text the text to set
   */
  void setStartBeatFieldText(String text);

  /**
   * Scroll to the beginning of the composition.
   * Pauses the song if not paused.
   */
  void scrollToBeginning();

  /**
   * Scroll to the end of the composition.
   * Pauses the song if not paused.
   */
  void scrollToEnd();

  /**
   * Pause or resume play.
   */
  void togglePlay();

  /**
   * Navigate forward through the composition by one beat.
   * Pauses the song if not paused.
   */
  void moveRight();

  /**
   * Navigate backward through the composition by one beat.
   * Pauses the song if not paused.
   */
  void moveLeft();

  /**
   * Displays popup dialog to confirm deletion
   *
   * @return the boolean, true if confirmed
   */
  boolean showDeleteDialog();

  /**
   * Returns the note at that is at the mouse position, or null
   *
   * @return the note at the location, or null
   */
  ANote getSelectedNote();

  /**
   * Reset the focus on the appropriate part of the view.
   */
  void resetFocus();

  /**
   * Add a mouse listener to the view.
   * @param m the mouse listener
   */
  void addMouseListener(MouseListener m);

  /**
   * Add a key listener to the view.
   * @param kbd the key listener
   */
  void addKeyListener(KeyListener kbd);

  /**
   * Add an action listener to this view
   * @param listener the listener
   */
  void addActionListener(ActionListener listener);

  /**
   * Displays a popup view to help the user add a note.
   *
   * @return the note the user creates
   */
  ANote showAddNotePopup();

  /**
   * Update the UI using the notes and tone map.
   *
   * @param allNotes the notes
   * @param toneMap the map of tones to notes played at that tone
   * @param tempo the tempo
   */
  void updateUI(List<ANote> allNotes, Map<Tone, List<ANote>> toneMap, int tempo,
                List<Repeat> repeats);

  /**
   * Gets the gui for the view.
   *
   * @return the gui
   */
  JFrame getGui();

  /**
   * Gets a preset note with tone and start beat already set.
   * @return the temporary note
   */
  ANote getTempNote();
}
