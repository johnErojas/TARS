package view.componets;

/**
 *
 * @author John Erney Rojas
 * #studentID: 12072801
 * #unit: COIT11134
 * #unitName: Object Oriented Programming [OOP]
 * #Assignment: II
 */
public interface ComponentInterface {    
    //
    void init();
    //
    public int getInnerWidth();
    public int getInnerHeight();
    public int getFullWidth();
    public int getFullHeigth();
    //
    public void changeSize(int width, int height);
    public void changeWidth(int widht);
    public void changeHeight(int height);
    
    //
    public void setPadding(int padding);
    public void setHorizontalPadding(int padding);
    public int getHorizontalPadding();
    public void setVerticalPadding(int padding);
    public int getVerticalPadding();
    //border:
    public void setStroke(int stroke);
    public int getStroke();
    //
    public void setBorderColor(int color);
    public void setBorderColor(int color, int alpha);
    public int getBorderColor();
    public void borderOnlyBottom();
    public void borderOnlyLeft();
    public void borderOnlyTop();
    public void borderOnlyRight();
    public void borderOnlyHorizontal();
    public void borderOnlyVertical();
    public void noBorder();    
    void updateBorder();
    //
    public void setBackgroundColor(int color);
    public void setBackgroundColor(int color,int alpha);
    public int getBackgroundColor();
    public void noBackground();
    //
    public void setColor(int color);
    public int getColor();
    public void setFontStyle(String style);
    public void setFontStyle(String style,float size);
    public void setFontStyle(String style,boolean bold);
    public void setFontStyle(String style,float size, boolean bold);
    
    
    
}
