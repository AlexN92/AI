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

// A room scheme contains a Subject scheduling.
// If a code's Subject in the room scheme is 0, it means the room
// is free in a specific class time.
public class RoomScheme{
    private boolean isFull;
    private int fitness;
    Subject[][] rooms;

    public RoomScheme() { }
    
    public RoomScheme(Subject[][] s) {
        this.rooms = s;
    }
    
    public boolean isFull() { return isFull; }
    public void setIsFull(boolean isFull) { this.isFull = isFull; }

    public int getFitness() { return fitness; }
    public void setFitness(int fitness) { this.fitness = fitness; }
}
