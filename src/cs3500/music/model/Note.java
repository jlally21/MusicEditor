package cs3500.music.model;

/**
 * A concrete note class.
 */
public class Note extends ANote {

  /**
   * Constructor
   *
   * @param tone       the tone
   * @param beatLength the length
   * @param startBeat  the start beat
   * @param instrument the instrument
   */
  public Note(Tone tone, int beatLength, int startBeat, int instrument) {
    super(tone, beatLength, startBeat, instrument);
  }
}
