/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets.bodypages;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import view.componets.Button;
import view.componets.ListConstructor;
import view.componets.Theme;

/**
 *
 * @author johnrojas
 */
public class NewReportVehicleList extends javax.swing.JFrame {
    /**
     * Creates new form NewReportVehicleList
     */
    public NewReportVehicleList() {
        setUndecorated(true);
        setAlwaysOnTop(true);
        initComponents();
        setVisible(false);
    }

    public JLabel getResultsOutput() {
        return resultsOutput;
    }

    public ListConstructor getListPanel() {
        return listPanel;
    }

    public JLabel getSelectedOutput() {
        return selectedOutput;
    }

    public JCheckBox getCheckOwner() {
        return CheckOwner;
    }

    public JCheckBox getCheckPlate() {
        return CheckPlate;
    }

    public Button getCmdCancel() {
        return cmdCancel;
    }

    public Button getCmdFinish() {
        return cmdFinish;
    }

    public Button getCmdNew() {
        return cmdNew;
    }

    public JTextField getFilterInput() {
        return filterInput;
    }

    public JLabel getFilterLabel() {
        return filterLabel;
    }

    public Button getCmdClearFilter() {
        return cmdClearFilter;
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
        Title = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        resultsLabel = new javax.swing.JLabel();
        resultsOutput = new javax.swing.JLabel();
        selectedLabel = new javax.swing.JLabel();
        selectedOutput = new javax.swing.JLabel();
        filterPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        filterLabel = new javax.swing.JLabel();
        filterInput = new javax.swing.JTextField();
        CheckPlate = new javax.swing.JCheckBox();
        CheckOwner = new javax.swing.JCheckBox();
        cmdClearFilter = new view.componets.Button();
        jPanel3 = new javax.swing.JPanel();
        cmdFinish = new view.componets.Button();
        cmdCancel = new view.componets.Button();
        cmdNew = new view.componets.Button();
        jPanel4 = new javax.swing.JPanel();
        THead = new javax.swing.JPanel();
        TH_id = new javax.swing.JLabel();
        TH_year = new javax.swing.JLabel();
        TH_model = new javax.swing.JLabel();
        TH_ownerName = new javax.swing.JLabel();
        TH_ownerAddress = new javax.swing.JLabel();
        TH_ownerPhone = new javax.swing.JLabel();
        listPanel = new view.componets.ListConstructor();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(Theme.Color.BG);

        Title.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        Title.setForeground(Theme.Color.OLIVE);
        Title.setText("Vehicle List");

        infoPanel.setBackground(null);
        infoPanel.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        resultsLabel.setForeground(Theme.Color.OLIVE);
        resultsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultsLabel.setText("Results");
        infoPanel.add(resultsLabel);

        resultsOutput.setForeground(Theme.Color.OLIVE);
        resultsOutput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultsOutput.setText("0");
        infoPanel.add(resultsOutput);

        selectedLabel.setForeground(Theme.Color.OLIVE);
        selectedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedLabel.setText("Selected");
        infoPanel.add(selectedLabel);

        selectedOutput.setForeground(Theme.Color.OLIVE);
        selectedOutput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedOutput.setText("0");
        infoPanel.add(selectedOutput);

        filterPanel.setBackground(null);

        jPanel2.setBackground(null);

        filterLabel.setForeground(Theme.Color.OLIVE);
        filterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterLabel.setText("Filter by plate number");

        filterInput.setBackground(null);
        filterInput.setForeground(Theme.Color.OLIVE);
        filterInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        filterInput.setToolTipText("Enter Criterial to filter");
        filterInput.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.Color.OLIVE));
        filterInput.setCaretColor(Theme.Color.OLIVE);

        CheckPlate.setForeground(Theme.Color.GRAY);
        CheckPlate.setSelected(true);
        CheckPlate.setText("Plate");

        CheckOwner.setForeground(Theme.Color.GRAY);
        CheckOwner.setText("Owner");

        cmdClearFilter.setText("Clear Filter");
        cmdClearFilter.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterInput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClearFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckPlate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CheckOwner))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdClearFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CheckPlate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckOwner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(filterInput))
                .addContainerGap())
        );

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(null);
        jPanel3.setLayout(new java.awt.GridLayout(1, 3, 5, 0));

        cmdFinish.setText("Finish");
        cmdFinish.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        cmdFinish.setOpaque(false);
        jPanel3.add(cmdFinish);

        cmdCancel.setText("Cancel");
        cmdCancel.setOpaque(false);
        jPanel3.add(cmdCancel);

        cmdNew.setText("New Vehicle");
        cmdNew.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        cmdNew.setOpaque(false);
        jPanel3.add(cmdNew);

        THead.setBackground(Theme.Color.DARK);
        THead.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Theme.Color.OLIVE));
        THead.setForeground(Theme.Color.CLEAR_20);
        THead.setPreferredSize(new java.awt.Dimension(0, 40));
        THead.setLayout(new java.awt.GridLayout(1, 6, 5, 0));

        TH_id.setForeground(Theme.Color.OLIVE);
        TH_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_id.setText("PLATE #");
        THead.add(TH_id);

        TH_year.setForeground(Theme.Color.OLIVE);
        TH_year.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_year.setText("YEAR");
        THead.add(TH_year);

        TH_model.setForeground(Theme.Color.OLIVE);
        TH_model.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_model.setText("MODEL");
        THead.add(TH_model);

        TH_ownerName.setForeground(Theme.Color.OLIVE);
        TH_ownerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_ownerName.setText("OWNER");
        THead.add(TH_ownerName);

        TH_ownerAddress.setForeground(Theme.Color.OLIVE);
        TH_ownerAddress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_ownerAddress.setText("ADDRESS");
        THead.add(TH_ownerAddress);

        TH_ownerPhone.setForeground(Theme.Color.OLIVE);
        TH_ownerPhone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TH_ownerPhone.setText("PHONE");
        THead.add(TH_ownerPhone);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(THead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(THead, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckOwner;
    private javax.swing.JCheckBox CheckPlate;
    private javax.swing.JLabel TH_id;
    private javax.swing.JLabel TH_model;
    private javax.swing.JLabel TH_ownerAddress;
    private javax.swing.JLabel TH_ownerName;
    private javax.swing.JLabel TH_ownerPhone;
    private javax.swing.JLabel TH_year;
    private javax.swing.JPanel THead;
    private javax.swing.JLabel Title;
    private view.componets.Button cmdCancel;
    private view.componets.Button cmdClearFilter;
    private view.componets.Button cmdFinish;
    private view.componets.Button cmdNew;
    private javax.swing.JTextField filterInput;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private view.componets.ListConstructor listPanel;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JLabel resultsOutput;
    private javax.swing.JLabel selectedLabel;
    private javax.swing.JLabel selectedOutput;
    // End of variables declaration//GEN-END:variables
}
