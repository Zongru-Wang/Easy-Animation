package cs3500.animator.model.shape;


import java.util.ArrayList;
import java.util.List;


// Represents ovals.
public class Oval implements IShape {
  private double xRadius;
  private double yRadius;

  /**
   * Creates a new Oval object.
   *
   * @param xRadius horizontal radius
   * @param yRadius vertical radius
   */
  public Oval(double xRadius, double yRadius) {
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Can't create a shape with non-positive dimensions.");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public String getType() {
    return "oval";
  }

  @Override
  public String posDesc() {
    return "Center";
  }

  @Override
  public String dimensions() {
    return "X radius: " + this.xRadius + ", Y radius: " + this.yRadius;
  }

  @Override
  public IShape copy() {
    return new Oval(this.xRadius, this.yRadius);
  }

  @Override
  public void scale(double... measurements) {
    if (measurements.length != 2) {
      throw new IllegalArgumentException("Arguments must be x radius and y radius only.");
    }
    xRadius = measurements[0];
    yRadius = measurements[1];
  }

  @Override
  public double[] allDimensions() {
    return new double[]{xRadius, yRadius};
  }

  @Override
  public String getSvgShape() {
    return "ellipse";
  }

  @Override
  public String getSvgShapeLenX() {
    return "rx";
  }

  @Override
  public String getSvgShapeLenY() {
    return "ry";
  }

  @Override
  public String getSvgShapeCodX() {
    return "cx";
  }

  @Override
  public String getSvgShapeCodY() {
    return "cy";
  }

  @Override
  public List<String> getattributeLennames() {
    List<String> names = new ArrayList<String>();
    names.add(getSvgShapeCodX());
    names.add(getSvgShapeCodY());
    return names;
  }

  /**
   * get the xRadius of a oval.
   *
   * @return the number of xRadius.
   */
  public double getxRadius() {
    return xRadius;
  }

  /**
   * get the yRadius of a oval.
   *
   * @return the number of yRadius.
   */
  public double getyRadius() {
    return yRadius;
  }
}



