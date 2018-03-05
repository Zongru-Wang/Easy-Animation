package cs3500.animator.model.shape;

import java.util.Objects;

// represent the position of objects in animations.
// used to be the code in the lecture, now made a new one to prevent duplicate and useless code.
public class Pos2D {

  private final double x;
  private final double y;

  /**
   * represent the position of objects in animations.
   *
   * @param x the x-coordinate.
   * @param y the y-coordinate.
   */
  public Pos2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   *  make a copy.
   * @param other the Pos2D that we want to copy on.
   */
  public Pos2D(Pos2D other) {
    this(other.x, other.y);
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Pos2D)) {
      return false;
    }
    Pos2D o = (Pos2D) other;
    return Math.abs(this.x - o.x) < 0.01 && Math.abs(this.y - o.y) < 0.01;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}


