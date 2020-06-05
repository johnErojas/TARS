package view.componets;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author John Erney Rojas 
 * #studentID: 12072801 
 * #unit: COIT11134 
 * #unitName: Object Oriented Programming [OOP] 
 * #Assignment: II
 */
public class PanelConstructor extends JPanel implements ComponentInterface {

    int mWidth;
    int mHeigth;
    int gapH;
    int gapV;
    int stroke;
    int stkLeft;
    int stkTop;
    int stkRight;
    int stkBottom;
    int borderColor;
    int borderAlpha;
    int bgColor;
    int bgAlpha;
    int color;
    FlowLayout layout;
    public PanelConstructor() {
        init();
    }

    //
    ArrayList<JPanel> items;

    @Override
    public void init() {
        mWidth = 0;
        mHeigth = 0;
        gapH = 0;
        gapV = 0;
        stroke = 0;
        borderColor = 0;
        borderAlpha = 255;
        bgColor = 0;
        color = 0;
        bgAlpha = 255;
        setBackground(null);
        alingContentLeft();
        items = new ArrayList<>();
    }

    public void alingContentLeft() {
        layout = new FlowLayout(FlowLayout.LEFT, gapH, gapV);
        setLayout(layout);
    }

    public void alingContentCenter() {
        layout = new FlowLayout(FlowLayout.CENTER, gapH, gapV);
        setLayout(layout);
    }

    public void alingContentRight() {
        layout = new FlowLayout(FlowLayout.RIGHT, gapH, gapV);
        setLayout(layout);
    }

    @Override
    public int getInnerWidth() {
        int around = mWidth - ((gapH * 2) + (stroke * 2));
        return around < 0 ? 0 : around;
    }

    @Override
    public int getInnerHeight() {
        int around = mHeigth - ((gapV * 2) + (stroke * 2));
        return around < 0 ? 0 : around;
    }

    @Override
    public int getFullWidth() {
        return mWidth;
    }

    @Override
    public int getFullHeigth() {
        return mHeigth;
    }

    @Override
    public void changeSize(int width, int height) {
        mWidth = width;
        mHeigth = height;
        Dimension size = new Dimension(mWidth, mHeigth);
        setPreferredSize(size);
        setSize(size);
        setMaximumSize(size);
    }

    @Override
    public void changeWidth(int widht) {
        changeSize(widht, mHeigth);
    }

    @Override
    public void changeHeight(int height) {
        changeSize(mWidth, height);
    }

    @Override
    public void setPadding(int padding) {
        gapH = padding;
        gapV = padding;
        layout.setHgap(gapH);
        layout.setVgap(gapV);
    }

    @Override
    public void setHorizontalPadding(int padding) {
        gapH = padding;
        layout.setHgap(gapH);
    }

    @Override
    public int getHorizontalPadding() {
        return gapH;
    }

    @Override
    public void setVerticalPadding(int padding) {
        gapV = padding;
        layout.setVgap(gapV);
    }

    @Override
    public int getVerticalPadding() {
        return gapV;
    }

    @Override
    public void updateBorder() {
        int stroke = stkLeft + stkRight + stkTop + stkBottom;
        if(stroke==0){
            setBorder(null);
        }else{
            MatteBorder out = BorderFactory.createMatteBorder(stkTop, stkLeft, stkBottom, stkRight, Theme.getColor(borderColor,borderAlpha));
            setBorder(BorderFactory.createCompoundBorder(out, BorderFactory.createLineBorder(Theme.getColor(borderColor,0),1)));
        }
        setBackgroundColor(bgColor,bgAlpha);
    }

    @Override
    public void setBackgroundColor(int color, int alpha) {
        bgColor = color;
        bgAlpha = alpha;
        if(bgColor>0)
            setBackground(Theme.getColor(bgColor, bgAlpha));
        else
            setBackground(null);
    }

    @Override
    public void setBackgroundColor(int color) {
        setBackgroundColor(color, 255);
    }

    @Override
    public void noBackground() {
        setBackground(null);
        setOpaque(true);
    }
    
    

    public void noBorder() {
        bgColor = 0;
        setBackground(null);
        setOpaque(true);
    }

