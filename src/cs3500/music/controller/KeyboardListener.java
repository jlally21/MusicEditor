package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Represents a keyboard listener. It is configurable by the controller that instantiates
 * it.
 *
 * Has a mapping of keys typed to function objects.
 */
public class KeyboardListener implements KeyListener {
  private Map<Integer, Runnable> keyTypedMap;

  /**
   * Empty default constructor
   */
  public KeyboardListener() {
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   */
  public void setKeyTypedMap(Map<Integer, Runnable> map) {
    keyTypedMap = map;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyTypedMap.containsKey(e.getKeyCode())) {
      keyTypedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}