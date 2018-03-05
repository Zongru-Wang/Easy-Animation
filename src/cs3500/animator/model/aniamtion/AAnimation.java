package cs3500.animator.model.aniamtion;

import java.util.List;

import cs3500.animator.model.shape.AniShape;

// the abstract class which respresent the objects in animation.
public abstract class AAnimation implements Comparable<AAnimation> {
  protected final int startime;
  protected final int endtime;
  protected AniShape shape;

  /**
   * Creates an Aniamtion.
   *
   * @param startime the time when the animation starts.
   * @param endtime  the time when the animation ends.
   */
  public AAnimation(int startime, int endtime) {
    if (startime < 0 || endtime < 0) {
      throw new IllegalArgumentException("Timeline can't be less than 0!");
    }

    this.startime = startime;
    this.endtime = endtime;
  }


  /**
   * Gets the starttime.
   *
   * @return the time for animation to start.
   */
  public int getStartime() {
    return startime;
  }


  /**
   * Gets the endtime.
   *
   * @return the time for animation to end.
   */
  public int getEndime() {
    return endtime;
  }

  /**
   * Give a shape to an animation if this shape in the animation is null.
   */
  public void setShape(AniShape shape) {
    if (this.shape == null) {
      this.shape = shape;
    }
  }

  @Override
  public String toString() {
    return toString(1, "");
  }

  /**
   * Returns the String form of the animation given a unit of time and the rate of ticks per unit.
   *
   * @param rate the number of ticks in one of the given unit of time
   * @param unit the shorthand form of the unit (e.g. 's' for seconds)
   * @return the String form of the animation
   */
  public String toString(int rate, String unit) {
    String ans = "Shape " + this.shape.getName() + " " + this.changeText();
    String startText;
    String endText;
    if (rate == 1) {
      startText = this.startime + unit;
      endText = this.endtime + unit;
    } else {
      startText = this.startime * (double) 1 / rate + unit;
      endText = this.endtime * (double) 1 / rate + unit;
    }
    ans += " from t=" + startText + " to t=" + endText;
    return ans;
  }

  /**
   * Returns a String that only shows the changes, not the names or times.
   *
   * @return a String showing changes.
   */
  public abstract String changeText();

  /**
   * Applies the animation changes to the animShape instantly.
   */
  public abstract void apply();

  /**
   * Applies the animation changes to the animShape based on the current time given Example: If
   * animation starts at t=0, ends at t=10, and the given time is 5, the animation is applied such
   * that it is halfway done.
   *
   * @param time the current time of the animation model
   */
  public abstract void apply(int time);


  /**
   * Returns the current value of the given measurement based on time, start value, and end value.
   *
   * @param t the current time
   * @param a the starting value of the measurement
   * @param b the ending value of the measurement
   * @return the current value of the measurement
   */
  protected double curVal(int t, double a, double b) {
    return a + (b - a) * ((double) (t - startime)) / (endtime - startime);
  }

  /**
   * An AbstractAnimation is less than another if it begins at an earlier time. This will be used
   * when running the animations.
   *
   * @param o AbstractAnimation to compare this to.
   * @return Negative if this is less, Positive if this is more, 0 otherwise.
   */
  @Override
  public int compareTo(AAnimation o) {
    return this.startime - o.startime;
  }

  /**
   * Checks if this animation conflicts with the given animation. Two animations conflict with each
   * other if and only if: 1. They operate on the same object. 2. They are different types of
   * animations. 3. One starts while the other is still going.
   *
   * @param a the animation to check with this for conflicts.
   * @return true if they conflict, false if they do not
   */
  private boolean conflicts(AAnimation a) {
    if (this.shape.getName().equals(a.shape.getName()) && a.getClass().isInstance(this)) {
      if ((this.startime >= a.startime && this.startime <= a.endtime) ||
              (a.startime >= this.startime && a.startime <= this.endtime)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if this animation conflicts with any animation in the given list of animations.
   *
   * @param as the list of animations to check
   * @return true if this conflicts with at least one animation in the list
   */
  public boolean conflicts(List<AAnimation> as) {
    for (AAnimation a : as) {
      if (this.conflicts(a)) {
        return true;
      }
    }
    return false;
  }

  /**
   * return the the animation shape in the animation.
   *
   * @return the the animation shape in the animation.
   */
  public AniShape getShape() {
    AniShape shape = this.shape;
    return shape;
  }


  /**
   * Count how many attributes for svg.
   *
   * @return the number of attributes.
   */
  public abstract int countattributions();

  /**
   * Get the name of attributes.
   *
   * @return the String represent attributes.
   */
  public abstract String getattributename(int index);

  /**
   * Get the attribute value of "from".
   *
   * @return the String represent the beginning value of an attribute.
   */
  public abstract String attributeValueFrom(int index);

  /**
   * Get the attribute value of "to".
   *
   * @return the String represent the dest value of an attribute.
   */
  public abstract String attributeValueTo(int index);


  /**
   * Return the svg text form for the animation.
   *
   * @return the svg text form for the animation.
   */
  public String svgAnimationText(int rate, String unit) {
    unit = "ms";
    String svgtext = "";
    for (int i = 0; i < this.countattributions(); i++) {

      svgtext += "<animate ";
      svgtext += "attributeType= \"xml\" ";
      svgtext += "begin=" + "\"" + (this.startime / (double) rate) * 1000 + unit + "\" "
              + "dur=" + "\""
              + ((this.endtime / (double) rate) - (this.startime / (double) rate)) * 1000
              + unit + "\" " + "attributeName="
              + getattributename(i)
              + " from="
              + attributeValueFrom(i) + " to=" + attributeValueTo(i)
              + " fill=" + "\"freeze\" />\n";

    }
    return svgtext;
  }


  /**
   * Loop back the svg.
   *
   * @param rate rate in integer
   * @param unit unit as string
   * @return string of new svg
   */
  public String svgAnimationTextloopback(int rate, String unit) {
    unit = "ms";
    String svgtext = "";

    for (int i = 0; i < this.countattributions(); ++i) {


      svgtext = svgtext + "<animate ";
      svgtext = svgtext + "attributeType= \"xml\" ";
      svgtext = svgtext + "begin=\"base.begin+"
              + (double) this.startime / (double) rate * 1000.0D
              + unit + "\" dur=\""
              + ((double) this.endtime / (double) rate
              - (double) this.startime / (double) rate) * 1000.0D
              + unit + "\" attributeName=" + this.getattributename(i)
              + " from=" + this.attributeValueFrom(i) + " to=" + this.attributeValueTo(i)
              + " fill=\"freeze\" />\n";
      svgtext = svgtext + "<animate ";
      svgtext = svgtext + "attributeType= \"xml\" ";
      svgtext = svgtext + "begin=\"base.end\" dur=\"1" + unit + "\" attributeName="
              + this.getattributename(i) + " to=" + this.attributeValueFrom(i)
              + " fill=\"freeze\" />\n";

    }

    return svgtext;
  }

  /**
   * return the duration.
   *
   * @return duration
   */
  public Double getDur(int rate) {
    Double dur = ((double) this.endtime / (double) rate -
            (double) this.startime / (double) rate) * 1000.0D;
    return dur;
  }
}

