package cs3500.animator.model.aniamtion;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.Pos2D;

// the class represent Moving an Anishape.
//used to be a medthod on model, changed it into a class.
public class Move extends AAnimation {
  private Pos2D dest;
  private Pos2D start;

  /**
   * Move an animation given a destniton.
   *
   * @param startime the time when the animation starts.
   * @param endtime  the time when the animation ends.
   * @param dest     the destination we want the animation object to get
   */
  public Move(int startime, int endtime, Pos2D dest) {
    super(startime, endtime);
    this.dest = dest;
  }


  /**
   * Move an animation given a destniton and start position.
   *
   * @param startime the time when the animation starts.
   * @param endtime  the time when the animation ends.
   * @param start    the start position for moving.
   * @param dest     the destination we want the animation object to get
   */
  public Move(int startime, int endtime, Pos2D start, Pos2D dest) {
    this(startime, endtime, dest);
    this.start = start;
  }

  @Override
  public String changeText() {
    return "Moves from " + this.start.toString() + " to " + this.dest.toString();
  }

  @Override
  public void apply() {

    this.shape.move(this.dest);
  }

  @Override
  public void apply(int time) {
    if (time <= this.endtime && time > this.startime) {
      double newX = this.curVal(time, start.getX(), dest.getX());
      double newY = this.curVal(time, start.getY(), dest.getY());
      this.shape.move(new Pos2D(newX, newY));
    }

  }


  @Override
  public void setShape(AniShape shape) {
    super.setShape(shape);
    if (start == null) {
      start = this.shape.getPos();
    }
  }


  @Override
  public int countattributions() {
    return 2;
  }

  @Override
  public String getattributename(int index) {
    List<String> attributenames = new ArrayList<>();
    attributenames.add(shape.getShape().getSvgShapeCodX());
    attributenames.add(shape.getShape().getSvgShapeCodY());

    return "\"" + attributenames.get(index) + "\"";
  }

  @Override
  public String attributeValueFrom(int index) {
    List<Double> attributevaluesfrom = new ArrayList<>();
    attributevaluesfrom.add(start.getX());
    attributevaluesfrom.add(start.getY());
    return "\"" + attributevaluesfrom.get(index) + "\"";
  }

  @Override
  public String attributeValueTo(int index) {
    List<Double> attributevaluesto = new ArrayList<>();
    attributevaluesto.add(dest.getX());
    attributevaluesto.add(dest.getY());

    return "\"" + attributevaluesto.get(index) + "\"";
  }


}
