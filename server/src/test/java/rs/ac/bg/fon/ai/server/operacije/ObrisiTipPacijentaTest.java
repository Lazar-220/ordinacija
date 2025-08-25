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

class ObrisiTipPacijentaTest {

	private Connection connection;
	private TipPacijenta tipPacijenta;
	private DbRepositoryGeneric broker;
	private ObrisiTipPacijenta obrisiSO;

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
		
		obrisiSO=new ObrisiTipPacijenta(false);
		broker=new DbRepositoryGeneric();
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		obrisiSO=null;
		broker=null;
		tipPacijenta=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		tipPacijenta=new TipPacijenta(-1, StarosnaDob.deca, Pol.musko);
		broker.add(tipPacijenta);
		
		List<ApstraktniDomenskiObjekat>listaPreBrisanja=broker.getAll(new TipPacijenta(),null);
		
		obrisiSO=new ObrisiTipPacijenta(false);
		
		TipPacijenta tipPacijentaZaBrisanje=(TipPacijenta)listaPreBrisanja.get(listaPreBrisanja.size()-1);
		
		obrisiSO.izvrsi(tipPacijentaZaBrisanje, null);
		
		List<ApstraktniDomenskiObjekat>listaPosleBrisanja=broker.getAll(new TipPacijenta(), null);
		
		assertEquals(listaPreBrisanja.size()-1, listaPosleBrisanja.size());
		
		assertFalse(listaPosleBrisanja.stream().
				anyMatch(tp->((TipPacijenta)tp).getIdTipPacijenta()==tipPacijentaZaBrisanje.getIdTipPacijenta()));
		

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
				()->obrisiSO.izvrsi(new TipPacijenta(), null));
	}
	
}
