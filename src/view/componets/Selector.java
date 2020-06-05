/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import view.componets.Selector.Option;

public class Selector extends JComboBox<Option> {
    private final DefaultComboBoxModel model;
    public Selector() {
        model =  new DefaultComboBoxModel();
        setModel(model);
        setEditable(true);
    }
    
    public void initRendering(){
        setRenderer(new Renderer());
        setEditor(new Editor());
    }

    @Override
    public void addItem(Option item){
        int id = model.getSize();
        item.setId(id);
        super.addItem(item);
    }
    
    public void addItems(ArrayList<Option> items){
        items.forEach((item)->{
            addItem(item);
        });
    }

    
    public static class Option {
        private int id;
        private String key;
        private String value;

        public Option(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    
    
}


class Renderer extends JPanel implements ListCellRenderer<Option>{
    private JLabel labelItem;

    public Renderer() {
        initComponents();
    }
    private void initComponents(){
        labelItem  = new JLabel();
        labelItem.setOpaque(true);
        labelItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelItem.setForeground(Theme.Color.OLIVE);

        setLayout(new GridLayout(1, 1, 1, 1));
        setBackground(Theme.Color.BG);
        setPreferredSize(new Dimension(0, 30));
        add(labelItem);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Option> list, Option value, int index, boolean isSelected, boolean cellHasFocus) {
        this.labelItem.setText(value.getValue());
        if(isSelected){
            this.labelItem.setForeground(Theme.Color.BG);
            this.labelItem.setBackground(Theme.Color.OLIVE);
        }else{
            this.labelItem.setForeground(Theme.Color.OLIVE);
            this.labelItem.setBackground(Theme.Color.BG);
        }
        return this;
    }
        
        
}

class Editor extends BasicComboBoxEditor {
        private final JPanel panel;
        private final JLabel labelItem;
        private Option selectedValue;

        public Editor() {
            this.labelItem = new JLabel();
            this.panel = new JPanel();

            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = 1.0;
            constraints.insets = new Insets(2, 5, 2, 2);

            labelItem.setOpaque(false);
            labelItem.setHorizontalAlignment(JLabel.CENTER);
            labelItem.setForeground(Theme.Color.OLIVE);

            panel.add(labelItem, constraints);
            panel.setBackground(Theme.Color.BG);

        }
    
        @Override
        public Component getEditorComponent() {
            return this.panel;
        }

        @Override
        public Option getItem() {
            return this.selectedValue;
        }

        
        @Override
        public void setItem(Object item) {
            if (item == null) {
                return;
            }
            selectedValue =(Option) item;
            labelItem.setText(selectedValue.getValue());
        }   
        
        
}
