/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class Client {
    public String utilizator;
    public static boolean seriz;
    
    public static boolean getseriz(){
        return seriz;
    }
    
    
    
    
    
    public static void main(String[] args) throws IOException{
        Client c=new Client();
        c.utilizator=new Scanner(System.in).nextLine();
        Socket sock=new Socket("",9000);
        ClientPeer cp=new ClientPeer(c.utilizator,sock);
        String reader=null;
       
        while(true){
          
           reader=new BufferedReader(new InputStreamReader(System.in)).readLine();
           if((reader.equals("/q"))){
                    break;
                    
            }
            else{
        System.out.println(reader);
        if(reader.startsWith("/w")){
        seriz=true;
        char a=' ';
        int b=reader.indexOf(a);
        int l=reader.indexOf(b,a);
        String msg=reader.substring(l+1);
        String recip=reader.substring(b,l);
        cp.sendMessage(msg,recip);
        }  
        else{
        seriz=false;
       
        String msg=reader;
        cp.sendMessage(msg);}
        }
            
            
        }
                
       
    }
    
    
}
