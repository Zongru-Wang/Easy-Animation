package cs3500.animator.controller;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.IAnimationReadOnlyModel;
import cs3500.animator.model.shape.AniShape;
import cs3500.animator.view.IHybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// the animation Controllor for interactice view.
// The controller takes in a model and a view, produce that view of that model.
public class AnimationControllor implements IViewController {
  protected IAnimationReadOnlyModel model;
  private IView view;
  private HashMap<String, AniShape> hshapes;
  protected ChechBoxListener chechBoxListener;
  private ButtonListener bl;
  private int k = 1;

  /**
   * Constructor of AnimationController.
   *
   * @param model the model takes in
   * @param view  the view to be ouput.
   */
  public AnimationControllor(IAnimationModel model, IView view) {
    this.model = model;
    this.view = view;
    this.hshapes = model.getShapes();
    configureButtonListener();
    configureCheckBoxListener();


  }


  /**
   * the checkBox listener for interactive view. if the check boxes for the shapes in the animation
   * are unselected, the shape in the animation will disappear.
   */
  private void configureCheckBoxListener() {
    Map<String, Runnable> checkBoxMap = new HashMap<>();
    List<AniShape> shapes = model.getShapeList();

    if (view instanceof IHybridView) {
      for (AniShape s : shapes) {

        checkBoxMap.put(s.getName(), () -> {
          ((IHybridView) view).selectOrUnseletShapes(hshapes.get(s.getName()));
          ((VisualView) view).repaint();
        });

      }

      ChechBoxListener chechBoxListener = new ChechBoxListener(checkBoxMap);
      this.chechBoxListener = chechBoxListener;
      ((IHybridView) view).addItemListener(chechBoxListener);


    }

  }


  /**
   * Creates and sets the button listener for the view. start button to start the animation. pause
   * button to pause the animation. resume button to resume the animation. restart button to restart
   * the animation.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    if (view instanceof IHybridView) {
      buttonMap.put("Start Button", () -> {
        ((IHybridView) view).startAnimation();

      });

      buttonMap.put("Pause Button", () -> {
        ((IHybridView) view).pauseAnimation();
      });

      buttonMap.put("Resume Button", () -> {
        ((IHybridView) view).resumeAnimation();
      });

      buttonMap.put("Restart Button", () -> {
        ((IHybridView) view).restartAnimation();
      });

      buttonMap.put("Speed Up", () -> {
        ((IHybridView) view).speedUP();

        System.out.println("speed up");
      });

      buttonMap.put("Slow Down", () -> {
        ((IHybridView) view).slowDwon();

        System.out.println("Slow down");
      });


      buttonMap.put("Not Loop", () -> {
        ((IHybridView) view).switchLoop();
      });

      buttonMap.put("Export Button", () -> {
        ((IHybridView) view).export();
      });

      buttonMap.put("change color", () ->{
        this.k = this.k + 1;
        if (this.k > 5) {
          this.k = 1;
        }
        ((IHybridView) view).setColor(k);
      });

      buttonListener.setButtonActionsMap(buttonMap);
      ((IHybridView) view).addActionListener(buttonListener);
      this.bl = buttonListener;
    }


  }


  @Override
  public ButtonListener getButtonListener() {
    return this.bl;
  }
}
