package cs3500.music.view;

import sun.awt.image.GifImageDecoder;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import java.util.*;
import java.util.List;

import javax.sound.midi.Sequencer;
import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Note;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends AMusicView implements GuiView {
  public GuiGridPanel gridPanel;
  private int totalWidth;
  private int totalHeight;
  private JScrollPane scrollPane = new JScrollPane();
  ;
  private int[][] notesMatrix;
  private Sequencer sequencer;
  private List<Repeat> repeats;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(600, 400));
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
  }

  /**
   * Setup the grid with beats
   */
  private void setupGrid() {
    //create a 2D array instructing where to draw notes.
    //a zero is nothing
    //a one is green-a sustain
    //a two is black-a start note.
    //we add 2 extra columns for the tones, and one extra row for the measure counter.
    int[][] notesMatrix = new int[this.toneList().size() + 1][this.songLength() + 2];

    List<Tone> toneList = this.toneList();
    //    Collections.reverse(toneList);


    for (int toneIndex = 0; toneIndex < toneList.size(); toneIndex++) {


      List<ANote> notesToPlay = this.getNotesForTone(toneList.get(toneIndex));

      //if one of the notes to play is starting now, display a black square, otherwise
      //if it is a sustain, display a green square
      if (notesToPlay != null) {
        for (ANote checkNote : notesToPlay) {
          int startBeat = checkNote.getStartBeat();
          int duration = checkNote.getBeatLength();
          notesMatrix[toneList.size() - toneIndex][startBeat + 2] = 2;
          for (int i = startBeat + 1; i < startBeat + duration; i++) {
            if (notesMatrix[toneList.size() - toneIndex][i + 2] != 2) {
              notesMatrix[toneList.size() - toneIndex][i + 2] = 1;
            }
          }
        }
        continue;
      }
    }

    //now that we know what the panel should look like, build it.
    int cellWidth = 30;
    int cellHeight = 20;
    this.gridPanel = new GuiGridPanel(toneList, notesMatrix, cellWidth, cellHeight,
            sequencer, this.songLength(), this.scrollPane, this.repeats);

    totalWidth = (songLength() + 2) * cellWidth;
    totalHeight = (toneList().size() + 1) * cellHeight;



}

  @Override
  public void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {
    this.notes = notes;
    this.toneMap = toneMap;
    this.repeats = repeats;
    this.setupGrid();
    this.gridPanel.setPreferredSize(new Dimension(this.totalWidth, this.totalHeight));
    //Scroll frame
    scrollPane.getViewport().add(gridPanel);
    scrollPane.setPreferredSize(new Dimension(this.totalWidth, this.totalHeight));
    this.getContentPane().add(scrollPane, BorderLayout.CENTER);
    this.setFocusable(true);
    this.requestFocus();
    this.scrollPane.addKeyListener(this.getKeyListeners()[0]);
    this.gridPanel.addKeyListener(this.getKeyListeners()[0]);
    this.scrollPane.addMouseListener(this.getMouseListeners()[0]);

  }

  @Override
  public void setVisibleBeat(int visibleBeat) {
    this.scrollPane.getViewport().scrollRectToVisible(gridPanel.getRectForBeat
            (visibleBeat));
    System.out.println("beat" + visibleBeat);
    System.out.println("rect" + gridPanel.getRectForBeat
            (visibleBeat));
    System.out.println("visiblerect" + scrollPane.getViewport().getVisibleRect());
    scrollPane.repaint();
  }


  /**
   * Returns the note at that x and y position
   *
   * @param p the point
   * @return the note at the location
   */
  private ANote getNoteAtLocation(Point p) {
    //get the beat and tone of the note
    Point point = gridPanel.getPointForPoint(p);

      ArrayList<Tone> reverseTones = new ArrayList<>(this.toneList());
      Collections.reverse(reverseTones);
      List<ANote> notesAtTone = this.getNotesForTone(reverseTones.get(point.y - 1));
      for (ANote note : notesAtTone) {
        if (note.getStartBeat() + note.getBeatLength() - (point.x - 2) <= note.getBeatLength
                () && note.getStartBeat() + note.getBeatLength() - (point.x - 2) > 0) {
          return note;
        }
      }
    return null;
  }

  /**
   * Get temporary note, with tone and start beat already set.
   *
   * @return the temporary note.
   */
  protected ANote getTempSelectedNote() {
    Point point = gridPanel.getPointForPoint(gridPanel.getMousePosition());
    ANote tempNote;

    ArrayList<Tone> reverseTones = new ArrayList<>(this.toneList());
    Collections.reverse(reverseTones);
    tempNote = new Note(reverseTones.get(point.y - 1), 1, point.x - 2, 1);
    return tempNote;

  }

  /**
   * Get the note under the mouse position, or null.
   *
   * @return the note or null
   */
  protected ANote getSelectedNote() {
    return this.getNoteAtLocation(gridPanel.getMousePosition());
  }

  @Override
  public void setSequencer(Sequencer sequencer) {
    this.sequencer = sequencer;
  }

}
