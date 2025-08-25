/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.pokreniServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.server.niti.ObradaKlijentskihZahteva;

/**
 *
 * @author milos
 */
public class Server extends Thread{
    
    private ServerSocket serverSocket;
    private boolean kraj=false;
    private List<ObradaKlijentskihZahteva>lista=new ArrayList<>();

    public List<ObradaKlijentskihZahteva> getLista() {
        return lista;
    }

    public void setLista(List<ObradaKlijentskihZahteva> lista) {
        this.lista = lista;
    }

    
    @Override
    public void run() {
        kraj=false;
        try {
            System.out.println("Soket je otvoren");
            serverSocket=new ServerSocket(9000);
            while(!kraj){
                if(serverSocket==null||serverSocket.isClosed()){
                    return;
                }
                Socket s=serverSocket.accept();
                System.out.println("Klijent je povezan");
                ObradaKlijentskihZahteva nit=new ObradaKlijentskihZahteva(s);
                lista.add(nit);
                nit.start();
            }
        } catch (IOException ex) {
            if (kraj) {
                System.out.println("Soket je zatvoren");
            } else {
                ex.printStackTrace(); 
            }
        }
    }
    
 
    
    public void zaustavi(){
        
        kraj=true;
        try {
                                                  
            for(ObradaKlijentskihZahteva o:lista){
                o.zaustaviNit();
            }
            
            serverSocket.close();  
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
