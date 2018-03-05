package cs3500.animator.view;


import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import cs3500.animator.model.shape.AniShape;


//A hybrid of svgView and VisualView, which implements IHybridView.
//Hybrid view extends the functionality of visual view, and new button panel and
//check box panel is added.
public class HybridView extends VisualView implements IHybridView {
  SvgView svgV;
  int rate;

  protected List<AniShape> shapeList;
  protected JPanel buttonPanel;
  protected JPanel exportPanel;
  protected JButton start;
  protected JButton pause;
  protected JButton resume;
  protected JButton restart;
  protected JButton notLoopBack;
  protected JButton export;
  protected JButton speedUp;
  protected JButton slowDown;
  protected JButton changeColor;
  protected JPanel checkBoxPanel;
  protected JLabel checkBoxDisplay;
  protected JCheckBox[] checkBox;
  protected JScrollPane checkBoxScrollPane;
  protected JLabel exportInfo;
  private JTextField exportInput;

  /**
   * Constructor of HybridView. added functional button AnishapeView and exportTextfield.
   */
  public HybridView(SvgView svgV, int rate) {
    super(svgV.getModel(), rate);
    this.setVisible(false);
    this.shapeList = svgV.getModel().getShapeList();
    checkBox = new JCheckBox[svgV.getModel().getShapeList().size()];

    this.pause();
    this.svgV = svgV;

    this.setTitle("Shape Animation");
    this.setSize(1200, 1200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //***************************************
    //this.setLayout(new BorderLayout());

    // button panel************************************
    buttonPanel = new JPanel();
    //buttonPanel.setLayout(new FlowLayout());
    start = new JButton("start");
    start.setActionCommand("Start Button");
    buttonPanel.add(start);

    pause = new JButton("pause");
    pause.setActionCommand("Pause Button");
    buttonPanel.add(pause);

    resume = new JButton("resume");
    resume.setActionCommand("Resume Button");
    buttonPanel.add(resume);

    restart = new JButton("restart");
    restart.setActionCommand("Restart Button");
    buttonPanel.add(restart);

    speedUp = new JButton("speedUp");
    speedUp.setActionCommand("Speed Up");
    buttonPanel.add(speedUp);

    slowDown = new JButton("slowDown");
    slowDown.setActionCommand("Slow Down");
    buttonPanel.add(slowDown);

    notLoopBack = new JButton("Switch LoopBack");
    notLoopBack.setActionCommand("Not Loop");
    buttonPanel.add(notLoopBack);

    changeColor = new JButton("Change Color");
    changeColor.setActionCommand("change color");
    buttonPanel.add(changeColor);

    //************************************
    exportPanel = new JPanel();
    exportInfo = new JLabel("File to export to");
    exportPanel.add(exportInfo);

    exportInput = new JTextField(10);
    exportPanel.add(exportInput);

    export = new JButton("Export");
    export.setActionCommand("Export Button");
    exportPanel.add(export);

    exportPanel.setPreferredSize(new Dimension(200, 200));

    buttonPanel.add(exportPanel);

    buttonPanel.setPreferredSize(new Dimension(150, 200));

    this.add(new JScrollPane(buttonPanel), BorderLayout.EAST);

    //************************************************
    this.checkBoxPanel = new JPanel();

    checkBoxPanel.setLayout(new GridLayout(5, 8));
    checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Interactive Buttons"));
    checkBoxScrollPane = new JScrollPane(checkBoxPanel);
    checkBoxDisplay = new JLabel("Please choose the shapes you want to see");
    checkBoxPanel.add(checkBoxDisplay);


    for (int i = 0; i < checkBox.length; i++) {
      checkBox[i] = new JCheckBox(shapeList.get(i).getName());
      checkBox[i].setSelected(true);
      checkBox[i].setActionCommand(shapeList.get(i).getName());
      checkBoxPanel.add(checkBox[i]);
    }


    checkBoxScrollPane = new JScrollPane(checkBoxPanel);
    this.add(checkBoxScrollPane, BorderLayout.PAGE_END);

    this.setVisible(true);


  }

  @Override
  public String viewText(boolean ifloopback, Color color) {

    return this.svgV.viewText(true, color);
  }

  @Override
  public void setRate(int rate) {
    this.svgV.setRate(rate);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    start.addActionListener(actionListener);
    pause.addActionListener(actionListener);
    resume.addActionListener(actionListener);
    restart.addActionListener(actionListener);
    speedUp.addActionListener(actionListener);
    slowDown.addActionListener(actionListener);
    notLoopBack.addActionListener(actionListener);
    export.addActionListener(actionListener);
    changeColor.addActionListener(actionListener);
  }

  @Override
  public void addItemListener(ItemListener itemListener) {

    for (JCheckBox checkBox : checkBox) {
      checkBox.addItemListener(itemListener);
    }
  }

  @Override
  public void setColor(int color) {
    switch (color) {
      case 1:
        this.shapePanel.setBackground(Color.white);
        break;
      case 2:
        this.shapePanel.setBackground(Color.cyan);
        break;
      case 3:
        this.shapePanel.setBackground(Color.pink);
        break;
      case 4:
        this.shapePanel.setBackground(Color.green);
        break;
      case 5:
        this.shapePanel.setBackground(Color.gray);
        break;
      default:
        throw new RuntimeException("No such color");
    }
  }

  @Override
  public void startAnimation() {
    super.timer.start();
  }

  @Override
  public void pauseAnimation() {
    super.timer.stop();
  }

  @Override
  public void resumeAnimation() {
    super.timer.start();
  }

  @Override
  public void restartAnimation() {
    shapePanel.reStart();
  }


  @Override
  public void speedUP() {
    this.rate = this.rate + 10;

    super.timer.setDelay(1000 / rate);
  }

  @Override
  public void slowDwon() {

    if (this.rate <= 10) {
      this.rate = 1;
      super.timer.setDelay(1000 / rate);

    } else {
      this.rate = this.rate - 10;
      super.timer.setDelay(1000 / rate);
    }

    System.out.println("current rate =" + super.getRate());

  }

  @Override
  public void switchLoop() {
    super.loopBack = !loopBack;
  }

  @Override
  public void export() {
    System.out.println("Your input: " + exportInput.getText());


    PrintStream origOut = System.out;
    try {
      PrintStream ps;

      if (exportInput.getText().equals("")) {
        ps = System.out;
      } else {
        ps = new PrintStream(new FileOutputStream(exportInput.getText()));
      }

      System.setOut(ps);

    } catch (FileNotFoundException o) {
      JOptionPane.showMessageDialog(this, "Can't find out put file!");
      return;
    }

    System.out.println(this.svgV.viewText(super.loopBack, shapePanel.getBackground()));
    System.setOut(origOut);


    exportInput.setText("");
  }

  /**
   * pause the animation in the HybridView.
   */
  private void pause() {
    this.timer.stop();
  }



  @Override
  public void selectOrUnseletShapes(AniShape shape) {
    if (this.visibleShapes.containsKey(shape.getName())) {
      visibleShapes.remove(shape.getName(), shape);
    } else {
      visibleShapes.put(shape.getName(), shape);
    }
  }


}
