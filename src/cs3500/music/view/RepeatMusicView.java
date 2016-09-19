package cs3500.music.view;

import cs3500.music.model.Repeat;

/**
 * A Music View that supports Repeats
 */
public interface RepeatMusicView extends MusicView{

  /**
   * Create a repeat.
   * @return the repeat, or null
   */
  Repeat createRepeat();
}
