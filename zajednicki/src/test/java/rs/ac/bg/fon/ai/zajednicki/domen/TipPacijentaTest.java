package rs.ac.bg.fon.ai.zajednicki.domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.ai.zajednicki.domen.Enum.Pol;
import rs.ac.bg.fon.ai.zajednicki.domen.Enum.StarosnaDob;

class TipPacijentaTest {

	TipPacijenta tp;

    @BeforeEach
    void setUp() {
        tp = new TipPacijenta();
    }
    @AfterEach
    void tearDown() {
        tp = null;
    }

    @Test
	void testTipPacijenta() {
		assertNotNull(tp);
	}
    
    @Test
	void testTipPacijentaIntStarosnaDobPol() {
    	tp=new TipPacijenta(1,StarosnaDob.deca,Pol.musko);
    	
		assertNotNull(tp);
		
		assertEquals(1, tp.getIdTipPacijenta());
		assertEquals(StarosnaDob.deca, tp.getStarosnaDob());
		assertEquals(Pol.musko, tp.getPol());
	}
    
    
    
    @Test
    void testSetIdTipPacijentaValid() {
        tp.setIdTipPacijenta(5);
        assertEquals(5, tp.getIdTipPacijenta());
    }

    @Test
    void testSetIdTipPacijentaPogresan() {
        assertThrows(IllegalArgumentException.class, 
        		() -> tp.setIdTipPacijenta(-1));
    }

    @Test
    void testSetPolNull() {
        assertThrows(NullPointerException.class, 
        		() -> tp.setPol(null));
    }

    @Test
    void testSetStarosnaDobNull() {
        assertThrows(NullPointerException.class, 
        		() -> tp.setStarosnaDob(null));
    }

    @ParameterizedTest
    @CsvSource({
    	"deca,musko,deca,musko,true",
    	"deca,musko,deca,zensko,false",
    	"deca,musko,odrasli,musko,false",
    	"deca,musko,odrasli,zensko,false",
    	"deca,musko,penzioneri,musko,false",
    	"deca,musko,penzioneri,zensko,false",

    	"deca,zensko,deca,zensko,true",
    	"deca,zensko,odrasli,musko,false",
    	"deca,zensko,odrasli,zensko,false",
    	"deca,zensko,penzioneri,musko,false",
    	"deca,zensko,penzioneri,zensko,false",

    	"odrasli,musko,odrasli,musko,true",
    	"odrasli,musko,odrasli,zensko,false",
    	"odrasli,musko,penzioneri,musko,false",
    	"odrasli,musko,penzioneri,zensko,false",

    	"odrasli,zensko,odrasli,zensko,true",
    	"odrasli,zensko,penzioneri,musko,false",
    	"odrasli,zensko,penzioneri,zensko,false",

    	"penzioneri,musko,penzioneri,musko,true",
    	"penzioneri,musko,penzioneri,zensko,false",

    	"penzioneri,zensko,penzioneri,zensko,true"

    })
    void testEquals(StarosnaDob dob1, Pol pol1, StarosnaDob dob2, Pol pol2, boolean expected) {
        TipPacijenta tp1 = new TipPacijenta(1, dob1, pol1);
        TipPacijenta tp2 = new TipPacijenta(2, dob2, pol2); // ID se ne koristi u equals
        assertEquals(expected, tp1.equals(tp2));
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
    	assertEquals("starosnaDob,pol", tp.vratiKoloneZaUbacivanje());
    }

    @Test
    void testToString() {
        tp.setStarosnaDob(StarosnaDob.deca);
        tp.setPol(Pol.zensko);
        String s = tp.toString();
        assertTrue(s.toString().contains("deca"));
        assertTrue(s.toString().contains("zensko"));
    }

    @Test
    void testVrednostiZaUbacivanje() {
        tp.setStarosnaDob(StarosnaDob.deca);
        tp.setPol(Pol.zensko);
        assertEquals("'deca','zensko'", tp.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVrednostiZaIzmenu() {
        tp.setStarosnaDob(StarosnaDob.odrasli);
        tp.setPol(Pol.musko);
        assertEquals("starosnaDob= 'odrasli',pol= 'musko'", tp.vratiVrednostiZaIzmenu());
    }

    @Test
    void testPrimarniKljuc() {
        tp.setIdTipPacijenta(77);
        assertEquals("tip_pacijenta.idTipPacijenta=77", tp.vratiPrimarniKljuc());
    }

    @Test
    void testNazivTabele() {
        assertEquals("tip_pacijenta", tp.vratiNazivTabele());
    }
}
