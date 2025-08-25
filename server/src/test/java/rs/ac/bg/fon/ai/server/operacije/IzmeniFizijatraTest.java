package rs.ac.bg.fon.ai.server.operacije;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija;
import rs.ac.bg.fon.ai.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.server.repository.db.impl.DbRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

class IzmeniFizijatraTest {

	private Fizijatar fizijatar;
	private Connection connection;
	private IzmeniFizijatra izvrsiSO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Konfiguracija.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/ordinacija_test");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Konfiguracija.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/ordinacija");
	}

	@BeforeEach
	void setUp() throws Exception {
		
		connection=DbConnectionFactory.getInstance().getConnection();
		
		izvrsiSO=new IzmeniFizijatra(false);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		izvrsiSO=null;
		fizijatar=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		DbRepositoryGeneric broker=new DbRepositoryGeneric();
		
		fizijatar=new Fizijatar();
		
		fizijatar.setIme("Ime");
		fizijatar.setPrezime("Prezime");
		fizijatar.setKorisnickoIme("KorIme");
		fizijatar.setSifra("Sifra");
		broker.add(fizijatar);                                               //dodajemo fizijatra
		List<ApstraktniDomenskiObjekat>lista1=broker.getAll(new Fizijatar(), " order by idFizijatra asc");  //vracamo ih da bi dobili id dodatog
		fizijatar.setIdFizijatra(((Fizijatar)lista1.get(lista1.size()-1)).getIdFizijatra());
		
		fizijatar.setIme("i");
		fizijatar.setPrezime("p");
		fizijatar.setKorisnickoIme("ki");
		fizijatar.setSifra("s");
		
		izvrsiSO.izvrsi(fizijatar, null);          //menjamo dodatog fizijatra
		
		
		List<ApstraktniDomenskiObjekat>lista=broker.getAll(new Fizijatar(), " WHERE fizijatar.ime ='i' AND fizijatar.prezime ='p' AND fizijatar.korisnickoIme ='ki' AND fizijatar.sifra ='s'");
		
		assertEquals(1, lista.size(),"Izmena fizijatra nije uspela.");
		
		Fizijatar testirani=(Fizijatar) lista.get(0);
		
		assertEquals("i", testirani.getIme());
		assertEquals("p", testirani.getPrezime());
		assertEquals("ki", testirani.getKorisnickoIme());
		assertEquals("s", testirani.getSifra());
		
	}

	@Test
    void testIzmeniFizijatraNull() {
    	assertThrows(java.lang.NullPointerException.class, 
    			()->izvrsiSO.izvrsi(null, null));
    }
    @Test
    void testIzmeniFizijatraPogresanTip() {
    	assertThrows(java.lang.ClassCastException.class, 
    			()->izvrsiSO.izvrsi(new Pacijent(), null));
    }
    @Test
    void testIzmeniFizijatraPrazanObjekat() {
    	assertThrows(java.lang.IllegalArgumentException.class, 
    			()->izvrsiSO.izvrsi(new Fizijatar(), null));
    }
}
