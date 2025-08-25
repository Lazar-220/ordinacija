/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.server.repository.Repository;
import rs.ac.bg.fon.ai.server.repository.db.DbRepository;
import rs.ac.bg.fon.ai.server.repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author milos
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }
    
    public final void izvrsi(Object objekat,String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            //ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object param)throws Exception;
    
    private void zapocniTransakciju() {
        try {
            ((DbRepository)broker).connect();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected abstract void izvrsiOperaciju(Object objekat,String kljuc);
    
    private void potvrdiTransakciju() {
        try {
            ((DbRepository)broker).commit();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ponistiTransakciju() {
        try {
            ((DbRepository)broker).rollback();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ugasiKonekciju() {
        try {
            ((DbRepository)broker).disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
