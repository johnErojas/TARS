/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import presenter.PagesHandler.PAGES;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author johnrojas
 */
public class DashboardItem extends javax.swing.JPanel{
    private CommandEventListener eventClick = null;
    private boolean eventActive = true;
    private PAGES page;
    private String iconRepository;
    
    /**
     * Creates new form DashboardItem
     */
    public DashboardItem() {
        initComponents();
        start();
    }
    
    
    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            if(eventActive)label.setBackground(Theme.Color.CLEAR_10);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(eventActive)label.setBackground(Theme.Color.CLEAR_20);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            onItemClicked();
        }
        
    };
    
    public void start(){
        addMouseListener(mouseAdapter);
        icon.addMouseListener(mouseAdapter);
        label.addMouseListener(mouseAdapter);
    }
    
    private void onItemClicked(){
        if(eventActive && eventClick !=null)eventClick.onCommandClicked(this);
    }
    
    public void disableListener(){
        eventActive = false;
        Dimension size = icon.getPreferredSize();
        size.width = 5;
        icon.setPreferredSize(size);
        icon.setSize(size);
        label.setBackground(Theme.Color.CLEAR_10);
        
        Dimension s = label.getSize();
        s.width = 150;
        label.setSize(s);
    }
    public void enableListener(){
        eventActive = true;
        Dimension size = icon.getPreferredSize();
        size.width = 50;
        icon.setSize(size);
        
        Dimension s = label.getSize();
        s.width = 195;
        label.setSize(s);
        label.setBackground(Theme.Color.CLEAR_10);
    }

    public void setEventClick(CommandEventListener eventClick) {
        this.eventClick = eventClick;
    }
    
    public void setTitle(String title){
        label.setText(title);
    }
    
    public void setItemIcon(String name){
        icon.setIcon(Theme.ICON.getIcon(name));
        iconRepository = name;
    }

    public PAGES getPage() {
        return page;
    }

    public void setPage(PAGES page) {
        this.page = page;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        stroke = new javax.swing.JPanel();

        setBackground(Theme.Color.CLEAR_10);
        setPreferredSize(new java.awt.Dimension(250, 50));

        icon.setBackground(Theme.Color.OLIVE);
        icon.setOpaque(true);

        label.setBackground(Theme.Color.CLEAR_10);
        label.setForeground(Theme.Color.OLIVE);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("jLabel1");
        label.setOpaque(true);

        stroke.setBackground(Theme.Color.OLIVE);

        javax.swing.GroupLayout strokeLayout = new javax.swing.GroupLayout(stroke);
        stroke.setLayout(strokeLayout);
        strokeLayout.setHorizontalGroup(
            strokeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        strokeLayout.setVerticalGroup(
            strokeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(stroke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stroke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel label;
    private javax.swing.JPanel stroke;
    // End of variables declaration//GEN-END:variables

}
