/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import view.componets.Theme;

/**
 *
 * @author johnrojas
 */
public class Alert extends javax.swing.JFrame implements MouseListener{

    private boolean opened;
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(e.getComponent().getName()){
            case "close":closeWindow();break;
            case "yes":yesClicked();break;
            case "no":noClicked();break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    public void closeWindow() {
        if(!this.opened)return;
        setVisible(false); //you can't see me!
        dispose();
        this.opened = false;
        if(listener!=null)listener.onClosed();
    }

    private void yesClicked() {
        closeWindow();
        if(listener!=null)listener.onYesClicked();
    }

    private void noClicked() {
        closeWindow();
        if(listener!=null)listener.onNoClicked();
        
    }

    interface AlertInterface {
        public void onYesClicked();
        public void onNoClicked();
        public void onClosed();
    }
    
    public static abstract class AlertAdapter implements AlertInterface{

        @Override
        public void onYesClicked() {}

        @Override
        public void onNoClicked() {}

        @Override
        public void onClosed() {}
    }
    
    AlertAdapter listener;
    /**
     * Creates new form Alert
     */
    public Alert() {
        opened = false;
        this.setUndecorated(true);
        initComponents();
        setupCommands();
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
    
    
    
    private void setupCommands(){
        cmdClose.setName("close");
        cmdYes.setName("yes");
        cmdNo.setName("no");
        
        cmdClose.addMouseListener(this);
        cmdYes.addMouseListener(this);
        cmdNo.addMouseListener(this);
    }
    
    public Alert showMessage(String message){
        return showMessage("Alert",message,null);
    }
    public Alert showMessage(String TITLE,String message){
        return showMessage(TITLE,message,null);
    } 
    public Alert showMessage(String TITLE,String message,AlertAdapter adapter){
        listener = adapter;
        setTheTitle(TITLE);
        setMessage(message);
        cmdNo.setVisible(false);
        cmdYes.setVisible(false);
        return showIt();
    }
    
    public Alert showQueryMessage(String message,AlertAdapter adapter){
        return showQueryMessage("Alert",message,adapter);
    }
    
    public Alert showQueryMessage(String TITLE,String message,AlertAdapter adapter){
        listener = adapter;
        setTheTitle(TITLE);
        setMessage(message);
        cmdNo.setVisible(true);
        cmdYes.setVisible(true);
        return showIt();
    }
    
    private void setTheTitle(String text){
        this.title.setText(text);
    }
    
    private void setMessage(String msn){
        this.message.setText("<html>"+ msn +"</html>");
    }
    
    public Alert replaceMessage(String msn){
        this.message.setText("<html>"+ msn +"</html>");
        return this;
    }
    
    public Alert changeIcon(ImageIcon icon){
        this.icon.setIcon(icon);
        return this;
    }
    

    private Alert showIt(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.opened = true;
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        cmdClose = new javax.swing.JLabel();
        iconBox = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        messageBox = new javax.swing.JPanel();
        message = new javax.swing.JLabel();
        commandBox = new javax.swing.JPanel();
        cmdYes = new view.componets.Button();
        cmdNo = new view.componets.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(Theme.Color.BG);

        header.setBackground(null);
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray));

        title.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        title.setForeground(Theme.Color.OLIVE);
        title.setText("Title");

        cmdClose.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        cmdClose.setForeground(Theme.Color.CLEAR);
        cmdClose.setText("X");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClose)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        iconBox.setBackground(null);

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/ico_alert.png"))); // NOI18N

        javax.swing.GroupLayout iconBoxLayout = new javax.swing.GroupLayout(iconBox);
        iconBox.setLayout(iconBoxLayout);
        iconBoxLayout.setHorizontalGroup(
            iconBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iconBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        iconBoxLayout.setVerticalGroup(
            iconBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        messageBox.setBackground(null);

        message.setForeground(Theme.Color.OLIVE);
        message.setText("Message");

        javax.swing.GroupLayout messageBoxLayout = new javax.swing.GroupLayout(messageBox);
        messageBox.setLayout(messageBoxLayout);
        messageBoxLayout.setHorizontalGroup(
            messageBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messageBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        messageBoxLayout.setVerticalGroup(
            messageBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messageBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        commandBox.setBackground(null);
        commandBox.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        cmdYes.setText("YES");
        cmdYes.setOpaque(false);
        commandBox.add(cmdYes);

        cmdNo.setText("NO");
        cmdNo.setOpaque(false);
        commandBox.add(cmdNo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iconBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 261, Short.MAX_VALUE)
                        .addComponent(commandBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(commandBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Alert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alert.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Alert().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cmdClose;
    private view.componets.Button cmdNo;
    private view.componets.Button cmdYes;
    private javax.swing.JPanel commandBox;
    private javax.swing.JPanel header;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel iconBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel message;
    private javax.swing.JPanel messageBox;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
