/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets.bodypages;

import javax.swing.JLabel;
import javax.swing.JTextField;
import view.componets.Button;
import view.componets.ListConstructor;
import view.componets.Theme;

/**
 *
 * @author johnrojas
 */
public class AccidentsBody extends javax.swing.JPanel {
    /**
     * Creates new form HomeBody
     */
    public AccidentsBody() {
        initComponents();
    }

    public Button getCmdSearch() {
        return cmdSearch;
    }

    public ListConstructor getListPanel() {
        return listPanel;
    }

    public JTextField getPlateIN() {
        return plateIN;
    }

    public JLabel getResultsOut() {
        return resultsOut;
    }

    public JLabel getTotalOut() {
        return totalOut;
    }

    public JLabel getSearchLabel() {
        return searchLabel;
    }

    public Button getCmdClear() {
        return cmdClear;
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        THeader = new javax.swing.JPanel();
        LABEL_ID = new javax.swing.JLabel();
        LABEL_DATE = new javax.swing.JLabel();
        LABEL_LOCATION = new javax.swing.JLabel();
        LABEL_VEHICLES = new javax.swing.JLabel();
        listPanel = new view.componets.ListConstructor();
        ResultsInfo = new javax.swing.JPanel();
        resultsOut = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        totalOut = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        plateIN = new javax.swing.JTextField();
        cmdSearch = new view.componets.Button();
        cmdClear = new view.componets.Button();

        setBackground(Theme.Color.BG);

        THeader.setBackground(Theme.Color.DARK);
        THeader.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

        LABEL_ID.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        LABEL_ID.setForeground(Theme.Color.OLIVE);
        LABEL_ID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_ID.setText("ACCIDENT ID");
        LABEL_ID.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        THeader.add(LABEL_ID);

        LABEL_DATE.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        LABEL_DATE.setForeground(Theme.Color.OLIVE);
        LABEL_DATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_DATE.setText("DATE");
        THeader.add(LABEL_DATE);

        LABEL_LOCATION.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        LABEL_LOCATION.setForeground(Theme.Color.OLIVE);
        LABEL_LOCATION.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_LOCATION.setText("LOCATION");
        LABEL_LOCATION.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        THeader.add(LABEL_LOCATION);

        LABEL_VEHICLES.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        LABEL_VEHICLES.setForeground(Theme.Color.OLIVE);
        LABEL_VEHICLES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LABEL_VEHICLES.setText("VEHICLES INVOLVED");
        LABEL_VEHICLES.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        THeader.add(LABEL_VEHICLES);

        ResultsInfo.setBackground(Theme.Color.DARK);
        ResultsInfo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Theme.Color.OLIVE));

        resultsOut.setForeground(Theme.Color.OLIVE);
        resultsOut.setText("3 Accidents found");

        totalLabel.setForeground(Theme.Color.OLIVE);
        totalLabel.setText("Total Records");
        totalLabel.setToolTipText("");

        totalOut.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        totalOut.setForeground(Theme.Color.OLIVE);
        totalOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalOut.setText("0");

        javax.swing.GroupLayout ResultsInfoLayout = new javax.swing.GroupLayout(ResultsInfo);
        ResultsInfo.setLayout(ResultsInfoLayout);
        ResultsInfoLayout.setHorizontalGroup(
            ResultsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResultsInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultsOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ResultsInfoLayout.setVerticalGroup(
            ResultsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsOut, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
            .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(totalOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        searchPanel.setBackground(null);

        searchLabel.setForeground(java.awt.Color.white);
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchLabel.setText("Enter a vehicle plate to see wich accidents it is involved");

        plateIN.setBackground(null);
        plateIN.setForeground(Theme.Color.OLIVE);
        plateIN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        plateIN.setToolTipText("Search by plate number");
        plateIN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.Color.OLIVE));
        plateIN.setCaretColor(Theme.Color.OLIVE);

        cmdSearch.setForeground(Theme.Color.CLEAR);
        cmdSearch.setText("Search");
        cmdSearch.setOpaque(false);

        cmdClear.setText("Clear");
        cmdClear.setOpaque(false);

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plateIN, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(plateIN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ResultsInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(THeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(THeader, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResultsInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LABEL_DATE;
    private javax.swing.JLabel LABEL_ID;
    private javax.swing.JLabel LABEL_LOCATION;
    private javax.swing.JLabel LABEL_VEHICLES;
    private javax.swing.JPanel ResultsInfo;
    private javax.swing.JPanel THeader;
    private view.componets.Button cmdClear;
    private view.componets.Button cmdSearch;
    private view.componets.ListConstructor listPanel;
    private javax.swing.JTextField plateIN;
    private javax.swing.JLabel resultsOut;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalOut;
    // End of variables declaration//GEN-END:variables
}
