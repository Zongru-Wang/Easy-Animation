package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


// Button listenr class that implents ActionListener.
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonActions;

  /**
   * Set the button map to the given map.
   *
   * @param map given map to be set
   */
  public void setButtonActionsMap(Map<String, Runnable> map) {
    buttonActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonActions.containsKey(e.getActionCommand())) {
      buttonActions.get(e.getActionCommand()).run();
    }
  }
}
