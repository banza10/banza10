/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads.tut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.ShortMessage;

/**
 *
 * @author dbanz
 */
public class ServerThreads implements Runnable{
    
    private Socket Socket;

    public ServerThreads(Socket Socket) {
        this.Socket = Socket;
        
    }
    
   

    @Override
    public void run() {
         BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader( new InputStreamReader( Socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
          PrintWriter out = null;
        try {
            out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(Socket.getOutputStream())),true);
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
          String clientMsg = null;
        try {
            
            clientMsg = bufferedReader.readLine();
            
  
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        out.println(clientMsg);
        
        
    }
    
}
