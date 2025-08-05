package rs.ac.bg.fon.ai.zajednicki.domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class NalazTest {

	private Nalaz nalaz;
	private Date datum;

    @BeforeEach
    void setUp() {
        nalaz = new Nalaz();
        datum =new Date(System.currentTimeMillis()-10000);
    }

    @AfterEach
	void tearDown() throws Exception {
    	nalaz=null;
    	datum=null;
	}
    
    @Test
    void testSetIdNalaz() {
        nalaz.setIdNalaz(1);
        assertEquals(1, nalaz.getIdNalaz());
    }

    @Test
    void testSetIdNalazPogresno() {
        assertThrows(IllegalArgumentException.class,
        		() -> nalaz.setIdNalaz(-1));
    }

    @Test
    void testSetDatumIzdavanja() {
        Date now = new Date();
        nalaz.setDatumIzdavanja(now);
        assertEquals(now, nalaz.getDatumIzdavanja());
    }

    @Test
    void testSetDatumIzdavanjaNull() {
        assertThrows(NullPointerException.class, 
        		() -> nalaz.setDatumIzdavanja(null));
    }

    @Test
    void testSetDatumIzdavanjaPogresno() {
        Date future = new Date(System.currentTimeMillis() + 100000);
        assertThrows(IllegalArgumentException.class, 
        		() -> nalaz.setDatumIzdavanja(future));
    }

    @Test
    void testSetOpisNalaza() {
        nalaz.setOpisNalaza("Opis");
        assertEquals("Opis", nalaz.getOpisNalaza());
    }

    @Test
    void testSetOpisNalazaNull() {
        assertThrows(NullPointerException.class, 
        		() -> nalaz.setOpisNalaza(null));
    }

    @Test
    void testSetOpisNalazaEmpty() {
        assertThrows(IllegalArgumentException.class, 
        		() -> nalaz.setOpisNalaza(""));
    }

    @Test
    void testSetUkupnaCena() {
        nalaz.setUkupnaCena(100);
        assertEquals(100, nalaz.getUkupnaCena());
    }

    @Test
    void testSetUkupnaCenaPogresno() {
        assertThrows(IllegalArgumentException.class, 
        		() -> nalaz.setUkupnaCena(-10));
    }

    @Test
    void testSetFizijatarValid() {
        Fizijatar f = new Fizijatar();
        nalaz.setFizijatar(f);
        assertEquals(f, nalaz.getFizijatar());
    }

    @Test
    void testSetFizijatarNull() {
        assertThrows(NullPointerException.class, 
        		() -> nalaz.setFizijatar(null));
    }

    @Test
    void testSetPacijent() {
        Pacijent p = new Pacijent();
        nalaz.setPacijent(p);
        assertEquals(p, nalaz.getPacijent());
    }

    @Test
    void testSetPacijentNull() {
        assertThrows(NullPointerException.class, 
        		() -> nalaz.setPacijent(null));
    }

    @Test
    void testSetListaStavki() {
        List<StavkaNalaza> lista = new ArrayList<>();
        lista.add(new StavkaNalaza());
        nalaz.setListaStavki(lista);
        assertEquals(lista, nalaz.getListaStavki());
    }

    @Test
    void testSetListaStavkiNull() {
        assertThrows(NullPointerException.class, 
        		() -> nalaz.setListaStavki(null));
    }

    @Test
    void testSetListaStavkiPrazna() {
        assertThrows(IllegalArgumentException.class, 
        		() -> nalaz.setListaStavki(new ArrayList<>()));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,true",
        "1,2,false",
        
    })
    void testEquals(int id1, int id2, boolean expected) {
        Nalaz n1 = new Nalaz();
        Nalaz n2 = new Nalaz();
        n1.setIdNalaz(id1);
        n2.setIdNalaz(id2);
        assertEquals(expected, n1.equals(n2));
    }

    @Test
    void testToString() {
        nalaz.setOpisNalaza("Opis");
        nalaz.setUkupnaCena(150);
        assertTrue(nalaz.toString().contains("Opis"));
        assertTrue(nalaz.toString().contains("150"));
    }
    
    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("datumIzdavanja,opisNalaza,ukupnaCena,fizijatar_id,pacijent_id", nalaz.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
    	Fizijatar fizijatar = new Fizijatar();
        fizijatar.setIdFizijatra(1);

        TipPacijenta tp = new TipPacijenta(1, StarosnaDob.odrasli, Pol.musko);
        Pacijent pacijent = new Pacijent(2, "Ime", "Prezime", "mail@mail.com", tp);

        Nalaz nalaz = new Nalaz();
        nalaz.setDatumIzdavanja(datum);
        nalaz.setOpisNalaza("Opis");
        nalaz.setUkupnaCena(2000.0);
        nalaz.setFizijatar(fizijatar);
        nalaz.setPacijent(pacijent);
        nalaz.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        String expected = "'"+new java.sql.Date(datum.getTime())+"','Opis',2000.0,1,2";
        assertEquals(expected, nalaz.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
    	nalaz.setIdNalaz(10);
        assertEquals("nalaz.idNalaz=10", nalaz.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
    	Fizijatar fizijatar = new Fizijatar();
        fizijatar.setIdFizijatra(1);

        TipPacijenta tp = new TipPacijenta(1, StarosnaDob.odrasli, Pol.musko);
        Pacijent pacijent = new Pacijent(2, "Ime", "Prezime", "mail@mail.com", tp);

        Nalaz nalaz = new Nalaz();
        nalaz.setDatumIzdavanja(datum);
        nalaz.setOpisNalaza("Opis");
        nalaz.setUkupnaCena(2000.0);
        nalaz.setFizijatar(fizijatar);
        nalaz.setPacijent(pacijent);
        nalaz.setListaStavki(List.of(new StavkaNalaza(1, 2000.0, new Terapija(1, "Naziv", "Opis", 2000.0), nalaz)));

        String expected = "datumIzdavanja= '"+new java.sql.Date(datum.getTime())+"',opisNalaza= 'Opis',ukupnaCena= 2000.0,fizijatar_id= 1,pacijent_id=2";
        assertEquals(expected, nalaz.vratiVrednostiZaIzmenu());
    }

    @Test
    void testVratiListu() throws Exception {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.next()).thenReturn(true, false);

        Mockito.when(rs.getInt("nalaz.idNalaz")).thenReturn(10);
        Mockito.when(rs.getDate("nalaz.datumIzdavanja")).thenReturn(new java.sql.Date(datum.getTime()));
        Mockito.when(rs.getString("nalaz.opisNalaza")).thenReturn("Opis test");
        Mockito.when(rs.getDouble("nalaz.ukupnaCena")).thenReturn(3000.0);

        Mockito.when(rs.getInt("fizijatar.idFizijatra")).thenReturn(5);
        Mockito.when(rs.getString("fizijatar.ime")).thenReturn("FizIme");
        Mockito.when(rs.getString("fizijatar.prezime")).thenReturn("FizPrez");
        Mockito.when(rs.getString("fizijatar.korisnickoIme")).thenReturn("fkorisnik");
        Mockito.when(rs.getString("fizijatar.sifra")).thenReturn("fsifra");

        Mockito.when(rs.getInt("pacijent.idPacijent")).thenReturn(6);
        Mockito.when(rs.getString("pacijent.ime")).thenReturn("PacIme");
        Mockito.when(rs.getString("pacijent.prezime")).thenReturn("PacPrez");
        Mockito.when(rs.getString("pacijent.email")).thenReturn("pacijent@email.com");

        List<ApstraktniDomenskiObjekat> lista = nalaz.vratiListu(rs);
        assertNotNull(lista);
        assertEquals(1, lista.size());
        
        Nalaz rezultat = (Nalaz) lista.get(0);

        // Nalaz
        assertEquals(10, rezultat.getIdNalaz());
        assertEquals(datum.getTime(), rezultat.getDatumIzdavanja().getTime());
        assertEquals("Opis test", rezultat.getOpisNalaza());
        assertEquals(3000.0, rezultat.getUkupnaCena());

        // Fizijatar
        assertEquals(5, rezultat.getFizijatar().getIdFizijatra());
        assertEquals("FizIme", rezultat.getFizijatar().getIme());
        assertEquals("FizPrez", rezultat.getFizijatar().getPrezime());
        assertEquals("fkorisnik", rezultat.getFizijatar().getKorisnickoIme());
        assertEquals("fsifra", rezultat.getFizijatar().getSifra());

        // Pacijent
        assertEquals(6, rezultat.getPacijent().getIdPacijent());
        assertEquals("PacIme", rezultat.getPacijent().getIme());
        assertEquals("PacPrez", rezultat.getPacijent().getPrezime());
        assertEquals("pacijent@email.com", rezultat.getPacijent().getEmail());
    }
}
    
