package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.aniamtion.AnimationColor;
import cs3500.animator.model.aniamtion.Move;
import cs3500.animator.model.aniamtion.Scale;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Pos2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.util.TweenModelBuilder;

// Implementation of the AbstractAnimation Model.
public final class AnimationModel implements IAnimationModel<AniShape, AAnimation> {

  private HashMap<String, AniShape> shapes;
  private List<AAnimation> animations;
  private HashMap<String, AniShape> originalShapes;
  private List<AniShape> shapeList;

  /**
   * initialize in the constructor.
   */
  private AnimationModel() {
    shapes = new HashMap<>();
    animations = new ArrayList<>();
    this.originalShapes = new HashMap<>();
    this.shapeList = new ArrayList<>();
  }

  /**
   * the Bulider pattern.
   *
   * @return the bulider.
   */
  public static Builder builder() {
    return new Builder();
  }

  // the Bulider pattern.
  public static final class Builder implements TweenModelBuilder<IAnimationModel> {
    IAnimationModel model = new AnimationModel();

    @Override
    public TweenModelBuilder<IAnimationModel> addOval(String name, float cx, float cy,
                                                      float xRadius, float yRadius,
                                                      float red, float green, float blue,
                                                      int startOfLife, int endOfLife) {
      this.model.addAnimShape(new AniShape(name, new Color(red, green, blue), new Pos2D(cx, cy),
              startOfLife, endOfLife, new Oval(xRadius, yRadius)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addRectangle(String name, float lx, float ly,
                                                           float width, float height,
                                                           float red, float green, float blue,
                                                           int startOfLife, int endOfLife) {
      this.model.addAnimShape(new AniShape(name, new Color(red, green, blue), new Pos2D(lx, ly),
              startOfLife, endOfLife, new Rectangle(width, height)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addMove(String name,
                                                      float moveFromX, float moveFromY,
                                                      float moveToX, float moveToY,
                                                      int startTime, int endTime) {
      this.model.addAnimation(name, new Move(startTime, endTime,
              new Pos2D(moveFromX, moveFromY), new Pos2D(moveToX, moveToY)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addColorChange(String name,
                                                             float oldR, float oldG, float oldB,
                                                             float newR, float newG, float newB,
                                                             int startTime, int endTime) {
      this.model.addAnimation(name, new AnimationColor(startTime, endTime,
              new Color(oldR, oldG, oldB), new Color(newR, newG, newB)));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimationModel> addScaleToChange(String name,
                                                               float fromSx, float fromSy,
                                                               float toSx, float toSy,
                                                               int startTime, int endTime) {
      this.model.addAnimation(name, new Scale(startTime, endTime,
              new double[]{fromSx, fromSy}, new double[]{toSx, toSy}));
      return this;
    }

    @Override
    public IAnimationModel build() {
      return this.model;
    }
  }

  @Override
  public String toText() {
    return toText(1, "");
  }

  @Override
  public String toText(int rate, String unit) {
    return objectsText(rate, unit) + "\n" + animationsText(rate, unit);
  }

  /**
   * Gives a text representation of all of the animations when start.
   *
   * @param rate number of ticks per given unit of time
   * @param unit shorthand form of unit of time
   * @return text representation of all of the animations.
   */
  private String animationsText(int rate, String unit) {
    Collections.sort(animations);
    String ans = "";
    for (AAnimation a : animations) {
      ans += a.toString(rate, unit) + "\n";
    }
    // takes off the final newline if the answer isn't empty
    if (ans.length() > 0) {
      ans = ans.substring(0, ans.length() - 1);
    }
    return ans;
  }

  /**
   * Gives a text representation of all of the AnimationObjects when appear.
   *
   * @param rate number of ticks per given unit of time
   * @param unit shorthand form of unit of time
   * @return text representation of all of the AnimationObjects.
   */
  private String objectsText(int rate, String unit) {
    List<AniShape> list = new ArrayList<>(shapes.values());
    Collections.sort(list);
    String anishape = "Shapes:";
    for (AniShape a : list) {
      anishape += "\n";
      anishape += a.toString(rate, unit);
      anishape += "\n";
    }
    return anishape;
  }

  @Override
  public void addAnimShape(String name, AniShape animObject)
          throws IllegalArgumentException {
    if (!name.equals(animObject.getName())) {
      throw new IllegalArgumentException("Names are not consistent.");
    }
    this.shapes.put(name, animObject);
    this.originalShapes.put(name, new AniShape(animObject));
    this.shapeList.add(animObject);
  }

  @Override
  public void addAnimShape(AniShape animShape) {
    this.addAnimShape(animShape.getName(), animShape);
  }

  @Override
  public void addAnimation(String name, AAnimation animation)
          throws IllegalArgumentException {
    if (!this.shapes.containsKey(name)) {
      throw new IllegalArgumentException("AbstractAnimation object of given name not found.");
    } else {
      animation.setShape(shapes.get(name));
      if (animation.conflicts(this.animations)) {
        throw new IllegalArgumentException("This animation conflicts with another one.");
      }
      this.animations.add(animation);
    }
  }

  @Override
  public void skipTo(int time) {
    for (AAnimation a : this.animations) {
      a.apply(time);
    }
  }

  @Override
  public List<AAnimation> getAnimations() {
    List<AAnimation> animations = new ArrayList<>(this.animations);
    return animations;
  }

  @Override
  public HashMap<String, AniShape> getShapes() {
    HashMap<String, AniShape> shapes = new HashMap<>();

    for (AniShape a : shapeList) {
      shapes.put(a.getName(), new AniShape(a));
    }
    return shapes;
  }

  @Override
  public List<AniShape> getShapeList() {

    List<AniShape> toRect = new ArrayList<>();
    for (AniShape a : shapeList) {
      toRect.add(new AniShape(a));
    }
    return toRect;
  }

  @Override
  public List<AniShape> getShapeList(int tick) {
    this.skipTo(tick);
    List<AniShape> torect = getShapeList();

    this.rewind();
    return torect;
  }


  @Override
  public List<AAnimation> getAniList() {
    return new ArrayList<>(this.animations);
  }

  @Override
  public int endTime() {
    int endTime = 0;
    for (String name : shapes.keySet()) {
      endTime = Math.max(endTime, shapes.get(name).getDisappears());
    }
    for (AAnimation a : animations) {
      endTime = Math.max(endTime, a.getEndime());
    }

    return endTime;
  }

  @Override
  public void rewind() {
    for (String name : this.shapes.keySet()) {
      this.shapes.get(name).changeTo(originalShapes.get(name));
    }
  }

  @Override
  public List<List<AniShape>> getAllTickShapesList() {
    this.rewind();
    List<List<AniShape>> los = new ArrayList<>();
    for (int i = 0; i <= this.endTime(); i++) {
      this.skipTo(i);
      los.add(this.getShapeList());
    }
    this.rewind();
    return los;
  }


  @Override
  public Double getmaxEndtime(int rate) {
    List<AniShape> aAnimationlist = new ArrayList();
    List<Double> endtinelist = new ArrayList();
    Iterator var4 = this.animations.iterator();

    while (var4.hasNext()) {
      AAnimation a = (AAnimation) var4.next();
      aAnimationlist.add(a.getShape());
    }

    var4 = aAnimationlist.iterator();

    while (var4.hasNext()) {
      AniShape s = (AniShape) var4.next();
      endtinelist.add((double) s.getDisappears());
    }

    return (Double) Collections.max(endtinelist);
  }
}
