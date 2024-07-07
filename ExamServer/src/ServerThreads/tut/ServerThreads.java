/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerThreads.tut;

import Database.tut.Database;
import Message.tut.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dbanz
 */
public class ServerThreads implements Runnable{
    
    private Socket socket;
    
    private Message message;
    
     BufferedReader bufferedReader = null;
        
        PrintWriter out = null;

    public ServerThreads(Socket socket) {
        this.socket = socket;
        
       
        
        try {
             bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
             
             out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
    
    

    @Override
    public void run() {
        
        try {
            
            String clientMsg = bufferedReader.readLine();
            System.out.println(clientMsg);
           
             
            String[] msg = clientMsg.split("#");
            
            Integer id = Integer.parseInt(msg[0]);
            
            String infor = msg[1];
            
            message = new Message(id, infor);
            
            try {
                Database server = new Database("jdbc:derby://localhost:1527/Messagers","Server","123");
                
                server.addMessage(message);
            } catch (SQLException ex) {
                Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThreads.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
}
