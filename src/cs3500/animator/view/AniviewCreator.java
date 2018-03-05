package cs3500.animator.view;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.shape.AniShape;

// create a view accroding to the given input.
public class AniviewCreator {
  private IAnimationModel<AniShape, AAnimation> model;
  private int rate;

  /**
   * to create an aniView.
   *
   * @param rate  the rate of the animation.
   * @param model the model of the animation.
   */
  public AniviewCreator(int rate, IAnimationModel<AniShape, AAnimation> model) {
    if (rate <= 0) {
      throw new IllegalArgumentException("The rate should be greater than 0!");
    }
    this.rate = rate;
    this.model = model;

  }

  /**
   * to create an IView.
   *
   * @param option the String represent the option.
   * @return the class represent the option.
   */
  public IView create(String option) {
    switch (option) {
      case "svg":
        return new SvgView(model, rate);
      case "text":
        return new TextualView(model, rate);
      case "visual":
        return new VisualView(model, rate);
      case "interactive":
        return new HybridView(new SvgView(model, rate), rate);

      default:
        throw new IllegalArgumentException("Please give a valid name!");
    }

  }
}
