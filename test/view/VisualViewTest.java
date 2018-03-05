package view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Pos2D;
import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;


// Test for text view and visual view.
public class VisualViewTest {

  // Test it should loop back.
  @Test
  public void testLoopBack() {
    IShape oval1 = new Oval(60, 30);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(300, 100), 6, 100, oval1);
    IAnimationModel<AniShape, AAnimation> model = AnimationModel.builder().build();
    model.addAnimShape("C", animOval1);
    IView visualView = new VisualView(model, 3);
    assertEquals(true, visualView.getLoopBack());
  }

  // Test getRate and setRate.
  @Test
  public void testGetandSetRate() {

    IShape oval1 = new Oval(60, 30);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(300, 100), 6, 100, oval1);
    IAnimationModel<AniShape, AAnimation> model = AnimationModel.builder().build();
    model.addAnimShape("C", animOval1);
    IView visualView = new VisualView(model, 3);

    visualView.setRate(10);
    assertEquals(10, visualView.getRate());
  }

  // Test viewText
  @Test(expected = UnsupportedOperationException.class)
  public void testViewText() {
    IShape oval1 = new Oval(60, 30);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(300, 100), 6, 100, oval1);
    IAnimationModel<AniShape, AAnimation> model = AnimationModel.builder().build();
    model.addAnimShape("C", animOval1);
    IView visualView = new VisualView(model, 3);

    visualView.viewText(true, Color.white);
  }

}

