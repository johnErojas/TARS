/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.Border;


public class Button extends JButton implements MouseListener {

    private int radius = 10;
    
    private Color bgNormal = Theme.Color.OLIVE;
    private Color colorNormal = Theme.Color.CLEAR;
    private Color border = Theme.Color.DARK;
    
    private Color bgOver = Theme.Color.OLIVE_CLEAR;
    private Color colorOver = Theme.Color.DARK;
    
    private Color currentGB;
    private Color currentColor;
    
    private CommandEventListener cmdListener;
    private Cursor defaultCursor;
    private Cursor handCursor;
    private Cursor clickedCursor;
    
    public Button() {
        super();
        start();
    }
    
    private void start(){
        setBackground(bgNormal);
        setForeground(colorNormal);
        currentGB = bgNormal;
        currentColor = colorNormal;
        addMouseListener(this);
        
        defaultCursor = this.getCursor();
        handCursor = new Cursor(Cursor.HAND_CURSOR);
        clickedCursor = new Cursor(Cursor.WAIT_CURSOR);
    }

    public void setCmdListener(CommandEventListener cmdListener) {
        this.cmdListener = cmdListener;
    }
    
    @Override
    public void setText(String text) {
        super.setText(text);
        updateBorder();
    }
    
    private void updateBorder(){
        setBorder(roundedBorder(getText(), currentGB, currentColor));
    }
    
    private Border roundedBorder(String text,Color bg, Color color){
        return new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                //g.drawRoundRect(x, y, width-1, height-1, radius, radius);
                Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                //Draws the rounded panel with borders.
                graphics.setColor(bg);
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                graphics.setColor(border);
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
                
                graphics.setFont(c.getFont());
                FontMetrics fm = graphics.getFontMetrics();
                int txtX = (width - fm.stringWidth(text)) / 2;
                int txtY = (height + fm.getAscent()) / 2;
                g.drawString(text, txtX, txtY);

            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius+1, radius+1, radius+2, radius);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(cmdListener != null)cmdListener.onCommandClicked(this);
        this.setCursor(clickedCursor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        currentColor = colorOver;
        currentGB = bgOver;
        updateBorder();
        this.setCursor(handCursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentColor = colorNormal;
        currentGB = bgNormal;
        updateBorder();
        this.setCursor(defaultCursor);
    }
}
