/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Posiljalac {
    private Socket s;

    public Posiljalac(Socket s) {
        this.s = s;
    }
    
    public void posalji(Object obj){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
             oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
}
