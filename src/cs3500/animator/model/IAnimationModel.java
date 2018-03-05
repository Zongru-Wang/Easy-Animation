package cs3500.animator.model;

import java.util.HashMap;
import java.util.List;

import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.shape.AniShape;

/**
 * Interface of the animation model.
 *
 * @param <O> the type that represents the animation object.
 * @param <A> the type that represents the animation transformation.
 */
public interface IAnimationModel<O, A> extends IAnimationReadOnlyModel {
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
   * Adds the given animation object to the model with its given name. If the given animation object
   * also stores its name AND the given name and object name are not consistent, throws an
   * IllegalArgumentException
   *
   * @param name       Name to refer to the animation object with.
   * @param animObject AbstractAnimation object to add to the animation.
   * @throws IllegalArgumentException if given name and object name are inconsistent
   */
  void addAnimShape(String name, O animObject) throws IllegalArgumentException;

  /**
   * Adds the given animation object to the model with its given name. Assumes the name of the
   * animation object can be retrieved from the given animShape.
   *
   * @param animShape the animation object to add that can have its name retrieved
   */
  void addAnimShape(O animShape);

  /**
   * Adds the given animation to this animation model.
   *
   * @param name      The name of the object to animate. Must already exist in this
   *                  cs3500.animator.model.IAnimationModel.
   * @param animation The animation/transformation to add.
   * @throws IllegalArgumentException if object with given name does not exist in model.
   * @throws IllegalArgumentException if animation conflicts with already existing one.
   */
  void addAnimation(String name, A animation) throws IllegalArgumentException;

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
   * get the shape list in that tick.
   *
   * @param tick integer takes in as tick
   * @return the list of shapes at that tick.
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
   * get the Maximum end time at the given rate.
   *
   * @param rate the given rate in integer
   * @return the maximum end time
   */
  Double getmaxEndtime(int rate);
}


