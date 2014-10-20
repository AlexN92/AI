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
    private int code;
    public RoomOutput() { }
    
    RoomOutput(int code, String name){
        this.code = code;
        this.name = name;
        this.setText(this.name + "\nCódigo: " + this.code);
        this.setSize(180, 50);
    }
    
    void setTextName(int code, String name){
        this.code = code;
        this.name = name;
        this.setText(this.name + "\nCódigo: " + this.code);
    }
}
