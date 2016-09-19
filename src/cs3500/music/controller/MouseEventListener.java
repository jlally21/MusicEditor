package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * Represents a mouse listener. It is configurable by the controller that instantiates
 * it.
 *
 * Has a mapping of mouse events to function objects.
 */
public class MouseEventListener implements MouseListener {
  private Map<Integer, Runnable> mouseEventMap;

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.mouseEventMap.containsKey(e.MOUSE_CLICKED)) {
      mouseEventMap.get(e.MOUSE_CLICKED).run();

    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  /**
   * Set the map for mouse events.
   */
  public void setMouseEventMap(Map<Integer, Runnable> mouseEventMap) {
    this.mouseEventMap = mouseEventMap;
  }
}
