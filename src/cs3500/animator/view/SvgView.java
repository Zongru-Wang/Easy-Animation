package cs3500.animator.view;


import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IAnimationReadOnlyModel;
import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.util.Util;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

// the class represent the svg of animation.
public class SvgView implements IView {
  private IAnimationReadOnlyModel model;
  private int rate;
  private String unit;
  protected boolean loopBack;
  protected HybridView hybridView;


  /**
   * to create a svg text.
   *
   * @param model the model we take in.
   * @param rate  the rate of the animation.
   */
  public SvgView(IAnimationModel model, int rate) {
    this.model = model;
    this.rate = rate;
  }

  @Override
  public String viewText(boolean ifloopback, Color color) {

    if (!ifloopback) {
      String svgText = "";
      svgText += svgCanvas();

      svgText += setbackground(color);

      svgText += svgShapeText();
      svgText += "</svg>";
      return svgText;
    } else {

      return viewTextLoopBack(color);
    }


  }


  @Override
  public void setRate(int rate) {
    this.rate = rate;
  }

  @Override
  public IAnimationReadOnlyModel getModel() {
    return this.model;
  }


  @Override
  public int getRate() {
    return this.rate;
  }

  @Override
  public boolean getLoopBack() {
    return this.loopBack;
  }

  /**
   * represent the canvas of an animation.
   *
   * @return the convas of an animation.
   */
  private String svgCanvas() {


    String svgCanvas = "<svg width=\"10000\" height=\"10000\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">" + "\n";

    return svgCanvas;

  }

  /**
   * Set the background of svg by given Color.
   *
   * @param color the color we want our background to be.
   * @return The Color of the background.
   */
  private String setbackground(Color color) {
    String back = "<" + "rect" + " id=" + "\"" + " background" + "\"" + " x="
            + "\"" + "0.0" + "\"" + " y=" + "\"" + "0.0" + "\"" + " width="
            + "\"" + "8000.0" + "\"" + " height=" + "\"" + "8000.0" + "\"" + " fill="
            + "\"" + "rgb" + Util.tranferrbg(color) + "\"" + " visibility="
            + "\"" + " visible" + "\"" + ">" + "\n" + "</rect>" + "\n";

    return back;
  }


  /**
   * change the AniShape into the svg.
   *
   * @return the text represent the svg format of animation.
   */
  private String svgShapeText() {
    String svgshape = "";
    HashMap<String, AniShape> shapes = this.model.getShapes();

    List<AniShape> shapes1 = this.model.getShapeList();


    for (AniShape s : shapes1) {


      AniShape ashape = shapes.get(s.getName());

      svgshape += "<" + ashape.getShape().getSvgShape() + " id=" +
              "\"" + s.getName() + "\"" + " " + ashape.getShape().getSvgShapeCodX()
              + "=" + "\""
              + ashape.getPos().getX() + "\"" + " "
              + ashape.getShape().getSvgShapeCodY() + "=" + "\"" + ashape.getPos().getY()
              + "\"" + " "

              + ashape.getShape().getSvgShapeLenX() + "=" + "\""
              + ashape.getShape().allDimensions()[0] + "\"" +
              " " + ashape.getShape().getSvgShapeLenY()
              + "=" + "\"" +
              ashape.getShape().allDimensions()[1] + "\"" + " fill=" + "\"" + "rgb" +
              Util.tranferrbg(ashape.getColor()) + "\"" + " visibility=\"visible\"" + ">" +

              "\n" +
              "<animate "
              + "attributeType= \"xml\" "
              + "begin=\"base.begin+" + +0 + "ms" + "\" "
              + "dur=" + "\""
              + s.getAppears() / rate * 1000
              + "ms" + "\" " + "attributeName="
              + "\"" + "visibility" + "\""
              + " from="
              + "\"" + "hidden" + "\"" + " to=" + "\"" + "hidden" + "\""
              + "/>\n" +

              "\n" +
              svgAnimationsText(ashape.getName()) +
              "</" + ashape.getShape().getSvgShape() + ">" +
              "\n";

    }
    return svgshape;
  }

