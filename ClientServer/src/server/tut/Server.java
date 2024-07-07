
package server.tut;

import Threads.tut.ServerThreads;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dbanz
 */
public class Server {
    
    
    public static void main(String[] args) {
        Socket soc = null;
        ExecutorService service = Executors.newFixedThreadPool(2);
        
        BufferedReader bufferedReader = null;
        
        PrintWriter out = null;
        
        String data,result;
        
        System.out.println("Waiting for client ");
        
        try {
           ServerSocket ss = new ServerSocket(9806);
          
            soc = ss.accept();
            System.out.println("Connection done");
            
            bufferedReader = new BufferedReader( new InputStreamReader(soc.getInputStream()));
            
            out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(soc.getOutputStream())));
            
            data = bufferedReader.readLine();
            
            while(! data.equals("END")){
            
                String[] token = data.split("#");
                
                char op = token[2].charAt(0);
                
                if( op == '+'){
                    
                    result = Integer.toString(Integer.parseInt(token[0]) + Integer.parseInt(token[1]));
                    
                }else if(op == '-'){
                
                    result = Integer.toString(Integer.parseInt(token[0]) - Integer.parseInt(token[1]));
                
                
                }else{
                
                     result = Integer.toString(Integer.parseInt(token[0]) * Integer.parseInt(token[1]));
                
                }
                
                out.println(result);
                System.out.println(result);
                System.out.println("data processed ....");
                
                
                data = bufferedReader.readLine();
                
            
            }
            
           
            
           
            
          
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
        
        
    }
    
}
