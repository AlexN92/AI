/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Juancho
 */
public class GeneticCalc {
    
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
        } 
        r = new RoomScheme(sub);
        r.setFitness(0);
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
    
    int getScheduleFitness(Schedule s){
        int scheduleFitness = 0;
        for (RoomScheme[] schedule : s.schedule) {
            for (RoomScheme sch: schedule) {
                scheduleFitness += sch.getFitness();
            }
        } return scheduleFitness;
    }
    
    ArrayList<Schedule> geneticAlgorithm(ArrayList<Schedule> schedule, double crossoverProb, double mutationProb, int generations, int population){
        ArrayList<Schedule> chromosomes;
        ArrayList<Schedule> solution = new ArrayList<>();
        for(int a=0; a<generations; a++){
            chromosomes = fitnessCalc(schedule);
            solution = generate(chromosomes, crossoverProb, mutationProb, population);
        }   
        return solution;
    }
    
    ArrayList<Schedule> fitnessCalc(ArrayList<Schedule> schedule){
	ArrayList<Schedule> scheduleFit = new ArrayList<>();
	Schedule chromoSchedule;
        
        //for(int i=0; i)
        
        for(int a=0; a<schedule.size(); a++){
	
            chromoSchedule = schedule.get(a);
            RoomScheme roomA = chromoSchedule.schedule[0][0];
            RoomScheme roomB = chromoSchedule.schedule[1][0];
            RoomScheme roomC = chromoSchedule.schedule[0][1];
            RoomScheme roomD = chromoSchedule.schedule[1][1];
            RoomScheme roomE = chromoSchedule.schedule[0][2];
            RoomScheme roomF = chromoSchedule.schedule[1][2];

            roomA.setFitness(checkFitness(roomA));
            roomB.setFitness(checkFitness(roomB));
            roomC.setFitness(checkFitness(roomC));
            roomD.setFitness(checkFitness(roomD));
            roomE.setFitness(checkFitness(roomE));
            roomF.setFitness(checkFitness(roomF));

            chromoSchedule.schedule[0][0] = roomA;
            chromoSchedule.schedule[1][0] = roomB;
            chromoSchedule.schedule[0][1] = roomC;
            chromoSchedule.schedule[1][1] = roomD;
            chromoSchedule.schedule[0][2] = roomE;
            chromoSchedule.schedule[1][2] = roomF;
            
            //chromoSchedule.setFitness(roomA.getFitness());
            chromoSchedule.setFitness(roomA.getFitness() + roomB.getFitness() + roomC.getFitness() + roomD.getFitness()+
                                      roomE.getFitness() + roomF.getFitness());
            
            scheduleFit.add(chromoSchedule);
	}
        return scheduleFit;
    }
    
    ArrayList<Schedule> generate(ArrayList<Schedule> chromoList, double crossoverProb, double mutationProb, int population){
	
	ArrayList<Schedule> newPopulation = new ArrayList<>();
	Schedule bestChromo = chromoList.get(0);
        Schedule auxParentA, auxParentB; // auxChromo;
        
	int populationCount = 0;
	int crossPoint, a1, b1, a2, b2;
	
	//Iterator<RoomScheme> itr = chromoList.listIterator();
        //Iterator<Schedule> itr = chromoList.listIterator();
	
	ArrayList<Schedule> parents = new ArrayList<>();
	ArrayList<RoomScheme> auxRoomA = new ArrayList<>();
	ArrayList<RoomScheme> auxRoomB = new ArrayList<>();
	
	RoomScheme auxScheme;
	
	Random rand = new Random();
	
	//Take best chromosome has it is
	Collections.sort(chromoList);
        
        if(population%2 == 0){
            newPopulation.add(chromoList.get(0));
            newPopulation.add(chromoList.get(1));
            populationCount+=2;
        }
        else{
            newPopulation.add(chromoList.get(0));
            populationCount++;
        }
        
	while(populationCount<population){
            
            while(parents.size() != 2){
                Schedule chromoCompA = chromoList.get(rand.nextInt(population)); 
                Schedule chromoCompB = chromoList.get(rand.nextInt(population)); 
                
                //Choose different parents
                while(chromoCompA.equals(chromoCompB)){
                    chromoCompB = chromoList.get(rand.nextInt(population));
                }
                
                //Tournament selection
                if(chromoCompA.getFitness() > chromoCompB.getFitness()){
                        parents.add(chromoCompA);
                }
                else{
                        parents.add(chromoCompB);
                }
            } 

            //Generate new chromosomes
            if(crossoverProb > rand.nextFloat()){
                    auxParentA = parents.remove(0);
                    auxParentB = parents.remove(0);
                    for(int a=0; a<2; a++){
                      crossPoint=rand.nextInt(2);
                      for(int  b=0; b<3; b++){
                            auxRoomA.add(auxParentA.schedule[crossPoint][b]);
                            auxRoomB.add(auxParentB.schedule[crossPoint][b]);
                      }
                      for(int c=0; c<3; c++){
                            auxParentA.schedule[crossPoint][c] = auxRoomA.remove(0);
                            auxParentB.schedule[crossPoint][c] = auxRoomB.remove(0);
                      }
                    }
                    
                    //New childs
                    parents.add(auxParentA);
                    parents.add(auxParentB);
            }

            while(parents.size() > 0){
                    auxParentA = parents.remove(0);

                    //Mutation
                    if(mutationProb > rand.nextFloat()){
                            a1 = rand.nextInt(2);
                            b1 = rand.nextInt(3);
                            a2 = rand.nextInt(2);
                            b2 = rand.nextInt(3);
                            
                            auxScheme = auxParentA.schedule[a1][b1];
                            auxParentA.schedule[a1][b1] = auxParentA.schedule[a2][b2];
                            auxParentA.schedule[a2][b2] = auxScheme;
                    }
                    
                    //Add new chromosome to solution
                    if(population-2 > populationCount){
                        newPopulation.add(auxParentA);
                        populationCount++;
                    }
                    else{
                        newPopulation.add(auxParentA);
                        populationCount++;
                        break;
                    }   
            }
	}	
        
        Collections.sort(newPopulation);
        
        return newPopulation;
    }
    
    int checkFitness(RoomScheme chromo){
        Subject subject;
        int fitness = 0;
        ArrayList<Integer> subjectsChecked = new ArrayList<>();
        //Check conditions for 1 Subjects
        for(int a=0; a<5; a++){
          for(int b=0; b<6; b++){
            subject = chromo.rooms[a][b];
            if(subjectsChecked.indexOf(subject.getCode()) == -1){
                fitness = fitness + checkHours(chromo, subject.getCode())+checkRepeat(chromo, subject.getCode(), a)+checkConsecutives(chromo, subject.getCode(), a);                
            }
            subjectsChecked.add(subject.getCode());
          }
        }
        return fitness;
    }

    int checkHours(RoomScheme chromo, int code){
      int counter=0;
      int auxCounter=0;
      for(int a=0; a<5; a++){
        for(int b=0; b<6; b++){
          if(chromo.rooms[a][b].getCode() == code){
              auxCounter++;
              if(auxCounter > 2){
                  counter--;
              }
          }
        }
      }
      return counter;
    }

    int checkRepeat(RoomScheme chromo, int code, int day){
      int counter=0;
      for(int a=0; a<6; a++){
        if(chromo.rooms[day][a].getCode() == code){
          counter--;
        }
      }
      return counter;
    }

    int checkConsecutives(RoomScheme chromo, int code, int day){
      int counter=0;

      switch(day){
        case 0:
          for(int a=0; a<6; a++){
            if(chromo.rooms[4][a].getCode() == code || chromo.rooms[1][a].getCode() == code){
              counter--;
            }
          }
        break;

        case 1:
          for(int a=0; a<6; a++){
            if(chromo.rooms[0][a].getCode() == code || chromo.rooms[2][a].getCode() == code){
              counter--;
            }
          }
        break;

        case 2:
          for(int a=0; a<6; a++){
            if(chromo.rooms[1][a].getCode() == code || chromo.rooms[3][a].getCode() == code){
              counter--;
            }
          }
        break;

        case 3:
          for(int a=0; a<6; a++){
            if(chromo.rooms[2][a].getCode() == code || chromo.rooms[4][a].getCode() == code){
              counter--;
            }
          }
        break;

        case 4:
          for(int a=0; a<6; a++){
            if(chromo.rooms[3][a].getCode() == code || chromo.rooms[0][a].getCode() == code){
              counter--;
            }
          }
        break;
      }
      return counter;
    }
}