  /**
   * represent the animate's svg. not shapes' included.
   *
   * @param shapename the name of the shape.
   * @return the text for svg animate.
   */
  private String svgAnimationsText(String shapename) {

    List<AAnimation> animations = this.model.getAnimations();
    String animText = "";
    for (int i = 0; i < animations.size(); i++) {
      if (animations.get(i).getShape().getName() == shapename) {
        animText += animations.get(i).svgAnimationText(this.rate, this.unit) + "\n";
      }
    }

    return animText;
  }


  // the main loop back method, will output the whole and functional
  // svg file as string. Set as private, since viewText method will take
  // in a boolean determine whether user want the svg to loop back.
  // if they want, viewText will call this method, otherwise, it will output
  // svg without loop back.
  private String svgShapeTextLoopBack() {
    String svgshape = "";
    HashMap<String, AniShape> shapes = this.model.getShapes();


    List<AniShape> shapes1 = this.model.getShapeList();

    for (AniShape s : shapes1) {
      AniShape ashape = shapes.get(s.getName());

      svgshape += "<" + ashape.getShape().getSvgShape() + " id=" +
              "\"" + s.getName() + "\"" + " " + ashape.getShape().getSvgShapeCodX()
              + "=" + "\""
              + ashape.getPos().getX() + "\"" + " "
              + ashape.getShape().getSvgShapeCodY() + "=" + "\"" + ashape.getPos().getY()
              + "\"" + " "

              + ashape.getShape().getSvgShapeLenX() + "=" + "\""
              + ashape.getShape().allDimensions()[0] + "\"" +
              " " + ashape.getShape().getSvgShapeLenY()
              + "=" + "\"" +
              ashape.getShape().allDimensions()[1] + "\"" + " fill=" + "\"" + "rgb" +
              Util.tranferrbg(ashape.getColor()) + "\"" + " visibility=\"visible\"" + ">"

              + "\n" +
              "<animate "
              + "attributeType= \"xml\" "
              + "begin=\"base.begin+" + 0 + "ms" + "\" "
              + "dur=" + "\""
              + s.getAppears() / rate * 1000
              + "ms" + "\" " + "attributeName="
              + "\"" + "visibility" + "\""
              + " from="
              + "\"" + "hidden" + "\"" + " to=" + "\"" + "visible" + "\""
              + "/>\n" +

              "\n" +
              "<animate "
              + "attributeType= \"xml\" "
              + "begin=\"base.begin+" + s.getAppears() / rate * 1000 + "ms" + "\" "
              + "dur=" + "\""
              + s.getDisappears() / rate * 1000
              + "ms" + "\" " + "attributeName="
              + "\"" + "visibility" + "\""
              + " from="
              + "\"" + "visible" + "\"" + " to=" + "\"" + "visible" + "\""
              + "/>\n" +


              svgAnimationsTextLoopback(ashape.getName()) +
              "</" + ashape.getShape().getSvgShape() + ">" +
              "\n";

    }
    return svgshape;
  }

  /**
   * the loop back svg out put for animate label.
   *
   * @param shapename the name of the shape.
   * @return the String in svg type for animate label.
   */
  private String svgAnimationsTextLoopback(String shapename) {
    List<AAnimation> animations = this.model.getAnimations();
    String animText = "";

    for (int i = 0; i < animations.size(); ++i) {
      if (((AAnimation) animations.get(i)).getShape().getName() == shapename) {
        animText += ((AAnimation)
                animations.get(i)).svgAnimationTextloopback(this.rate, this.unit) + "\n";
      }
    }

    return animText;
  }

  /**
   * the method for loopback svg text outsput.
   *
   * @return the String in svg file type for the loopback view.
   */
  private String viewTextLoopBack(Color color) {
    String svgText = "";
    svgText = svgText + this.svgCanvas();
    svgText = svgText + setbackground(color);
    svgText = svgText + "<rect>\n";
    svgText = svgText + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" +
            this.model.getmaxEndtime(this.rate).doubleValue() / (double) this.rate * 1000.0D +
            "ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n</rect>\n";
    svgText = svgText + this.svgShapeTextLoopBack();
    svgText = svgText + "</svg>";
    return svgText;
  }

}
