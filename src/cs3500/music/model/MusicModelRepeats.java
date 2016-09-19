package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for music compositions with support for repeats.
 */
public class MusicModelRepeats extends MusicModelImpl {
  private List<Repeat> repeatList;

  /**
   * Default constructor.
   * Intializes data.
   */
  public MusicModelRepeats() {
    super();
    this.repeatList = new ArrayList<Repeat>();
  }

  /**
   * Add a repeat to this composition.
   * @param repeat the repeat to add
   */
  public void addRepeat(Repeat repeat) {
    this.repeatList.add(repeat);
    System.out.println(repeat);
  }

  /**
   * Get the repeats
   * @return the repeats
   */
  public List<Repeat> getRepeats() {
    return repeatList;
  }
}
