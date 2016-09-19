package cs3500.music.view;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.music.model.ANote;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A music view for the console. Can be used with any Appendable output destination.
 */
public class ConsoleMusicView extends AMusicView {
  private Appendable output; // the output

  /**
   * Constructor.
   *
   * @param output the Appendable output
   */
  public ConsoleMusicView(Appendable output) {
    this.output = output;
  }

  @Override
  public void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {
    this.notes = notes;

    String display = "";
    if (notes.size() > 0) {

      int beatCounterLength = String.valueOf(this.songLength()).length();

      //set up first row
      for (int b = 0; b < beatCounterLength; b++) {
        display += " ";
      }

      for (Tone s : this.toneList()) {
        if (s.toString().length() == 2) {
          display += "  " + s + " ";
        } else if (s.toString().length() == 3) {
          display += " " + s + " ";
        } else if (s.toString().length() == 4) {
          display += " " + s;
        }
      }

      display += '\n';

      //iterate from 0 to max beat
      for (int i = 0; i < this.songLength(); i++) {
        //right justify the row counter
        display += String.format("%" + beatCounterLength + "d", i);

        //for each beat, iterate through the pitches and add them to the display.
        List<ANote> beatsToDisplay = this.getNotesAtBeat(i);

        for (int pitchIndex = 0; pitchIndex < this.toneList().size(); pitchIndex++) {

          ArrayList<ANote> notesToPlay = new ArrayList<ANote>();
          for (ANote note : beatsToDisplay) {
            String pitchCheck = this.toneList().get(pitchIndex).toString();
            String noteCheck = note.getTone().toString();
            if (noteCheck.equals(pitchCheck)) {
              //the note is supposed to be played here.
              notesToPlay.add(note);
            }
          }

          //if one of the notes to play is starting now, display an X. otherwise a sustain
          boolean startNote = false;
          for (ANote checkNote : notesToPlay) {
            if (checkNote.getStartBeat() == i) {
              startNote = true;
              break;
            }
          }

          //if there are notes to play
          if (notesToPlay.size() > 0) {
            if (startNote) {
              display += "  X  ";
            } else {
              display += "  |  ";
            }
          } else {
            display += "     ";
          }
        }

        display += '\n';
      }
    }
    //append the result to the output destination
    try {
      this.output.append(display);
    } catch (IOException e) {
      System.err.println("Could not append output");
    }
  }
}
