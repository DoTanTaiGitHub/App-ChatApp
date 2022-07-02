/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.*;
/**
 *
 * @author ACER
 */
public class Room implements Serializable{
    private int id;
    private String name;
    private boolean isGroup;

    public boolean isIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Room(int id, String name, boolean isGroup){
        this.id = id;
        this.name = name;
        this.isGroup = isGroup;
    }
}
