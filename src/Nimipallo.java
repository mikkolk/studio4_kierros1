import java.util.ArrayList;
import processing.core.*;


/**
 * Nimipallo visualisoi yht‰ nime‰. Se piirt‰‰ ymp‰rilleen tietyn m‰‰r‰n
 * renkaita.
 *
 * @author Gandalf.
 *         Created 5.10.2012.
 */
public class Nimipallo
{
	// ATTRIBUUTIT
	// jossa n on nimien maara, r on pallon s‰de ja k on viivan paksuus
	private int x, y, n, r, k;
	private String nimi;
	private PApplet pohja;
	
	
	// KONSTRUKTORI
	/**
	 * 
	 * Metodi luo uuden Nimipallon tiettyihin koordinaatteihin tietyll‰ nimell‰
	 *
	 * @param uusix nimipallon keskipisteen x-koordinaatti 
	 * @param uusiy nimipallon keskipisteen y-koordinaatti
	 * @param uusinimi nimipallon tulostama nimi
	 * @param maara kuinka monta rengasta nimipallon ymp‰rille tulee
	 * @param sade nimipallon uusi s‰de
	 * @param paksuus nimipallon renkaiden paksuus pikselein‰
	 * @param pohja PApplet, joka piirt‰‰ nimipallon
	 */
	public Nimipallo(int uusix, int uusiy, String uusinimi, int maara,
			int sade, int paksuus, PApplet pohja)
	{
		this.x = uusix;
		this.y = uusiy;
		this.nimi = uusinimi;
		this.n = maara;
		this.r = sade;
		this.k = paksuus;
		this.pohja = pohja;
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
	 * Palauttaa nimipallon ympyr‰n s‰teen
	 * 
	 * @return nimipallon s‰de
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
	
	/**
	 * 
	 * Palauttaa nimipallon kirjoittaman nimen
	 *
	 * @return nimipallon kirjoittama nimi
	 */
	public String annaNimi()
	{
		return this.nimi;
	}
	
	// OMAT METODIT
	
	/**
	 * 
	 * Kertoo, leikkisivatko nimipallojen s‰teet tai tekstit, mik‰li
	 * t‰m‰ nimipallo sijoitettaisiin annettuun sijaintin.
	 *
	 * @param uusix testattava x-koordinaatti
	 * @param uusiy testattava y-koordinaatti
	 * @param nimet lista nimipalloista, joiden kanssa tˆrm‰yksi‰ voi tulla
	 * @return leikkaavatko nimipallojen s‰teet
	 */
	public boolean mahtuuSijaintiin(int uusix, int uusiy,
			ArrayList<Nimipallo> nimet)
	{
		float txtw = this.pohja.textWidth(this.nimi);
		float txth = this.pohja.textAscent();
		
		// Tarkistaa tormayksen kaikille nimille erikseen
		for (Nimipallo pallo2 : nimet)
		{
			int[] xy = pallo2.annaKoordinaatit();
			
			// Tarkistaa, etteivat pallojen ympyrat leikkaa toisiaan
			if (annaEtaisyys(uusix, uusiy, xy[0], xy[1])
					< this.r + pallo2.annaSade())
				return false;
			
			// TODO: Lis‰‰ fontin muuttaminen niin, ett‰ tarkistaminen on
			// ‰lyk‰st‰
			
			// TODO: Tee valmiiksi
			
			//float txtw2 = this.pohja.textWidth(pallo2.annaNimi());
			
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
	 * Testaa, lˆytyykˆ tarpeeksi l‰helt‰ annettua sijaintia toista nimiPalloa
	 * T‰llˆin pallojen kaarien v‰lille saa j‰‰d‰ enint‰‰n pyydetty v‰limatka
	 *
	 * @param uusix kokeiltava x-koordinaatti
	 * @param uusiy kokeiltava y-koordinaatti
	 * @param nimet lista mahdollisista vierustovereista
	 * @param minetaisyys suurin sallittu kehien v‰linen et‰isyys
	 * @return olisiko uudessa sijainnissa tarpeeksi l‰hell‰ naapuria
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
	 * Testaa, mahtuisiko nimipallo kokonaan ikkunaan annetuissa koordinaateissa
	 *
	 * @param uusix uuden sijainnin x-koordinaatti
	 * @param uusiy uuden sijainnin y-koordinaatti
	 * @param w ikkunan leveys pikselein‰
	 * @param h ikkunan korkeus pikselein‰
	 * @return mahtuuko pallo kokonaan ikkunaan
	 */
	public boolean mahtuukoIkkunaan(int uusix, int uusiy, int w,
			int h)
	{
		// Checkkaa reunojen ylitykset
		if (uusix - this.r < 0 || uusix + this.r > w)
			return false;
		if (uusiy - this.r < 0 || uusiy + this.r > h)
			return false;
		
		// Jos ei konflikteja, palauttaa true
		return true;
	}
	
	/**
	 * 
	 * Piirt‰‰ nimipallon ympyr‰t sek‰ nimen
	 *
	 */
	public void piirraItse()
	{
		// TODO: Poista kommentointi (sensuuri)
		// Piirtaa taustaympyrat
		for (int i = 1; i <= this.n; i++)
		{
			// Lasketaan pallon s‰de
			int ri = (int) (this.r * (i / ((double) this.n)));
			
			// Asetetaan piirtov‰ri
			// TODO: Mariannan duuni
			
			// Asetetaan kaaren paksuuus
			this.pohja.strokeWeight(this.k);
			this.pohja.noFill();
			
			// Piirret‰‰n ympyr‰
			this.pohja.ellipse(this.x, this.y, 2*ri, 2*ri);
		}
		
		// Piirtaa nimen
		
		// Asettaa fontin
		// TODO: Jonkun muun duuni
		
		// Sijoittaa ja piirt‰‰ tekstin
		float txtw = this.pohja.textWidth(this.nimi);
		float txth = this.pohja.textAscent();
		this.pohja.text(this.nimi, this.x - txtw/2, this.y + txth/2);
	}
}
