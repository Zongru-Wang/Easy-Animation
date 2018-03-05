package cs3500.animator.model.aniamtion;

import cs3500.animator.model.util.Util;
import cs3500.animator.model.shape.AniShape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// the class represent the color of animation.
//used to be a field in model, now it becomes a class.
public class AnimationColor extends AAnimation {
  private Color startColor;
  private Color endColor;

  /**
   * give a endColor.
   *
   * @param start    the time when the color starts to appear.
   * @param end      the time when the color stop change.
   * @param endColor the color in the end.
   */
  public AnimationColor(int start, int end, Color endColor) {
    super(start, end);
    this.endColor = endColor;
  }

  /**
   * Give a start color.
   *
   * @param start      the time when the color starts to appear.
   * @param end        the time when the color stop change.
   * @param startColor the color at beginning.
   * @param endColor   the color in the end.
   */
  public AnimationColor(int start, int end, Color startColor, Color endColor) {
    this(start, end, endColor);
    this.startColor = startColor;
  }


  /**
   * Set up the shape's color.
   *
   * @param shape the shape that we want to make change on.
   */
  public void setShape(AniShape shape) {
    super.setShape(shape);
    if (startColor == null) {
      startColor = this.shape.getColor();
    }
  }

  @Override
  public String changeText() {
    return "Changes color from " + Util.colString(this.shape.getColor())
            +
            " to " + Util.colString(this.endColor);
  }

  @Override
  public void apply() {
    this.shape.changeColor(this.endColor);

  }

  @Override
  public void apply(int time) {
    if (time > startime && time <= endtime) {
      float[] startCom = this.startColor.getColorComponents(null);
      float[] endCom = this.endColor.getColorComponents(null);
      float r = (float) this.curVal(time, startCom[0], endCom[0]);
      float g = (float) this.curVal(time, startCom[1], endCom[1]);
      float b = (float) this.curVal(time, startCom[2], endCom[2]);
      this.shape.changeColor(new Color(r, g, b));
    }

  }


  @Override
  public int countattributions() {
    return 1;
  }

  @Override
  public String getattributename(int index) {
    return "\"" + "fill" + "\"";
  }

  @Override
  public String attributeValueFrom(int index) {
    List<String> attributevaluesfrom = new ArrayList<>();
    attributevaluesfrom.add(Util.tranferrbg(startColor));
    return "\"" + "rgb" + attributevaluesfrom.get(index) + "\"";
  }

  @Override
  public String attributeValueTo(int index) {
    List<String> attributevaluesto = new ArrayList<>();
    attributevaluesto.add(Util.tranferrbg(endColor));

    return "\"" + "rgb" + attributevaluesto.get(index) + "\"";
  }


  ////////////////////////////new added///////////
  public Color getStartColor() {
    return startColor;
  }

  public Color getEndColor() {
    return endColor;
  }
}
