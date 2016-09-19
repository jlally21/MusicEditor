package cs3500.music.util;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelRepeats;
import cs3500.music.model.Note;
import cs3500.music.model.Repeat;

/**
 * Created by mwhite on 6/26/16.
 */
public class MusicCompositionRepeatBuilder implements CompositionBuilderRepeats<MusicModelRepeats> {
  private MusicCompositionBuilder musicCompositionBuilder;
  private MusicModelRepeats model;

  /**
   * Constructor
   *
   * @param model the model
   */
  public MusicCompositionRepeatBuilder(MusicModelRepeats model) {
    this.model = model;
    this.musicCompositionBuilder = new MusicCompositionBuilder(this.model);
  }

  @Override
  public MusicModelRepeats build() {
    return this.model;
  }

  @Override
  public CompositionBuilderRepeats<MusicModelRepeats> setTempo(int tempo) {
    this.model.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilderRepeats<MusicModelRepeats> addNote(int start, int end, int
          instrument, int pitch, int volume) {
    int duration = end - start;
    ANote toAdd = new Note(musicCompositionBuilder.convertToTone(pitch), duration, start, instrument);
    model.addNote(toAdd);
    return this;
  }

  @Override
  public CompositionBuilderRepeats addRepeat(Repeat repeat) {
     model.addRepeat(repeat);
    return this;
  }
}
