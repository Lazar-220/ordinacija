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

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class PacijentTest {

	private Pacijent p;

	@BeforeEach
	void setUp() {
		p = new Pacijent();
	}

	@AfterEach
	void tearDown() {
		p = null;
	}

	@Test
	void testPacijent() {
		assertNotNull(p);
	}

	@Test
	void testPacijentSaParametrima() {
		TipPacijenta tp = new TipPacijenta(1, StarosnaDob.odrasli, Pol.zensko);
		p = new Pacijent(1, "Ana", "Anić", "ana@gmail.com", tp);

		assertEquals(1, p.getIdPacijent());
		assertEquals("Ana", p.getIme());
		assertEquals("Anić", p.getPrezime());
		assertEquals("ana@gmail.com", p.getEmail());
		assertEquals(tp, p.getTipPacijenta());
	}

	@Test
	void testSetIdPacijent() {
		p.setIdPacijent(5);
		assertEquals(5, p.getIdPacijent());
	}

	@Test
	void testSetIdPacijentPogresan() {
		assertThrows(IllegalArgumentException.class, 
				() -> p.setIdPacijent(-1));
	}

	@Test
	void testSetIme() {
		p.setIme("Mina");
		assertEquals("Mina", p.getIme());
	}

	@Test
	void testSetImeNull() {
		assertThrows(NullPointerException.class, 
				() -> p.setIme(null));
	}

	@Test
	void testSetImePrazno() {
		assertThrows(IllegalArgumentException.class, 
				() -> p.setIme(""));
	}

	@Test
	void testSetPrezime() {
		p.setPrezime("Marić");
		assertEquals("Marić", p.getPrezime());
	}

	@Test
	void testSetPrezimeNull() {
		assertThrows(NullPointerException.class, 
				() -> p.setPrezime(null));
	}

	@Test
	void testSetPrezimePrazno() {
		assertThrows(IllegalArgumentException.class, 
				() -> p.setPrezime(""));
	}

	@Test
	void testSetEmail() {
		p.setEmail("m@gmail.com");
		assertEquals("m@gmail.com", p.getEmail());
	}

	@Test
	void testSetEmailNull() {
		assertThrows(NullPointerException.class, 
				() -> p.setEmail(null));
	}

	@Test
	void testSetEmailPrazan() {
		assertThrows(IllegalArgumentException.class, 
				() -> p.setEmail(""));
	}

	@Test
	void testSetTipPacijenta() {
		TipPacijenta tp = new TipPacijenta(1, StarosnaDob.odrasli, Pol.musko);
		p.setTipPacijenta(tp);
		assertEquals(tp, p.getTipPacijenta());
	}

	@Test
	void testSetTipPacijentaNull() {
		assertThrows(NullPointerException.class, 
				() -> p.setTipPacijenta(null));
	}

	@Test
	void testToString() {
		p.setIme("Ivana");
		p.setPrezime("Ivanic");
		assertTrue(p.toString().contains("Ivana") && p.toString().contains("Ivanic"));
	}

	@ParameterizedTest
	@CsvSource({
		"ana@gmail.com,ana@gmail.com,true",
		"ana@gmail.com,pera@gmail.com,false",
	})
	void testEquals(String email1, String email2, boolean expected) {
		
		Pacijent p1=new Pacijent();
		p1.setEmail(email1);
		Pacijent p2=new Pacijent();
		p2.setEmail(email2);
		
		assertEquals(expected, p1.equals(p2));
	}

	@Test
	void testVratiListu() throws Exception {
		
		ResultSet rs = mock(ResultSet.class);

		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("pacijent.idPacijent")).thenReturn(1);
		when(rs.getString("pacijent.ime")).thenReturn("Maja");
		when(rs.getString("pacijent.prezime")).thenReturn("Majic");
		when(rs.getString("pacijent.email")).thenReturn("maja@gmail.com");
		when(rs.getInt("tip_pacijenta.idTipPacijenta")).thenReturn(2);
		when(rs.getString("tip_pacijenta.starosnaDob")).thenReturn("odrasli");
		when(rs.getString("tip_pacijenta.pol")).thenReturn("zensko");

		List<ApstraktniDomenskiObjekat> lista = p.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());
		
		Pacijent pacijent = (Pacijent) lista.get(0);
		assertEquals(1, pacijent.getIdPacijent());
		assertEquals("Maja", pacijent.getIme());
		assertEquals("Majic", pacijent.getPrezime());
		assertEquals("maja@gmail.com", pacijent.getEmail());
		assertEquals(StarosnaDob.odrasli, pacijent.getTipPacijenta().getStarosnaDob());
		assertEquals(Pol.zensko, pacijent.getTipPacijenta().getPol());
	}

	@Test
	void testVratiNazivTabele() {
		assertEquals("pacijent", p.vratiNazivTabele());
	}

	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("ime,prezime,email,tipPacijenta_id", p.vratiKoloneZaUbacivanje());
	}

	@Test
	void testVratiVrednostiZaUbacivanje() {
		TipPacijenta tp = new TipPacijenta(4, StarosnaDob.odrasli, Pol.musko);
		p.setIme("Marko");
		p.setPrezime("Markovic");
		p.setEmail("marko@gmail.com");
		p.setTipPacijenta(tp);

		String expected = "'Marko','Markovic','marko@gmail.com',4";
		assertEquals(expected, p.vratiVrednostiZaUbacivanje());
	}

	@Test
	void testVratiPrimarniKljuc() {
		p.setIdPacijent(5);
		assertEquals("pacijent.idPacijent=5", p.vratiPrimarniKljuc());
	}

	@Test
	void testVratiVrednostiZaIzmenu() {
		TipPacijenta tp = new TipPacijenta(3, StarosnaDob.penzioneri, Pol.zensko);
		p.setIme("Lana");
		p.setPrezime("Lazic");
		p.setEmail("lana@gmail.com");
		p.setTipPacijenta(tp);

		String expected = "ime= 'Lana',prezime= 'Lazic',email= 'lana@gmail.com',tipPacijenta_id= 3";
		assertEquals(expected, p.vratiVrednostiZaIzmenu());
	}

}
