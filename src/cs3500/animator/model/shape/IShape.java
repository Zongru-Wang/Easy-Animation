package cs3500.animator.model.shape;


import java.util.List;

//Represents shapes (only the shape - anything to do with the animation is in AnimShape).
//add this interface to just represent a shape, rather than a animation objects.
public interface IShape {

  /**
   * Gets a description of what position this shape would be mapped from. Examples: center, top
   * right corner, bottom center, etc.
   *
   * @return description of significance of position
   */
  String posDesc();

  /**
   * Gets a String representing the type of shape it is (rectangle, oval, etc.).
   *
   * @return the String representing the shape.
   */
  String getType();


  /**
   * Gets the dimensions of this IShape as a String separated by commas.
   *
   * @return String representation of dimensions
   */
  String dimensions();

  /**
   * Returns a new copy of this IShape.
   *
   * @return copy of this IShape
   */
  IShape copy();

  /**
   * Changes this IShape's dimensions to the new ones.
   *
   * @param measurements the data we want to change on any animation.
   */
  void scale(double... measurements);

  /**
   * Returns all of this IShape's dimensions.
   *
   * @return the dimensions of this ISHape
   */
  double[] allDimensions();

  /**
   * Returns a svg shape format shape name.
   *
   * @return the svg shape format shape name.
   */
  String getSvgShape() throws IllegalArgumentException;

  /**
   *  Represent the length of shape.
   * @return the String represrnt the length of shape.
   */
  String getSvgShapeLenX();

  /**
   *  Represent the length of shape.
   * @return the String represrnt the length of shape.
   */
  String getSvgShapeLenY();

  /**
   * represent the x-coordinate of a shape.
   * @return the x-coordinate of a shape.
   */
  String getSvgShapeCodX();

  /**
   * represent the x-coordinate of a shape.
   * @return the x-coordinate of a shape.
   */
  String getSvgShapeCodY();

  /**
   * A list contains all lenNames of a shape.
   * Such as withd, height.
   *
   * @return a List contains all Len names of a shape.
   */
  abstract List<String> getattributeLennames();


}
