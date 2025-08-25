package rs.ac.bg.fon.ai.server.operacije;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija;
import rs.ac.bg.fon.ai.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ai.server.repository.db.impl.DbRepositoryGeneric;
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

class LoginOperacijaTest {

	private Connection connection;
	private LoginOperacija loginSO;
	private Fizijatar fizijatar;
	private DbRepositoryGeneric broker;
	
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
		
		loginSO=new LoginOperacija(false);
		
		fizijatar=new Fizijatar();
		fizijatar.setIme("ime");
		fizijatar.setPrezime("prezime");
		fizijatar.setKorisnickoIme("korIme");
		fizijatar.setSifra("sifra");
		
		broker=new DbRepositoryGeneric();
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		
		loginSO=null;
		
		fizijatar=null;
		
		broker=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		broker.add(fizijatar);
		
		loginSO.izvrsi(fizijatar, null);
		Fizijatar fizijatarBaza=loginSO.getFizijatar();
		
		assertNotNull(fizijatarBaza);
		assertEquals("ime", fizijatarBaza.getIme());
		assertEquals("prezime", fizijatarBaza.getPrezime());
		assertEquals("korIme", fizijatarBaza.getKorisnickoIme());
		assertEquals("sifra", fizijatarBaza.getSifra());
	}
	
	@Test
	void testIzvrsiNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()->loginSO.izvrsi(null, null));
	}

	@Test
	void testIzvrsiPogresanTip() {
		assertThrows(java.lang.ClassCastException.class, 
				()->loginSO.izvrsi(new Terapija(), null));
	}
	
	@Test
	void testIzvrsiPrazanObjekat() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()->loginSO.izvrsi(new Fizijatar(), null));
	}
	
	@Test
	void testIzvrsiNePostoji() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()->loginSO.izvrsi(new Fizijatar(2,"im","pr","ki","sif"), null));
	}
}
