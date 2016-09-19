package cs3500.music.util;

import cs3500.music.model.Repeat;

/**
 * A builder with repeats supported
 */
public interface CompositionBuilderRepeats<T> extends CompositionBuilder<T> {

  /**
   * Add the repeat to the model.
   *
   * @param repeat the repeat to add.
   */
  CompositionBuilderRepeats addRepeat(Repeat repeat);
}
