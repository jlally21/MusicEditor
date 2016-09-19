package cs3500.music.controller;

import java.util.ArrayList;

import cs3500.music.model.MusicModel;
import cs3500.music.view.MusicView;

/**
 * A concrete music controller implementation. Requires a model and view to construct. Its
 * only method, playMusic(), just displays the view using the model's notes, its tonemap
 * and tempo.
 */
public class MusicControllerImpl implements MusicController {
  MusicModel model;
  MusicView view;

  /**
   * Constructor.
   *
   * @param model the model for the controller
   * @param view  the view for the controller
   */
  public MusicControllerImpl(MusicModel model, MusicView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void playMusic() {
    this.view.initView(model.getAllNotes(), model.getToneMap(), model.getTempo(), new ArrayList<>());
  }
}
