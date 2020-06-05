/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import javax.swing.JLabel;

/**
 *
 * @author johnrojas
 */
public interface CommandEventListener<T> {
    public void onCommandClicked(T cmd);
}
