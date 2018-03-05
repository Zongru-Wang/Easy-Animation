package cs3500.animator.model.util;

import java.awt.Color;

//Class for storing static utility methods.
public class Util {

  /**
   * Gets a String describing the Color in RGB values each between 0 and 1.
   *
   * @param col the Color to describe
   * @return the String formatted as (R,G,B) where each value is between 0 and 1
   */
  public static String colString(Color col) {
    float[] com = col.getColorComponents(null);
    return "(" + com[0] + "," + com[1] + "," + com[2] + ")";
  }

  /**
   * transfer the rbg to the svg.
   *
   * @param col the color we want to transfer.
   * @return the String for svg format.
   */
  public static String tranferrbg(Color col) {
    float[] com = col.getColorComponents(null);
    return "(" + (int) (com[0] * 255) + ","
            + (int) (com[1] * 255) + "," + (int) (com[2] * 255) + ")";

  }
}
