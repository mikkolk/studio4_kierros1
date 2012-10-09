import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import processing.core.PApplet;


/**
 * Sijoittaja asettelee nimipallot kauniisti kent‰lle niin, etteiv‰t ne mene
 * toistensa p‰‰lle ja ett‰ ne menev‰t tarpeeksi l‰hekk‰in
 *
 * @author Gandalf.
 *         Created 5.10.2012.
 */
public class Sijoittaja
{
	// ATTRIBUUTIT
	private ArrayList<Nimipallo> nimet;
	private int leveys, korkeus;
	private PApplet pohja;
	
	
	// KONSTRUKTORI
	/**
	 * 
	 * Luo uuden sijoittajan ja annetaan t‰lle tiedot maailman (ikkunan)
	 * koosta
	 *
	 * @param w ikkunan leveys
	 * @param h ikkunan korkeus
	 * @param vuosia kuinka monen vuoden kooste nimim‰‰r‰ on
	 * @param pohja PApplet, joka lopulta piirt‰‰ kaikki pallot
	 */
	public Sijoittaja(int w, int h, PApplet pohja)
	{
		this.nimet = new ArrayList<Nimipallo>();
		this.leveys = w;
		this.korkeus = h;
		this.pohja = pohja;
	}
	
	
	
	// OMAT METODIT
	
	/**
	 * 
	 * Metodi sijoittaa annetut nimet annetussa j‰rjestyksess‰ ikkunaan
	 * niin, ett‰ eniten esiintynyt nimi tulee ikkunan keskiosaan ja muut sen
	 * ymp‰rille
	 *
	 * @param nimidata Datalukijalta saatu data nimist‰
	 * ja niiden esiintymism‰‰rist‰
	 * @param nimijarjestys Nimidatan nimet j‰rjestettyn‰ suuruusj‰rjestykseen
	 * @param vuosia kuinka monen vuoden nimiotos on kyseessa
	 * @return Palauttaa, onnistuiko sijoittelu
	 */
	public boolean sijoitaData(HashMap<String, Integer> nimidata,
			String[] nimijarjestys, int vuosia)
	{	
		Random rand = new Random();
		
		// Sijoittaa ensin eniten esiintyneen nimen keskelle
		lisaaPallo(this.leveys / 2, this.korkeus / 2, nimijarjestys[0],
				nimidata.get(nimijarjestys[0]), vuosia);
		
		// T‰m‰n j‰lkeen sijoittaa kaikki loput nimet suuruusj‰rjestyksess‰
		for (int i = 1; i < nimijarjestys.length; i++)
		{
			String uusinimi = nimijarjestys[i];
			int uusimaara = nimidata.get(uusinimi);
			
			// Ei lis‰‰ nimi‰, joita on vain 1 kpl
			if (uusimaara == 1)
				break;
			
			Nimipallo pallo =
					lisaaPallo(-1000, -1000, uusinimi, uusimaara, vuosia);
			
			int iteraatioita = 0;
			
			// TODO: Lisaa stackOverFlow-tarkistukset
			// Toistaa, kunnes lˆydet‰‰n sopiva kohta ja breakataan
			while (true)
			{
				iteraatioita ++;
				if (iteraatioita > 10000)
				{
					System.out.println("STACKOVERFLOOW");
					break;
				}
				
				// Arpoo uuden sijainnin
				int uusix = rand.nextInt(this.leveys + 1);
				int uusiy = rand.nextInt(this.korkeus + 1);
				
				// Tarkistaa, ett‰ pallo on kokonaan ikkunassa
				if (!pallo.mahtuukoIkkunaan(uusix, uusiy,
						this.leveys, this.korkeus))
					continue;
				// Tarkistaa, ettei sijainnin kanssa tˆrm‰ill‰
				if (!pallo.mahtuuSijaintiin(uusix, uusiy, this.nimet))
					continue;
				// Tarkistaa, ett‰ l‰hell‰ (<50 px p‰‰ss‰) on toinen pallo
				if (pallo.onkoLiianKaukana(uusix, uusiy, this.nimet, 30))
					continue;
					
				// Jos kaikki OK, sijoittaa pallon uuteen sijaintiin
				pallo.asetaSijainti(uusix, uusiy);
				break;
			}
		}
		
		return false;
	}
	
	// Luo uuden nimipallon annettuun sijaintiin
	// Palauttaa luodun pallon
	private Nimipallo lisaaPallo(int x, int y, String nimi, int n, int vuosia)
	{
		// Lasketaan pallon s‰de ja viivan paksuus
		int r = (int)( (n / ( (double) vuosia)) * 70 + 5 );
		int k = 13 - vuosia;
		if (k < 1)
			k = 1;
		
		Nimipallo uusipallo = new Nimipallo(x, y, nimi,n, r, k, this.pohja);
		this.nimet.add(uusipallo);
		
		return uusipallo;
	}
	
	/**
	 * 
	 * Piirt‰‰ kaikki pallot, jotka on kent‰ll‰
	 *
	 */
	public void piirraPallot()
	{
		for (Nimipallo pallo : this.nimet)
		{
			pallo.piirraItse();
		}
	}
}
