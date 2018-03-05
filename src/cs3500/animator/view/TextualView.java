package cs3500.animator.view;


import java.awt.*;

import cs3500.animator.model.IAnimationReadOnlyModel;


// the class represent the textural view.
public class TextualView implements IView {
  private IAnimationReadOnlyModel model;
  private int rate;
  private String unit;

  /**
   * to make a textural view for animation.
   * @param model the model we take in.
   * @param rate the rate for animation move.
   */
  TextualView(IAnimationReadOnlyModel model, int rate) {
    this.model = model;
    this.rate = rate;
    this.unit = "s";

  }

  @Override
  public String viewText(boolean ifloopback, Color color) {
    return model.toText(rate, unit);
  }



  @Override
  public void setRate(int rate) {
    this.rate = rate;
  }

  @Override
  public IAnimationReadOnlyModel getModel() {
    return this.model;
  }


  @Override
  public int getRate() {
    return this.rate;
  }

  @Override
  public boolean getLoopBack() {
    return false;
  }


}
