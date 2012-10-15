import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Datalukija lukee tiedostosta nimidataa ja tallentaa sen hashmappiin
 * Mahdollisesti myˆs j‰rjest‰en dataa suuruusj‰rjestykseen ja sijoittaen
 * sen esim. taulukkoon
 *
 * @author Gandalf.
 *         Created 2.10.2012.
 */
public class DataLukija
{
	// ATTRIBUUTIT --------------------------------------------------
	
	// Nimidata sis‰lt‰‰ hashmapin (kaikki) hashmapeista (vuosikohtaiset),
	// jotka sis‰lt‰v‰t nimi-luku-pareja (esiintymism‰‰r‰)
	private HashMap<Integer, HashMap<String, Integer>> 	nimidata;
	//private File						tiedosto;
	
	
	// KONSTRUKTORI -------------------------------------------------
	/**
	 * Luo uuden datalukijan annetun tiedoston avulla
	 * Datalukija lukee syntyessaan tarvittavan datan tiedostosta
	 *
	 * @param tiedostonimi sen tiedoston nimi, joka sisaltaa luomiseen
	 * tarvittavan nimidatan.
	 */
	public DataLukija(String tiedostonimi)
	{
		// Alustaa muuttujat
		// TODO: Lis‰‰ tarkistus t‰nne?
		this.nimidata = new HashMap<Integer, HashMap<String, Integer>>();
		
		// Lukee datan tiedostosta
		try
		{
			System.out.println(this.getClass().getResource(tiedostonimi));
		    lueDataTiedostosta(this.getClass().getResourceAsStream( tiedostonimi));
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("Tiedostoa " + tiedostonimi + " ei loytynyt!");
		}
	}
	
	// OMAT METDIT ---------------------------------------------------
	
	// Lukee nimet tiedostosta ja tallentaa ne tietokantaan
	private void lueDataTiedostosta(InputStream stream)
			throws FileNotFoundException
	{
		// Luodaan skanneri
	    Scanner skanneri = new Scanner(stream);
		
		// Muistaa, mihin tieto tulee tallentaa
		HashMap<String, Integer> vuosidata = null;
		int vuosi = 0;
		// Kertoo, tuleeko ensi rivilla uusi vuosiluku
		boolean vuositulee = true;
			
		while (skanneri.hasNextLine())
		{
			// Luetaan pelin tiedot skannerilla
			String uusirivi = skanneri.nextLine();
			
			// Tarkistetaan, tuleeko uusi vuosi
			if (uusirivi.equals("&"))
			{
				vuositulee = true;
				continue;
			}
			
			// Uuden vuoden asettaminen ja vanhan tallentaminen
			if (vuositulee)
			{
				// Tallentaa ensin vanhan vuosidatan
				if (vuosidata != null)
					this.nimidata.put(vuosi, (HashMap<String, Integer>)
							vuosidata.clone());
				
				// Paivittaa nykyiset tiedot
				try
				{
					vuosi = Integer.parseInt(uusirivi);
				}
				catch (NumberFormatException nfe)
				{
					System.out.println("VUOSILUVUSSA VIRHE: " + uusirivi);
				}
				vuosidata = new HashMap<String, Integer>();
				
				vuositulee = false;
				continue;
			}
			
			// Luetaan nimi ja lis‰t‰‰n se mappiin
			String[] uusidata = uusirivi.split(" ");
			String uusinimi = uusidata[1]; // Tallentaa vain etunimen
			lisaaNimiMappiin(uusinimi, vuosidata, 1);
		}
		
		// Tallentaa lopuksi viimeisen vuosidatan
		if (vuosidata != null)
			this.nimidata.put(vuosi, (HashMap<String, Integer>)
					vuosidata.clone());
	}
	
	/**
	 * Metodi tulostaa datalukijan sisaltaman nimidatan muodossa
	 * vuosi:
	 * nimi: lukumaara
	 * . . .
	 * 
	 * @param data tulostettava data (esim kokoaVuodet -metodilla saatu)
	 * syota null, jos haluat tulostaa kaiken datan
	 */
	public void tulostaData(HashMap<String, Integer> data)
	{
		// Nullilla tulostaa vuodet erikseen
		if (data == null)
		{	
			// Kay lapi kaikki vuosidatat
			for (int vuosi : this.nimidata.keySet())
			{
				// Printtaa vuoden ja kay lapi kaikki nimet
				System.out.println(vuosi + ":");
				HashMap<String, Integer> vuosidata = this.nimidata.get(vuosi);
				
				for (String nimi : vuosidata.keySet())
				{
					// Printtaa nimen ja lukumaaran
					int n = vuosidata.get(nimi);
					System.out.println(nimi + ": " + n);
				}
			}
		}
		else
		{
			for (String nimi : data.keySet())
			{
				// Printtaa nimen ja lukumaaran
				int n = data.get(nimi);
				System.out.println(nimi + ": " + n);
			}
		}
	}
	
