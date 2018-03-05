package cs3500.animator.model.shape;

import java.awt.Color;

import cs3500.animator.model.util.Util;


// the class represent a shape in the animation.
// used to be similar to IShape, now it becomes a class contains IShape.
public class AniShape implements Comparable<AniShape> {

  private final String name;
  private Color color;
  private Pos2D pos;
  private final int appears;
  private final int disappears;
  private final IShape shape;


  /**
   * Creates a new AnimShape with the given value.
   *
   * @param name       unique name of the AnimShape. Must match the one given when adding to model.
   * @param color      color of this AnimShape.
   * @param pos        (x,y) position of this AnimShape.
   * @param appears    the time this object appears in the animation.
   * @param disappears the time this object disappears in the animation.
   * @param shape      the shape of this object.
   */
  public AniShape(String name, Color color, Pos2D pos, int appears
          , int disappears, IShape shape) {

    if (appears < 0 || disappears < 0 || appears > disappears) {
      throw new IllegalArgumentException("Appears and Disappears time are not valid!");
    }

    this.name = name;
    this.color = color;
    this.pos = pos;
    this.appears = appears;
    this.disappears = disappears;
    this.shape = shape;
  }

  /**
   * Constructor for copy opeartion.
   *
   * @param other the shape we want to copy
   */
  public AniShape(AniShape other) {
    this.name = other.name;
    this.color = other.color;
    this.pos = other.pos;
    this.disappears = other.disappears;
    this.appears = other.appears;
    this.shape = other.shape.copy();

  }


  /**
   * Comparison function. An animation object is less than another if it appears earlier.
   *
   * @param o AbstractAnimation object to compare to
   * @return negative if this is less, positive if this is more, 0 otherwise.
   */
  @Override
  public int compareTo(AniShape o) {
    return this.appears - o.disappears;
  }


  /**
   * Gets the name of this animation object.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the position of this animation object.
   *
   * @return the position.
   */
  public Pos2D getPos() {
    return new Pos2D(this.pos);
  }


  /**
   * Gets the color of this animation object. Note: Color is immutable according to the library.//
   * told by others, do a lot help.
   *
   * @return the color.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets a copy of the shape of this animation object.
   *
   * @return the shape
   */
  public IShape getShape() {
    return this.shape.copy();
  }


  /**
   * Changes the color of this AnimShape to the given Color.
   *
   * @param color the color to change this to
   */
  public void changeColor(Color color) {
    this.color = color;
  }

  /**
   * Gets all of the dimensions of this shape.
   *
   * @return the dimensions
   */
  public double[] allDimensions() {
    return this.shape.allDimensions();
  }

  /**
   * Moves this AnimShape to the given Pos.
   *
   * @param dest destination position
   */
  public void move(Pos2D dest) {
    this.pos = dest;
  }

  /**
   * Scales this AnimShape to the given measurements.
   *
   * @param measurements new measurements to scale to - order of measurements depends on shape
   */
  public void scale(double... measurements) {
    this.shape.scale(measurements);
  }


  /**
   * represent animation state as String.
   *
   * @param rate the rate of animation will play.
   * @param unit the time unit of animation.
   */
  public String toString(int rate, String unit) {
    if (rate < 1) {
      throw new IllegalArgumentException("Rate cannot be less than one.");
    }
    String ans = "";
    ans += "Name: " + this.name + "\n";
    ans += "Type: " + this.shape.getType() + "\n";
    ans += this.shape.posDesc() + ": " + this.pos.toString();
    ans += ", " + this.shape.dimensions() + ", Color: " + Util.colString(this.color) + "\n";
    String appearsText;
    String disappearsText;
    // the following code is for getting rid of the decimal point if no decimals are necessary
    if (rate == 1) {
      appearsText = this.appears + unit;
      disappearsText = this.disappears + unit;
    } else {
      appearsText = this.appears * (double) 1 / rate + unit;
      disappearsText = this.disappears * (double) 1 / rate + unit;
    }
    ans += "Appears at t=" + appearsText + "\n";
    ans += "Disappears at t=" + disappearsText;
    return ans;
  }

  /**
   * Get the appear time of the object.
   *
   * @return the time when shape appears.
   */
  public int getAppears() {
    return this.appears;
  }

  /**
   * Get the time that the object vanish.
   *
   * @return the time when shape disappears.
   */
  public int getDisappears() {
    return this.disappears;
  }


  /**
   * Takes in an AniShape and change the current shape to that shape.
   *
   * @param other the AniShape takes in
   */
  public void changeTo(AniShape other) {
    this.move(other.pos);
    this.changeColor(other.color);
    this.scale(other.shape.allDimensions());
  }


}

