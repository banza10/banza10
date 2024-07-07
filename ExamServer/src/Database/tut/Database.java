
package Database.tut;

import Message.tut.Message;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    
    private Connection connection;
    private Message message;

    public Database(String url , String username , String password ) throws SQLException {
        
        connection = DriverManager.getConnection( url,username, password);
        
    }
    
    public void addMessage(Message message) throws SQLException{
        
        String sql = "INSERT INTO DATABASESERVER VALUES(?,?)";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setInt(1, message.getNumber());
        ps.setString(2, message.getMessage());
        
        ps.executeUpdate();
        ps.close();
    
    
    
    }
    
    public Message getMessage(Integer number) throws SQLException{
    
        Message messages = null;

        String sql = "SELECT * FROM DATABASESERVER FROM ID = ?";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setInt(1, number);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        
            Integer numberS = rs.getInt("ID");
            
            String msg = rs.getString("INFORNATION");
            
        messages = new Message(number, msg);
        
        }
        
    
    
    return messages;
    
    }
    
    
    
    
}
