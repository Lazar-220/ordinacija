/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;
    private String putanja="C:\\PS_nakonBlokade\\Sem_PS\\1_SEM_Server_\\config\\config.properties";

    public static Konfiguracija getInstance() {
        if(instance==null){
            instance=new Konfiguracija();
        }
        return instance;
    }

    private Konfiguracija() {
        konfiguracija=new Properties();

            try {
                FileInputStream fis=new FileInputStream(putanja);
                konfiguracija.load(fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
            

    }

    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream(putanja) , null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getProperty(String key){
        return konfiguracija.getProperty(key, "n/a");
    }
    public void setProperty(String key, String val){
        konfiguracija.setProperty(key, val);
    }
}
