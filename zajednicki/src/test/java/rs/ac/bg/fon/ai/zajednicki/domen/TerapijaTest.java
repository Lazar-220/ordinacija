package rs.ac.bg.fon.ai.zajednicki.domen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TerapijaTest {

	Terapija t;
	
	@BeforeEach
	void setUp() throws Exception {
		t=new Terapija();
	}

	@AfterEach
	void tearDown() throws Exception {
		t=null;
	}

	
	@Test
	void testTerapija() {
		assertNotNull(t);
	}

	@Test
	void testTerapijaSaParametrima() {
		t = new Terapija(1, "Fizikalna", "Opis1", 1500.0);

		assertEquals(1, t.getIdTerapija());
		assertEquals("Fizikalna", t.getNaziv());
		assertEquals("Opis1", t.getOpis());
		assertEquals(1500.0, t.getCena());
	}

	@Test
	void testSetIdTerapijaValidno() {
		t.setIdTerapija(5);
		assertEquals(5, t.getIdTerapija());
	}

	@Test
	void testSetIdTerapijaNevalidno() {
		assertThrows(IllegalArgumentException.class, () -> t.setIdTerapija(-1));
	}

	@Test
	void testSetNazivValidan() {
		t.setNaziv("Masaža");
		assertEquals("Masaža", t.getNaziv());
	}

	@Test
	void testSetNazivNull() {
		assertThrows(NullPointerException.class, () -> t.setNaziv(null));
	}

	@Test
	void testSetNazivPrazan() {
		assertThrows(IllegalArgumentException.class, () -> t.setNaziv(""));
	}

	@Test
	void testSetOpisValidan() {
		t.setOpis("Opis terapije");
		assertEquals("Opis terapije", t.getOpis());
	}

	@Test
	void testSetOpisNull() {
		assertThrows(NullPointerException.class, () -> t.setOpis(null));
	}

	@Test
	void testSetOpisPrazan() {
		assertThrows(IllegalArgumentException.class, () -> t.setOpis(""));
	}

	@Test
	void testSetCenaValidna() {
		t.setCena(1200.0);
		assertEquals(1200.0, t.getCena());
	}

	@Test
	void testSetCenaNevalidna() {
		assertThrows(IllegalArgumentException.class, () -> t.setCena(-500.0));
	}

	@ParameterizedTest
	@CsvSource({
		"1,1,true",
		"1,2,false"
	})
	void testEquals(int id1,int id2,boolean ocekivano) {
		Terapija t1=new Terapija();
		t1.setIdTerapija(id1);
		Terapija t2=new Terapija();
		t2.setIdTerapija(id2);
		
		assertEquals(ocekivano, t1.equals(t2));
	}
	
	@Test
	void testToString() {
		t.setNaziv("Laser");
		assertTrue(t.toString().contains("Laser"));
	}

	@Test
	void testVratiNazivTabele() {
		assertEquals("terapija", t.vratiNazivTabele());
	}

	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("naziv,cena,opis", t.vratiKoloneZaUbacivanje());
	}

	@Test
	void testVratiVrednostiZaUbacivanje() {
		t = new Terapija(1, "Fizikalna", "Opis1", 1000.0);
		assertEquals("'Fizikalna',1000.0,'Opis1'", t.vratiVrednostiZaUbacivanje());
	}

	@Test
	void testVratiPrimarniKljuc() {
		t.setIdTerapija(3);
		assertEquals("terapija.idTerapija=3", t.vratiPrimarniKljuc());
	}

	@Test
	void testVratiVrednostiZaIzmenu() {
		t = new Terapija(1, "Fizikalna", "Opis1", 1000.0);
		assertEquals("naziv= 'Fizikalna',cena= 1000.0,opis= 'Opis1'", t.vratiVrednostiZaIzmenu());
	}
	
	@Test
	void testVratiListu() throws Exception {
		
		ResultSet rs=mock(ResultSet.class);
		
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("terapija.idTerapija")).thenReturn(1);
		when(rs.getString("terapija.naziv")).thenReturn("Kineziterapija");
		when(rs.getString("terapija.opis")).thenReturn("Primena specifičnih pokreta i vežbi");
		when(rs.getDouble("terapija.cena")).thenReturn(3000.00);
		
		List<ApstraktniDomenskiObjekat>lista=t.vratiListu(rs);
		
		assertNotNull(lista);
		assertEquals(1, lista.size());
		
		Terapija terapija=(Terapija) lista.get(0);
		assertEquals(1, terapija.getIdTerapija());
		assertEquals("Kineziterapija", terapija.getNaziv());
		assertEquals("Primena specifičnih pokreta i vežbi", terapija.getOpis());
		assertEquals(3000, terapija.getCena());
	}

}
