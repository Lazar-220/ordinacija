/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.niti;

import rs.ac.bg.fon.ai.server.forme.KonfiguracijaBazaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class PasswordKonfiguracija extends Thread {
    private KonfiguracijaBazaForma kbf;
    private boolean kraj= false;

    public PasswordKonfiguracija(KonfiguracijaBazaForma kbf) {
        this.kbf = kbf;
    }

    
    @Override
    public void run() {
        try {
            while(!kraj){
                if(kbf.getjCheckBox1().isSelected()){
                    kbf.getjPasswordField1().setEchoChar((char)0);
                }
                else{
                    kbf.getjPasswordField1().setEchoChar('•');
                }
                
            }
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PasswordKonfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    
}
