/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seriac.poo.server.config;
import seriac.poo.structs.*;
import java.io.*;
import java.lang.Exception.*;
import static java.lang.Integer.parseInt;
import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import static seriac.poo.server.config.ServerConfig.*;
import seriac.poo.server.exceptions.*;
/**
 *
 * @author student
 */
public class Server {
    public static int Tcp;
    public static int MaxClients;
    public static ArrayList <ServerPeer> clientlist;
    public Server(int a,int b) throws IOException, FileNotFoundException, MissingKeyException, InvalidFormatException, UnknownKeyException{
        clientlist=new ArrayList<ServerPeer>();
        ServerConfig server = new ServerConfig();
        ServerSocket srv=new ServerSocket(server.getTcpPort());
        Tcp=a;
        MaxClients=b;
    }
    
    public void listen() throws IOException, FileNotFoundException, MissingKeyException, InvalidFormatException, UnknownKeyException{
        int i=0;
        Server temp=new Server(Tcp,MaxClients);
        ServerSocket e=new ServerSocket(Tcp);
        try{
        while(i<MaxClients){
        Socket s=e.accept();
        i++;
        ServerPeer th=new ServerPeer(temp,s);
        Thread _th=new Thread(th);
        _th.start();
        _th.join();
        clientlist.add(th);
        }
        
        
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void dispatch(Message e) throws IOException{
      for(ServerPeer client :clientlist ) {
          ObjectOutputStream oos=new ObjectOutputStream(client.servsock.getOutputStream());
          oos.writeObject(e);
          
      } 
    }
    
    public void removeClient(ServerPeer s){
        for(ServerPeer client:clientlist){
            int i=0;
            if(client.interrupted()==true){
                clientlist.remove(i);
            }
            i++;
        }
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, MissingKeyException, InvalidFormatException, UnknownKeyException {
        ServerConfig server=new ServerConfig();
        Server temp=new Server(server.getTcpPort(),server.getMaxClients());
        ServerSocket srv=new ServerSocket(temp.Tcp);
        temp.listen();
        
        
        
    }
}