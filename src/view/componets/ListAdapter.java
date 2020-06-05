/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.componets.ListConstructor.ListRow;

/**
 *
 * @author johnrojas
 */
public abstract class ListAdapter {
    public void onRowSelected(ListRow row){}
    public void onRowUnSelected(ListRow row){}
}



