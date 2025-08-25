package rs.ac.bg.fon.ai.server.operacije;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija;
import rs.ac.bg.fon.ai.server.operacije.DodajTerapiju;
import rs.ac.bg.fon.ai.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.server.repository.db.impl.DbRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DodajTerapijuTest {

    private Connection connection;
    private Terapija testTerapija;
    private DodajTerapiju soDodaj;

    @BeforeAll
    static void preTestova() {
    	
        Konfiguracija.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/ordinacija_test");
        Konfiguracija.getInstance().setProperty("username", "root");
        Konfiguracija.getInstance().setProperty("password", "");
    }

    @AfterAll
    static void posleTestova() {
    	
        Konfiguracija.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/ordinacija");
        Konfiguracija.getInstance().setProperty("username", "root");
        Konfiguracija.getInstance().setProperty("password", "");
    }
    
    @BeforeEach
    void setUp() throws Exception {
    
        connection = DbConnectionFactory.getInstance().getConnection();
        
        soDodaj = new DodajTerapiju(false);
    }

    @AfterEach
    void tearDown() throws Exception {
    	
        connection.rollback();
        
        connection=null;
        soDodaj=null;
        testTerapija=null;
    }

    @Test
    void testDodajTerapiju() throws Exception {
    	
    	testTerapija = new Terapija();
        testTerapija.setCena(1000);
        testTerapija.setOpis("Test terapija JUnit");
        testTerapija.setNaziv("Test terapija");
        
        soDodaj.izvrsi(testTerapija, null);

        DbRepositoryGeneric broker = new DbRepositoryGeneric();
        List<ApstraktniDomenskiObjekat> lista = broker.getAll(new Terapija(), " WHERE terapija.cena = 1000 AND terapija.opis='Test terapija JUnit' AND terapija.naziv='Test terapija'");

        assertEquals(1, lista.size(), "Dodavanje terapije nije uspelo.");
        Terapija dobijena = (Terapija) lista.get(0);
        assertEquals("Test terapija JUnit", dobijena.getOpis());
        assertEquals("Test terapija", dobijena.getNaziv());
        assertEquals(1000, dobijena.getCena());
        
        
    }
    @Test
    void testDodajTerapijuNull() {
    	assertThrows(java.lang.NullPointerException.class, 
    			()->soDodaj.izvrsi(null, null));
    }
    @Test
    void testDodajTerapijuPogresanTip() {
    	assertThrows(java.lang.ClassCastException.class, 
    			()->soDodaj.izvrsi(new Pacijent(), null));
    }
    @Test
    void testDodajTerapijuPrazanObjekat() {
    	assertThrows(java.lang.IllegalArgumentException.class, 
    			()->soDodaj.izvrsi(new Terapija(), null));
    }
    
    
}
