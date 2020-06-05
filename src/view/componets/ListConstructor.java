/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author johnrojas
 */
public class ListConstructor extends JPanel implements MouseListener
{

    private int rowHeight = 10;
    private boolean hasContent = false;
    private ListAdapter eventListener;
    // mode
    private Color rowBG;
    private Color rowBGover;
    private Color rowColor;
    private Color rowColorOver;
    
    public ListConstructor() {
        initComponents();
    }

    private void initComponents() {
        scroll = new JScrollPane();
        container = new JPanel();
        table = new JPanel();
        list = new ArrayList<>();
        
        setBackground(Theme.Color.BG);
        setLayout(new java.awt.CardLayout(5, 5));
        scroll.setBackground(Theme.Color.BG);
        rowBG = Theme.Color.DARK;
        rowColor = Theme.Color.OLIVE;
        
        container.setBackground(Theme.Color.TRANSPARENT);
        
        table.setBackground(Theme.Color.TRANSPARENT);
        table.setLayout(new GridLayout(1, 0, 0, 1));
        
        scroll.setViewportView(container);
        activeScrollHozirontal(false);
        activeScrollVertical(true);
        add(scroll,"listScroll");
    }
    
    public void activeScrollHozirontal(boolean active){
        if(active){
            scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }else{
            scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
    }
    
    public void activeScrollVertical(boolean active){
        if(active){
            scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        }else{
            scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        }
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if(table!=null)table.setBackground(bg);
        if(scroll!=null)scroll.setBackground(bg);
    }

    public void setEventListener(ListAdapter eventListener) {
        this.eventListener = eventListener;
    }
    
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public ArrayList<ListRow> getRows() {
        return list;
    }
    
    public ListRow findRow(String name){
        for(ListRow row:list){
            if(row.getName().equals(name))
                return row;
        }
            
        return null;
    }
    
    public void addRow(String rowID, ArrayList<String> labels){
        ListRow row = new ListRow();
        row.setName(rowID);
        row.setBackground(rowBG);
        row.setLayout(new java.awt.GridLayout(1, labels.size(), 1, 0));
        int i = 0;
        for(String caption:labels){
            row.add(createLabel(row,"col"+i, caption));
            i++;
        }
        row.addMouseListener(this);
        list.add(row);
    }
    
    private ListRowLabel createLabel(ListRow row,String id, String caption){
        ListRowLabel label = new ListRowLabel();
        label.setText(caption);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setRow(row);
        label.setId(id);
        label.setForeground(rowColor);
        label.setOpaque(false);
        row.getLabels().add(label);
        return label;
    }
    
    public void printList(){
        if(hasContent)deletePrintedList();
        int num = list.isEmpty() ? 1 : list.size();//num rows
        table.setLayout(new GridLayout(num, 0, 0, 2));
        int tableH = rowHeight*list.size();
        int tableW = container.getWidth() - 10;//less gap
        Dimension size = new Dimension(tableW,tableH);
        table.setPreferredSize(size);
        for(JPanel row: list)
            table.add(row);
        container.add(table);
        scroll.setViewportView(container);
        hasContent = list.size()>0;
    }
    
    public void deletePrintedList(){
        for(JPanel row: list)
            table.remove(row);
        table.setLayout(new GridLayout(0, 1, 0, 1));
        container.remove(table);
        hasContent = false;
    }
    
    public void reset(){
        if(list.isEmpty())return;
        deletePrintedList();
        list = new ArrayList<>();
    }
    
    //rows status:
    public void setRowBG(Color color) {
        this.rowBG = color;
    }

    public void setRowBGover(Color color) {
        this.rowBGover = color;
    }

    public void setRowColor(Color color) {
        this.rowColor = color;
    }

    public void setRowColorOver(Color color) {
        this.rowColorOver = color;
    }

    public Color getRowBG() {
        return rowBG;
    }

    public Color getRowBGover() {
        return rowBGover;
    }

    public Color getRowColor() {
        return rowColor;
    }

    public Color getRowColorOver() {
        return rowColorOver;
    }
    
    //
    
    public void unselectAllExcept(ListRow row){
        list.stream().filter((item) -> (item.isSelected() && !item.getName().equals(row.getName()))).map((item) -> {
            item.setSelected(false);
            return item;
        }).map((item) -> {
            item.setBackground(rowBG);
            return item;
        }).forEachOrdered((item) -> {
            item.getLabels().forEach((label) -> {
                label.setForeground(rowColor);
            });
        });
    }
    
    public void unselectAll(){
        list.stream().map((row) -> {
            row.setSelected(false);
            return row;
        }).map((row) -> {
            row.setBackground(rowBG);
            return row;
        }).forEachOrdered((row) -> {
            for(ListRowLabel label:row.labels){
                label.setForeground(rowColor);
            }
        });
    }
    
    // Mouse Events:
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ListRow row = getRowFromEvent(e);
        if(row.isSelected()){
            row.setSelected(false);
            if(eventListener!=null)eventListener.onRowUnSelected(row);
        }else{
            row.setSelected(true);
            if(eventListener!=null)eventListener.onRowSelected(row);
        }
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(rowBGover == null && rowColorOver == null)return;
        
        ListRow row = getRowFromEvent(e);
        if(rowBGover!=null)row.setBackground(rowBGover);
        if(rowColorOver!=null){
            row.getLabels().forEach((label)->{
                label.setForeground(rowColorOver);
            });
        }        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ListRow row = getRowFromEvent(e);
        if(row.isSelected())return;
        row.setBackground(rowBG);
        row.getLabels().forEach((label)->{
               label.setForeground(rowColor);
        });
    }
    
    private ListRow getRowFromEvent(MouseEvent e){
        ListRow row = null;
        if(e.getComponent().getClass() == ListRow.class)
            row = (ListRow) e.getComponent();
        if(e.getComponent().getClass() == ListRowLabel.class)
            row = ((ListRowLabel)e.getComponent()).getRow();
        return row;
    }
    
    //components:
    private JScrollPane scroll;
    private JPanel container;
    private JPanel table;
    private ArrayList<ListRow> list;

    //
    public class ListRow extends JPanel {
        private boolean selected = false;
        private final ArrayList<ListRowLabel> labels = new ArrayList<>();

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public ArrayList<ListRowLabel> getLabels() {
            return labels;
        }
        
    }
    
     public class ListRowLabel extends JLabel{
        private ListRow row;
        private String id;


        public ListRow getRow() {
            return row;
        }

        public void setRow(ListRow row) {
            this.row = row;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
    
}
