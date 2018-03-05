package model;

import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.AnimationModel;
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

import static org.junit.Assert.assertEquals;

public class AnimationModelTest {

  // Test get shapes, get shape lists.
  @Test
  public void testGetShapeLists() {
    IShape rect1 = new Rectangle(30, 50);
    IShape oval1 = new Oval(60, 30);

    AniShape animRect1 = new AniShape("R", new Color(1f, 0f, 0f),
            new Pos2D(200, 200), 1, 100, rect1);
    AniShape animOval1 = new AniShape("C", new Color(0f, 0f, 1f),
            new Pos2D(300, 100), 6, 100, oval1);

    IAnimationModel<AniShape, AAnimation> model = AnimationModel.builder().build();

    model.addAnimShape("R", animRect1);
    model.addAnimShape("C", animOval1);

    assertEquals(2, model.getShapes().size());
    assertEquals(2, model.getShapeList().size());
    assertEquals(2, model.getShapeList(3).size());
  }

  // Test get Animations
  @Test
  public void testGetAnimations() {
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

    IAnimationModel<AniShape, AAnimation> model = AnimationModel.builder().build();

    model.addAnimShape("R", animRect1);
    model.addAnimShape("C", animOval1);
    model.addAnimation("R", move1);
    model.addAnimation("C", move2);
    model.addAnimation("C", col1);
    model.addAnimation("R", scale1);
    model.addAnimation("R", move3);

    assertEquals(5, model.getAnimations().size());
  }


}