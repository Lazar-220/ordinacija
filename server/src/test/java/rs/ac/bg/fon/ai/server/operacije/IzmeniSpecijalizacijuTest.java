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
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;

class IzmeniSpecijalizacijuTest {

	private Specijalizacija specijalizacija;
	private Connection connection;
	private IzmeniSpecijalizaciju izmeniSO;
	
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
		
		izmeniSO=new IzmeniSpecijalizaciju(false);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		izmeniSO=null;
		specijalizacija=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		DbRepositoryGeneric broker=new DbRepositoryGeneric();
		
		specijalizacija=new Specijalizacija();
		
		specijalizacija.setNaziv("naziv");
		specijalizacija.setInstitucija("inst");
		broker.add(specijalizacija);                                               //dodajemo spec
		List<ApstraktniDomenskiObjekat>lista1=broker.getAll(new Specijalizacija(), null);  //vracamo ih da bi dobili id dodatne
		specijalizacija.setIdSpecijalizacija(((Specijalizacija)lista1.get(lista1.size()-1)).getIdSpecijalizacija());
		
		
		specijalizacija.setNaziv("n");
		specijalizacija.setInstitucija("i");
		izmeniSO.izvrsi(specijalizacija, null);          //menjamo dodatnu spec
		
		
		List<ApstraktniDomenskiObjekat>lista=broker.getAll(new Specijalizacija(), " WHERE specijalizacija.naziv ='n' AND specijalizacija.institucija ='i'");
		
		assertEquals(1, lista.size(),"Izmena specijalizacije nije uspela.");
		
		Specijalizacija testirana=(Specijalizacija) lista.get(0);
		
		assertEquals("n", testirana.getNaziv());
		assertEquals("i", testirana.getInstitucija());
		
	}

	@Test
    void testIzmeniSpecijalizacijuNull() {
    	assertThrows(java.lang.NullPointerException.class, 
    			()->izmeniSO.izvrsi(null, null));
    }
    @Test
    void testIzmeniSpecijalizacijuPogresanTip() {
    	assertThrows(java.lang.ClassCastException.class, 
    			()->izmeniSO.izvrsi(new Pacijent(), null));
    }
    @Test
    void testIzmeniSpecijalizacijuPrazanObjekat() {
    	assertThrows(java.lang.IllegalArgumentException.class, 
    			()->izmeniSO.izvrsi(new Specijalizacija(), null));
    }
    
    @Test
    void testIzmeniSpecijalizacijuProveraDuplikata() throws Exception {
    	DbRepositoryGeneric broker=new DbRepositoryGeneric();
    	specijalizacija=new Specijalizacija();
		
		specijalizacija.setNaziv("naziv");
		specijalizacija.setInstitucija("inst");
		broker.add(specijalizacija);                                               //dodajemo spec
		List<ApstraktniDomenskiObjekat>lista1=broker.getAll(new Specijalizacija(), null);  //vracamo ih da bi dobili id dodatne
		specijalizacija.setIdSpecijalizacija(((Specijalizacija)lista1.get(lista1.size()-1)).getIdSpecijalizacija());
		
    	
    	assertThrows(java.lang.IllegalArgumentException.class, 
    			()->izmeniSO.izvrsi(new Specijalizacija(specijalizacija.getIdSpecijalizacija(),"Fizioterapeut","Visoka zdravstvena škola strukovnih studija u Nišu"), null));
    }

}
