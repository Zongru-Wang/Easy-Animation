package view;

import org.junit.Test;

import java.awt.Color;

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
import cs3500.animator.view.IView;

import static org.junit.Assert.assertEquals;

// Test for text view and svg view
public class TextAndSVGTest {
  String view1String = "Shapes:\n" +
          "Name: C\n" +
          "Type: oval\n" +
          "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n" +
          "Appears at t=3.0s\n" +
          "Disappears at t=50.0s\n" +
          "\n" +
          "Name: R\n" +
          "Type: rectangle\n" +
          "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
          "Appears at t=0.5s\n" +
          "Disappears at t=50.0s\n" +
          "\n" +
          "Shape R Moves from (200.0,200.0) to (300.0,300.0) from t=5.0s to t=25.0s\n" +
          "Shape C Moves from (500.0,100.0) to (500.0,400.0) from t=10.0s to t=35.0s\n" +
          "Shape C Changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=25.0s to t=40.0s\n" +
          "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
          "Height: 100.0 from t=25.5s to t=35.0s\n" +
          "Shape R Moves from (300.0,300.0) to (200.0,200.0) from t=35.0s to t=50.0s";

  @Test
  public void testViewText() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);

    AAnimation move1 = new Move(10, 50, new Pos2D(300, 300));
    AAnimation move2 = new Move(20, 70, new Pos2D(500, 400));
    AAnimation move3 = new Move(70, 100, new Pos2D(300, 300),
            new Pos2D(200, 200));
    AAnimation col1 = new AnimationColor(50, 80, new Color(0f, 1f, 0f));
    AAnimation scale1 = new Scale(51, 70, 25, 100);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);
    model.addAnimation("R", move1);
    model.addAnimation("C", move2);
    model.addAnimation("C", col1);
    model.addAnimation("R", scale1);
    model.addAnimation("R", move3);

    AniviewCreator creator = new AniviewCreator(2, model);
    IView textView = creator.create("text");
    assertEquals(view1String, textView.viewText(false, Color.white));
  }

  @Test
  public void testViewText3() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);


    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);
    AniviewCreator creator = new AniviewCreator(2, model);
    IView textView = creator.create("svg");
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"visible\">\n" +
            "</rect>\n" +
            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"visible\">\n" +
            "</ellipse>\n" +
            "</svg>", textView.viewText(false, Color.white));

  }

  @Test
  public void testViewText4() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 1, 15, oval1);


    AAnimation move1 = new Move(2, 5, new Pos2D(500, 400));
    AAnimation move2 = new Move(2, 10, new Pos2D(200, 400));
    AAnimation move3 = new Move(6, 11, new Pos2D(300, 300),
            new Pos2D(200, 200));
    AAnimation col1 = new AnimationColor(3, 9, new Color(0f, 1f, 0f));
    AAnimation scale1 = new Scale(4, 9, 25, 80);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);
    model.addAnimation("R", move1);
    model.addAnimation("C", move2);
    model.addAnimation("C", col1);
    model.addAnimation("R", scale1);
    model.addAnimation("R", move3);
    AniviewCreator creator = new AniviewCreator(2, model);
    IView textView = creator.create("svg");
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n" +
            "<animate attributeType= \"xml\" begin=\"1000.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"x\" from=\"200.0\" to=\"500.0\" fill=\"freeze\" />\n" +
            "<animate attributeType= \"xml\" begin=\"1000.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"y\" from=\"200.0\" to=\"400.0\" fill=\"freeze\" />\n" +
            "\n" +
            "<animate attributeType= \"xml\" begin=\"2000.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
            "<animate attributeType= \"xml\" begin=\"2000.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"height\" from=\"100.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "\n" +
            "<animate attributeType= \"xml\" begin=\"3000.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "<animate attributeType= \"xml\" begin=\"3000.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "\n" +
            "</rect>\n" +
            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" " +
            "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n" +
            "<animate attributeType= \"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" " +
            "attributeName=\"cx\" from=\"500.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "<animate attributeType= \"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" " +
            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
            "\n" +
            "<animate attributeType= \"xml\" begin=\"1500.0ms\" dur=\"3000.0ms\" " +
            "attributeName=\"fill\" from=\"(0,0,255)\" to=\"(0,255,0)\" fill=\"freeze\" />\n" +
            "\n" +
            "</ellipse>\n" +
            "</svg>", textView.viewText(true, Color.white));

  }

}
