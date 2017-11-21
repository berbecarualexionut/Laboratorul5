/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server.config;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import seriac.poo.structs.*;
import static seriac.poo.client.Client.getseriz;

/**
 *
 * @author Alex
 */
public class ServerPeer extends Thread {
    private String username;
    Socket servsock;
    public Server ser;
    public ServerPeer(Server server, Socket soc){
        servsock=soc;
        ser=server;
    }
    
   public void run(){
        try{
        while(true){
        ObjectInputStream ois;
        ois = new ObjectInputStream(servsock.getInputStream());
       Message msg=(Message) ois.readObject();
       ser.dispatch(msg);
        }
        }
        catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
   void sendMessage(Message msg) throws IOException{
       ObjectOutputStream out=new ObjectOutputStream(servsock.getOutputStream());
       out.writeObject(msg);
  
   }
   
   public void setUsername() throws IOException, ClassNotFoundException{
        ObjectInputStream in=new ObjectInputStream(servsock.getInputStream());
       PrivateMessage priv=(PrivateMessage) in.readObject();
       username=priv.getSender();
   }
   
   public String getUserName() throws IOException, ClassNotFoundException{
      return username;
       }
}
