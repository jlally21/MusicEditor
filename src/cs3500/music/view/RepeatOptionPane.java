package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.Ending;
import cs3500.music.model.Repeat;

/**
 * A custom option pane for creating Repeats.
 */
public class RepeatOptionPane extends JOptionPane {
  private JTextField startBeatField;
  private JTextField endBeatField;
  private JTextField endingsField;
  private JPanel panel;

  /**
   * Default constructor.
   */
  public RepeatOptionPane() {
    this.panel = new JPanel(new GridLayout(0, 1));
    this.startBeatField = new JTextField(10);
    this.endBeatField = new JTextField(10);
    this.endingsField = new JTextField(10);

    panel.add(new JLabel("Enter Start Beat:"));
    panel.add(startBeatField);
    panel.add(new JLabel("Enter End Beat:"));
    panel.add(endBeatField);
    panel.add(new JLabel("Enter Endings (startBeat,endBeat) (startBeat, endBeat)..."));
    panel.add(endingsField);
  }

  /**
   * Gets the created repeat, or null if it was invalid.
   *
   * @return the repeat or null
   */
  public Repeat getRepeat() {
    //get the input
    String start = startBeatField.getText();
    String end = endBeatField.getText();
    String ending = endingsField.getText();

    int startBeat = 0;
    int endBeat = 0;
    ArrayList<Ending> endingList = new ArrayList<Ending>();

    //validate start
    try {
      startBeat = Integer.parseInt(start);
    } catch (Exception e) {
      return null;
    }

    //validate end beat
    try {
      endBeat = Integer.parseInt(end);
    } catch (Exception e) {
      return null;
    }

    //validate endings
    endingList = this.getEndings(ending);

    if (endingList != null) {
      //create and return the repeat
      return new Repeat(startBeat, endBeat, endingList);
    }

    return null;
  }

  /**
   * Returns the list of endings from the inputted String or null if it was invalid.
   *
   * @return the endings or null
   */
  private ArrayList<Ending> getEndings(String input) {
    ArrayList<Ending> endings = new ArrayList<Ending>();

    if(input.length() > 0) {
      //split by spaces separating endings
      String[] inputArray = input.split(" ");

      int[] startBeatArray = new int[inputArray.length];
      int[] endBeatArray = new int[inputArray.length];

      for (int i = 0; i < inputArray.length; i++) {
        String s = inputArray[i];
        String withoutParens = s.substring(1, s.length() - 1);
        String[] nums = withoutParens.split(",");
        int start = 0;
        int end = 0;
        try {
          start = Integer.parseInt(nums[0]);
          end = Integer.parseInt(nums[1]);
        } catch (Exception e) {
          return null;
        }

        if (start < end) {
          startBeatArray[i] = start;
          endBeatArray[i] = end;
        } else {
          return null;
        }
      }

      //make them into endings, return
      for (int j = 0; j < startBeatArray.length; j++) {
        endings.add(new Ending(startBeatArray[j], endBeatArray[j]));
      }
    }

    return endings;
  }

  /**
   * Gets the JPanel for the option pane.
   *
   * @return the panel
   */
  public JPanel getPanel() {
    return this.panel;
  }
}
