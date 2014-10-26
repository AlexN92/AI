/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Data {
    // You will say "Are you kidding me?! If there's no classes on Fridays after 16:00!!"
    // Yep, you're right... It's only an example! ;)
    Normal n = new Normal();
    
    
    int[] code = {0, 0, 0, 0, 0, 0, 2025995, 2025972, 2022615, 2016716, 
                  2025970, 1000047, 2016722, 2025994, 2025960, 2025966};
    
    String[] subjects = {"Libre", "Libre", "Libre", "Libre", "Libre", "Libre", 
                         "Sistemas Inteligentes", "Criptografía",
                         "Alemán I", "Arquitectura de Software", "Modelos y Simulación",
                         "Inglés IV", "Computación Paralela", "Sistemas de Comunicación",
                         "Computación Visual", "Lenguajes de Programación"};
    
    Subject[] generateSubjectGroup(int size, int[] code, int[] slot, String[] name){
        Subject[] s = new Subject[size];
        ArrayList<Subject> arr = new ArrayList<>(size);
        int t = 1;
        for(int i=0; i<size; i++){
            arr.add(new Subject(code[i], slot[i], t, name[i]));
            s[i] = arr.get(i);
        }
        
        for(int i=0; i<size; i++){
            if(arr.get(i).getCode() == arr.get(size-1).getCode()){
                s[i].setGroup(t+1);
            } if(arr.get(i).getCode() == 0){
                s[i].setMaxStudents(0);
            }
        } return s;
    }
    
    int[] generateNormalDistSlots(int size, int avg, int stdDev){
        int[] slot = new int[size];
        
        for(int i=0; i<slot.length; i++){
            slot[i] = (int) n.Normal(avg, stdDev);
        } return slot;
    }
    
    int[] slots = generateNormalDistSlots(16, 30, 5);
    Subject[] subject = generateSubjectGroup(16, code, slots, subjects);
}