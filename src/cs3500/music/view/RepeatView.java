package cs3500.music.view;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Repeat;
import cs3500.music.model.Tone;

/**
 * A music view that supports repeats
 */
public class RepeatView extends CombinedView implements RepeatMusicView {
  private JButton addRepeatButton; //the 'Add repeat' button
  private RepeatOptionPane repeatPane;

  /**
   * Default constructor to initialize fields
   */
  public RepeatView() {
    super();
    this.addRepeatButton = new JButton();
    this.repeatPane = new RepeatOptionPane();

  }

  @Override
  public Repeat createRepeat() {
    this.repeatPane = new RepeatOptionPane();

    int result = repeatPane.showConfirmDialog(null, repeatPane.getPanel(), "Create a " +
            "repeat",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
      return repeatPane.getRepeat();
    }
    else {
      return null;
    }
  }

  @Override
  public void initView(List<ANote> notes, Map<Tone, List<ANote>> toneMap, int tempo,
                       List<Repeat> repeats) {
    super.initView(notes, toneMap, tempo, repeats);

    this.addRepeatButton.setText("Add repeat");
    this.addRepeatButton.setActionCommand("Add repeat");
    this.getGui().getJMenuBar().add(this.addRepeatButton);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    super.addActionListener(listener);
    this.addRepeatButton.addActionListener(listener);
  }

  public MidiViewImpl getMidiView() {
    return this.midiView;
  }
}
