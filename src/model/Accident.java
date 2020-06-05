/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author johnrojas
 */
public class Accident {
    private int ld;
    private String location;
    private String comments;
    private LocalDateTime date;
    private ArrayList<String> plates;

    public Accident() {
        plates = new ArrayList<>();
    }
    
    

    public int getID() {
        return ld;
    }

    public void setID(int ld) {
        this.ld = ld;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<String> getPlates() {
        return plates;
    }

    public void setPlates(ArrayList<String> plates) {
        this.plates = plates;
    }
    
    
}
