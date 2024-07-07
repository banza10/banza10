/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clientserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dbanz
 */
public class ClientServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner kb = new Scanner(System.in);
        
        BufferedReader bufferedReader = null;
        
         PrintWriter out = null;
         
         int option,num1 = 0,num2 = 0;
         
         String data,op = null;
        
         Socket soc = null;
        System.out.println("Client started  ");
        
        
        try {
           soc = new Socket("localHost",9806);
           
            
          
            
             bufferedReader = new BufferedReader(  new InputStreamReader( soc.getInputStream()));
             
             out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(soc.getOutputStream())),true);
            
             option = displayMenu();
             
             while(option != 4){
             
                 System.out.println("please enter the first number");
                 
                 num1 = kb.nextInt();
                 
                 System.out.println("please enter the second number");
                 
                 num2 = kb.nextInt();
                 
                 data = num1 + "#" + num2;
                 
                 switch(option){
                 
                     case 1 : data = data + "#" + "+";
                     op = " + ";
                     break;
                     
                     case 2 : data = data + "#" + "-";
                     op = " - ";
                     break;
                     
                     default: data = data + "#" + "*";
                     op = " * ";
                     break;
               
                 }
                 
                 out.println(data);
                 
                  String serverResponce = bufferedReader.readLine();
                 
                 System.out.println(num1 + op + num2 + " = " + serverResponce);
                 
                 
                 
                 option = displayMenu();
                 
                   
             
             }
             
           
            
        } catch (IOException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            
        
             try {
                 soc.close();
             } catch (IOException ex) {
                 Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
    
    public static int displayMenu(){
    
        int option ;
        
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Select one of the following options : " + "\n" + 
                           "--------------------------------------------" + "\n" +
                           "1 - add numbers " + "\n" + 
                           "2 - subtract numbers" + "\n" +
                           "3 = multiply numbers " + "\n" + 
                           "4 = exit " + "\n\n" + 
                           "your choice \t .");
    
    
     option= kb.nextInt();
    
    
     return option;
    
    }
    
}
