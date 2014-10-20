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
    
    int[] code = {0, 0, 0, 0, 0, 0, 2025995, 2025972, 2022615, 2016716, 
                  2025970, 1000047, 2016722, 2025994, 2025960, 2025966};
    
    String[] subjects = {"Libre", "Libre", "Libre", "Libre", "Libre", "Libre", 
                         "Sistemas Inteligentes", "Criptografía",
                         "Alemán I", "Arquitectura de Software", "Modelos y Simulación",
                         "Inglés IV", "Computación Paralela", "Sistemas de Comunicación",
                         "Computación Visual", "Lenguajes de Programación"};
    
    Subject[] subject = {new Subject(code[0], subjects[0]), new Subject(code[1], subjects[1]),
                         new Subject(code[2], subjects[2]), new Subject(code[3], subjects[3]),
                         new Subject(code[4], subjects[4]), new Subject(code[5], subjects[5]),
                         new Subject(code[6], subjects[6]), new Subject(code[7], subjects[7]),
                         new Subject(code[8], subjects[8]), new Subject(code[9], subjects[9]),
                         new Subject(code[10], subjects[10]), new Subject(code[11], subjects[11]),
                         new Subject(code[12], subjects[12]), new Subject(code[13], subjects[13]),
                         new Subject(code[14], subjects[14]), new Subject(code[15], subjects[15])};
}