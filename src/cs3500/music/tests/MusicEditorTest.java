package cs3500.music.tests;

import org.junit.*;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.Receiver;
import javax.swing.*;

import cs3500.music.controller.*;
import cs3500.music.mock.MockReceiver;
import cs3500.music.mock.MockSequencer;
import cs3500.music.model.*;
import cs3500.music.util.MusicCompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.util.ViewFactory;
import cs3500.music.view.*;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Music Editors
 */
public class MusicEditorTest {
  private MusicController controller;
  private MusicModel model;
  private MusicView view;

  /**
   * Tests console view output
   */
  @Test
  public void consoleTestEmpty() {
    model = new MusicModelImpl();
    StringBuffer testBuffer = new StringBuffer();
    view = new ConsoleMusicView(testBuffer);
    controller = new MusicControllerImpl(model, view);
    controller.playMusic();
    assertEquals("", testBuffer.toString());
  }

  @Test
  public void consoleTestLamb() {
    model = new MusicModelImpl();
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), new
              MusicCompositionBuilder(model));
    } catch (FileNotFoundException e) {
      System.err.println("Could not read file");
    }

    StringBuffer testBuffer = new StringBuffer();
    view = new ConsoleMusicView(testBuffer);
    controller = new MusicControllerImpl(model, view);
    controller.playMusic();
    assertEquals("    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   " +
            "D4  D#4   E4   F4  F#4   G4 \n" +
            " 0                 X                                           " +
            " X                 \n" +
            " 1                 |                                           " +
            " |                 \n" +
            " 2                 |                                  X        " +
            "                   \n" +
            " 3                 |                                  |        " +
            "                   \n" +
            " 4                 |                        X                  " +
            "                   \n" +
            " 5                 |                        |                 " +
            "                    \n" +
            " 6                 |                                  X       " +
            "                    \n" +
            " 7                                                    |       " +
            "                    \n" +

            " 8                 X                                          " +
            "  X                 \n" +
            " 9                 |                                         " +
            "   |                 \n" +
            "10                 |                                           " +
            " X                 \n" +
            "11                 |                                          " +
            "  |                 \n" +
            "12                 |                                          " +
            "  X                 \n" +
            "13                 |                                          " +
            "  |                 \n" +
            "14                 |                                          " +
            "  |                 \n" +
            "15                                                            " +
            "                    \n" +
            "16                 X                                  X       " +
            "                    \n" +
            "17                 |                                  |        " +
            "                   \n" +
            "18                 |                                  X        " +
            "                   \n" +
            "19                 |                                  |        " +
            "                   \n" +
            "20                 |                                  X        " +
            "                   \n" +
            "21                 |                                  |     " +
            "                      \n" +
            "22                 |                                  |       " +
            "                    \n" +
            "23                 |                                  |      " +
            "                     \n" +
            "24                 X                                         " +
            "   X                 \n" +
            "25                 |                                         " +
            "   |                 \n" +
            "26                                                           " +
            "                  X  \n" +
            "27                                                           " +
            "                  |  \n" +
            "28                                                             " +
            "                X  \n" +
            "29                                                            " +
            "                 |  \n" +
            "30                                                            " +
            "                 |  \n" +
            "31                                                           " +
            "                  |  \n" +
            "32                 X                                         " +
            "   X                 \n" +
            "33                 |                                         " +
            "   |                 \n" +
            "34                 |                                  X      " +
            "                     \n" +
            "35                 |                                  |      " +
            "                     \n" +
            "36                 |                        X                " +
            "                     \n" +
            "37                 |                        |                 " +
            "                    \n" +
            "38                 |                                  X       " +
            "                    \n" +
            "39                 |                                  |       " +
            "                    \n" +
            "40                 X                                          " +
            "  X                 \n" +
            "41                 |                                           " +
            " |                 \n" +
            "42                 |                                           " +
            " X                 \n" +
            "43                 |                                           " +
            " |                 \n" +
            "44                 |                                           " +
            " X                 \n" +
            "45                 |                                          " +
            "  |                 \n" +
            "46                 |                                          " +
            "  X                 \n" +
            "47                 |                                           " +
            " |                 \n" +
            "48                 X                                  X       " +
            "                    \n" +
            "49                 |                                  |        " +
            "                   \n" +
            "50                 |                                  X        " +
            "                   \n" +
            "51                 |                                  |        " +
            "                   \n" +
            "52                 |                                           " +
            " X                 \n" +
            "53                 |                                           " +
            " |                 \n" +
            "54                 |                                  X        " +
            "                   \n" +
            "55                 |                                  |        " +
            "                   \n" +
            "56  X                                       X                  " +
            "                   \n" +
            "57  |                                       |                  " +
            "                   \n" +
            "58  |                                       |                   " +
            "                  \n" +
            "59  |                                       |                  " +
            "                   \n" +
            "60  |                                       |                   " +
            "                  \n" +
            "61  |                                       |                  " +
            "                   \n" +
            "62  |                                       |                  " +
            "                   \n" +
            "63  |                                       |                  " +
            "                   \n", testBuffer.toString());
  }


  /**
   * Tests midi view output
   */
  @Test
  public void midiTestEmpty() {
    StringBuilder sb = new StringBuilder();
    Receiver mockReceiver = new MockReceiver(sb);
    MidiDevice mockDevice = new MockSequencer(mockReceiver);
    model = new MusicModelImpl();
    view = MidiViewImpl.builder().midiDevice(mockDevice).build();
    controller = new MusicControllerImpl(model, view);
    controller.playMusic();
    assertEquals("", sb.toString());
  }

  @Test
  public void midiTestLamb() {
    StringBuilder sb = new StringBuilder();
    Receiver mockReceiver = new MockReceiver(sb);
    MidiDevice mockDevice = new MockSequencer(mockReceiver);
    model = new MusicModelImpl();
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), new
              MusicCompositionBuilder(model));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    view = MidiViewImpl.builder().midiDevice(mockDevice).build();
    controller = new MusicControllerImpl(model, view);
    controller.playMusic();
    assertEquals("144 0 52 64 0\n" +
            "144 0 43 64 0\n" +
            "128 0 52 64 10\n" +
            "144 0 50 64 10\n" +
            "128 0 50 64 20\n" +
            "144 0 48 64 20\n" +
            "128 0 48 64 30\n" +
            "144 0 50 64 30\n" +
            "128 0 43 64 35\n" +
            "128 0 50 64 40\n" +
            "144 0 43 64 40\n" +
            "144 0 52 64 40\n" +
            "128 0 52 64 50\n" +
            "144 0 52 64 50\n" +
            "128 0 52 64 60\n" +
            "144 0 52 64 60\n" +
            "128 0 43 64 75\n" +
            "128 0 52 64 75\n" +
            "144 0 43 64 80\n" +
            "144 0 50 64 80\n" +
            "128 0 50 64 90\n" +
            "144 0 50 64 90\n" +
            "128 0 50 64 100\n" +
            "144 0 50 64 100\n" +
            "128 0 43 64 120\n" +
            "128 0 50 64 120\n" +
            "144 0 43 64 120\n" +
            "144 0 52 64 120\n" +
            "128 0 43 64 130\n" +
            "128 0 52 64 130\n" +
            "144 0 55 64 130\n" +
            "128 0 55 64 140\n" +
            "144 0 55 64 140\n" +
            "128 0 55 64 160\n" +
            "144 0 43 64 160\n" +
            "144 0 52 64 160\n" +
            "128 0 52 64 170\n" +
            "144 0 50 64 170\n" +
            "128 0 50 64 180\n" +
            "144 0 48 64 180\n" +
            "128 0 48 64 190\n" +
            "144 0 50 64 190\n" +
            "128 0 43 64 200\n" +
            "128 0 50 64 200\n" +
            "144 0 43 64 200\n" +
            "144 0 52 64 200\n" +
            "128 0 52 64 210\n" +
            "144 0 52 64 210\n" +
            "128 0 52 64 220\n" +
            "144 0 52 64 220\n" +
            "128 0 52 64 230\n" +
            "144 0 52 64 230\n" +
            "128 0 43 64 240\n" +
            "128 0 52 64 240\n" +
            "144 0 43 64 240\n" +
            "144 0 50 64 240\n" +
            "128 0 50 64 250\n" +
            "144 0 50 64 250\n" +
            "128 0 50 64 260\n" +
            "144 0 52 64 260\n" +
            "128 0 52 64 270\n" +
            "144 0 50 64 270\n" +
            "128 0 43 64 280\n" +
            "128 0 50 64 280\n" +
            "144 0 40 64 280\n" +
            "144 0 48 64 280\n" +
            "128 0 40 64 320\n" +
            "128 0 48 64 320\n", sb.toString());
  }

  /**
   * Tests for keyboard handler
   */
  @Test
  public void keyHandlerTest() {
    Map<Integer, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> mouseMap = new HashMap<>();

    //add mock runnables.
    ArrayList<Integer> testEvents = new ArrayList<>();

    //home scroll
    keyTypes.put(KeyEvent.VK_HOME, () -> {
      testEvents.add(KeyEvent.VK_HOME);
    });

    //end scroll
    keyTypes.put(KeyEvent.VK_END, () -> {
      testEvents.add(KeyEvent.VK_END);
    });

    //pause
    keyTypes.put(KeyEvent.VK_P, () -> {
      testEvents.add(KeyEvent.VK_P);
    });

    //move forward
    keyTypes.put(KeyEvent.VK_RIGHT, () -> {
      testEvents.add(KeyEvent.VK_RIGHT);
    });

    //move backward
    keyTypes.put(KeyEvent.VK_LEFT, () -> {
      testEvents.add(KeyEvent.VK_LEFT);
    });

    //mouse click
    mouseMap.put(MouseEvent.MOUSE_CLICKED, () -> {
      testEvents.add(MouseEvent.MOUSE_CLICKED);
    });

    MouseEventListener mouseEventListener = new MouseEventListener();
    mouseEventListener.setMouseEventMap(mouseMap);

    KeyboardListener keyboardListener = new KeyboardListener();
    keyboardListener.setKeyTypedMap(keyTypes);

    JPanel testPanel = new JPanel();
    testPanel.requestFocus();
    testPanel.addKeyListener(keyboardListener);
    testPanel.addMouseListener(mouseEventListener);

    try {
      Robot robot = new Robot();

      // Simulate a mouse click
      testPanel.requestFocus();
robot.mousePress(MouseEvent.BUTTON1_MASK);
      robot.mouseRelease(MouseEvent.BUTTON1_MASK);

      KeyEvent k1 = new KeyEvent(testPanel, KeyEvent.KEY_PRESSED, System
              .currentTimeMillis(), 0,  KeyEvent.VK_HOME,'Z');
      KeyEvent k2 = new KeyEvent(testPanel, KeyEvent.KEY_PRESSED, System
              .currentTimeMillis(), 0,  KeyEvent.VK_P,'Z');
      KeyEvent k3 = new KeyEvent(testPanel, KeyEvent.KEY_PRESSED, System
              .currentTimeMillis(), 0,  KeyEvent.VK_END,'Z');
      KeyEvent k4 = new KeyEvent(testPanel, KeyEvent.KEY_PRESSED, System
              .currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');
      KeyEvent k5 = new KeyEvent(testPanel, KeyEvent.KEY_PRESSED, System
              .currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

      testPanel.getKeyListeners()[0].keyPressed(k1);
      testPanel.getKeyListeners()[0].keyPressed(k2);
      testPanel.getKeyListeners()[0].keyPressed(k3);
      testPanel.getKeyListeners()[0].keyPressed(k4);
      testPanel.getKeyListeners()[0].keyPressed(k5);
      testPanel.getMouseListeners()[0].mouseClicked(new MouseEvent(testPanel,1, 1,1,1,
              1,1,true,1));

    } catch (AWTException e) {
      e.printStackTrace();
    }

    //check that all the runnables were called.
    ArrayList<Integer> comparison = new ArrayList<>();
    comparison.add(KeyEvent.VK_HOME);
    comparison.add(KeyEvent.VK_END);
    comparison.add(KeyEvent.VK_P);
    comparison.add(KeyEvent.VK_LEFT);
    comparison.add(KeyEvent.VK_RIGHT);
    comparison.add(MouseEvent.MOUSE_CLICKED);

    for(Integer i : comparison) {
      assertEquals(true, testEvents.contains(i));
    }


  }

  /**
   * Controller tests
   */
  @Test
  public void controllerTest() {
    MusicModel musicModel = new MusicModelImpl();
    // FileReader reads text files in the default encoding.
    Readable fileReader = null;
    try {
      fileReader = new FileReader("mary-little-lamb.txt");
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid file name");
    }
    musicModel = MusicReader.parseFile(fileReader, new MusicCompositionBuilder
            (musicModel));

    MusicEditorView musicView = (MusicEditorView)new ViewFactory().getView("combined");

    MusicEditorController testEditor = new MusicEditorController(musicModel, musicView);
    testEditor.playMusic();
    assertEquals(true, musicView.getGui().getKeyListeners().length == 1);
    assertEquals(true, musicView.getGui().getMouseListeners().length == 1);

  }
}
