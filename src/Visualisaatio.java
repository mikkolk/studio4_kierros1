import java.util.HashMap;
import processing.core.*;

/**
 * 
 * Visualisaatio piirt‰‰ kaiken n‰ytˆlle ja hoitaa objektien alustamisesta
 * Ohjelman alussa
 *
 * @author Gandalf.
 *         Created 9.10.2012.
 */
public class Visualisaatio extends PApplet
{
	// ATTRIBUUTIT
	private DataLukija lukija;
	private Sijoittaja sijoittaja;
	private SlideriPiirtaja slideripiirtaja;
	private SlideriPiirtaja2 slideripiirtaja2;
	private int viimeekavuos, viimevikavuos;
	
	public Sijoittaja annaSijoittaja(){
	    return this.sijoittaja;
	}
	
	public DataLukija annaDatalukija(){
	    return this.lukija;
	}
	
	public SlideriPiirtaja annaSlideriPiirtaja(){
	    return this.slideripiirtaja;
	}

    @Override
	public void setup()
	{
		size(1200, 600);
		
		// Alustaa datalukijan, sijoittajan ja slideripiirtajan
		this.lukija = new DataLukija("../nimilista.txt");
		this.sijoittaja = new Sijoittaja(this.width, this.height, this);
		this.slideripiirtaja = new SlideriPiirtaja(this);
		this.slideripiirtaja2 = new SlideriPiirtaja2(this);
		
		this.viimeekavuos = 1999;
		this.viimevikavuos = 2012;
		paivitaKuva(1999, 2012);
		/*
		// Lukee vuodet datalukijalla
		HashMap<String, Integer> data =
				this.lukija.kokoaVuodet(1999, 2012);
		String[] jarjestys = this.lukija.annaJarjestys(data);
		
		// Sijoittaa datan kent‰lle
		this.sijoittaja.sijoitaData(data, jarjestys, 13);
		*/
	}

	@Override
	public void draw()
	{	
	    this.background(255);
	    
		// Checkkaa, tuliko vuosien puolesta muutoksia ja p‰ivitt‰‰
		// tarvittaessa kuvan
		tarkistaVuosimuutos();
	    
		// Piirt‰‰ kaikki nimipallot
		textSize(14);
		this.sijoittaja.piirraPallot();
		this.slideripiirtaja.piirraSlideri();
		this.slideripiirtaja2.piirraSlideri();
	}
	
	/**
	 * 
	 * Hakee datalukijasta uuden datan annettujen tietojen perusteella ja
	 * p‰ivitt‰‰ sen n‰ytˆlle
	 *
	 * @param ekavuosi ensimm‰inen mukaan otettava vuosi
	 * @param vikavuosi viimeinen mukaan otettava vuosi
	 */
	private void paivitaKuva(int ekavuosi, int vikavuosi)
	{	
		int a = ekavuosi;
		int b = vikavuosi;
		
		// Tarkistaa, ett‰ arvot ovat oikein p‰in ja vaihtaa
		// ne p‰ikseen tarvittaessa
		if (ekavuosi > vikavuosi)
		{
			a = vikavuosi;
			b = ekavuosi;
		}
		
		// Lukee vuodet datalukijalla
		HashMap<String, Integer> data =
				this.lukija.kokoaVuodet(a, b);
		System.out.println(data.size());
		String[] jarjestys = this.lukija.annaJarjestys(data);
		System.out.println(jarjestys.length);
		
		// Sijoittaa datan kent‰lle
		this.sijoittaja.sijoitaData(data, jarjestys, b - a + 1);
	}
	
	// Tarkistaa sliderien arvot ja vertaa niit‰ vanhoihin,
	// piirt‰‰ tarvittaessa systeemit uudestaan ja palauttaa, mik‰li arvot
	// muuttuivat
	private boolean tarkistaVuosimuutos()
	{
		int uusiekavuos = this.slideripiirtaja.annaSlider1Arvo();
		int uusivikavuos = this.slideripiirtaja2.annaSlider2Arvo();
		
		System.out.println(uusiekavuos);
		System.out.println(uusivikavuos);
		
		// P‰ivitt‰‰ ruudun, mik‰li jotain muutosta tapahtui
		if (uusiekavuos != this.viimeekavuos ||
				uusivikavuos != this.viimevikavuos)
		{
			paivitaKuva(uusiekavuos, uusivikavuos);
			this.viimeekavuos = uusiekavuos;
			this.viimevikavuos = uusivikavuos;
			
			return true;
		}
		
		return false;
	}
}