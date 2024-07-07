/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dbanz
 */
public class ExamClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        String data;
        
        
        try {
            socket = new Socket("localhost",9091);
            
            System.out.println("connected");
            
            
            bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream()));
            
            out = new PrintWriter( new BufferedWriter( new OutputStreamWriter( socket.getOutputStream())),true);
            
             out.println("1#hi there dan");
             
             
            data = bufferedReader.readLine();
            System.out.println(data);
            
           
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ExamClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