    @Override
    public int getBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setBorderColor(int color) {
        setBorderColor(color,255);
    }

    @Override
    public void setBorderColor(int color, int alpha) {
        borderColor = color;
        borderAlpha = alpha;
        if(getStroke()==0)setStroke(1);
        updateBorder();
    }

    @Override
    public int getBorderColor() {
        return borderColor;
    }

    @Override
    public void setStroke(int stroke) {
        this.stroke = stroke;
        stkLeft = stroke;
        stkTop = stroke;
        stkRight = stroke;
        stkBottom = stroke;
        updateBorder();
    }

    @Override
    public int getStroke() {
        return stroke;
    }

    @Override
    public void borderOnlyBottom() {
        stkLeft = 0;
        stkTop = 0;
        stkRight = 0;
        stkBottom = stroke > 1 ? stroke : 1;
        updateBorder();
    }

    @Override
    public void borderOnlyLeft() {
        stkLeft = stroke > 1 ? stroke : 1;
        stkTop = 0;
        stkRight = 0;
        stkBottom = 0;
        updateBorder();
    }

    @Override
    public void borderOnlyTop() {
        stkLeft = 0;
        stkTop = stroke > 1 ? stroke : 1;
        stkRight = 0;
        stkBottom = 0;
        updateBorder();
    }

    @Override
    public void borderOnlyRight() {
        stkLeft = 0;
        stkTop = 0;
        stkRight = stroke > 1 ? stroke : 1;
        stkBottom = 0;
        updateBorder();
    }

    @Override
    public void borderOnlyHorizontal() {
        stkLeft = stroke > 1 ? stroke : 1;
        stkTop = 0;
        stkRight = stroke > 1 ? stroke : 1;
        stkBottom = 0;
        updateBorder();
    }

    @Override
    public void borderOnlyVertical() {
        stkLeft = 0;
        stkTop = stroke > 1 ? stroke : 1;
        stkRight = 0;
        stkBottom = stroke > 1 ? stroke : 1;
        updateBorder();
    }

    @Override
    public void setFontStyle(String style) {
        setFontStyle(style,12f,false);
    }

    @Override
    public void setFontStyle(String style, float size) {
        setFontStyle(style,size,false);
    }

    @Override
    public void setFontStyle(String style, boolean bold) {
        setFontStyle(style,12f,bold);
    }

    @Override
    public void setFontStyle(String style, float size, boolean bold) {
        setFont(Theme.getFont(style, bold, size));
    }

    //
    public static enum FILL {
        FULL, HORIZONTAL, VERTICAL, NONE
    }

    public boolean existsItem(String name)
    {
        boolean match = false;
        for(JPanel item:items){
            if(item.getName().equals(name)){
                match = true;
                break;
            }
        }
        return match;
    }
    
    public void addItem(ComponentInterface comp, String tag) {
        this.addItem(comp, tag, FILL.NONE);
    }

    public void addItem(ComponentInterface comp, String tag, FILL fill) {
        if(tag == null)return;
        Dimension size = new Dimension();
        JPanel box = new JPanel();
        box.setBackground(null);
        switch (fill) {
            case FULL:
                size.setSize(getInnerWidth(), getInnerHeight());
                break;
            case HORIZONTAL:
                size.setSize(getInnerWidth(), comp.getFullHeigth());
                break;
            case VERTICAL:
                size.setSize(comp.getFullWidth(), getInnerHeight());
                break;
            case NONE:
                size.setSize(comp.getFullWidth(), comp.getFullHeigth());
                break;
        }
        comp.changeSize(size.width, size.height);
        box.setPreferredSize(size);
        box.setSize(size);
        box.setName(tag);
        box.add((Component) comp);
        items.add(box);
        box.setLayout(new CardLayout(0, 0));
        add(box);
    }
    
    //
    public void removeItem(String name) {
        JPanel match = null;
        for (JPanel item : items) {
            if (item.getName().equals(name)) {
                match = item;
                break;
            }
        }

        if (match != null) {
            remove(match);
            items.remove(match);
        }
    }
    
    public void clearAll()
    {
        removeAll();
        repaint();
        items = new ArrayList<>();
    }

}
