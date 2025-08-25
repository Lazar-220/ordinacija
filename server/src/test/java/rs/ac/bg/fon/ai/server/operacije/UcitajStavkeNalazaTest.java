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
import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;
import rs.ac.bg.fon.ai.zajednicki.domen.Nalaz;
import rs.ac.bg.fon.ai.zajednicki.domen.Pacijent;
import rs.ac.bg.fon.ai.zajednicki.domen.StavkaNalaza;
import rs.ac.bg.fon.ai.zajednicki.domen.Terapija;

class UcitajStavkeNalazaTest {

	private Connection connection;
	private List<StavkaNalaza>lista;
	private UcitajStavkeNalaza ucitajSO;
	
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
		ucitajSO=new UcitajStavkeNalaza(false);
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
        //nalaz.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        
        Fizijatar fizijatar2 = new Fizijatar();
        fizijatar2.setIdFizijatra((((Fizijatar)broker.getAll(new Fizijatar(), null).get(broker.getAll(new Fizijatar(), null).size()-1)).getIdFizijatra()));

        
        Pacijent pacijent2 = new Pacijent();
        pacijent2.setIdPacijent((((Pacijent)broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta").get(broker.getAll(new Pacijent(), " join tip_pacijenta on tipPacijenta_id=idTipPacijenta").size()-1)).getIdPacijent()));
        
        
        

        Nalaz nalaz2 = new Nalaz();
        nalaz2.setDatumIzdavanja(datum);
        nalaz2.setOpisNalaza("Opis2");
        nalaz2.setUkupnaCena(3000.0);
        nalaz2.setFizijatar(fizijatar2);
        nalaz2.setPacijent(pacijent2);
        //nalaz2.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        broker.add(nalaz);
        broker.add(nalaz2);
        
        nalaz.setIdNalaz(((Nalaz)broker.getAll(new Nalaz()," join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz").get(broker.getAll(new Nalaz()," join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz").size()-2)).getIdNalaz());
		
        nalaz2.setIdNalaz(((Nalaz)broker.getAll(new Nalaz()," join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz").get(broker.getAll(new Nalaz()," join fizijatar on fizijatar_id=idFizijatra join pacijent on pacijent_id=idPacijent order by idNalaz").size()-1)).getIdNalaz());
		
		
		Terapija t1 = new Terapija(-1, "Fizikalna", "Opis1", 1500.0);
		
		broker.add(t1);
		
		t1.setIdTerapija(((Terapija)broker.getAll(new Terapija(),null).get(broker.getAll(new Terapija(),null).size()-1)).getIdTerapija());
		
        
		
		StavkaNalaza sn1 = new StavkaNalaza(1, 1500, t1, nalaz);
        
        
        
		Terapija t2 = new Terapija(-1, "Terapija", "Opis2", 3000.0);
        
		broker.add(t2);
		
		t2.setIdTerapija(((Terapija)broker.getAll(new Terapija(),null).get(broker.getAll(new Terapija(),null).size()-1)).getIdTerapija());
		
		
		StavkaNalaza sn2 = new StavkaNalaza(1, 3000, t2, nalaz2);
        
		broker.add(sn1);
		broker.add(sn2);
		
		List<StavkaNalaza>listaDodatihStavkiUBazu=new ArrayList<StavkaNalaza>();
		
		ucitajSO.izvrsi(nalaz, null);
		
		lista=ucitajSO.getLista();
		
		listaDodatihStavkiUBazu.add(lista.get(0));
		
		ucitajSO.izvrsi(nalaz2, null);
		
		lista=ucitajSO.getLista();
		
		listaDodatihStavkiUBazu.add(lista.get(0));
		
		assertNotNull(listaDodatihStavkiUBazu);
		assertTrue(listaDodatihStavkiUBazu.size()==2);
		
		
		assertEquals(sn1, listaDodatihStavkiUBazu.get(0));
		assertEquals(sn2, listaDodatihStavkiUBazu.get(1));
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
