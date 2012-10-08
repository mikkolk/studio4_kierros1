import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Sijoittaja asettelee nimipallot kauniisti kent�lle niin, etteiv�t ne mene
 * toistensa p��lle ja ett� ne menev�t tarpeeksi l�hekk�in
 *
 * @author Gandalf.
 *         Created 5.10.2012.
 */
public class Sijoittaja
{
	// ATTRIBUUTIT
	private ArrayList<Nimipallo> nimet;
	private int leveys, korkeus, vuodet;
	
	
	// KONSTRUKTORI
	/**
	 * 
	 * Luo uuden sijoittajan ja annetaan t�lle tiedot maailman (ikkunan)
	 * koosta
	 *
	 * @param w ikkunan leveys
	 * @param h ikkunan korkeus
	 * @param vuosia kuinka monen vuoden kooste nimim��r� on
	 */
	public Sijoittaja(int w, int h, int vuosia)
	{
		this.nimet = new ArrayList<Nimipallo>();
		this.leveys = w;
		this.korkeus = h;
		this.vuodet = vuosia;
	}
	
	
	
	// OMAT METODIT
	
	/**
	 * 
	 * Metodi sijoittaa annetut nimet annetussa j�rjestyksess� ikkunaan
	 * niin, ett� eniten esiintynyt nimi tulee ikkunan keskiosaan ja muut sen
	 * ymp�rille
	 *
	 * @param nimidata Datalukijalta saatu data nimist�
	 * ja niiden esiintymism��rist�
	 * @param nimijarjestys Nimidatan nimet j�rjestettyn� suuruusj�rjestykseen
	 * @return Palauttaa, onnistuiko sijoittelu
	 */
	public boolean sijoitaData(HashMap<String, Integer> nimidata,
			String[] nimijarjestys)
	{	
		Random rand = new Random();
		
		// Sijoittaa ensin eniten esiintyneen nimen keskelle
		lisaaPallo(this.leveys / 2, this.korkeus / 2, nimijarjestys[0],
				nimidata.get(nimijarjestys[0]));
		
		// T�m�n j�lkeen sijoittaa kaikki loput nimet suuruusj�rjestyksess�
		for (int i = 1; i < nimijarjestys.length; i++)
		{
			String uusinimi = nimijarjestys[i];
			int uusimaara = nimidata.get(uusinimi);
			
			Nimipallo pallo =
					lisaaPallo(-1000, -1000, uusinimi, uusimaara);
			
			// TODO: Lisaa stackOverFlow-tarkistukset
			// Toistaa, kunnes l�ydet��n sopiva kohta ja breakataan
			while (true)
			{
				// Arpoo uuden sijainnin
				int uusix = rand.nextInt(this.leveys + 1);
				int uusiy = rand.nextInt(this.korkeus + 1);
				
				// Tarkistaa, ettei sijainnin kanssa t�rm�ill�
				if (!pallo.mahtuuSijaintiin(uusix, uusiy, this.nimet))
					continue;
				// Tarkistaa, ett� l�hell� (<50 px p��ss�) on toinen pallo
				if (pallo.onkoLiianKaukana(uusix, uusiy, this.nimet, 50))
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
	private Nimipallo lisaaPallo(int x, int y, String nimi, int n)
	{
		// Lasketaan pallon s�de ja viivan paksuus
		int r = (int)( (n / ( (double) this.vuodet)) * 10 + 3 );
		int k = 5 - this.vuodet;
		if (k < 1)
			k = 1;
		
		Nimipallo uusipallo = new Nimipallo(x, y, nimi,n, r, k);
		this.nimet.add(uusipallo);
		
		return uusipallo;
	}
}