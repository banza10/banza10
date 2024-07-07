
package examserver;

import ServerThreads.tut.ServerThreads;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExamServer {

    
    public static void main(String[] args) {
        
        ServerSocket serverSocket = null;
        
        ExecutorService service = Executors.newFixedThreadPool(2);
        
        Socket socket = null;
        
        BufferedReader bufferedReader = null;
        
        PrintWriter out = null;
        
        System.out.println("Waiting for Connection ");
        try {
            
              serverSocket = new ServerSocket(9091);
            
            while(true){
                
            
            socket = serverSocket.accept();
            
            System.out.println(" Connection established ");
            
            ServerThreads t = new ServerThreads(socket);
            
            
            service.execute(t);
            
           
            
        }
            
        } catch (IOException ex) {
            Logger.getLogger(ExamServer.class.getName()).log(Level.SEVERE, null, ex);
        
            
        
        
        }finally{
        
               try { 
                
               socket.close();
                    
                
                } catch (IOException ex) {
                    Logger.getLogger(ExamServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            
        
        }
        
    }
    
}
