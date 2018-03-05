package cs3500.animator.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JCheckBox;

// Listener class of checkBox.
public class ChechBoxListener implements ItemListener {
  Map<String, Runnable> checkboxActions;

  /**
   * Set the checkBox to the given map.
   *
   * @param map given map to be set
   */
  public ChechBoxListener(Map<String, Runnable> map) {
    super();
    checkboxActions = map;
  }


  @Override
  public void itemStateChanged(ItemEvent e) {

    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();

    if (checkboxActions.containsKey(who)) {
      checkboxActions.get(who).run();
    }

  }
}