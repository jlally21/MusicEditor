package cs3500.music.view;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import javax.sound.midi.Sequencer;
import javax.swing.*;

import cs3500.music.model.Ending;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A grid panel for music notes
 */
public class GuiGridPanel extends JPanel {
  private int[][] notesMatrix;
  private int cellWidth;
  private int cellHeight;
  private List<Tone> toneList;
  private Sequencer midi;
  private int songLength;
  private JScrollPane scrollpane;
  private boolean waitToScroll;
  private int prevBeat;
  private List<Repeat> repeats;

  /**
   * Default constructor.
   */
  public GuiGridPanel() {

  }

  /**
   * Constructor.
   *
   * @param notesMatrix the 2D array of notes to draw.
   * @param cellHeight  the height of a cell
   * @param cellWidth   the width of a cell
   */
  public GuiGridPanel(List<Tone> toneList, int[][] notesMatrix, int cellWidth, int
          cellHeight, Sequencer midi, int songLength, JScrollPane scrollPane,
                      List<Repeat> repeats) {
    this.notesMatrix = notesMatrix;
    this.toneList = toneList;
    Collections.reverse(toneList);
    this.cellWidth = cellWidth;
    this.cellHeight = cellHeight;
    this.midi = midi;
    this.songLength = songLength;
    this.scrollpane = scrollPane;
    waitToScroll = true;
    this.repeats = repeats;
  }

  @Override
  public void paintComponent(Graphics g) {
    //paint the grid.

    //create graphics2D object
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.white);
    g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    //iterate through matrix, painting the notes
    for (int i = 1; i < notesMatrix.length; i++) {
      for (int j = 2; j < notesMatrix[0].length; j++) {
        if (notesMatrix[i][j] == 2) {
          g2.setColor(Color.black);
        } else if (notesMatrix[i][j] == 1) {
          g2.setColor(Color.green);
        } else {
          g2.setColor(Color.white);
        }

        if (!g2.getColor().equals(Color.white)) {
          g2.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
        }

        //draw repeats
        Color repeatColor = Color.red;
        Color ending1Color = Color.blue;
        Color ending2Color = Color.yellow;

        for(Repeat r : repeats) {
          for(int k = r.getStart(); k < r.getEnd(); k++) {
            if (k +2== j) {
              g2.setColor(repeatColor);
              g2.fillRect(j * cellWidth + cellWidth/4, i * cellHeight + cellHeight/4,
                      cellWidth/4,
                      cellHeight/4);
            }
          }
          for(int p = 0; p < r.getEndings().size(); p++) {
            Ending e = r.getEndings().get(p);
            for(int m = e.getStart(); m < e.getEnd(); m++) {
              if(m+2==j) {
                if(p%2==0) {
                  g2.setColor(ending1Color);
                }
                else {
                  g2.setColor(ending2Color);
                }
                g2.fillRect(j * cellWidth + cellWidth/4, i * cellHeight + cellHeight/4,
                        cellWidth/4,
                        cellHeight/4);
              }
            }
          }
        }
      }
    }

    //iterate through matrix, painting the measure/tone breaks
    for (int i = 1; i < notesMatrix.length; i++) {
      //draw tone breaks
      //draw bold octave separators
      if (toneList.get(i - 1).getPitch().ordinal() == 11) {
        g2.setStroke(new BasicStroke(3));
      } else {
        g2.setStroke(new BasicStroke(1));
      }
      g2.setColor(Color.black);
      g2.drawLine(cellWidth * 2, i * cellHeight, this.cellWidth * notesMatrix[0].length, i *
              cellHeight);

      //reset stroke
      g2.setStroke(new BasicStroke(1));
      for (int j = 2; j < notesMatrix[0].length; j++) {
        //draw measure break
        if ((j + 2) % 4 == 0) {
          g2.setColor(Color.black);
          g2.drawLine((j) * cellWidth, i * cellHeight, (j) * cellWidth, (i + 1) *
                  cellHeight);
          g2.drawString(Integer.toString(j - 2), (float) (j * cellWidth), (float) cellHeight
                  - cellHeight / 5);
        }
      }
    }
    //draw bottom line
    g2.setColor(Color.black);
    g2.drawLine(cellWidth * 2, cellHeight * notesMatrix.length, this
                    .cellWidth * notesMatrix[0].length,
            cellHeight * notesMatrix.length);

    //draw last measure line
    g2.setColor(Color.black);
    g2.drawLine(notesMatrix[0].length * cellWidth, cellHeight, notesMatrix[0].length *
                    cellWidth,
            cellHeight * notesMatrix.length);

    //draw tone labels
    for (int i = 1; i <= toneList.size(); i++) {
      g2.setColor(Color.black);
      g2.drawString(toneList.get(i - 1).toString(), (float) 0, (float) cellHeight * (i + 1)
              - cellHeight / 5);
    }
    g2.setColor(Color.RED);
    double percentComplete = (double) midi.getMicrosecondPosition() / midi.getMicrosecondLength();
//    this.scrollpane.createHorizontalScrollBar();
//    this.scrollpane.getHorizontalScrollBar().setMaximum(this.songLength * cellWidth + 1000);
    if (midi.isRunning()) {
      g2.drawLine(cellWidth + (int) (percentComplete * this.songLength * cellWidth), cellHeight,
              cellWidth + (int) (percentComplete * this.songLength * cellWidth),
              cellHeight + cellHeight * toneList.size());

      if (((int) (percentComplete * this.songLength * this.cellWidth + cellWidth) %
              (46 * cellWidth)) == 0) {
        this.scrollpane.getHorizontalScrollBar().setValue((int) (percentComplete *
                this.songLength * this.cellWidth));
        this.scrollpane.repaint();
      }

      int currentBeat = (int) (midi.getTickPosition() / midi.getSequence()
              .getResolution());
      float tempo = midi.getTempoInMPQ();

      for (Repeat repeat : repeats) {

        if (currentBeat == repeat.getEnd() && repeat.isOn() && repeat.getEndings().size
                () == 0) {
          repeat.turnOffRepeat();
          midi.setTickPosition(repeat.getStart() * midi.getSequence().getResolution
                  ());
          break;
        } else {

          for (Ending ending : repeat.getEndings()) {
            if (currentBeat == ending.getEnd() && ending.isOn()) {
              ending.turnOffEnding();
              midi.setTickPosition(repeat.getStart() * midi.getSequence()
                      .getResolution());
              break;
            }
          }
        }
      }
      midi.setTempoInMPQ(tempo);


      repaint();

    } else
      g2.drawLine(28 + cellWidth + (int) (percentComplete * this.songLength * cellWidth), cellHeight,
              28 + cellWidth + (int) (percentComplete * this.songLength * cellWidth),
              cellHeight + cellHeight * toneList.size());

  }


  /**
   * Returns the rect for any note on the given beat.
   *
   * @param beat the beat to get the rect for
   * @return the rect
   */
  protected Rectangle getRectForBeat(int beat) {
    return new Rectangle(beat * cellWidth, 0, cellWidth, cellHeight);
  }

  /**
   * Takes in a point, returns the point where it falls on the grid
   *
   * @param point the point
   * @return the point, or null if there is no note there
   */
  protected Point getPointForPoint(Point point) {
    int realX = point.x;
    int realY = point.y;


    realX /= cellWidth;

    realY /= cellHeight;


    return new Point(realX, realY);
  }
}
