package cs3500.music.model;

import java.util.List;
import java.util.Map;

/**
 * A model for a music editor.
 */
public interface MusicModel {

  /**
   * Add a note to this song.
   *
   * @param note the note to add
   * @throws IllegalArgumentException if the note is null
   */
  void addNote(ANote note) throws IllegalArgumentException;

  /**
   * Remove a note from this song, if it exists.
   *
   * @param note the note to remove.
   */
  void removeNote(ANote note);

  /**
   * Returns the notes in the song.
   *
   * @return a list of the notes in the song.
   */
  List<ANote> getAllNotes();

  /**
   * Sets the tempo for the composition
   *
   * @param tempo the tempo
   */
  void setTempo(int tempo);

  /**
   * Get the tempo
   *
   * @return the tempo
   */
  int getTempo();

  /**
   * Get the map of tones to notes
   *
   * @return the map
   */
  Map<Tone, List<ANote>> getToneMap();
}
