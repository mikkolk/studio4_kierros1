import java.util.ArrayList;
import processing.core.*;


/**
 * Nimipallo visualisoi yht� nime�. Se piirt�� ymp�rilleen tietyn m��r�n
 * renkaita.
 *
 * @author Gandalf.
 *         Created 5.10.2012.
 */
public class Nimipallo
{
	// ATTRIBUUTIT
	// jossa vuosirenkaita on nimien maara, r on pallon s�de ja renkaan viivan paksuus
	private int x, y, vuosirenkaita, r, rengaspaksuus, opasiteetti, fonttikoko;
	private String nimi;
	private PApplet pohja;
	private PFont font;
	
	
	
	// KONSTRUKTORI
	/**
	 * 
	 * Metodi luo uuden Nimipallon tiettyihin koordinaatteihin tietyll� nimell�
	 *
	 * @param uusix nimipallon keskipisteen x-koordinaatti 
	 * @param uusiy nimipallon keskipisteen y-koordinaatti
	 * @param uusinimi nimipallon tulostama nimi
	 * @param maara kuinka monta rengasta nimipallon ymp�rille tulee
	 * @param sade nimipallon uusi s�de
	 * @param paksuus nimipallon renkaiden paksuus pikselein�
	 * @param pohja PApplet, joka piirt�� nimipallon
	 */
	public Nimipallo(int uusix, int uusiy, String uusinimi, int maara,
			int sade, int paksuus, PApplet pohja)
	{
		this.x = uusix;
		this.y = uusiy;
		this.nimi = uusinimi;
		this.vuosirenkaita = maara;
		this.r = sade;
		this.rengaspaksuus = paksuus;
		this.pohja = pohja;
		this.fonttikoko = 18;
		this.font = this.pohja.createFont("../Mawns_Handwriting.ttf", this.fonttikoko);
		this.opasiteetti = 0;
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
	 * Palauttaa nimipallon ympyr�n s�teen
	 * 
	 * @return nimipallon s�de
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
	 * Kertoo, leikkisivatko nimipallojen s�teet tai tekstit, mik�li
	 * t�m� nimipallo sijoitettaisiin annettuun sijaintin.
	 *
	 * @param uusix testattava x-koordinaatti
	 * @param uusiy testattava y-koordinaatti
	 * @param nimet lista nimipalloista, joiden kanssa t�rm�yksi� voi tulla
	 * @return leikkaavatko nimipallojen s�teet
	 */
	public boolean mahtuuSijaintiin(int uusix, int uusiy,
			ArrayList<Nimipallo> nimet)
	{
		//float txtw = this.pohja.textWidth(this.nimi);
		//float txth = this.pohja.textAscent();
		
		// Tarkistaa tormayksen kaikille nimille erikseen
		for (Nimipallo pallo2 : nimet)
		{
			int[] xy = pallo2.annaKoordinaatit();
			
			// Tarkistaa, etteivat pallojen ympyrat leikkaa toisiaan
			if (annaEtaisyys(uusix, uusiy, xy[0], xy[1])
					< this.r + pallo2.annaSade() + 5 + this.rengaspaksuus)
				return false;
			
			// TODO: Lis�� fontin muuttaminen niin, ett� tarkistaminen on
			// �lyk�st�
			
			//float txtw2 = this.pohja.textWidth(pallo2.annaNimi());
			
			// Tarkistaa, etteiv�t pallojen tekstit leikkaa toisiaan
			/*
			int ax1 = this.x - (int) txtw/2;
			int ax2 = ax1 + (int) txtw;
			int ay1 = this.y - (int) txth/2;
			int ay2 = ay1 + (int) txth;
			int bx1 = xy[0] - (int) txtw2/2;
			int bx2 = bx1 + (int) txtw2;
			int by1 = xy[1] - (int) txth/2;
			int by2 = by1 + (int) txth;
			
			if (((ax1 < bx2 && ax1 > bx1) && (ay1 < by2 && ay1 > by1)) ||
					((bx1 < ax2 && bx1 > ax1) && (by1 < ay2 && by1 > ay1)))
			{
				System.out.println("ASDASDASDASDASDASDA!!!!");
				return false;
			}
			*/
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
	 * Testaa, l�ytyyk� tarpeeksi l�helt� annettua sijaintia toista nimiPalloa
	 * T�ll�in pallojen kaarien v�lille saa j��d� enint��n pyydetty v�limatka
	 *
	 * @param uusix kokeiltava x-koordinaatti
	 * @param uusiy kokeiltava y-koordinaatti
	 * @param nimet lista mahdollisista vierustovereista
	 * @param minetaisyys suurin sallittu kehien v�linen et�isyys
	 * @return olisiko uudessa sijainnissa tarpeeksi l�hell� naapuria
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
	 * @param w ikkunan leveys pikselein�
	 * @param h ikkunan korkeus pikselein�
	 * @param marginaali kuinka paljon marginaalia j�tet��n ikkunan reunoille
	 * @return mahtuuko pallo kokonaan ikkunaan
	 */
	public boolean mahtuukoIkkunaan(int uusix, int uusiy, int w,
			int h, int marginaali)
	{
		// Checkkaa reunojen ylitykset
		if (uusix - this.r < 0 + marginaali || uusix + this.r > w - marginaali)
			return false;
		if (uusiy - this.r < 0 + marginaali + 50 || uusiy + this.r > h - marginaali)
			return false;
		
		// Jos ei konflikteja, palauttaa true
		return true;
	}
	
	/**
	 * 
	 * Kertoo, tulisiko nimipallo sliderien taakse, jos se sijoitettaisiin
	 * annettuihin koordinaatteihin
	 *
	 * @param uusix testattava x-koordinaatti
	 * @param uusiy testattava y-koordinaatti
	 * @return osuuko slidereihin
	 */
	public boolean leikkaakoSliderit(int uusix, int uusiy)
	{
		// Checkkaa marginaalin reunojen ylitykset
		SlideriPiirtaja piirtaja =
				((Visualisaatio) this.pohja).annaSlideriPiirtaja();
		
		int slidx = piirtaja.annaYlinX();
		int slidy = piirtaja.annaYlinY();
		int w = piirtaja.annaYlinLeveys();
		
		if (uusix - this.r > slidx - 16 && uusix - this.r < slidx + w + 16
				&& uusiy + this.r > slidy -16)
			return true;
		// Jos ei konflikteja, palauttaa true
		return false;
	}
	
	/**
	 * 
	 * Piirt�� nimipallon ympyr�t sek� nimen
	 *
	 */
	public void piirraItse()
	{
		// Piirtaa taustaympyrat
		for (int i = 1; i <= this.vuosirenkaita; i++)
		{
			// Lasketaan pallon s�de
			int ri = (int) (this.r * (i / ((double) this.vuosirenkaita)));
			this.opasiteetti = 35*this.vuosirenkaita;
			this.pohja.stroke(255, 40, 40, this.opasiteetti);
			this.opasiteetti = 0;
			// Asetetaan piirtov�ri
			// TODO: Mariannan duuni
			
			
			// Asetetaan kaaren paksuuus
			this.pohja.strokeWeight(this.rengaspaksuus);
			this.pohja.noFill();
			
			// Piirret��n ympyr�
			this.pohja.ellipse(this.x, this.y, 2*ri, 2*ri);
		}
		
		
		if (this.vuosirenkaita > 4){
		    this.fonttikoko = 25;
		}
		
		if (this.vuosirenkaita > 8){
		    this.fonttikoko = 50;
		}
		
		if (this.vuosirenkaita > 12){
		    this.fonttikoko = 65;
		}
		
		this.pohja.textFont(font,fonttikoko);
		// Piirtaa nimen
		
		// Asettaa fontin
		// TODO: Jonkun muun duuni
		
		// Sijoittaa ja piirt�� tekstin
		float txtw = this.pohja.textWidth(this.nimi);
		float txth = this.pohja.textAscent();
		this.pohja.text(this.nimi, this.x - txtw/2, this.y + txth/2);
		this.pohja.fill(0);
	}
}
