package cs3500.music.view;

import java.util.List;
import java.util.Map;

import cs3500.music.model.ANote;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A view for music editors
 */
public interface MusicView {
  /**
   * Initialize the view.
   *
   * @param notes   the notes to play
   * @param toneMap the map of tones to notes
   * @param tempo   the tempo to play the song at
   */
  void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                List<Repeat> repeats);

  /**
   * Get the notes for a specific tone
   *
   * @param tone the tone to filter by.
   * @return the list of notes
   */
  List<ANote> getNotesForTone(Tone tone);

  /**
   * Sets the given beat to be visible.
   * @param visibleBeat the beat to make visible.
   */
  void setVisibleBeat(int visibleBeat);
}
