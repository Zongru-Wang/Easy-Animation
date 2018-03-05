package cs3500.animator.model;


//Creates animation models.
public class AnimationModelCreator {
  public static IAnimationModel create() {
    return AnimationModel.builder().build();
  }
}
