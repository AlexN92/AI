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

// A schedule contains the Room scheduling for a specific class time, i.e. 
// Monday 7 - 9 am.
public class Schedule implements Comparable<Schedule>{
    RoomScheme[][] schedule;
    private int fitness;
    
    public Schedule(RoomScheme[][] schedule) {
        this.schedule = schedule;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Schedule sched) {
        int result = 0;
        if(this.fitness<sched.fitness){
            result = 1;
        }
        else if(this.fitness>sched.fitness){
            result = -1;
        }
        return result;
    }
}
