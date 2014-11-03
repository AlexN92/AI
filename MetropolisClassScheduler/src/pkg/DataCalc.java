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
    
    Schedule generateRandomSchedule(int x, int y, int i, int j){
        Schedule s = new Schedule(new RoomScheme[x][y]);
        for(int k=0; k<x; k++){
            for(int m=0; m<y; m++){
                s.schedule[k][m] = generateRandomScheme(i, j, d.subject);
            }
        } return s;
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
    
    int codeSetting(int x){
        return (int)(x+x +1 -Math.pow(-1, x))/4;
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
    Schedule MetropolisAlgorithm(Schedule s, double coolRate, int quota){
        Schedule best = new Schedule(s.schedule);
        int ce = s.getFitness();
        double temp = s.schedule.length * s.schedule[0].length *
                      s.schedule[0][0].rooms.length * s.schedule[0][0].rooms[0].length;
        /*
                     - ln(Temperature)
            Cycles = -----------------
                      ln(1 - coolRate)
        */
        best.setCycles((int)(-1*(Math.log(temp)/Math.log(1-coolRate))));
        while(temp > 1){
            for (RoomScheme[] schedule : best.schedule) {
                for (RoomScheme sch: schedule) {
                    for (Subject[] room : sch.rooms) {
                        for (int m = 0; m < sch.rooms[0].length; m++) {
                            if (classCount(sch, 0) > quota){
                                for(int w=0; w<(classCount(sch, 0)-quota); w++){
                                    int z = (int)(Math.random()*(classCount(sch, 0) - quota));
                                    Subject t = new Subject(d.code[z], (int)n.Normal(30, 5), 1, d.subjects[z]);
                                    room[m] = t;
                                }
                            } if (classCount(sch, room[m].getCode()) > 2 && room[m].getCode() != 0) {
                                room[m].setGroup(codeSetting(m)+1);
                                for(int i=m; i>=0; i--){
                                    if(room[m].getGroup() == room[i].getGroup()){
                                        room[m].setMaxStudents((int)n.Normal(30, 5));
                                        room[i].setMaxStudents(room[m].getMaxStudents());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // if exp((currentEnergy - newEnergy) / temperature) > u ~ Uniform(0,1)
            if (acceptanceProbability(ce, best.getFitness(), temp) > Math.random()){
                s.schedule = best.schedule;
            }
            // if fitness(new) > fitness(current)
            if(getScheduleFitness(best) > getScheduleFitness(s)){
                best = s;
                ce = best.getFitness();
            } temp *= 1 - coolRate;
        } return best;
    }
}
