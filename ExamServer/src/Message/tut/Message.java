/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Message.tut;

/**
 *
 * @author dbanz
 */
public class Message {
    
    Integer number;
    String message;

    public Message(Integer number, String message) {
        this.number = number;
        this.message = message;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" + "number=" + number + ", message=" + message + '}';
    }
    
    
    
}
