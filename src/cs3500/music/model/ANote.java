package cs3500.music.model;

import java.util.Objects;

/**
 * Represents an abstract note.
 */
public abstract class ANote implements Comparable<ANote> {
  private Tone tone; //the pitch of the note

  private int beatLength; //the length of the note
  private int startBeat; //the beat this note starts on
  private int instrument; //the instrument for the note


  /**
   * Constructor.
   *
   * @param tone       the tone for this note
   * @param beatLength (must be at least 0)
   * @param startBeat  the start beat (must be at least 0)
   * @param instrument the instrument
   */
  public ANote(Tone tone, int beatLength, int startBeat, int instrument) {
    if (tone.getOctave() > 0 && startBeat >= 0 && beatLength >= 0) {
      this.tone = tone;
      this.beatLength = beatLength;
      this.startBeat = startBeat;
      this.instrument = instrument;
    } else {
      throw new IllegalArgumentException("Invalid arguments");
    }
  }

  /**
   * Get the pitch of this note
   *
   * @return the pitch of this note
   */
  public Tone getTone() {
    return this.tone;
  }

  /**
   * Get the start beat for this note
   *
   * @return the start beat
   */
  public int getStartBeat() {
    return this.startBeat;
  }

  /**
   * Returns true if this note would be played at the indicated beat. False otherwise.
   *
   * @return boolean value indicating if the note is played at the beat
   */
  public boolean isNotePlayedAtBeat(int beat) {
    if (beat - this.startBeat <= this.beatLength - 1 &&
            beat - this.startBeat >= 0) {
      return true;
    }
    return false;
  }

  /**
   * Notes are comparable by octave and pitch and starting beat.
   *
   * A negative value would indicate a lower note, and a positive value would indicate a
   * higher note. A zero means the same note.
   *
   * @return integer value indicating the result of comparison
   */
  @Override
  public int compareTo(ANote other) {

    // First compare by octave
    int octaveDifference = this.tone.getOctave() - other.getTone().getOctave();
    if (octaveDifference == 0) {
      // If they are the same octave, compare by pitch
      int pitchDifference = this.tone.getPitch().getPitchVal() - other.tone.getPitch()
              .getPitchVal();
      if (pitchDifference == 0) {
        return this.startBeat - other.startBeat;
      } else {
        return pitchDifference;
      }
    } else {
      // Otherwise we know one card is greater than the other
      return octaveDifference;
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else {
      if (other instanceof ANote) {
        return this.compareTo((ANote) other) == 0;
      } else {
        return false;
      }
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getTone().getOctave()) + Objects.hash(this.getTone()
            .getPitch().getPitchVal());
  }

  @Override
  public String toString() {
    return this.getTone().toString();
  }

  /**
   * Returns the beat length
   *
   * @return the beat length
   */
  public int getBeatLength() {
    return beatLength;
  }


  /**
   * Gets the instrument
   *
   * @return the instrument
   */
  public int getInstrument() {
    return instrument;
  }
}
