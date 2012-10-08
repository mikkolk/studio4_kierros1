import java.util.ArrayList;


/**
 * Nimipallo visualisoi yhtä nimeä. Se piirtää ympärilleen tietyn määrän
 * renkaita.
 *
 * @author Gandalf.
 *         Created 5.10.2012.
 */
public class Nimipallo
{
	// ATTRIBUUTIT
	// jossa n on nimien maara, r on pallon säde ja k on viivan paksuus
	private int x, y, n, r, k;
	private String nimi;
	
	
	// KONSTRUKTORI
	/**
	 * 
	 * Metodi luo uuden Nimipallon tiettyihin koordinaatteihin tietyllä nimellä
	 *
	 * @param uusix nimipallon keskipisteen x-koordinaatti 
	 * @param uusiy nimipallon keskipisteen y-koordinaatti
	 * @param uusinimi nimipallon tulostama nimi
	 * @param maara kuinka monta rengasta nimipallon ympärille tulee
	 * @param sade nimipallon uusi säde
	 * @param paksuus nimipallon renkaiden paksuus pikseleinä
	 */
	public Nimipallo(int uusix, int uusiy, String uusinimi, int maara,
			int sade, int paksuus)
	{
		this.x = uusix;
		this.y = uusiy;
		this.nimi = uusinimi;
		this.n = maara;
		this.r = sade;
		this.k = paksuus;
	}
	
	
	// GETTERIT JA SETTERIT
	
	/**
	 * 
	 * Palauttaa nimiolennon keskipisteen sijainnin x-y-koordinaatistossa
	 *
	 * @return x-y-koodrinaatit taulukkomuodossa (x, y)
	 */
	public int[] annaKoordinaatit()
	{
		int[] koordinaatit = {this.x, this.y};
		return koordinaatit;
	}
	
	/**
	 * Palauttaa nimipallon ympyrän säteen
	 * 
	 * @return nimipallon säde
	 */
	public int annaSade()
	{
		return this.r;
	}
	
	/**
	 * 
	 * Asettaa nimipallolle uudet koordinaatit
	 *
	 * @param uusix uusi x-koordinaatti
	 * @param uusiy uusi y-koordinaatti
	 */
	public void asetaSijainti(int uusix, int uusiy)
	{
		this.x = uusix;
		this.y = uusiy;
	}
	
	
	// OMAT METODIT
	
	/**
	 * 
	 * Kertoo, leikkisivatko nimipallojen säteet, mikäli tämä nimipallo
	 * sijoitettaisiin annettuun sijaintin.
	 *
	 * @param uusix testattava x-koordinaatti
	 * @param uusiy testattava y-koordinaatti
	 * @param nimet lista nimipalloista, joiden kanssa törmäyksiä voi tulla
	 * @return leikkaavatko nimipallojen säteet
	 */
	public boolean mahtuuSijaintiin(int uusix, int uusiy,
			ArrayList<Nimipallo> nimet)
	{
		// Tarkistaa tormayksen kaikille nimille erikseen
		for (Nimipallo pallo2 : nimet)
		{
			int[] xy = pallo2.annaKoordinaatit();
			
			// Tarkistaa, etteivat pallojen ympyrat leikkaa toisiaan
			if (annaEtaisyys(uusix, uusiy, xy[0], xy[1])
					< this.r + pallo2.annaSade())
				return false;
			
			// TODO: Lisaa tarkistus, etteivat tekstit leikkaa toisiaan
		}
		return true;
	}
	
	// Kertoo kahden pisteen valisen etaisyyden
	private int annaEtaisyys(int x1, int y1, int x2, int y2)
	{
		double a = x1 - x2;
		double b = y1 - y2;
		
		return (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	/**
	 * 
	 * Testaa, löytyykö tarpeeksi läheltä annettua sijaintia toista nimiPalloa
	 * Tällöin pallojen kaarien välille saa jäädä enintään pyydetty välimatka
	 *
	 * @param uusix kokeiltava x-koordinaatti
	 * @param uusiy kokeiltava y-koordinaatti
	 * @param nimet lista mahdollisista vierustovereista
	 * @param minetaisyys suurin sallittu kehien välinen etäisyys
	 * @return olisiko uudessa sijainnissa tarpeeksi lähellä naapuria
	 */
	public boolean onkoLiianKaukana(int uusix, int uusiy,
			ArrayList<Nimipallo> nimet, int minetaisyys)
	{
		// Tarkistaa kaikille palloille erikseen
		// (yksikin tarpeeksi lahella riittaa)
		for (Nimipallo pallo2 : nimet)
		{
			int xy[] = pallo2.annaKoordinaatit();
			int etaisyys = annaEtaisyys(uusix, uusiy, xy[0], xy[1]);
			
			if (etaisyys - this.r - pallo2.annaSade() < minetaisyys)
				return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * Piirtää nimipallon ympyrät sekä nimen
	 *
	 */
	public void piirraItse()
	{
		// TODO: Poista kommentointi (sensuuri)
		// Piirtaa taustaympyrat
		for (int i = 1; i <= n; i++)
		{
			// Lasketaan pallon säde
			int ri = this.r * (i / this.n);
			
			// Asetetaan piirtoväri
			// TODO: Mariannan duuni
			
			// Asetetaan kaaren paksuuus
			// stroke(k);
			// noFill()
			
			// Piirretään ympyrä
			// eclipse(this.x, this.y, ri);
		}
		
		// Piirtaa nimen
		
		// Asettaa fontin
		// TODO: Jonkun muun duuni
		// text(nimi, x, y);
	}
}
