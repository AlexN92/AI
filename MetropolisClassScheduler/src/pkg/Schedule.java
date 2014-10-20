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
public class Schedule{
    RoomScheme[][] schedule;
    
    public Schedule(RoomScheme[][] schedule) {
        this.schedule = schedule;
    }
}
