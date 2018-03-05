package cs3500.animator.view;






import java.awt.*;

import cs3500.animator.model.IAnimationReadOnlyModel;

// interface to make a view of animation.
public interface IView {

  /**
   * use the text to represent the Animation.
   * @return the String represent the animation.
   */
  String viewText(boolean ifloopback, Color color);


  /**
   * Change the rate of the animation.
   *
   * @param rate the rate to change to
   */
  void setRate(int rate);

  /**
   * Get the model of all views.
   */
  IAnimationReadOnlyModel getModel();


  /**
   * Get the rate of the view.
   * @return integer as the rate
   */
  int getRate();

  /**
   * get the boolean value of loopback field in the view.
   * @return boolean value of loopback field
   */
  boolean getLoopBack();

}

