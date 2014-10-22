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
public class Data {
    // You will say "Are you kidding me?! If there's no classes on Fridays after 16:00!!"
    // Yep, you're right... It's only an example! ;)
    DataCalc dc = new DataCalc();
    
    int[] code = {0, 0, 0, 0, 0, 0, 2025995, 2025972, 2022615, 2016716, 
                  2025970, 1000047, 2016722, 2025994, 2025960, 2025966};
    
    String[] subjects = {"Libre", "Libre", "Libre", "Libre", "Libre", "Libre", 
                         "Sistemas Inteligentes", "Criptografía",
                         "Alemán I", "Arquitectura de Software", "Modelos y Simulación",
                         "Inglés IV", "Computación Paralela", "Sistemas de Comunicación",
                         "Computación Visual", "Lenguajes de Programación"};
    
    int[] slots = dc.generateNormalDistSlots(16, 30, 5);
    Subject[] subject = dc.generateSubjectGroup(16, code, slots, subjects);
}