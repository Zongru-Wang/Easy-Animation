package controller;

import org.junit.Test;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import cs3500.animator.controller.AnimationControllor;
import cs3500.animator.controller.IViewController;
import cs3500.animator.model.AnimationModelCreator;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.aniamtion.AnimationColor;
import cs3500.animator.model.aniamtion.Move;
import cs3500.animator.model.aniamtion.Scale;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Pos2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.AniviewCreator;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IHybridView;
import cs3500.animator.view.IView;

import static org.junit.Assert.assertEquals;

// test for animation controller.
public class AnimationControllorTest {
  private JButton testButton = new JButton("testB");
  private JCheckBox testCheckBox = new JCheckBox("testC");
  IViewController controller;
  IAnimationModel model;
  IView view;

  String testSvg = "<svg width=\"10000\" height=\"10000\" version=\"1.1\" " +
          "xmlns=\"http://www.w3.org/2000/svg\">\n" +
          "<rect>\n" +
          "<animate id=\"base\" begin=\"0;base.end\" dur=\"100000.0ms\" " +
          "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
          "</rect>\n" +
          "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"30.0\" height=\"50.0\" " +
          "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+0ms\" dur=\"1000ms\" " +
          "attributeName=\"visibility\" from=\"hidden\" to=\"visible\"/>\n" +
          "\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+1000ms\" dur=\"100000ms\"" +
          " attributeName=\"visibility\" from=\"visible\" to=\"visible\"/>\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+0.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=" +
          "\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+0.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" " +
          "to=\"200.0\" fill=\"freeze\" />\n" +
          "\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+6000.0ms\" dur=\"64000.0ms\" " +
          "attributeName=\"width\" from=\"30.0\" to=\"25.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"width\"" +
          " to=\"30.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+6000.0ms\" dur=\"64000.0ms\" " +
          "attributeName=\"height\" from=\"50.0\" to=\"100.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" " +
          "attributeName=\"height\"" +
          " to=\"50.0\" fill=\"freeze\" />\n" +
          "\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+70000.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"x\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"x\"" +
          " to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+70000.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"y\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"y\" " +
          "to=\"200.0\" fill=\"freeze\" />\n" +
          "\n" +
          "</rect>\n" +
          "<ellipse id=\"C\" cx=\"300.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" " +
          "fill=\"rgb(0,0,255)\"" +
          " visibility=\"visible\">\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+0ms\" dur=\"6000ms\" " +
          "attributeName=\"visibility\" from=\"hidden\" to=\"visible\"/>\n" +
          "\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+6000ms\" dur=\"100000ms\" " +
          "attributeName=\"visibility\" from=\"visible\" to=\"visible\"/>\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+40000.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"cx\" from=\"300.0\" to=\"500.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cx\" " +
          "to=\"300.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+40000.0ms\" dur=\"30000.0ms\" " +
          "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"cy\" " +
          "to=\"100.0\" fill=\"freeze\" />\n" +
          "\n" +
          "<animate attributeType= \"xml\" begin=\"base.begin+40000.0ms\" dur=\"40000.0ms\" " +
          "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
          "<animate attributeType= \"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" " +
          "to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
          "\n" +
          "</ellipse>\n" +
          "</svg>";

  /**
   * initialize the controller.
   */
  public void init() {
    model = AnimationModelCreator.create();
    IShape rect1 = new Rectangle(30, 50);
    IShape oval1 = new Oval(60, 30);

    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(300, 100), 6, 100, oval1);

    AAnimation move1 = new Move(0, 30, new Pos2D(300, 300));
    AAnimation move2 = new Move(40, 70, new Pos2D(500, 400));
    AAnimation move3 = new Move(70, 100, new Pos2D(200, 200));
    AAnimation col1 = new AnimationColor(40, 80, new Color(0f, 1f, 0f));
    AAnimation scale1 = new Scale(6, 70, 25, 100);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);
    model.addAnimation("R", move1);
    model.addAnimation("C", move2);
    model.addAnimation("C", col1);
    model.addAnimation("R", scale1);
    model.addAnimation("R", move3);

    view = new AniviewCreator(1, model).create("interactive");
    controller = new AnimationControllor(model, (HybridView) view);


  }

  // test all of the action perform event in the given controller.
  @Test
  public void testActionPerformed() {
    init();

    ActionEvent startA = new ActionEvent(testButton, 1, "Start Button");
    ActionEvent pauseA = new ActionEvent(testButton, 1, "Pause Button");
    ActionEvent resumeA = new ActionEvent(testButton, 1, "Resume Button");
    ActionEvent restartA = new ActionEvent(testButton, 1, "Restart Button");
    ActionEvent switchLoop = new ActionEvent(testButton, 1, "Not Loop");
    ActionEvent speedUp = new ActionEvent(testButton, 1, "Speed up");
    ActionEvent slowDown = new ActionEvent(testButton, 1, "Slow down");
    ActionEvent export = new ActionEvent(testButton, 1, "Export Button");

    controller.getButtonListener().actionPerformed(startA);
    controller.getButtonListener().actionPerformed(pauseA);
    controller.getButtonListener().actionPerformed(resumeA);
    controller.getButtonListener().actionPerformed(restartA);
    controller.getButtonListener().actionPerformed(switchLoop);
    controller.getButtonListener().actionPerformed(speedUp);
    controller.getButtonListener().actionPerformed(slowDown);
    controller.getButtonListener().actionPerformed(export);
    assertEquals(testSvg, ((IHybridView) view).viewText(true, Color.white));

  }
}