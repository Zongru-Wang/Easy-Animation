package view;

import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.AnimationModelCreator;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Pos2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IHybridView;
import cs3500.animator.view.SvgView;

import static org.junit.Assert.assertEquals;

// test for hybrid view.
public class HybridViewTest {

  // Test viewText
  @Test(expected = UnsupportedOperationException.class)
  public void testViewText() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);

    SvgView svgV = new SvgView(model, 3);

    IHybridView hybridView = new HybridView(svgV, 3);

    hybridView.viewText(true, Color.white);
  }


  // Test getRate and setRate
  @Test
  public void testGetandSetRate() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);

    SvgView svgV = new SvgView(model, 3);
    IHybridView hybridView = new HybridView(svgV, 3);
    hybridView.setRate(10);
    assertEquals(10, hybridView.getRate());
  }

  // test SpeedUp and SlowDown
  @Test
  public void testSpeedUpandSlowDown() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);

    SvgView svgV = new SvgView(model, 3);
    IHybridView hybridView = new HybridView(svgV, 3);
    hybridView.setRate(10);
    assertEquals(10, hybridView.getRate());
    hybridView.speedUP();
    assertEquals(20, hybridView.getRate());
    hybridView.slowDwon();
    assertEquals(10, hybridView.getRate());
  }

  // test if loop back method works properly.
  @Test
  public void testLoopBack() {
    IAnimationModel model = AnimationModelCreator.create();

    IShape rect1 = new Rectangle(50, 100);
    IShape oval1 = new Oval(60, 30);
    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(500, 100), 6, 100, oval1);

    model.addAnimShape(animRect1);
    model.addAnimShape(animOval1);

    SvgView svgV = new SvgView(model, 3);
    IHybridView hybridView = new HybridView(svgV, 3);
    hybridView.switchLoop();
    assertEquals(false, hybridView.getLoopBack());
    hybridView.switchLoop();
  }


}