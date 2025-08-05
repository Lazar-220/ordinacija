package rs.ac.bg.fon.ai.zajednicki.domen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Fizijatar_specijalistaTest {

	private Fizijatar_specijalista fs;

	private Fizijatar fizijatar;
	private Specijalizacija specijalizacija;
	private Date datum;

	@BeforeEach
	void setUp() throws Exception {
		fizijatar = new Fizijatar(1, "Pera", "Peric", "pera", "p1");
		specijalizacija=new Specijalizacija(1,"Fizioterapeut","Visoka zdravstvena škola strukovnih studija u Nišu");
		datum = new Date(System.currentTimeMillis() - 10000); // validan datum u prošlosti
		fs = new Fizijatar_specijalista();
	}

	@AfterEach
	void tearDown() throws Exception {
		fs = null;
		fizijatar=null;
		specijalizacija=null;
		datum=null;
	}

	@Test
	void testConstructor() {
		assertNotNull(fs);
	}
	
	@Test
	void testConstructorWithAllArguments() {
		fs = new Fizijatar_specijalista(10, fizijatar, specijalizacija, datum);

		assertEquals(10, fs.getIdSertifikat());
		assertEquals(fizijatar, fs.getFizijatar());
		assertEquals(specijalizacija, fs.getSpecijalizacija());
		assertEquals(datum, fs.getDatumIzdavanja());
	}

	@Test
	void testSetIdSertifikat() {
		fs.setIdSertifikat(5);
		assertEquals(5, fs.getIdSertifikat());
	}

	@Test
	void testSetIdSertifikatPogresan() {
		assertThrows(IllegalArgumentException.class, 
				() -> fs.setIdSertifikat(-1));
	}

	@Test
	void testSetFizijatar() {
		fs.setFizijatar(fizijatar);
		assertEquals(fizijatar, fs.getFizijatar());
	}

	@Test
	void testSetFizijatarNull() {
		assertThrows(NullPointerException.class, 
				() -> fs.setFizijatar(null));
	}

	@Test
	void testSetSpecijalizacija() {
		fs.setSpecijalizacija(specijalizacija);
		assertEquals(specijalizacija, fs.getSpecijalizacija());
	}

	@Test
	void testSetSpecijalizacijaNull() {
		assertThrows(NullPointerException.class, 
				() -> fs.setSpecijalizacija(null));
	}

	@Test
	void testSetDatum() {
		fs.setDatumIzdavanja(datum);
		assertEquals(datum, fs.getDatumIzdavanja());
	}

	@Test
	void testSetDatumNull() {
		assertThrows(NullPointerException.class, 
				() -> fs.setDatumIzdavanja(null));
	}

	@Test
	void testSetDatumPogresan() {
		Date future = new Date(System.currentTimeMillis() + 100000);
		assertThrows(IllegalArgumentException.class, 
				() -> fs.setDatumIzdavanja(future));
	}

	@Test
	void testToString() {
		fs = new Fizijatar_specijalista(10, fizijatar, specijalizacija, datum);
		String s = fs.toString();
		assertTrue(s.contains(fizijatar.getIme()));
		assertTrue(s.contains(fizijatar.getPrezime()));
		assertTrue(s.contains(specijalizacija.getNaziv()));
	}

	@ParameterizedTest
	@CsvSource({
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,true",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz2,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz1,Ergoterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz2,Ergoterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz2,Ergoterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz2,Fizioterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"fiz1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,fiz1,Ergoterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
	
	})
	void testEquals(String sifra1, String naziv1, String inst1,
					String sifra2, String naziv2, String inst2,
	                boolean expected) {

		Fizijatar f1=new Fizijatar();
		f1.setSifra(sifra1);
		Fizijatar f2=new Fizijatar();
		f2.setSifra(sifra2);
		Specijalizacija s1 = new Specijalizacija(1, naziv1, inst1);
		Specijalizacija s2 = new Specijalizacija(2, naziv2, inst2);

		Fizijatar_specijalista fs1 = new Fizijatar_specijalista(10, f1, s1, datum);
		Fizijatar_specijalista fs2 = new Fizijatar_specijalista(20, f2, s2, datum);

		assertEquals(expected, fs1.equals(fs2));
	}

	@Test
	void testVratiNazivTabele() {
		assertEquals("fizijatar_specijalista", fs.vratiNazivTabele());
	}

	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("datumIzdavanja,fizijatar_id,specijalizacija_id", fs.vratiKoloneZaUbacivanje());
	}

	@Test
	void testVratiVrednostiZaUbacivanje() {
		fs = new Fizijatar_specijalista(10, fizijatar, specijalizacija, datum);
		String ocekivano = "'"+new java.sql.Date(datum.getTime())+"',"+1+","+1;
		
		assertEquals(ocekivano,fs.vratiVrednostiZaUbacivanje());
	}

	@Test
	void testVratiPrimarniKljuc() {
		fs.setIdSertifikat(77);
		assertEquals("fizijatar_specijalista.idSertifikat=77", fs.vratiPrimarniKljuc());
	}

	@Test
	void testVratiVrednostiZaIzmenu() {
		fs = new Fizijatar_specijalista(10, fizijatar, specijalizacija, datum);
		String ocekivano = "datumIzdavanja= '"+datum+"',fizijatar_id= "+1+",specijalizacija_id= "+1;
		
		assertEquals(ocekivano,fs.vratiVrednostiZaIzmenu());
	}

	@Test
	void testVratiListu() throws Exception {
		ResultSet rs = mock(ResultSet.class);

		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("fizijatar_specijalista.idSertifikat")).thenReturn(1);
		when(rs.getDate("fizijatar_specijalista.datumIzdavanja")).thenReturn(new java.sql.Date(datum.getTime()));
		when(rs.getInt("specijalizacija.idSpecijalizacija")).thenReturn(1);
		when(rs.getString("specijalizacija.naziv")).thenReturn("Fizioterapeut");
		when(rs.getString("specijalizacija.institucija")).thenReturn("Visoka zdravstvena škola strukovnih studija u Nišu");

		when(rs.getInt("fizijatar.idFizijatra")).thenReturn(1);
		when(rs.getString("fizijatar.ime")).thenReturn("Pera");
		when(rs.getString("fizijatar.prezime")).thenReturn("Peric");
		when(rs.getString("fizijatar.korisnickoIme")).thenReturn("pera");
		when(rs.getString("fizijatar.sifra")).thenReturn("p1");

		List<ApstraktniDomenskiObjekat> lista = fs.vratiListu(rs);

		assertNotNull(lista);
		assertEquals(1, lista.size());

		Fizijatar_specijalista sertifikat = (Fizijatar_specijalista) lista.get(0);
		assertEquals(1, sertifikat.getIdSertifikat());
		assertEquals(datum, new java.util.Date(sertifikat.getDatumIzdavanja().getTime()));
		assertEquals(1, sertifikat.getFizijatar().getIdFizijatra());
		assertEquals("Pera", sertifikat.getFizijatar().getIme());
	    assertEquals("Peric", sertifikat.getFizijatar().getPrezime());
	    assertEquals("pera", sertifikat.getFizijatar().getKorisnickoIme());
	    assertEquals("p1", sertifikat.getFizijatar().getSifra());
		assertEquals(1, sertifikat.getSpecijalizacija().getIdSpecijalizacija());
		assertEquals("Fizioterapeut", sertifikat.getSpecijalizacija().getNaziv());
		assertEquals("Visoka zdravstvena škola strukovnih studija u Nišu", sertifikat.getSpecijalizacija().getInstitucija());
	
	}
}
