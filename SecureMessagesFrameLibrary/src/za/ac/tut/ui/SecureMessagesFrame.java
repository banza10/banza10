/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;


/**
 *
 * @author MemaniV
 */
public class SecureMessagesFrame extends JFrame {
    //menu bar
    private JMenuBar menuBar;
    
    //menu 
    private JMenu fileMenu;
    
    //menu items
    private JMenuItem openFileMenuItem;
    private JMenuItem encryptFileMenuItem;
    private JMenuItem saveEncryptedFileMenuItem;
    private JMenuItem clearFileMenuItem;
    private JMenuItem exitFileMenuItem;
    
    //panels
    private JPanel headingPnl;
    private JPanel plainTextPnl;
    private JPanel encryptedTextPnl;
    private JPanel mainPnl;
    
    //label
    private JLabel headingLbl;
    
    //text area
    private JTextArea plainMsgTxtArea;
    private JTextArea encryptedMsgTxtArea;

    //text area
    private JScrollPane scrollablePlainMsgTxtArea;
    private JScrollPane scrollableEncryptedMsgTxtArea;
        
    public SecureMessagesFrame(){
        //initialise the frame
        setTitle("Secure Messages");
        setSize(50, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //create menu bar
        menuBar = new JMenuBar();
        
        //create menu 
        fileMenu = new JMenu("File");
        
        //create menu items
        openFileMenuItem = new JMenuItem("Open file...") ;
        openFileMenuItem.addActionListener(new OpenFileMenuItemListener());
        
        encryptFileMenuItem = new JMenuItem("Encrypt message...") ;
        encryptFileMenuItem.addActionListener(new EncryptFileMenuItemListener());
        
        saveEncryptedFileMenuItem = new JMenuItem("Save encrypted message...") ;
        saveEncryptedFileMenuItem.addActionListener(new SaveEncryptedFileMenuItemListener());
        
        clearFileMenuItem = new JMenuItem("Clear") ;
        clearFileMenuItem.addActionListener(new ClearFileMenuItemListener());

        exitFileMenuItem = new JMenuItem("Exit") ;
        exitFileMenuItem.addActionListener(new ExitFileMenuItemListener());
        
        //add menu items to menu
        fileMenu.add(openFileMenuItem);
        fileMenu.add(encryptFileMenuItem);
        fileMenu.add(saveEncryptedFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(clearFileMenuItem);
        fileMenu.add(exitFileMenuItem);
        
        //add menu to the menu bar
        menuBar.add(fileMenu);

        //create panels
        headingPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        plainTextPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plainTextPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Plain message"));
        
        encryptedTextPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        encryptedTextPnl.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Encrypted message"));
        
        mainPnl = new JPanel(new BorderLayout());
        
        //create label
        headingLbl = new JLabel("Message Encryptor");
        headingLbl.setForeground(Color.BLUE);
        headingLbl.setFont(new Font("SERIF", Font.BOLD + Font.ITALIC, 30));
        headingLbl.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        //text areas
        plainMsgTxtArea = new JTextArea(10,30);
        plainMsgTxtArea.setEditable(false);
        
        encryptedMsgTxtArea = new JTextArea(10, 30);
        encryptedMsgTxtArea.setEditable(false);
        
        //make the text areas scrollable
        scrollablePlainMsgTxtArea = new JScrollPane(plainMsgTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableEncryptedMsgTxtArea = new JScrollPane(encryptedMsgTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //add components to panels
        headingPnl.add(headingLbl);
        plainTextPnl.add(scrollablePlainMsgTxtArea);
        encryptedTextPnl.add(scrollableEncryptedMsgTxtArea);
        
        mainPnl.add(headingPnl, BorderLayout.NORTH);
        mainPnl.add(plainTextPnl, BorderLayout.WEST);
        mainPnl.add(encryptedTextPnl, BorderLayout.EAST);
                 
        //add the menu bar to the frame
        setJMenuBar(menuBar);
        
        //add main panel to the frame
        add(mainPnl);
        
        //pack the components
        pack();
        
        //set resizable
        setResizable(false);
        
        //visible
        setVisible(true);
    }
    
    //anonymous class for opening a plain text file. It handles the Open file menu item.
    private class OpenFileMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {     
		
           File file;
           JFileChooser filechooser;
           int responce;
           BufferedReader br;
           String data, record = null;
           
           
           filechooser = new JFileChooser();
           
           responce = filechooser.showOpenDialog(SecureMessagesFrame.this);
           
           if(responce == JFileChooser.APPROVE_OPTION){
           
           file = filechooser.getSelectedFile();
           
               try {
                   
                   br = new BufferedReader( new FileReader(file));
                   
                   while((data = br.readLine() )!= null){
                   
                   record += data;
                   
                   
                   }
                   
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
           
           
           }
         
          
        }
    }

    //anonymous class for encrypting the message. It handles the Encrypt message menu item.
    private class EncryptFileMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {  
            
           String encryption = "";
           
           String message = plainMsgTxtArea.getText().toLowerCase();
           
           for( int i =0 ; i< message.length(); i ++){
               
             
               char c = message.charAt(i);
               
               if(Character.isLetter(c)){
                   
                   if( c != 'x' && c != 'y' && c != 'z'){
                   
                       c += 3;
                    
                       encryption += c;
                       
                   }else if( c == 'x'){
                   
                       c = 'a';
                       encryption += c;
                   }else if( c == 'y'){
                   
                       c = 'b';
                       encryption += c;
                       
                   }else if( c== 'z'){
                   
                       c = 'c';
                       encryption += c;
                   
                   }
                   
                   
                   
                }else{
               
               
                    encryption += c;
               }
           
           
           
           
             }
           
           
                 encryptedMsgTxtArea.setText(encryption);
            
               
               
           }
           
            
    }

    //anonymous class for saving encrypted message. It handles the Save encrypted message menu item.
    private class SaveEncryptedFileMenuItemListener implements ActionListener {

        private Connection connection;
        
        
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {    
	
              Date date = new Date();
               
                long mills = date.getTime();
                Timestamp time = new Timestamp(mills);
                
                
                Random rand = new Random();
                
                int num = rand.nextInt(20)+1;
                
                 int number = num;
                 
                 if(number == num){
                     
                     num--;
                     
                 }
                 
                    
                 
                
               
                    
                 
            
            try {
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Encryption","dan","123");
                
                
                String sql  = "INSERT INTO SAVEDENCRYPTION VALUES(?,?,?)";
               
                
                PreparedStatement ps = connection.prepareStatement(sql);
                
                ps.setInt(1,number);
                ps.setString(2, encryptedMsgTxtArea.getText());
                ps.setTimestamp(3, time);
                
                ps.executeUpdate();
                ps.close();
                
                
                
               
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
              
                
                
            
                
                
         
               }
  
    }
    //anonymous class for clearing the respective text areas. It handles the clear menu item
    private class ClearFileMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //write your code here
        }
        
    }
    
    //anonymous class for exiting the application. It handles the exit menu item
    private class ExitFileMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
           
            System.exit(0);
        }
        
    }
}
