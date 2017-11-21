/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.client;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import seriac.poo.structs.*;

/**
 *
 * @author Alex
 */
public class ClientPeer extends Thread  {
    String user;
    Socket clientsoc;
    
    public ClientPeer(String util,Socket soc){
        user=util;
        clientsoc=soc;
    }
    
   
   
    void sendMessage(String message) throws IOException{
        
        try{
            
            ObjectOutputStream out=null;
            out=new ObjectOutputStream(clientsoc.getOutputStream());
            
            Message msg=new Message(user,message);
            out.writeObject(msg);
            
            
            
        }
        catch(SocketException e){
            e.printStackTrace();
            
        }
    }
    
    void sendMessage(String message,String recipient) throws IOException{
           
            ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(clientsoc.getOutputStream());
            
            PrivateMessage privmsg=new PrivateMessage(user,message,recipient);
            out.writeObject(privmsg);
            }
        catch(SocketException e){
            e.printStackTrace();
        }
        finally{
            try{
                out.close();
               
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void run(){
        try {
            ObjectInputStream in=new ObjectInputStream(clientsoc.getInputStream());
            Message temp;
            temp=(Message) in.readObject();
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        
    }
}
