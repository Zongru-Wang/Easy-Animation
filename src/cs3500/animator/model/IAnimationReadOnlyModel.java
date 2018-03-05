package cs3500.animator.model;

import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.shape.AniShape;

// the read only model that view classes can take,
// only has getters and without setters, so view classes won't
// be able to change the model in the view classes.
public interface IAnimationReadOnlyModel<O, A> {

  /**
   * Produces a text description of the animation, specifying the shapes and how they change over
   * time.
   *
   * @return a String describing the animation
   */
  String toText();

  /**
   * Produces a text description of the animation with the given tick/unit rate and the unit.
   *
   * @param rate number of ticks per given unit
   * @param unit the unit of time in shorthand (e.g. 's' for seconds)
   * @return the text form of the animation
   */
  String toText(int rate, String unit);


  /**
   * Skips to the given time in the animation.
   *
   * @param time the time to skip to.
   */
  void skipTo(int time);

  /**
   * get all the animation from model.
   */
  List<A> getAnimations();

  /**
   * get all the shapes from model.
   */
  HashMap<String, O> getShapes();

  /**
   * get the list of shapes.
   *
   * @return a list of shapes
   */
  List<AniShape> getShapeList();

  /**
   * get the shape list in the given tick.
   *
   * @param tick given tick in integer
   * @return the shape list
   */
  List<AniShape> getShapeList(int tick);

  /**
   * get the list of animations.
   *
   * @return a list of aniamtions
   */
  List<AAnimation> getAniList();

  /**
   * get the end time of the animation.
   *
   * @return integer stands for the end time
   */
  int endTime();

  /**
   * change the name of shapes to the original one.
   */
  void rewind();

  /**
   * get all shapes in all ticks in a list.
   *
   * @return a list of shapes
   */
  List<List<AniShape>> getAllTickShapesList();

  /**
   * get the maximum end time at the given rate.
   *
   * @param rate the given rate in integer
   * @return the maximum time in double
   */
  Double getmaxEndtime(int rate);


}
