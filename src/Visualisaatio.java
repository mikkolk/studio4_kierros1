import java.util.HashMap;

import processing.core.*;

/**
 * 
 * Visualisaatio piirtää kaiken näytölle ja hoitaa objektien alustamisesta
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
	
	public Sijoittaja annaSijoittaja(){
	    return this.sijoittaja;
	}
	
	public DataLukija annaDatalukija(){
	    return this.lukija;
	}

    @Override
	public void setup()
	{
		size(1200, 600);
		
		// Alustaa datalukijan, sijoittajan ja slideripiirtajan
		this.lukija = new DataLukija("../nimilista.txt");
		this.sijoittaja = new Sijoittaja(this.width, this.height, this);
		this.slideripiirtaja = new SlideriPiirtaja(this);
		
		// Lukee vuodet 1999-2011
		HashMap<String, Integer> data = this.lukija.kokoaVuodet(1999, 2011);
		String[] jarjestys = this.lukija.annaJarjestys(data);
		
		// Sijoittaa datan kentälle
		this.sijoittaja.sijoitaData(data, jarjestys, 12);
	}

	@Override
	public void draw()
	{
		// Piirtää kaikki nimipallot
		textSize(14);
		this.sijoittaja.piirraPallot();
		this.slideripiirtaja.piirraSliderit();
	}
}