	/**
	 * 
	 * Metodi palauttaa uuden mapin, joka sisaltaa useamman vuoden datan
	 * koottuna yhteen.
	 *
	 * @param ekavuosi pienin mukaanluettava vuosi
	 * @param vikavuosi suurin mukaanluettava vuosi
	 * @return vuosien perusteella koottu yhteenveto
	 */
	public HashMap<String, Integer> kokoaVuodet(int ekavuosi, int vikavuosi)
	{
		// Luo uuden mapin tallentamista varten
		HashMap<String, Integer> uusidata = new HashMap<String, Integer>();
		
		// Kay lapi kaikki vuosimapit, jotka osuvat valille
		for (int vuosi : this.nimidata.keySet())
		{
			if (vuosi < ekavuosi || vuosi > vikavuosi)
				continue;
			
			// Kay lapi vuosidatan ja lisaa sen nimet uuteen dataan
			for (String uusinimi : this.nimidata.get(vuosi).keySet())
			{
				int montalisataan = this.nimidata.get(vuosi).get(uusinimi);
				lisaaNimiMappiin(uusinimi, uusidata, montalisataan);
			}
		}
		
		return uusidata;
	}
	
	// Lisaa mappiin uuden nimen tai lisaa sen
	// esiintymismaaraa annetulla maaralla
	private void lisaaNimiMappiin(String uusinimi, HashMap<String,
			Integer> mappi, int lukumaara)
	{
		// Uuden nimen essintymislukum‰‰r‰
		int nimimaara = lukumaara;
		
		// Jos nimi on jo mapissa, lis‰‰ arvoa, muutoin j‰‰ 1:ksi
		if (mappi.containsKey(uusinimi))
			nimimaara += mappi.get(uusinimi);
		
		mappi.put(uusinimi, nimimaara);
	}
	
	// 
	/**
	 * Palauttaa taulukon, joka sisaltaa annetun datan nimet
	 * jarjestettyna niin, etta eniten esiintynyt nimi tulee ensin 
	 *
	 * @param data nimidata, jonka nimet jarjestetaan
	 * @return taulukon, joka sisaltaa jarjestetyt nimet
	 */
	public String[] annaJarjestys(HashMap<String, Integer> data)
	{
		String[] jarjestysdata = new String[data.size()];
		ArrayList<String> lisatytnimet = new ArrayList<String>();
		String nimiehdokas = "";
		int suurinesiintyma = 0;
		int indeksinyt = 0;
		
		// Toimintoa toistetaan, kunnes koko taulukko on taynna
		while (indeksinyt < data.size())
		{
			// Kaydaan nimia lapi ja etsitaan sielta suurin lisattava, jota ei viela
			// ole lisatty
			for (String nimi : data.keySet())
			{
				// Ei kaksoiskappaleita
				if (lisatytnimet.contains(nimi))
					continue;
				
				// Uusi ehdokas?
				if (data.get(nimi) > suurinesiintyma)
				{
					// Tallennetaan tiedot
					nimiehdokas = nimi;
					suurinesiintyma = data.get(nimi);
				}
			}
			
			// Lisataan nimi taulukkoon ja tehdaan tarvittavat muutokset
			// valimuuttujiin
			jarjestysdata[indeksinyt] = nimiehdokas;
			lisatytnimet.add(nimiehdokas);
			suurinesiintyma = 0;
			indeksinyt++;
		}
		
		return jarjestysdata;
	}
	
	// TESTIMAIN -----------------------------------------------------
	/**
	 * Paametodi testaa dataluvun toimivuuden tulostamalla tiedostosta
	 * testi.txt saadun tiedon
	 *
	 * @param args ei tee mitaan
	 */
	/*
	public static void main(String[] args)
	{
		DataLukija datamies = new DataLukija("nimilista.txt");
		//System.out.println("Tulostetaan nimidataa...");
		//datamies.tulostaData(null);
		
		HashMap<String, Integer> kokoelma = datamies.kokoaVuodet(2008, 2011);
		System.out.println("Tulostetaan vuosien 1999-2011 yhteenvetoa...");
		datamies.tulostaData(kokoelma);
		
		HashMap<String, Integer> kaikki = datamies.kokoaVuodet(2008, 2011);
		String[] ranking = datamies.annaJarjestys(kaikki);
		System.out.println("Tulostetaan kaikkien aikojen ranking...");
		for (int i = 0; i < ranking.length; i++)
		{
			System.out.println((i + 1) + ": " + ranking[i]);
		}
	}
	*/
}
