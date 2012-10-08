
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;

public class Visualisaatio extends PApplet {

	// ATTRIBUUTIT
	private DataLukija lukija;
	private Sijoittaja sijoittaja;

	@Override
	public void setup()
	{
		size(800, 500);
		
		// Alustaa datalukijan ja sijoittajan
		this.lukija = new DataLukija("../nimilista.txt");
		this.sijoittaja = new Sijoittaja(this.width, this.height, this);
		
		// Lukee vuodet 1999-2011
		HashMap<String, Integer> data = this.lukija.kokoaVuodet(1999, 2011);
		String[] jarjestys = this.lukija.annaJarjestys(data);
		
		// Sijoittaa datan kent‰lle
		this.sijoittaja.sijoitaData(data, jarjestys, 12);
	}

	@Override
	public void draw()
	{
		// Piirt‰‰ kaikki nimipallot
		this.sijoittaja.piirraPallot();
	}
}