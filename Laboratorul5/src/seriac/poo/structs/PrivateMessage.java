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
public class PrivateMessage extends Message implements Serializable{
    private final String destinatar;
    
    public PrivateMessage(String a,String b,String c){
        super(a,b);
        destinatar=c;
    }
    
    @Override
    public String toString(){
        return "(priv)"+super.toString();
    }
    
}
