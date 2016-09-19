package cs3500.music.model;

/**
 * The 12 core pitches.
 */
public enum Pitch {
  C("C", 1), CSharp("C#", 2), D("D", 3), DSharp("D#", 4), E("E", 5),
  F("F", 6), FSharp("F#", 7), G("G", 8), GSharp("G#", 9), A("A", 10),
  ASharp("A#", 11), B("B", 12);

  private String pitchString;
  private int pitchVal;

  /**
   * Constructor.
   *
   * @param pitchString the string
   * @param pitchVal    the value
   */
  Pitch(String pitchString, int pitchVal) {
    this.pitchString = pitchString;
    this.pitchVal = pitchVal;
  }

  /**
   * Get the value for the pitch
   *
   * @return the value for this pitch
   */
  public int getPitchVal() {
    return this.pitchVal;
  }

  @Override
  public String toString() {
    return this.pitchString;
  }

}
