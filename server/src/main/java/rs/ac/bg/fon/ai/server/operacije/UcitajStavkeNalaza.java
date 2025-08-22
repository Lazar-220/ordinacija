/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.operacije;

import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class UcitajStavkeNalaza extends ApstraktnaGenerickaOperacija {

    private List<StavkaNalaza>lista=new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Nalaz)) {
            try {
                throw new Exception("UCITAVANJE STAVKI NALAZA NIJE USPELO");
            } catch (Exception ex) {
                Logger.getLogger(ObrisiTerapiju.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) {
        try {
            if(((Nalaz)objekat).getIdNalaz()==0){
                String uslov=" join terapija on terapija_id=idTerapija join nalaz on nalaz_id=idNalaz";
                lista=broker.getAll(new StavkaNalaza(), uslov);
                return;
            }
            String uslov=" join terapija on terapija_id=idTerapija join nalaz on nalaz_id=idNalaz where nalaz_id="+ ((Nalaz)objekat).getIdNalaz();
            lista=broker.getAll(new StavkaNalaza(), uslov);
        } catch (Exception ex) {
            Logger.getLogger(UcitajStavkeNalaza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StavkaNalaza> getLista() {
        return lista;
    }

    public void setLista(List<StavkaNalaza> lista) {
        this.lista = lista;
    }
    
}
