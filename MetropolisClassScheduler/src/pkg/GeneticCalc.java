/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

import java.util.ArrayList;
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
        //r.setFitness(getRoomFitness(r));
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
    
    Schedule geneticAlgorithm(Schedule schedule, double crossoverProb, double mutationProb, int generations){
        ArrayList<RoomScheme> chromosomes;
        Schedule solution = new Schedule(new RoomScheme[2][3]);
        for(int a=0; a<generations; a++){
            chromosomes = fitnessCalc(schedule);
            solution = generate(chromosomes, crossoverProb, mutationProb);
            //schedule = generate(chromosomes, crossoverProb, mutationProb);
        }   
        return solution;
    }
    
    ArrayList<RoomScheme> fitnessCalc(Schedule schedule){
	ArrayList<RoomScheme> chromoList = new ArrayList<>();
	//for(int a=0; a<6; a++){
		
        RoomScheme chromoA = schedule.schedule[0][0];
        RoomScheme chromoB = schedule.schedule[1][0];
        RoomScheme chromoC = schedule.schedule[0][1];
        RoomScheme chromoD = schedule.schedule[1][1];
        RoomScheme chromoE = schedule.schedule[0][2];
        RoomScheme chromoF = schedule.schedule[1][2];

        chromoA.setFitness(checkFitness(chromoA));
        chromoB.setFitness(checkFitness(chromoB));
        chromoC.setFitness(checkFitness(chromoC));
        chromoD.setFitness(checkFitness(chromoD));
        chromoE.setFitness(checkFitness(chromoE));
        chromoF.setFitness(checkFitness(chromoF));

        chromoList.add(chromoA);
        chromoList.add(chromoB);
        chromoList.add(chromoC);
        chromoList.add(chromoD);
        chromoList.add(chromoE);
        chromoList.add(chromoF);
	//}
        return chromoList;
    }
    
    Schedule generate(ArrayList<RoomScheme> chromoList, double crossoverProb, double mutationProb){
	
	Schedule newPopulation = new Schedule(new RoomScheme[2][3]);
	
	//RoomScheme[][] newChromosomes = new RoomScheme[3][2];
	RoomScheme bestChromo = chromoList.get(0);
	RoomScheme auxParentA, auxParentB, auxChromo;
	
	int bestChromoPost = 0;
        int parentAPost, parentBPost;
	int populationCount = 0;
	int crossPoint, a1, b1, a2, b2, auxMut;
	
	Iterator<RoomScheme> itr = chromoList.listIterator();
	
	ArrayList<RoomScheme> parents = new ArrayList<>();
	ArrayList<Subject> auxSubjectA = new ArrayList<>();
	ArrayList<Subject> auxSubjectB = new ArrayList<>();
	
	Subject auxSubject;
	
	Random rand = new Random();
	
	//Take best chromosome has it is
	while(itr.hasNext()){
		auxChromo = itr.next();
		if(auxChromo.getFitness() > bestChromo.getFitness()){
			bestChromo = auxChromo;
			bestChromoPost = chromoList.indexOf(bestChromo);
		}
	}
	
	switch(bestChromoPost){
		
		case 0:
                    newPopulation.schedule[0][0] = bestChromo;
		break;
		
		case 1:
                    newPopulation.schedule[1][0] = bestChromo;
		break;
		
		case 2:
                    newPopulation.schedule[0][1] = bestChromo;
		break;
		
		case 3:
                    newPopulation.schedule[1][1] = bestChromo;
		break;
		
		case 4:
                    newPopulation.schedule[0][2] = bestChromo;
		break;
		
		case 5:
                    newPopulation.schedule[1][2] = bestChromo;
		break;
	}
	
	
	while(populationCount<5){
            while(parents.size() != 2){
                parentAPost = rand.nextInt(6);
                parentBPost = rand.nextInt(6);
                while(parentBPost == parentAPost){
                    parentBPost = rand.nextInt(6);
                }
                RoomScheme chromoCompA = chromoList.get(parentAPost);
                RoomScheme chromoCompB = chromoList.get(parentBPost);

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
                    auxParentA = parents.get(0);
                    auxParentB = parents.get(1);
                    for(int a=0; a<2; a++){
                      crossPoint=rand.nextInt(6);
                      for(int  b=0; b<5; b++){
                            auxSubjectA.add(auxParentA.rooms[b][crossPoint]);
                            auxSubjectB.add(auxParentB.rooms[b][crossPoint]);
                      }
                      for(int c=0; c<5; c++){
                            auxParentA.rooms[c][crossPoint] = auxSubjectA.remove(0);
                            auxParentB.rooms[c][crossPoint] = auxSubjectB.remove(0);
                      }
                    }

                    parents.set(0,auxParentA);
                    parents.set(1,auxParentB);
            }


            while(parents.size() > 0){
                    auxParentA = parents.remove(0);

                    //Mutation
                    if(mutationProb > rand.nextFloat()){
                            a1 = rand.nextInt(5);
                            b1 = rand.nextInt(6);
                            a2 = rand.nextInt(5);
                            b2 = rand.nextInt(6);
                            auxSubject = auxParentA.rooms[a1][b1];
                            auxParentA.rooms[a1][b1] = auxParentA.rooms[a2][b2];
                            auxParentA.rooms[a2][b2] = auxSubject;
                    }

                    //Add new chromosome to solution
                    for(int a=0; a<2; a++){
                            for(int b=0; b<3; b++){
                                    if(newPopulation.schedule[a][b] == null){
                                            newPopulation.schedule[a][b] = auxParentA;
                                            populationCount++;
                                    }
                            }
                    }
            }
	}
	
	return newPopulation;
    }
    
    int checkFitness(RoomScheme chromo){
        Subject subject;
        int fitness = 0;
        ArrayList<Subject> subjectsChecked = new ArrayList<>();
        //Check conditions for 1 Subjects
        for(int a=0; a<5; a++){
          for(int b=0; b<6; b++){
            subject = chromo.rooms[a][b];
            fitness =+ 1-(checkHours(chromo, subject.getCode())+checkRepeat(chromo, subject.getCode(), a)+checkConsecutives(chromo, subject.getCode(), a));                
          }
        }
        return fitness;
    }

    int checkHours(RoomScheme chromo, int code){
      int counter=0;
      for(int a=0; a<5; a++){
        for(int b=0; b<6; b++){
          if(chromo.rooms[a][b].getCode() == code){
            counter++;
          }
        }
      }
      return counter;
    }

    int checkRepeat(RoomScheme chromo, int code, int day){
      int counter=0;
      for(int a=0; a<6; a++){
        if(chromo.rooms[day][a].getCode() == code){
          counter++;
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
              counter++;
            }
          }
        break;

        case 1:
          for(int a=0; a<6; a++){
            if(chromo.rooms[0][a].getCode() == code || chromo.rooms[2][a].getCode() == code){
              counter++;
            }
          }
        break;

        case 2:
          for(int a=0; a<6; a++){
            if(chromo.rooms[1][a].getCode() == code || chromo.rooms[3][a].getCode() == code){
              counter++;
            }
          }
        break;

        case 3:
          for(int a=0; a<6; a++){
            if(chromo.rooms[2][a].getCode() == code || chromo.rooms[4][a].getCode() == code){
              counter++;
            }
          }
        break;

        case 4:
          for(int a=0; a<6; a++){
            if(chromo.rooms[3][a].getCode() == code || chromo.rooms[0][a].getCode() == code){
              counter++;
            }
          }
        break;
      }
      return counter;
    }
}
