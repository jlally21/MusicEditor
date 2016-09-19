package cs3500.music.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Tone;

/**
 * An abstract music view Contains a list of notes and a map of tones to notes.
 */
public abstract class AMusicView extends JFrame implements MusicView {
  //The notes
  protected List<ANote> notes;
  protected Map<Tone, List<ANote>> toneMap;


  /**
   * Returns a list of tone, in order, without skipping any. For instance, if E(first
   * octave) is the lowest note and C#(2nd octave) is the highest note, returns
   * E1,F1,F♯1,G1,G♯1,A1,A♯1,B1,C2,C#2
   *
   * @return the list of tones
   */
  protected List<Tone> toneList() {
    ArrayList<Tone> toneList = new ArrayList<Tone>();
    if (this.notes.size() > 0) {
      Tone minTone = this.minNote().getTone();
      Tone maxTone = this.maxNote().getTone();

      //iterate through relevant octaves
      for (int i = minTone.getOctave(); i <= maxTone.getOctave(); i++) {
        ArrayList<Pitch> allPitches = new ArrayList<Pitch>();
        allPitches.addAll(Arrays.asList(Pitch.values()));
        for (Pitch p : allPitches) {
          //if it's within range, add it.
          Tone checkTone = new Tone(p, i);
          if (checkTone.compareTo(minTone) >= 0) {
            if (checkTone.compareTo(maxTone) <= 0) {
              toneList.add(checkTone);
            }
          }
        }
      }
    }
    return toneList;
  }

  /**
   * Return the min note in the song. Null if the song is empty.
   *
   * @return the min note
   */

  protected ANote minNote() {
    ANote minNote;

    if (this.notes.size() > 0) {
      minNote = this.notes.get(0);
    } else {
      return null;
    }

    for (ANote note : this.notes) {
      if (note.compareTo(minNote) < 0) {
        minNote = note;
      }
    }
    return minNote;
  }

  /**
   * Return the max note in the song. Null if the song is empty.
   *
   * @return the max note
   */
  protected ANote maxNote() {
    ANote maxNote;

    if (this.notes.size() > 0) {
      maxNote = this.notes.get(0);
    } else {
      return null;
    }

    for (ANote note : this.notes) {
      if (note.compareTo(maxNote) > 0) {
        maxNote = note;
      }
    }
    return maxNote;
  }

  /**
   * Return the length in beats of the song
   *
   * @return the length in beats
   */
  protected int songLength() {
    int maxBeat = 0;

    if (this.notes.size() > 0) {
      for (ANote note : this.notes) {
        if (note.getStartBeat() + note.getBeatLength() > maxBeat) {
          maxBeat = note.getStartBeat() + note.getBeatLength();
        }
      }
    }
    return maxBeat;
  }

  /**
   * Returns the notes at the specified beat.
   *
   * @param beat the beat the caller wants the notes of.
   * @return a list of the notes at the specified beat.
   * @throws IllegalArgumentException if beat is negative
   */
  protected List<ANote> getNotesAtBeat(int beat) throws IllegalArgumentException {
    if (beat < 0) {
      throw new IllegalArgumentException("Beat must be at least 0");
    }

    ArrayList<ANote> notes = new ArrayList<ANote>();

    for (ANote note : this.notes) {
      if (note.isNotePlayedAtBeat(beat)) {
        notes.add(note);
      }
    }
    return notes;
  }

  @Override
  public List<ANote> getNotesForTone(Tone t) {
    return this.toneMap.get(t);
  }

  @Override
  public void setVisibleBeat(int visibleBeat) {
    throw new UnsupportedOperationException("Method not supported by this view.");
  }
}
