package cs3500.animator.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.IAnimationReadOnlyModel;
import cs3500.animator.model.shape.AniShape;


// Class that display the visual view for the animation.
public class VisualView extends JFrame implements IView {

  protected IAnimationReadOnlyModel<AniShape, AAnimation> model;
  protected ShapePanel shapePanel;
  private JScrollPane scrollPane;
  protected int rate;
  protected Timer timer;
  protected boolean loopBack;

  protected HashMap<String, AniShape> visibleShapes;

  /**
   * Constructor for Visual View.
   *
   * @param model model takes in to produce animation
   */
  public VisualView(IAnimationReadOnlyModel<AniShape, AAnimation> model, int rate) {
    super();

    this.model = model;
    this.loopBack = true;
    this.visibleShapes = model.getShapes();
    this.setTitle("Shape Animation");
    this.setSize(1200, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    timer = new Timer(1000 / rate, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        shapePanel.advance();
        scrollPane.repaint();
      }
    });


    shapePanel = new ShapePanel(this.model);
    scrollPane = new JScrollPane(shapePanel);
    this.add(scrollPane, BorderLayout.CENTER);
    this.setBackground(Color.blue);
    timer.start();
    this.repaint();
    this.setVisible(true);
  }


  @Override
  public int getRate() {
    return this.rate;
  }

  @Override
  public boolean getLoopBack() {
    return loopBack;
  }


  @Override
  public String viewText(boolean ifloopback, Color color) {
    throw new UnsupportedOperationException("this method not supported in this view.");
  }


  @Override
  public void setRate(int rate) {
    this.rate = rate;
  }

  @Override
  public IAnimationReadOnlyModel getModel() {
    return this.model;
  }


  /**
   * Set the state of the animation.
   *
   * @param state true or false
   */
  public void setState(boolean state) {
    if (state = true) {
      timer.start();
    }
    if (state = false) {
      timer.stop();
    }
  }


  // Class that represents Shape Panel
  public class ShapePanel extends JPanel {
    private IAnimationReadOnlyModel<AniShape, AAnimation> model;
    private int time;
    List<List<AniShape>> shapeList;
    int endTime;
    Color color;

    /**
     * Constructor of ShapePanel.
     *
     * @param model Takes in an animation model
     */
    public ShapePanel(IAnimationReadOnlyModel<AniShape, AAnimation> model) {
      this.model = model;
      this.time = 0;
      this.setBackground(color);
      this.setPreferredSize((new Dimension(1200, 500)));

      this.endTime = model.endTime();
      this.shapeList = model.getAllTickShapesList();
    }


    public void advance() {
      this.time++;
      //model.skipTo(time);
    }

    public void reStart() {
      this.time = 0;
      timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (loopBack && time > endTime) {
        this.reStart();
        return;
      } else if (time > endTime) {
        timer.stop();
        return;
      }

      for (AniShape s : shapeList.get(time)) {

        if (visibleShapes.containsKey(s.getName())) {
          if (time >= s.getAppears() && time <= s.getDisappears()) {
            g.setColor(s.getColor());
            String shape = s.getShape().getType();
            if (shape.equals("rectangle")) {
              g.fillRect((int) s.getPos().getX(), (int) s.getPos().getY(),
                      (int) s.allDimensions()[0], (int) s.allDimensions()[1]);
            } else if (shape.equals("oval")) {
              g.fillOval((int) (s.getPos().getX() - 0.5 * s.allDimensions()[0]),
                      (int) (s.getPos().getY() + 0.5 * s.allDimensions()[1]),
                      (int) s.allDimensions()[0], (int) s.allDimensions()[1]);
            } else {
              System.out.println(s.getShape().getType() + "not in the model");
            }
          }
        }
      }
    }
  }
}


