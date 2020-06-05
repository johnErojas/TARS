/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.componets;

import java.awt.Font;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author johnrojas
 */
public class Theme {
    public static class Color {
        public static final java.awt.Color BG = new java.awt.Color(30,31,32);
        public static final java.awt.Color DARK = new java.awt.Color(24,25,26);
        public static final java.awt.Color CLEAR = new java.awt.Color(255,255,223);
        public static final java.awt.Color CLEAR_10 = new java.awt.Color(35,35,36);
        public static final java.awt.Color CLEAR_20 = new java.awt.Color(55,55,56);
        public static final java.awt.Color OLIVE = new java.awt.Color(105,105,54);
        public static final java.awt.Color OLIVE_CLEAR = new java.awt.Color(125,125,74);
        public static final java.awt.Color GRAY = new java.awt.Color(105,105,105);
        public static final java.awt.Color TRANSPARENT = new java.awt.Color(105,105,105,0);
        public static final java.awt.Color DANGER = new java.awt.Color(105,0,0);
    }
    
    public static class ICON {
        public static final String ROOT_PATH_ICONS = "/resources/icons/";
        public static final String HOME = "ico_home";
        public static final String REPORT = "ico_report";
        public static final String ACCIDENT = "ico_crash";
        public static final String VEHICLE = "ico_vehicle";
        
        public static final String BUTTON_NORMAL = "../button_normal";
        public static ImageIcon getIcon(String name){
            String uri = ROOT_PATH_ICONS + name + ".png";
            URL path =  Theme.class.getResource(uri);
            return new javax.swing.ImageIcon(path);
        }        
        
    }
    
    //DATE TIME PARSE
    public static final String DATE_FORMAT = "d MMMM y";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT+" "+TIME_FORMAT;
    public static String dateToString(LocalDateTime dt){
        return dt.format(getDateTimeFeromater());
    }
    
    public static DateTimeFormatter getDateFeromater(){
        return getFormater(DATE_FORMAT);
    }
    
    public static DateTimeFormatter getTimeFeromater(){
        return getFormater(TIME_FORMAT);
    }
    
    public static DateTimeFormatter getDateTimeFeromater(){
        return getFormater(DATE_TIME_FORMAT);
    }
    
    private static DateTimeFormatter getFormater(String format){
        return DateTimeFormatter.ofPattern(format,Locale.ENGLISH);
    }
    
