package rs.ac.bg.fon.ai.server.operacije;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
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
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;
import rs.ac.bg.fon.ai.zajednicki.domen.TipPacijenta;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class UcitajNalazeTest {

	private Connection connection;
	private List<Nalaz>lista;
	private UcitajNalaze ucitajSO;
	
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
		ucitajSO=new UcitajNalaze(false);
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.rollback();
		
		connection=null;
		ucitajSO=null;
		lista=null;
	}

	@Test
	void testIzvrsi() throws Exception {
		
		DbRepositoryGeneric broker=new DbRepositoryGeneric();
		
		
		List<Nalaz>pomocna=new ArrayList<Nalaz>();
		
		Fizijatar fizijatar = new Fizijatar();
		assertNotNull(broker.getAll(new Fizijatar(), null));
        fizijatar.setIdFizijatra(((Fizijatar)broker.getAll(new Fizijatar(), null).get(0)).getIdFizijatra());

       
        Pacijent pacijent = new Pacijent();
        assertNotNull(broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta"));
        pacijent.setIdPacijent(((Pacijent)broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta").get(0)).getIdPacijent());
        
        Date datum =new Date(System.currentTimeMillis()-10000);
        
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(datum);
        
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        datum=calendar.getTime();
        
        Nalaz nalaz = new Nalaz();
        nalaz.setDatumIzdavanja(datum);
        nalaz.setOpisNalaza("Opis");
        nalaz.setUkupnaCena(2000.0);
        nalaz.setFizijatar(fizijatar);
        nalaz.setPacijent(pacijent);
        nalaz.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        
        Fizijatar fizijatar2 = new Fizijatar();
        fizijatar2.setIdFizijatra((((Fizijatar)broker.getAll(new Fizijatar(), null).get(broker.getAll(new Fizijatar(), null).size()-1)).getIdFizijatra()));

        
        Pacijent pacijent2 = new Pacijent();
        pacijent2.setIdPacijent((((Pacijent)broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta").get(broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta").size()-1)).getIdPacijent()));
        
        
        

        Nalaz nalaz2 = new Nalaz();
        nalaz2.setDatumIzdavanja(datum);
        nalaz2.setOpisNalaza("Opis");
        nalaz2.setUkupnaCena(2000.0);
        nalaz2.setFizijatar(fizijatar2);
        nalaz2.setPacijent(pacijent2);
        nalaz2.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        
        pomocna.add(nalaz);
        pomocna.add(nalaz2);
        
		for(Nalaz n:pomocna) {
			broker.add(n);
		}
		
		ucitajSO.izvrsi(new Nalaz(), " join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent");
		lista=ucitajSO.getLista();
		
		assertNotNull(lista);
		assertTrue(lista.size()>=2);
		
		int indeksPoslednjeg=lista.size()-1;
		
		
        
		assertEquals(nalaz.getDatumIzdavanja().getTime(),lista.get(indeksPoslednjeg-1).getDatumIzdavanja().getTime());
		assertEquals(nalaz.getFizijatar().getIdFizijatra(),lista.get(indeksPoslednjeg-1).getFizijatar().getIdFizijatra());
		
		assertEquals(nalaz2.getPacijent().getIdPacijent(),lista.get(indeksPoslednjeg).getPacijent().getIdPacijent());
		assertEquals(nalaz2.getOpisNalaza(),lista.get(indeksPoslednjeg).getOpisNalaza());
	}
	
	
	@Test
	void testIzvrsiNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()->ucitajSO.izvrsi(null, null));
	}
	@Test
	void testIzvrsiPogresanTip() {
		assertThrows(java.lang.ClassCastException.class, 
				()->ucitajSO.izvrsi(new Fizijatar(), null));
	}

}
