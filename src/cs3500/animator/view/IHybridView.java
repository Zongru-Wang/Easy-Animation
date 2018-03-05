package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import cs3500.animator.model.shape.AniShape;


//interface of class HybridView, HybridView class implements it.
// the HybridView can interact with users.
public interface IHybridView extends IView {


  /**
   * set the rate of both of model.
   *
   * @param rate the rate to be set to
   */
  void setRate(int rate);

  /**
   * this is to force the view to have a method to set up actions for buttons. All the buttons must
   * be given this action listener. The buttons are start, pause, resume, restart.
   */
  void addActionListener(ActionListener listener);


  /**
   * start the animation by the start button.
   */
  void startAnimation();

  /**
   * pause the animation by the pause button.
   */
  void pauseAnimation();

  /**
   * resume the animation by the resume button.
   */
  void resumeAnimation();

  /**
   * restart the animation by the restart button.
   */
  void restartAnimation();

  /**
   * speed up the animation by the speed up button.
   */
  void speedUP();

  /**
   * slow down the animation by the slow down button.
   */
  void slowDwon();

  /**
   * Switch the loop back boolean.
   */
  void switchLoop();

  /**
   * Export the animation to the given view.
   */
  void export();

  /**
   * Check select the shape or not.
   */
  void selectOrUnseletShapes(AniShape shape);

  /**
   * add itemListener.
   *
   * @param itemListener item listener to be added
   */
  void addItemListener(ItemListener itemListener);

  /**
   * Set the background color of the animation.
   *
   * @param color the color to be set
   */
  void setColor(int color);



}
