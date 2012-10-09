import guicomponents.*;
import processing.core.*;

/**
 * Luokka, joka vastaa alemman sliderin piirtämisestä. Ei hienointa arkkitehtuuria,
 * mutta käyttämäni gui-kirjasto ei tykänny siitä, että ne piirrettiin samassa
 * luokassa. Tuli suht likainen olo koodatessa tämmöistä ratkaisua.
 * @author Mikko
 *
 */
public class SlideriPiirtaja2 {
    private PApplet pohja; // pohjana toimiva visualisaatio
    private int slider2Arvo; // sliderin 2 arvo
    private GWSlider slider2; // slider 2
    
    public SlideriPiirtaja2(Visualisaatio pohja){
        this.pohja = pohja;
        this.slider2 = new GWSlider(this.pohja, (int) (0.2*(this.annaPohja().width)),
                (int) (0.97*(this.annaPohja().height)),
                (int) (0.6*(this.annaPohja().width)));
        this.slider2.setValueType(GWSlider.INTEGER);
        this.slider2.setLimits(1, 1999, 2012);
        this.slider2.setTickCount(13);
        this.slider2.setStickToTicks(true);
        this.slider2.setRenderMaxMinLabel(false);
        this.slider2.setTickColour(this.annaPohja().color(255,255,255,0));
    }
    
    public PApplet annaPohja() {
        return this.pohja;
    }
    
    public int annaSlider2Arvo() {
        return this.slider2Arvo;
    }
    
    public void piirraSlideri(){
        this.handleSliderEvents(this.slider2);
    }
    
    public void handleSliderEvents(GSlider slider) {
        this.slider2Arvo = slider.getValue();
        System.out.println(this.slider2Arvo);
    }
}