    public static LocalDateTime getNow(){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.now();
    }
    
    
    public static LocalDateTime parseTime(String time){
        try{
            return LocalDateTime.parse(time, getTimeFeromater());
        }catch(DateTimeParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static LocalDateTime parseDate(String date){
        try{
            return LocalDate.parse(date,getDateFeromater()).atStartOfDay();
        }catch(DateTimeParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static LocalDateTime parseDateTime(String datetime){
        try{
            return LocalDateTime.parse(datetime, getDateTimeFeromater());
        }catch(DateTimeParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
     //FONTS:
    public static final String FONT_SEGOE = "seguili.ttf";
    public static final String FONT_DIN = "DIN_Condensed_Bold.ttf";
    
    public static Font getFont(String fontName)
    {
        return getFont(fontName,false,12f);
    }
    
    public static Font getFont(String fontName,float size)
    {
        return getFont(fontName,false,size);
    }
    public static Font getFont(String fontName,boolean bold, float size)
    {
        int type = bold ? Font.BOLD : 0;
        return loadFont(fontName).deriveFont(type, size);
    }
    
    private static Font loadFont(String fontName)
    {
        return new JLabel().getFont();
    }
    
    //maths:
    public static double getXcien(double number, double media) {
        return (media * number) / 100;
    }

    private static double getXcienFrom(int total, int media) {
        return (media * 100) / total;
    }

    private static double getProportional(int A, int B, int X) {
        return (X * B) / A;
    }
    
    public interface SleepInterface {
        public void onWakeup();
        //public void doWhileSleep();
    }

    public static void goToSleep(long milliseconds, SleepInterface response){
           CompletableFuture.runAsync(() -> {
               try {
                   sleep(milliseconds);
               } catch (InterruptedException ex) {
                   
               }
           }).thenRunAsync(()-> response.onWakeup());
    }
    
    public interface EnterFrameInterface {
        public boolean stopNow();
        public void run();
        public void onStoped();
    };
    public static void EnterFrame(EnterFrameInterface callback,long speedML){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(callback.stopNow()){
                    timer.cancel();
                    callback.onStoped();
                }else{
                    callback.run();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000,speedML);
    }
    
    public static int parseMonth(String month){
        int m = 0;
        switch(month.toLowerCase()){
            case "jan":
            case "january":m=1;break;
            case "feb":
            case "february":m=2;break;
            case "mar":
            case "march":m=3;break;
            case "apr":
            case "april":m=4;break;
            case "may":m=5;break;
            case "jun":
            case "june":m=6;break;
            case "jul":
            case "july":m=7;break;
            case "aug":
            case "august":m=8;break;
            case "sep":
            case "september":m=9;break;
            case "oct":
            case "october":m=10;break;
            case "nov":
            case "november":m=11;break;
            case "dec":
            case "december":m=12;break;
        }
        return m;
    }
    
    
    //colours:
    public static final int RED = 1;
    public static final int RED_BLOOD = 2;
    public static final int PINK = 3;
    public static final int PURPLE = 4;
    public static final int DEPP_PURPLE = 5;
    public static final int BLUE = 6;
    public static final int DARK_BLUE = 7;
    public static final int BLUE_GRAY = 8;
    public static final int GRAY = 9;
    public static final int INDIGO = 10;
    public static final int CYAN = 11;
    public static final int TEAL = 12;
    public static final int DARK_TEAL = 13;
    public static final int DEEP_DARK_TEAL = 14;
    public static final int GREEN = 15;
    public static final int LIME = 16;
    public static final int YELLOW = 17;
    public static final int AMBER = 18;
    public static final int ORANGE = 19;
    public static final int DEEP_ORANGE = 20;
    public static final int BROWN = 21;
    public static final int DARK = 22;
    public static final int DEEP_DARK = 23;
    public static final int CLEAR = 24;
    public static final int SILVER = 25;
    public static final int BLACK = 26;
    public static final int SKY = 27;
    public static final int SKY_BLUE = 28;
    public static final int DARK_GRAY = 29;
    public static final int CLOUD = 30;
    public static final int NIGHT_BLUE = 31;
    public static final int BONE = 32;
    public static final int CLEAR_SKY = 33;
    public static final int BLACKBERRY_ICE_CREAM = 34;
    public static final int CARROT = 35;
    public static java.awt.Color getColor(int color) {
        return getColor(color,255);
    }
    public static java.awt.Color getColor(int color, int alpha) {
        int R, G, B;
        switch (color) {
            case RED:
                R = 153;
                G = 0;
                B = 0;
                break;
            case RED_BLOOD:
                R = 64;
                G = 1;
                B = 1;
                break;
            case PINK:
                R = 233;
                G = 30;
                B = 99;
                break;
            case PURPLE:
                R = 156;
                G = 39;
                B = 176;
                break;
            case DEPP_PURPLE:
                R = 103;
                G = 58;
                B = 183;
                break;
            case BLUE:
                R = 33;
                G = 150;
                B = 243;
                break;
            case DARK_BLUE:
                R = 59;
                G = 89;
                B = 152;
                break;
            case BLUE_GRAY:
                R = 96;
                G = 125;
                B = 139;
                break;
            case GRAY:
                R = 50;
                B = 50;
                G = 50;
                break;
            case DARK_GRAY:
                R = 69;
                G = 71;
                B = 74;
                break;
            case INDIGO:
                R = 63;
                G = 81;
                B = 181;
                break;
            case CYAN:
                R = 0;
                G = 188;
                B = 212;
                break;
            case TEAL:
                R = 38;
                G = 166;
                B = 154;
                break;
            case DARK_TEAL:
                R = 6;
                G = 134;
                B = 122;
                break;
            case DEEP_DARK_TEAL:
                R = 4;
                G = 79;
                B = 71;
                break;
            case GREEN:
                R = 76;
                G = 175;
                B = 80;
                break;
            case LIME:
                R = 205;
                G = 220;
                B = 57;
                break;
            case YELLOW:
                R = 255;
                G = 235;
                B = 59;
                break;
            case AMBER:
                R = 255;
                G = 193;
                B = 7;
                break;
            case ORANGE:
                R = 255;
                G = 152;
                B = 0;
                break;
            case DEEP_ORANGE:
                R = 255;
                G = 87;
                B = 34;
                break;
            case BROWN:
                R = 121;
                G = 85;
                B = 72;
                break;
            case DARK:
                R = 50;
                G = 50;
                B = 50;
                break;
            case DEEP_DARK:
                R = 31;
                G = 31;
                B = 31;
                break;
            case CLEAR:
                R = 241;
                G = 241;
                B = 242;
                break;
            case SILVER:
                R = 192;
                G = 192;
                B = 192;
                break;
            case BLACK:
                R = 24;
                G = 24;
                B = 24;
                break;
            case SKY:
                R = 249;
                G = 249;
                B = 249;
                break;
            case SKY_BLUE:
                R = 42;
                G = 138;
                B = 201;
                break;
            case CLOUD:
                R = 225;
                G = 225;
                B = 226;
                break;
            case NIGHT_BLUE:
                R = 64;
                G = 70;
                B = 97;
                break;
            case BONE:
                R = 215;
                G = 215;
                B = 215;
                break;
            case CLEAR_SKY:
                R = 125;
                G = 167;
                B = 217;
                break;
            case BLACKBERRY_ICE_CREAM:
                R = 161;
                G = 134;
                B = 190;
                break;
            case CARROT:
                R = 242;
                G = 108;
                B = 79;
                break;
            default:
                R = 0;
                G = 0;
                B = 0;
                break;
        }
        return new java.awt.Color(R, G, B, alpha);
    }

}
