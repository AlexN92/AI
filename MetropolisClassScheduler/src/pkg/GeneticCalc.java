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
public class Genetic {
    
    float crosspointProb;
    float mutationProb;
    
    void fitnessCalc(Schedule schedule){
	ArrayList<RoomScheme> chromoList = new ArrayList<RoomScheme>();
	for(int a=0; a<6; a++){
		
		RoomScheme chromoA = schedule.schedule[0][0];
		RoomScheme chromoB = schedule.schedule[0][1];
		RoomScheme chromoC = schedule.schedule[1][0];
		RoomScheme chromoD = schedule.schedule[1][1];
		RoomScheme chromoE = schedule.schedule[2][0];
		RoomScheme chromoF = schedule.schedule[2][1];
		
		
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
	}
    }
    
    Schedule selection(ArrayList<RoomScheme> chromoList){
	
	Schedule newPopulation = new Schedule(new RoomScheme[3][2]);
	
	//RoomScheme[][] newChromosomes = new RoomScheme[3][2];
	RoomScheme bestChromo = chromoList.get(0);
	RoomScheme auxParentA, auxParentB, auxChromo;
	
	int bestChromoPost = 0;
	int populationCount = 0;
	int crossPoint, a1, b1, a2, b2, auxMut;
	
	Iterator<RoomScheme> itr = chromoList.listIterator();
	
	ArrayList<RoomScheme> parents = new ArrayList<>();
	ArrayList<Subject> auxSubjectA = new ArrayList<>();
	ArrayList<Subject> auxSubjectB = new ArrayList<>();
	
	Subject auxSubject;
	
	Random rand = new Random();
	
	//Initialize new population
	for(int a=0; a<3; a++){
		for(int b=0; b<2; b++){
                    Subject[][] subs = new Subject[5][6];
                    newPopulation.schedule[a][b].rooms = subs;
		}
	}
	
	//newPopulation = new Schedule(newChromosomes);
	
	//Buscar el mejor y pasarlo tal cual
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
                    newPopulation.schedule[0][1] = bestChromo;
		break;
		
		case 2:
                    newPopulation.schedule[1][0] = bestChromo;
		break;
		
		case 3:
                    newPopulation.schedule[1][1] = bestChromo;
		break;
		
		case 4:
                    newPopulation.schedule[2][0] = bestChromo;
		break;
		
		case 5:
                    newPopulation.schedule[2][1] = bestChromo;
		break;
	}
	
	
	while(populationCount<5){
            while(parents.size() != 2){
                RoomScheme chromoCompA = chromoList.get(rand.nextInt(6));
                RoomScheme chromoCompB = chromoList.get(rand.nextInt(6));

                //Tournament selection
                if(chromoCompA.getFitness() > chromoCompB.getFitness()){
                        parents.add(chromoCompA);
                }
                else{
                        parents.add(chromoCompB);
                }
            } 

            //Generate new chromosomes
            if(crosspointProb > rand.nextFloat()){
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
                    for(int a=0; a<5; a++){
                            for(int b=0; b<6; b++){
                                    if(newPopulation.schedule[a][b] == null){
                                            newPopulation.schedule[a][b] = auxParentA;
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
