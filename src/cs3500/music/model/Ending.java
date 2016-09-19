package cs3500.music.model;

/**
 * An ending as part of a Repeat.
 *
 * An ending is a start beat, end beat and is on or off.
 */
public class Ending {
  private int startBeat;
  private int endBeat;
  private boolean isOn;

  /**
   * Create an Ending with the data.
   *
   * @param startBeat the beat to begin the ending
   * @param endBeat the beat to end the ending
   * Note: the repeat is on by default.
   */
  public Ending(int startBeat, int endBeat) {
    this.startBeat = startBeat;
    this.endBeat = endBeat;
    this.isOn = true;
  }

  /**
   * Turn it off.
   */
  public void turnOffEnding() {
    this.isOn = false;
  }

  /**
   * Turn it on.
   */
  public void turnOnEnding() {
    this.isOn = true;
  }

  /**
   * Find out if the ending is on.
   */
  public boolean isOn() {
    return isOn;
  }


  /**
   * Get the start
   * @return the start
   */
  public int getStart() {
    return startBeat;
  }

  /**
   * Get the end
   * @return the end
   */
  public int getEnd() {
    return endBeat;
  }
}