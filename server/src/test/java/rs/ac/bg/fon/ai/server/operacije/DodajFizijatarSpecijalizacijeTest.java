package rs.ac.bg.fon.ai.server.operacije;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Date;
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
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar_specijalista;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

class DodajFizijatarSpecijalizacijeTest {
	
	private Connection connection;
    private Fizijatar_specijalista sertikikat;
    private DodajFizijatarSpecijalizacije dodajSO;
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
		dodajSO=new DodajFizijatarSpecijalizacije(false);
		broker=new DbRepositoryGeneric();
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		dodajSO=null;
		broker=null;
		sertikikat=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		Fizijatar f=new Fizijatar(-1,"Zika","Zikic","zika","z1");
		broker.add(f);
		List<ApstraktniDomenskiObjekat>listaF=broker.getAll(new Fizijatar(), null);
		for(ApstraktniDomenskiObjekat fiz:listaF) {
			if(((Fizijatar)fiz).equals(f)) {
				f.setIdFizijatra(((Fizijatar)fiz).getIdFizijatra());
			}
		}
		Specijalizacija s=new Specijalizacija(-1,"Terapeut","Visoka zdravstvena škola strukovnih studija u Nišu");
		broker.add(s);
		List<ApstraktniDomenskiObjekat>listaS=broker.getAll(new Specijalizacija(), null);
		for(ApstraktniDomenskiObjekat sp:listaS) {
			if(((Specijalizacija)sp).equals(s)) {
				s.setIdSpecijalizacija(((Specijalizacija)sp).getIdSpecijalizacija());
			}
		}
		List<ApstraktniDomenskiObjekat>fS1=broker.getAll(new Fizijatar_specijalista(), " JOIN specijalizacija ON specijalizacija_id=specijalizacija.idSpecijalizacija JOIN fizijatar ON fizijatar_id=fizijatar.idFizijatra order by idSertifikat asc");
		
		
		sertikikat=new Fizijatar_specijalista(-1, f, s, new Date((new Date()).getTime()-10000));
		dodajSO.izvrsi(sertikikat, null);
		
		List<ApstraktniDomenskiObjekat>fS2=broker.getAll(new Fizijatar_specijalista(), " JOIN specijalizacija ON specijalizacija_id=specijalizacija.idSpecijalizacija JOIN fizijatar ON fizijatar_id=fizijatar.idFizijatra order by idSertifikat asc");
		assertEquals(fS2.size(),fS1.size()+1);
		assertEquals(sertikikat,fS2.get(fS2.size()-1));
	}

	@Test
	void testIzvrsiNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()->dodajSO.izvrsi(null, null));
	}
	@Test
	void testIzvrsiPogresanTip() {
		assertThrows(java.lang.ClassCastException.class, 
				()->dodajSO.izvrsi(new Fizijatar(), null));
	}
	@Test
	void testIzvrsiPrazanObjekat() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()->dodajSO.izvrsi(new Fizijatar_specijalista(), null));
	}
}
