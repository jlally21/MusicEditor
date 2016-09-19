package cs3500.music;

import cs3500.music.controller.MusicController;
import cs3500.music.controller.MusicControllerImpl;
import cs3500.music.controller.MusicEditorController;
import cs3500.music.controller.MusicEditorRepeatController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.MusicModelRepeats;
import cs3500.music.view.*;
import cs3500.music.util.*;

import java.io.FileReader;

/**
 * Main class. Takes in two string arguments, the file name to play and the type of view
 * to play with.
 */
public class MusicEditor {

  /**
   * Main method.
   *
   * @param args two string arguments, the file name to play and the type of view to play
   *             with
   */
  public static void main(String[] args) {
    String fileName = args[0];
    String viewType = args[1];

    MusicModel musicModel = new MusicModelImpl();
    // FileReader reads text files in the default encoding.
    Readable fileReader = null;


    MusicView musicView = new ViewFactory().getView(viewType);

    if (viewType.equals("combined")) {
      MusicEditorController controller = new MusicEditorController(musicModel, (MusicEditorView)
              musicView);
      controller.playMusic();
    } else if (viewType.equals("repeats")) {
      MusicModelRepeats musicModelRepeats = new MusicModelRepeats();

      try {
        fileReader = new FileReader(fileName);
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid file name");
      }
      musicModelRepeats = MusicRepeatReader.parseFile(fileReader, new
              MusicCompositionRepeatBuilder(musicModelRepeats));

      MusicEditorRepeatController controller = new MusicEditorRepeatController(
              musicModelRepeats,
              new RepeatView());
      controller.playMusic();
    }
    else{
      try {
        fileReader = new FileReader(fileName);
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid file name");
      }
      musicModel = MusicReader.parseFile(fileReader, new MusicCompositionBuilder
              (musicModel));
      MusicController musicController = new MusicControllerImpl(musicModel, musicView);
      musicController.playMusic();

    }
  }
}
