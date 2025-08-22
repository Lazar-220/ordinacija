/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.niti;

import rs.ac.bg.fon.ai.server.forme.GlavnaServerskaForma;
import rs.ac.bg.fon.ai.server.forme.model.ModelTabeleFizijatar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UlogovaniFizijatriNit extends Thread {

    private GlavnaServerskaForma gsf;

    public UlogovaniFizijatriNit(GlavnaServerskaForma gsf) {
        this.gsf = gsf;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                ModelTabeleFizijatar model=new ModelTabeleFizijatar(rs.ac.bg.fon.ai.server.controller.Controller.getInstance().getOnlineUseri());
                gsf.getjTable1().setModel(model);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UlogovaniFizijatriNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
