package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * A model for a music editor.
 */
public class MusicModelImpl implements MusicModel {
  //List of notes.
  protected List<ANote> notes;

  //Maps tones to notes with that tone
  protected Map<Tone, List<ANote>> toneMap;

  //The tempo for the composition
  private int tempo;

  public MusicModelImpl() {
    this.notes = new ArrayList<ANote>();
    this.toneMap = new HashMap<Tone, List<ANote>>();
    this.tempo = 0;
  }

  @Override
  public void addNote(ANote note) throws IllegalArgumentException {
    if (note != null) {
      this.notes.add(note);
    } else {
      throw new IllegalArgumentException("Can't add a null note.");
    }

    if (note != null) {
      Tone tone = note.getTone();
      if (toneMap.get(tone) == null) {
        toneMap.put(tone, new ArrayList<ANote>());
      }
      toneMap.get(tone).add(note);
    }
  }


  @Override
  public void removeNote(ANote note) {
    this.notes.remove(note);
    this.toneMap.get(note.getTone()).remove(note);
  }

  @Override
  public List<ANote> getAllNotes() {
    ArrayList<ANote> notesList = new ArrayList<ANote>(this.notes);
    return notesList;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public Map<Tone, List<ANote>> getToneMap() {
    return this.toneMap;
  }


}

