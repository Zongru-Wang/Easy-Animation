package cs3500.animator.model.aniamtion;


import java.util.List;

import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.IShape;


// the class can change the scale of shape in animation.
// used to be a method, now make it a class.
public class Scale extends AAnimation {
  private double[] endDims;
  private double[] startDims;

  /**
   * Creates a new ScaleAnimation.
   *
   * @param start   the time the animation starts.
   * @param end     the time the animation ends.
   * @param endDims the dimensions to scale to.
   */
  public Scale(int start, int end, double... endDims) {
    super(start, end);
    this.endDims = endDims;
  }

  /**
   * Creates a new ScaleAnimation given a startpoint.
   *
   * @param start     the time the animation starts.
   * @param end       the time the animation ends.
   * @param startDims the start dimesion.
   * @param endDims   the dimensions to scale to.
   */
  public Scale(int start, int end, double[] startDims, double[] endDims) {
    this(start, end, endDims);
    if (this.startDims == null) {
      this.startDims = startDims;
    }
  }

  @Override
  public void setShape(AniShape shape) {
    super.setShape(shape);
    this.startDims = shape.allDimensions();
    if (endDims.length != startDims.length) {
      throw new IllegalArgumentException("Wrong number of dimensions.");
    }
  }

  @Override
  public String changeText() {
    // this creates a copy to get the new description of the IShape object without knowing
    // if the description of the rectangle has changed (may be a subclass)
    IShape copy = this.shape.getShape().copy();
    copy.scale(endDims);
    return "scales from " + this.shape.getShape().dimensions() + " to " + copy.dimensions();
  }

  @Override
  public void apply() {
    this.shape.scale(this.endDims);
  }

  @Override
  public void apply(int time) {
    double[] newDims = new double[startDims.length];
    if (time > this.startime && time <= this.endtime) {
      for (int i = 0; i < startDims.length; i++) {
        double newDim = this.curVal(time, startDims[i], endDims[i]);
        newDims[i] = newDim;
      }
      this.shape.scale(newDims);
    }
  }


  @Override
  public int countattributions() {
    return endDims.length;
  }

  @Override
  public String getattributename(int index) {
    List<String> attributenames = this.shape.getShape().getattributeLennames();

    return "\"" + attributenames.get(index) + "\"";
  }

  @Override
  public String attributeValueFrom(int index) {
    double[] scalefrom = startDims;

    return "\"" + scalefrom[index] + "\"";
  }

  @Override
  public String attributeValueTo(int index) {
    double[] scaleto = endDims;

    return "\"" + scaleto[index] + "\"";
  }


}