/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seriac.poo.server.config;

import java.io.*;
import java.lang.Exception.*;
import static java.lang.Integer.parseInt;
import java.util.Properties;
import seriac.poo.server.exceptions.*;


/**
 *
 * @author student
 */
public class ServerConfig {

    int TcpPort;
    int MaxClients;
    public static String filename;

    void configureaza() throws FileNotFoundException, IOException, UnknownKeyException, MissingKeyException, InvalidFormatException{
        
        String tcp;
        String client;
        Properties prop=new Properties();
        InputStream input=null;
        input = new FileInputStream(filename);
            prop.load(input);

            if (prop.keySet().size() >= 3) {
                throw new UnknownKeyException();
            }
            tcp = prop.getProperty("TCP_PORT");
            if (tcp == null) {
                throw new MissingKeyException();
            }
            try {
               TcpPort = parseInt(tcp);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            client = prop.getProperty("MAX_CLIENTS");
            if (client == null) {
                throw new MissingKeyException();
            }
            try {
                MaxClients = parseInt(client);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException();
    }
    }
    
    public ServerConfig() throws FileNotFoundException, IOException, MissingKeyException, InvalidFormatException, UnknownKeyException {
          filename = "server.conf";
          configureaza();
       
    }

    int getTcpPort() {
        return TcpPort;
    }

   
    int getMaxClients() {
        return MaxClients;
    }


    public ServerConfig(String path) throws FileNotFoundException, IOException, UnknownKeyException, MissingKeyException, InvalidFormatException {
        filename = path;
       configureaza();
    }
    
}
