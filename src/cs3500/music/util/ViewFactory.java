package cs3500.music.util;

import cs3500.music.view.CombinedView;
import cs3500.music.view.ConsoleMusicView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.RepeatView;

/**
 * A factory for creating Music Views
 */
public class ViewFactory {

  /**
   * Gets the view of the indicated type
   *
   * @param viewType the type of view
   * @return the view
   */
  public MusicView getView(String viewType) {
    MusicView view = null;
    switch (viewType) {
      case "console":
        view = new ConsoleMusicView(System.out);
        break;
      case "midi":
        view = MidiViewImpl.builder().build();
        break;
      case "visual":
        view = new GuiViewFrame();
        break;
      case "combined":
        view = new CombinedView();
        break;
      case "repeats":
        view = new RepeatView();
        break;
      default:
        throw new IllegalArgumentException("Incorrect view type");
    }
    return view;
  }
}
