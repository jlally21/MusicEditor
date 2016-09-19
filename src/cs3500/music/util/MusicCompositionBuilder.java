package cs3500.music.util;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Tone;

/**
 * A builder for music compositions.
 */
public class MusicCompositionBuilder implements CompositionBuilder<MusicModel> {
  private MusicModel model;

  /**
   * Constructor
   *
   * @param model the model
   */
  public MusicCompositionBuilder(MusicModel model) {
    this.model = model;
  }

  @Override
  public MusicModel build() {
    return this.model;
  }

  @Override
  public CompositionBuilder<MusicModel> setTempo(int tempo) {
    this.model.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<MusicModel> addNote(int start, int end, int instrument,
                                                int pitch, int volume) {
    int duration = end - start;
    ANote toAdd = new Note(this.convertToTone(pitch), duration, start, instrument);
    model.addNote(toAdd);
    return this;
  }

  /**
   * Converts integer pitches to Tones
   *
   * @param pitch the integer pitch
   * @return Tone the tone
   */
  protected Tone convertToTone(int pitch) {
    int octave = pitch / 12 - 1;
    int pitchValue = pitch % 12;

    Pitch[] pitchValues = Pitch.values();
    Pitch pitch1 = pitchValues[pitchValue];


    return new Tone(pitch1, octave);

  }
}
