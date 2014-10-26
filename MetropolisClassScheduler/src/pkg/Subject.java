/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

/**
 *
 * @author Alex
 */

// A Subject contains a number (a class code).
// If 0, this means a free room on a determinated class time.    
public class Subject{
    private int code, maxStudents, group;
    private String name;

    public Subject() { }
    
    public Subject(int code, int maxStudents, int group, String name) {
        this.code = code;
        this.maxStudents = maxStudents;
        this.group = group;
        this.name = name;
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public int getGroup() { return group; }
    public void setGroup(int group) { this.group = group; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
}
