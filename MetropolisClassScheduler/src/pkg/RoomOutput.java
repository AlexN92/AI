/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class RoomOutput extends JTextArea{
    private String name;
    private int code, slots, group;
    public RoomOutput() { }
    
    RoomOutput(int code, String name, int slots, int group){
        this.code = code;
        this.name = name;
        this.slots = slots;
        this.group = group;
        if(code == 0) this.setText("\n                        " + this.name);
        else this.setText(this.name + "\nCódigo: " + this.code + "\nCupos: " +  this.slots + ", Grupo: " + this.group);
        this.setSize(180, 50);
    }
    
    void setTextName(int code, String name, int slots, int group){
        this.code = code;
        this.name = name;
        this.slots = slots;
        this.group = group;
        if(code == 0) this.setText("\n                        " + this.name);
        else this.setText(this.name + "\nCódigo: " + this.code + "\nCupos: " +  this.slots + ", Grupo: " + this.group);
    }
}
