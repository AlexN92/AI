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

// A schedule contains the Class scheduling for a specific room, i.e. 
// Room 203.
public class Schedule implements Comparable<Schedule>{
    RoomScheme[][] schedule;
    private int fitness, cycles;
    
    public Schedule(RoomScheme[][] schedule) {
        this.schedule = schedule;
    }

    public int getFitness() { return fitness; }
    public void setFitness(int fitness) { this.fitness = fitness; }
    
    public int getCycles() { return cycles; }
    public void setCycles(int cycles) { this.cycles = cycles; }
    
    @Override
    public int compareTo(Schedule sched) {
        int result = 0;
        if(this.fitness<sched.fitness) result = 1;
        else if(this.fitness>sched.fitness) result = -1;
        return result;
    }
}
