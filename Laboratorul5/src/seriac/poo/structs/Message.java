/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.structs;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Message implements Serializable {
    private final String expeditor;
    private final String mesaj;
    
    public Message(String a,String b){
        expeditor=a;
        mesaj=b;
    }
    public String getSender(){
        return expeditor;
    }
    
    @Override
   public String toString(){
       return expeditor+":"+mesaj;
   } 
    
}




