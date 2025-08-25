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
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.Specijalizacija;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class DodajNalazTest {

	private Connection connection;
    private Nalaz nalaz;
    private DodajNalaz dodajSO;
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
		dodajSO=new DodajNalaz(false);
		broker=new DbRepositoryGeneric();
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		dodajSO=null;
		broker=null;
		nalaz=null;
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
		
		TipPacijenta tp = (TipPacijenta) broker.getAll(new TipPacijenta(),null).get(0);
		Pacijent p = new Pacijent(-1, "Ana", "Anić", "ana@gmail.com", tp);
		broker.add(p);
		List<ApstraktniDomenskiObjekat>listaP=broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta");
		for(ApstraktniDomenskiObjekat pac:listaP) {
			if(((Pacijent)pac).equals(p)) {
				p.setIdPacijent(((Pacijent)pac).getIdPacijent());
			}
		}
		
		List<ApstraktniDomenskiObjekat>n1=broker.getAll(new Nalaz(), " join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz asc");
		
		
		nalaz = new Nalaz();
        nalaz.setDatumIzdavanja(new Date((new Date()).getTime()-10000));
        nalaz.setOpisNalaza("Opis");
        nalaz.setUkupnaCena(2000.0);
        nalaz.setFizijatar(f);
        nalaz.setPacijent(p);
        nalaz.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

		dodajSO.izvrsi(nalaz, null);
		
		List<ApstraktniDomenskiObjekat>n2=broker.getAll(new Nalaz(), " join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz asc");
		assertEquals(n2.size(),n1.size()+1);
		assertEquals(nalaz.getFizijatar(),((Nalaz)n2.get(n2.size()-1)).getFizijatar());
		assertEquals(nalaz.getPacijent(),((Nalaz)n2.get(n2.size()-1)).getPacijent());
		assertEquals(nalaz.getOpisNalaza(),((Nalaz)n2.get(n2.size()-1)).getOpisNalaza());
		assertEquals(nalaz.getUkupnaCena(),((Nalaz)n2.get(n2.size()-1)).getUkupnaCena());
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
				()->dodajSO.izvrsi(new Nalaz(), null));
	}

}
