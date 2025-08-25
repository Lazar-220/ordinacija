/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.repository.db.impl;

import rs.ac.bg.fon.ai.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.server.repository.db.DbRepository;
import rs.ac.bg.fon.ai.zajednicki.domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author milos
 */
public class DbRepositoryGeneric implements DbRepository <ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param,String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat>lista=new ArrayList<>();
        String upit="SELECT * FROM "+param.vratiNazivTabele();
        if(uslov!=null){ 
            upit+=uslov;
        }
        Statement st=DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs=st.executeQuery(upit);
        
        lista=param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit="INSERT INTO "+param.vratiNazivTabele()+" ("+param.vratiKoloneZaUbacivanje()+") VALUES ("+param.vratiVrednostiZaUbacivanje()+")";
        
        PreparedStatement ps=DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
        ps.executeUpdate();
        
        ps.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit="UPDATE "+param.vratiNazivTabele()+" SET "+param.vratiVrednostiZaIzmenu()+" WHERE "+param.vratiPrimarniKljuc();
        
        PreparedStatement ps=DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit="DELETE FROM "+param.vratiNazivTabele() +" WHERE "+param.vratiPrimarniKljuc();
        
        PreparedStatement ps=DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() { //TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
