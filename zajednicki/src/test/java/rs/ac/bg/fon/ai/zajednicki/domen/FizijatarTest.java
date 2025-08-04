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

class FizijatarTest {

	private Fizijatar f;
	
	@BeforeEach
	void setUp() throws Exception {
		f=new Fizijatar();
	}

	@AfterEach
	void tearDown() throws Exception {
		f=null;
	}

	
	@Test
	void testFizijatar() {
		assertNotNull(f);
	}
	
	@Test
	void testFizijatarIntStringStringStringString() {
		
		f=new Fizijatar(3,"Zika","Zikic","zika","z1");
		
		assertNotNull(f);
		
		assertEquals(3, f.getIdFizijatra());
		assertEquals("Zika", f.getIme());
		assertEquals("Zikic", f.getPrezime());
		assertEquals("zika", f.getKorisnickoIme());
		assertEquals("z1", f.getSifra());
	}
	
	@Test
	void testSetId() {
		
		f.setIdFizijatra(1);
		
		assertEquals(1, f.getIdFizijatra());
	}
	
	@Test
	void testSetIdPogresno() {
		
		assertThrows(java.lang.IllegalArgumentException.class,
				()-> f.setIdFizijatra(-5));
	}
	
	@Test
	void testSetIme() {
		
		f.setIme("Zika");
		
		assertEquals("Zika", f.getIme());
	}

	@Test
	void testSetImeNull() {
		
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setIme(null));
	}
	
	@Test
	void testSetImePrazno() {
		
		assertThrows(java.lang.IllegalArgumentException.class,
				()-> f.setIme(""));
	}
	
	@Test
	void testSetPrezime() {
		
		f.setPrezime("Zikic");
		
		assertEquals("Zikic", f.getPrezime());
	}

	@Test
	void testSetPrezimeNull() {
		
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setPrezime(null));
	}
	
	@Test
	void testSetPrezimePrazno() {
		
		assertThrows(java.lang.IllegalArgumentException.class,
				()-> f.setPrezime(""));
	}
	
	@Test
	void testSetKorisnickoIme() {
		
		f.setKorisnickoIme("Zikic");
		
		assertEquals("Zikic", f.getKorisnickoIme());
	}

	@Test
	void testSetKorisnickoImeNull() {
		
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setKorisnickoIme(null));
	}
	
	@Test
	void testSetKorisnickoImePrazno() {
		
		assertThrows(java.lang.IllegalArgumentException.class,
				()-> f.setKorisnickoIme(""));
	}
	
	@Test
	void testSetSifra() {
		
		f.setKorisnickoIme("Zikic");
		
		assertEquals("Zikic", f.getKorisnickoIme());
	}

	@Test
	void testSetSifraNull() {
		
		assertThrows(java.lang.NullPointerException.class,
				()-> f.setSifra(null));
	}
	
	@Test
	void testSetSifraPrazno() {
		
		assertThrows(java.lang.IllegalArgumentException.class,
				()-> f.setSifra(""));
	}
	
	@Test
	void testToString() {
		f.setIme("Pera");
		f.setPrezime("Peric");
		
		assertTrue(f.toString().contains("Pera") && f.toString().contains("Peric"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"pera, p1, pera, p1, true",
		"pera, p1, zika, p1, false",
		"pera, p1, pera, z1, false",
		"pera, p1, zika, z1, false",
		
	})
	void testEquals(String korisnickoIme1,String sifra1,String korisnickoIme2,String sifra2,boolean ocekivano) {
		f=new Fizijatar(1,"ime","prezime",korisnickoIme1,sifra1);
		Fizijatar f1=new Fizijatar(1,"ime","prezime",korisnickoIme2,sifra2);
		
		assertEquals(ocekivano, f.equals(f1));
	}
	
	@Test
	void testVratiListu() throws Exception {                //JedanRed
	    // 1. Pravimo mock ResultSet
	    ResultSet rs = mock(ResultSet.class);

	    // 2. Simuliramo ponašanje ResultSet-a
	    when(rs.next()).thenReturn(true).thenReturn(false);  // ima jedan red
	    when(rs.getInt("fizijatar.idFizijatra")).thenReturn(1);
	    when(rs.getString("fizijatar.ime")).thenReturn("Pera");
	    when(rs.getString("fizijatar.prezime")).thenReturn("Peric");
	    when(rs.getString("fizijatar.korisnickoIme")).thenReturn("pera123");
	    when(rs.getString("fizijatar.sifra")).thenReturn("lozinka");

	    // 3. Pozivamo metodu koju testiramo
	    Fizijatar f = new Fizijatar();
	    List<ApstraktniDomenskiObjekat> lista = f.vratiListu(rs);

	    // 4. Proveravamo rezultate
	    assertNotNull(lista);
	    assertEquals(1, lista.size());

	    Fizijatar rezultat = (Fizijatar) lista.get(0);
	    assertEquals(1, rezultat.getIdFizijatra());
	    assertEquals("Pera", rezultat.getIme());
	    assertEquals("Peric", rezultat.getPrezime());
	    assertEquals("pera123", rezultat.getKorisnickoIme());
	    assertEquals("lozinka", rezultat.getSifra());
	}

	@Test
	void testVratiNazivTabele() {
		assertEquals("fizijatar", f.vratiNazivTabele());
	}
	
	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("ime,prezime,korisnickoIme,sifra", f.vratiKoloneZaUbacivanje());
	}
	
	@Test
	void vratiVrednostiZaUbacivanje() {
		f.setIme("Zika");
		f.setPrezime("Zikic");
		f.setKorisnickoIme("zika");
		f.setSifra("z1");
		
		String ocekivano="'Zika','Zikic','zika','z1'";
		assertEquals(ocekivano, f.vratiVrednostiZaUbacivanje());
	}
	@Test
	void testVratiPrimarniKljuc() {
		f.setIdFizijatra(5);
		
		String ocekivano="fizijatar.idFizijatra=5";
		assertEquals(ocekivano, f.vratiPrimarniKljuc());
	}
	
	@Test
	void testVratiVrednostiZaIzmenu() {
		f.setIme("Zika");
		f.setPrezime("Zikic");
		f.setKorisnickoIme("zika");
		f.setSifra("z1");
		
		String ocekivano="ime= 'Zika',prezime= 'Zikic',korisnickoIme= 'zika',sifra= 'z1'";
		assertEquals(ocekivano, f.vratiVrednostiZaIzmenu());
	}
}
