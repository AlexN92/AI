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
    Data d = new Data();
    Normal n = new Normal();
    
    // Generates a random room scheme, using the count of rooms and the Subjects
    // to sort.
    RoomScheme generateRandomScheme(int days, int classTimes, Subject[] s){
        Subject[][] sub = new Subject[days][classTimes];
        RoomScheme r;
        
        for(int i=0; i<days; i++){
            for(int j=0; j<classTimes; j++){
                sub[i][j] = s[(int)(Math.random()*(s.length))];
            }
        } r = new RoomScheme(sub);
        r.setFitness(getRoomFitness(r));
        return r;
    }
    
    // How many free rooms are there in the room scheme for a specific
    // class time, i.e. Thursday at 11:00 - 13:00.
    int getRoomFitness(RoomScheme r){
        int roomFitness = maxFitness(r);
        for (Subject[] room : r.rooms) {
            for (Subject room1 : room) {
                if (room1.getCode() == 0) roomFitness --;
                if (classCount(r, room1.getCode()) > 2) roomFitness --;
            }
        } return roomFitness;
    }
    
    // Counts how many times is a class registered on a scheme
    int classCount(RoomScheme r, int code){
        int count = 0;
        for (Subject[] room : r.rooms) {
            for (int j = 0; j<r.rooms[0].length; j++) {
                if (room[j].getCode() == code) {
                    count ++;
                }
            }
        } return count;
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
                r[k][m] = new RoomOutput(s.schedule[i][j].rooms[k][m].getCode(),
                                         s.schedule[i][j].rooms[k][m].getName(),
                                         s.schedule[i][j].rooms[k][m].getMaxStudents(),
                                         s.schedule[i][j].rooms[k][m].getGroup());
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
                r[k][m].setTextName(s.schedule[i][j].rooms[k][m].getCode(),
                                    s.schedule[i][j].rooms[k][m].getName(),
                                    s.schedule[i][j].rooms[k][m].getMaxStudents(),
                                    s.schedule[i][j].rooms[k][m].getGroup());
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
    
    double acceptanceProbability(int energy, int newEnergy, double temperature){
        if(newEnergy < energy) return 1;
        return Math.exp((energy - newEnergy) / temperature);
    }
    
    // Metropolis' Algorithm - Annealing Simulation
    Schedule MetropolisAlgorithm(Schedule s){
        Schedule best = new Schedule(s.schedule);
        double coolRate = 0.003;
        double temp = s.schedule.length * s.schedule[0].length *
                      s.schedule[0][0].rooms.length * s.schedule[0][0].rooms[0].length;
        
        while(temp > 1){
            int ce = 0;
            for (RoomScheme[] schedule : s.schedule) {
                for (int j = 0; j<s.schedule[0].length; j++) {
                    if (acceptanceProbability(ce, schedule[j].getFitness(), temp) > Math.random()) {
                        for (Subject[] room : schedule[j].rooms) {
                            for (int m = 0; m < schedule[j].rooms[0].length; m++) {
                                if (classCount(schedule[j], room[m].getCode()) > 1) {
                                    room[m].setMaxStudents((int)n.Normal(30, 5));
                                    room[m].setGroup(room[m].getGroup() + 1);
                                }
                                if (schedule[j].getFitness() < 0 && classCount(schedule[j], 0) > (s.schedule[0][0].rooms.length * s.schedule[0][0].rooms[0].length / 3)) {
                                    if (room[m].getCode() == 0) {
                                        int x = (int)(d.code.length * Math.random());
                                        Subject t = new Subject(d.code[x], (int)n.Normal(30, 5), 1, d.subjects[x]);
                                        room[m] = t;
                                    }                                  
                                }
                            }
                        }
                    }
                    if(getScheduleFitness(s) < getScheduleFitness(best)){
                        s = best;
                    }
                    ce = schedule[j].getFitness();
                }
            }
            temp *= 1 - coolRate;
        } return best;
    }
}
