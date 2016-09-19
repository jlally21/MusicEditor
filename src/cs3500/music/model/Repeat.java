package cs3500.music.model;

import java.util.List;

/**
 * A Repeat in a music composition.
 */
public class Repeat {
  private int startBeat;
  private int endBeat;
  private boolean isOn;
  private List<Ending> endings;

  /**
   * Create a repeat with the data.
   *
   * @param startBeat the beat to begin the repeat.
   * @param endBeat the beat to end the repeat
   * @param endings the list of endings
   * Note: the repeat is on by default.
   */
  public Repeat(int startBeat, int endBeat, List<Ending> endings) {
    this.startBeat = startBeat;
    this.endBeat = endBeat;
    this.endings = endings;
    this.isOn = true;
  }

  /**
   * Turn it off.
   */
  public void turnOffRepeat() {
    this.isOn = false;
  }

  /**
   * Turn it on.
   */
  public void turnOnRepeat() {
    this.isOn = true;
  }

  /**
   * Find out if the repeat is on or not.
   */
  public boolean isOn() {
    return isOn;
  }

  /**
   * Get the list of endings
   * @return the endings
   */
  public List<Ending> getEndings() {
    return endings;
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
