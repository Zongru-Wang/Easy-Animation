package cs3500.animator;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.FileWriter;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import cs3500.animator.controller.AnimationControllor;
import cs3500.animator.controller.IViewController;
import cs3500.animator.model.util.AnimationFileReader;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.aniamtion.AAnimation;
import cs3500.animator.model.shape.AniShape;

import cs3500.animator.view.AniviewCreator;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;

// the class can takes in a file and output another.
// type of file.
public final class EasyAnimator {

  /**
   * takes in command lines and generate text.
   *
   * @param args the args we want to takes in.
   */
  public static void main(String[] args) throws IOException {
    String inputFile = null;
    String inputView = null;
    String speed = null;
    String output = null;
    int rate;
    JFrame frame = new JFrame();

    if (args.length % 2 != 0) {
      throw new IllegalArgumentException("Invalid arguements!");
    }

    for (int i = 0; i < args.length; i += 2) {
      String arguement = args[i + 1];

      switch (args[i]) {
        case "-if":
          inputFile = arguement;
          break;

        case "-iv":
          inputView = arguement;
          break;

        case "-o":
          output = arguement;
          break;

        case "-speed":
          speed = arguement;
          break;

        default:
          JOptionPane.showMessageDialog(frame, "Invalid Args");
          System.exit(1);
          return;
      }
    }

    if (inputFile == null || inputView == null) {
      JOptionPane.showMessageDialog(frame, "Didn't provide inputs!");
      System.exit(1);
      return;
    }

    if (output != null && !output.equals("out")) {
      try {
        System.out.println("Filename: " + output);
        PrintStream printStream = new PrintStream(new FileOutputStream(output));
        System.setOut(printStream);
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(frame, "Can't find output files!");
        System.exit(1);
        return;
      }
    }

    // default speed is 1.
    if (speed == null) {
      rate = 1;
    } else {
      try {
        rate = Integer.parseInt(speed);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Speend is an int!");
        System.exit(1);
        return;
      }
    }

    AnimationFileReader reader = new AnimationFileReader();
    IAnimationModel<AniShape, AAnimation> model;
    try {
      model = reader.readFile(inputFile, AnimationModel.builder());
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(frame, "Can't find input files");
      System.exit(1);
      return;
    }

    AniviewCreator aniviewCreator = new AniviewCreator(rate, model);
    IView view = aniviewCreator.create(inputView);


    System.out.println(model.toText());


    if (inputView.equals("interactive")) {
      IViewController hybridViewController = new AnimationControllor(model, (HybridView) view);
    } else {
      FileWriter writer = new FileWriter(output);
      writer.write(aniviewCreator.create(inputView).viewText(view.getLoopBack(), Color.white));
      writer.flush();
      writer.close();

    }




  }
}
