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

class SpecijalizacijaTest {

	Specijalizacija s;
	@BeforeEach
	void setUp() throws Exception {
		s=new Specijalizacija();
	}

	@AfterEach
	void tearDown() throws Exception {
		s=null;
	}

	@Test
	void testSpecijalizacija() {
		assertNotNull(s);
	}

	@Test
	void testSpecijalizacijaIntStringString() {
		
		s=new Specijalizacija(1,"Fizioterapeut","Visoka zdravstvena škola strukovnih studija u Nišu");
		
		assertNotNull(s);
		
		assertEquals(1, s.getIdSpecijalizacija());
		assertEquals("Fizioterapeut", s.getNaziv());
		assertEquals("Visoka zdravstvena škola strukovnih studija u Nišu", s.getInstitucija());
	}
	
	@Test
	void testSetIdSpecijalizacija() {
		
		s.setIdSpecijalizacija(1);
		assertEquals(1, s.getIdSpecijalizacija());
	}
	
	@Test
	void testSetIdSpecijalizacijaPogresno() {
		
		assertThrows(java.lang.IllegalArgumentException.class, 
				() -> s.setIdSpecijalizacija(-3));
	}
	
	@Test
	void testSetNaziv() {
		s.setNaziv("Fizioterapeut");
		assertEquals("Fizioterapeut", s.getNaziv());
	}
	
	@Test
	void testSetNazivNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> s.setNaziv(null));
	}
	
	@Test
	void testSetNazivPrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()-> s.setNaziv(""));
	}
	
	@Test
	void testSetInstitucija() {
		s.setInstitucija("Visoka zdravstvena škola strukovnih studija u Nišu");
		assertEquals("Visoka zdravstvena škola strukovnih studija u Nišu", s.getInstitucija());
	}
	
	@Test
	void testSetInstitucijaNull() {
		assertThrows(java.lang.NullPointerException.class, 
				()-> s.setInstitucija(null));
	}
	@Test
	void testSetInstitucijaPrazno() {
		assertThrows(java.lang.IllegalArgumentException.class, 
				()-> s.setInstitucija(""));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,true",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,2,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,1,Ergoterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,2,Ergoterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,2,Ergoterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,2,Fizioterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
		"1,Fizioterapeut,Visoka zdravstvena škola strukovnih studija u Nišu,1,Ergoterapeut,Visoka zdravstvena škola strukovnih studija Beograd,false",
	})
	void testEquals(int id1,String naziv1,String instritucija1,int id2,String naziv2,String instritucija2,boolean ocekivano) {
		
		
		s=new Specijalizacija(id1,naziv1,instritucija1);
		Specijalizacija s2=new Specijalizacija(id2,naziv2,instritucija2);
		
		assertEquals(ocekivano, s.equals(s2));

	}
	
	@Test
	void testToString() {
		s.setNaziv("Fizioterapeut");
		assertTrue(s.toString().contains("Fizioterapeut"));
	}
	
	@Test
	void testVratiListu() throws Exception {
		
		ResultSet rs=mock(ResultSet.class);
		
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt("specijalizacija.idSpecijalizacija")).thenReturn(1);
		when(rs.getString("specijalizacija.naziv")).thenReturn("Fizioterapeut");
		when(rs.getString("specijalizacija.institucija")).thenReturn("Visoka zdravstvena škola strukovnih studija u Nišu");
		
		List<ApstraktniDomenskiObjekat>lista=s.vratiListu(rs);
		
		assertNotNull(lista);
		assertEquals(lista.size(), 1);
		
		
		s=(Specijalizacija) lista.get(0);
		
		assertEquals(1, s.getIdSpecijalizacija());
		assertEquals("Fizioterapeut", s.getNaziv());
		assertEquals("Visoka zdravstvena škola strukovnih studija u Nišu", s.getInstitucija());
	}
	
	@Test
	void testVratiNazivTabele() {
		assertEquals("specijalizacija", s.vratiNazivTabele());
	}
	
	@Test
	void testVratiKoloneZaUbacivanje() {
		assertEquals("naziv,institucija", s.vratiKoloneZaUbacivanje());
	}
	
	@Test
	void testVratiVrednostiZaUbacivanje() {
		s.setNaziv("Fizioterapeut");
		s.setInstitucija("Visoka zdravstvena škola strukovnih studija u Nišu");
		
		assertEquals("'Fizioterapeut','Visoka zdravstvena škola strukovnih studija u Nišu'", s.vratiVrednostiZaUbacivanje());
	}
	
	@Test 
	void testVratiPrimarniKljuc(){
		s.setIdSpecijalizacija(1);
		assertEquals("specijalizacija.idSpecijalizacija=1", s.vratiPrimarniKljuc());
	}
	
	@Test
	void testVratiVrednostiZaIzmenu() {
		s.setNaziv("Fizioterapeut");
		s.setInstitucija("Visoka zdravstvena škola strukovnih studija u Nišu");
		
		assertEquals("naziv= 'Fizioterapeut',institucija= 'Visoka zdravstvena škola strukovnih studija u Nišu'", s.vratiVrednostiZaIzmenu());
		
	}
}
