package cs3500.music.model;

import java.util.Objects;

/**
 * A tone represents a combination of pitch and octave
 */
public class Tone implements Comparable<Tone> {
  private Pitch pitch;
  private int octave;

  /**
   * Constructor.
   *
   * @param pitch  the pitch for this tone
   * @param octave the octave for this tone
   */
  public Tone(Pitch pitch, int octave) {
    this.pitch = pitch;
    this.octave = octave;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else {
      return (other instanceof Tone) && ((Tone) other).getPitch().equals(this.getPitch())
              && ((Tone) other).getOctave() == this.getOctave();
    }
  }

  @Override
  public String toString() {
    return this.pitch.toString() + this.octave;
  }

  @Override
  public int compareTo(Tone other) {
    int octaveDifference = this.octave - other.octave;
    if (octaveDifference == 0) {
      // If they are the same octave, compare by pitch
      int pitchDifference = this.pitch.getPitchVal() - other.pitch.getPitchVal();
      if (pitchDifference == 0) {
        return 0;
      } else {
        return pitchDifference;
      }
    } else {
      return octaveDifference;
    }
  }

  /**
   * Get the pitch
   *
   * @return the pitch of this tone
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Get the octave
   *
   * @return the octave of this tone
   */
  public int getOctave() {
    return this.octave;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch) + Objects.hash(this.octave);
  }

}
