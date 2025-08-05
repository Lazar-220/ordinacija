package rs.ac.bg.fon.ai.zajednicki.domen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StavkaNalazaTest {

	private StavkaNalaza sn;

	@BeforeEach
	void setUp() throws Exception {
		sn = new StavkaNalaza();
	}

	@AfterEach
	void tearDown() throws Exception {
		sn = null;
	}

	@Test
	void testKonstruktorSa4Parametra() {
		Terapija t = new Terapija(1, "Naziv", "Opis", 1000);
		Nalaz n = new Nalaz(); n.setIdNalaz(5);
		sn = new StavkaNalaza(2, 1500, t, n);
		assertEquals(2, sn.getRb());
		assertEquals(1500, sn.getCena());
		assertEquals(t, sn.getTerapija());
		assertEquals(n, sn.getNalaz());
	}

	@Test
	void testKonstruktorBezNalaza() {
		Terapija t = new Terapija(1, "Naziv", "Opis", 1000);
		sn = new StavkaNalaza(2, 1500, t);
		assertEquals(2, sn.getRb());
		assertEquals(1500, sn.getCena());
		assertEquals(t, sn.getTerapija());
	}

	@Test
	void testSetRb() {
		sn.setRb(1);
		assertEquals(1, sn.getRb());
	}

	@Test
	void testSetRbPogresno() {
		assertThrows(IllegalArgumentException.class, 
				() -> sn.setRb(0));
	}

	@Test
	void testSetCena() {
		sn.setCena(100);
		assertEquals(100, sn.getCena());
	}

	@Test
	void testSetCenaPogresno() {
		assertThrows(IllegalArgumentException.class, 
				() -> sn.setCena(-1));
	}

	@Test
	void testSetNalaz() {
		Nalaz n = new Nalaz();
		sn.setNalaz(n);
		assertEquals(n, sn.getNalaz());
	}

	@Test
	void testSetNalazNull() {
		assertThrows(NullPointerException.class, () -> sn.setNalaz(null));
	}

	@Test
	void testSetTerapija() {
		Terapija t = new Terapija();
		sn.setTerapija(t);
		assertEquals(t, sn.getTerapija());
	}

	@Test
	void testSetTerapijaNull() {
		assertThrows(NullPointerException.class,
				() -> sn.setTerapija(null));
	}

	@Test
	void testToString() {
		Terapija t = new Terapija();
		t.setNaziv("Kineziterapija");
		sn.setRb(1);
		sn.setCena(200);
		sn.setTerapija(t);
		assertTrue(sn.toString().contains("Kineziterapija"));
		assertTrue(sn.toString().contains(1+""));
		assertTrue(sn.toString().contains(200+""));
	}

	@Test
	void testVratiNazivTabele() {
		assertEquals("stavka_nalaza", sn.vratiNazivTabele());
	}

	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("rb,nalaz_id,cena,terapija_id", sn.vratiKoloneZaUbacivanje());
	}

	@Test
	void testVratiVrednostiZaUbacivanje() {
		Nalaz n = new Nalaz(); n.setIdNalaz(10);
		Terapija t = new Terapija(); t.setIdTerapija(5);
		sn = new StavkaNalaza(1, 500.0, t, n);
		assertEquals("1,10,500.0,5", sn.vratiVrednostiZaUbacivanje());
	}

	@Test
	void testVratiPrimarniKljuc() {
		Nalaz n = new Nalaz(); n.setIdNalaz(10);
		sn.setRb(3);
		sn.setNalaz(n);
		assertEquals("stavka_nalaza.rb=3 AND stavka_nalaza.nalaz_id=10", sn.vratiPrimarniKljuc());
	}

	@Test
	void testVratiVrednostiZaIzmenu() {
		Nalaz n = new Nalaz(); n.setIdNalaz(10);
		Terapija t = new Terapija(); t.setIdTerapija(5);
		sn = new StavkaNalaza(1, 500.0, t, n);
		assertEquals("rb= 1,nalaz_id= 10,cena= 500.0,terapija_id= 5", sn.vratiVrednostiZaIzmenu());
	}

	@Test
	void testVratiListu() throws Exception {
		
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("stavka_nalaza.rb")).thenReturn(1);
		when(rs.getDouble("stavka_nalaza.cena")).thenReturn(3000.0);
		
		when(rs.getInt("nalaz.idNalaz")).thenReturn(1);
		long miliSec=System.currentTimeMillis()-10000;
		when(rs.getDate("nalaz.datumIzdavanja")).thenReturn(new java.sql.Date(miliSec));
		when(rs.getString("nalaz.opisNalaza")).thenReturn("Opis nalaza");
		when(rs.getDouble("nalaz.ukupnaCena")).thenReturn(10000.0);
		
		
		when(rs.getInt("terapija.idTerapija")).thenReturn(2);
		when(rs.getString("terapija.naziv")).thenReturn("Kineziterapija");
		when(rs.getString("terapija.opis")).thenReturn("Primena specifičnih pokreta i vežbi");
		when(rs.getDouble("terapija.cena")).thenReturn(3000.0);

		
		List<ApstraktniDomenskiObjekat> lista = sn.vratiListu(rs);
		
		assertNotNull(lista);
		assertEquals(1, lista.size());
		
		
		StavkaNalaza stavka = (StavkaNalaza) lista.get(0);
		
		
		assertEquals(1, stavka.getRb());
		assertEquals(3000.0, stavka.getCena());
		
		assertEquals(1, stavka.getNalaz().getIdNalaz());
		assertEquals("Opis nalaza", stavka.getNalaz().getOpisNalaza());
		assertEquals(new java.util.Date(miliSec).getTime(),stavka.getNalaz().getDatumIzdavanja().getTime());
		assertEquals(10000, stavka.getNalaz().getUkupnaCena());
		
		assertEquals(2, stavka.getTerapija().getIdTerapija());
		assertEquals("Kineziterapija", stavka.getTerapija().getNaziv());
		assertEquals("Primena specifičnih pokreta i vežbi", stavka.getTerapija().getOpis());
		assertEquals(3000, stavka.getTerapija().getCena());
		
		
	}

	@ParameterizedTest
	@CsvSource({
		
	    "1, 10, 100, 1, 10, 100, true",
	    "1, 10, 100, 2, 10, 100, false",  
	    "1, 10, 100, 1, 11, 100, false",  
	    "1, 10, 100, 1, 10, 101, false",  
	    "1, 10, 100, 2, 11, 100, false",  
	    "1, 10, 100, 2, 10, 101, false",  
	    "1, 10, 100, 1, 11, 101, false",  
	    "1, 10, 100, 2, 11, 101, false"
	})
	void testEquals(int rb1, int idN1, int idT1, int rb2, int idN2, int idT2, boolean expected) {
		Nalaz n1 = new Nalaz(); n1.setIdNalaz(idN1);
		Terapija t1 = new Terapija(); t1.setIdTerapija(idT1);
		StavkaNalaza s1 = new StavkaNalaza(rb1, 100, t1, n1);

		Nalaz n2 = new Nalaz(); n2.setIdNalaz(idN2);
		Terapija t2 = new Terapija(); t2.setIdTerapija(idT2);
		StavkaNalaza s2 = new StavkaNalaza(rb2, 200, t2, n2);

		assertEquals(expected, s1.equals(s2));
	}

}
