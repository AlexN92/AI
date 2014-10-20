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
public class DataCalc {
    
    // Generates a random room scheme, using the count of rooms and the Subjects
    // to sort.
    RoomScheme generateRandomScheme(int floors, int roomsPerFloor, Subject[] s){
        Subject[][] sub = new Subject[roomsPerFloor][floors];
        
        for(int i=0; i<roomsPerFloor; i++){
            for(int j=0; j<floors; j++){
                sub[i][j] = s[(int)(Math.random()*(s.length))];
            }
        } return new RoomScheme(sub);
    }
    
    // How many free rooms are there in the room scheme for a specific
    // class time, i.e. Thursday at 11:00 - 13:00.
    int getRoomFitness(RoomScheme r){
        int roomFitness = maxFitness(r);
        for (Subject[] room : r.rooms) {
            for (Subject room1 : room) {
                if (room1.getCode() == 0) roomFitness --;
            }
        } return roomFitness;
    }
    
    // A scheme's fitness
    int getScheduleFitness(Schedule s){
        int scheduleFitness = 0;
        for (RoomScheme[] schedule : s.schedule) {
            for (RoomScheme sch: schedule) {
                scheduleFitness += sch.getFitness();
            }
        } return scheduleFitness;
    }
    
    // A method that prints a scheme for a specific day and a specific time,
    // i.e. Wednesday at 11:00 - 13:00.
    RoomOutput[][] printScheme(int i, int j, Schedule s){
        RoomOutput[][] r = new RoomOutput[s.schedule[0][0].rooms.length][s.schedule[0][0].rooms[0].length];
        
        for(int k=0; k<r.length; k++){
            for(int m=0; m<r[0].length; m++){
                r[k][m] = new RoomOutput(s.schedule[j][i].rooms[k][m].getCode(), s.schedule[j][i].rooms[k][m].getName());
                r[k][m].setEditable(false);
                r[k][m].setLocation(5 + 185*k, 65 + 55*m);
            }
        } return r;
    }
    
    // A method that changes the output for a scheme, depending of the selected
    // day and time.
    void changeText(Schedule s, RoomOutput[][] r, int i, int j){
        for(int k=0; k<r.length; k++){
            for(int m=0; m<r[0].length; m++){
                r[k][m].setTextName(s.schedule[j][i].rooms[k][m].getCode(),
                                    s.schedule[j][i].rooms[k][m].getName());
            }
        }
    }
    
    // Using the room scheme's fitness, fullRooms(int fitness) evaluates
    // if the rooms are available or full.
    boolean fullRooms(int fitness){
        return fitness == 0;
    }
    
    // The maximum possible fitness for a room scheme.
    int maxFitness(RoomScheme r){
        return r.rooms.length * r.rooms[0].length;
    }
    
    // Metropolis' Algorithm - Annealing Simulation
    void MetropolisAlgorithm(Schedule s){
        int[][] roomFitness = new int[s.schedule.length][s.schedule[0].length];
        for(int i=0; i<roomFitness.length; i++){
            for(int j=0; j<roomFitness[0].length; j++){
                roomFitness[i][j] = getRoomFitness(s.schedule[i][j]);
                System.out.print(roomFitness[i][j] + " ");
            } System.out.println();
        }
    }
}
