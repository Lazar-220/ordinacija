package rs.ac.bg.fon.ai.server.operacije;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

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
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class ObrisiPacijentaTest {
	
	private Connection connection;
	private Pacijent pacijent;
	private DbRepositoryGeneric broker;
	private ObrisiPacijenta obrisiSO;

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
		
		obrisiSO=new ObrisiPacijenta(false);
		broker=new DbRepositoryGeneric();
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		obrisiSO=null;
		broker=null;
		pacijent=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		pacijent=new Pacijent(-1, "ime1", "prezime1", "email1", (TipPacijenta) broker.getAll(new TipPacijenta(), null).get(0));
		broker.add(pacijent);
		
		List<ApstraktniDomenskiObjekat>listaPreBrisanja=broker.getAll(new Pacijent(), " JOIN tip_pacijenta ON tipPacijenta_id=tip_pacijenta.idTipPacijenta ORDER BY idPacijent ASC");
		
		obrisiSO=new ObrisiPacijenta(false);
		
		Pacijent pacijentZaBrisanje=(Pacijent)listaPreBrisanja.get(listaPreBrisanja.size()-1);
		
		obrisiSO.izvrsi(pacijentZaBrisanje, null);
		
		List<ApstraktniDomenskiObjekat>listaPosleBrisanja=broker.getAll(new Pacijent(), " JOIN tip_pacijenta ON tipPacijenta_id=tip_pacijenta.idTipPacijenta ORDER BY idPacijent ASC");
		
		assertEquals(listaPreBrisanja.size()-1, listaPosleBrisanja.size());
		
		assertFalse(listaPosleBrisanja.stream().
				anyMatch(p->((Pacijent)p).getIdPacijent()==pacijentZaBrisanje.getIdPacijent()));
		

	}
	
	@Test
	void testIzvrsiNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()->obrisiSO.izvrsi(null, null));
	}
	@Test
	void testIzvrsiPogresanTip() {
		assertThrows(java.lang.ClassCastException.class, 
				()->obrisiSO.izvrsi(new Fizijatar(), null));
	}
	@Test
	void testIzvrsiPrazanObjekat() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()->obrisiSO.izvrsi(new Pacijent(), null));
	}
	
	

}
