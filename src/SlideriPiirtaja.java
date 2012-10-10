import guicomponents.*;
import processing.core.*;


/**
 * Luokka, joka vastaa piirrett‰v‰n vuosiv‰lin piirt‰misest‰. K‰ytt‰‰ 
 * gui4processing-kirjastoa.
 * @author Mikko
 *
 */
public class SlideriPiirtaja {
    private PApplet pohja; // pohjana toimiva visualisaatio
    private int slider1Arvo; // sliderin 1 arvo
    private GWSlider slider1; // slider 1
    // ylemm‰n sliderin x- ja y-koordinaattit sek‰ leveys, k‰ytet‰‰n marginaaliin
    private int ylinX;
    private int ylinY;
    private int ylinLeveys;
    
    /**
     * Konstruktori luo sliderit, laittaa niiden asetukset kuntoon ja ottaa
     * pohjana toimivan Visualisaation
     * @param pohja pohjana toimiva Visualisaatio
     */
    public SlideriPiirtaja(Visualisaatio pohja){
    	this.slider1Arvo = 1999;
        this.pohja = pohja;
        
        this.ylinX = (int) (0.2*(this.annaPohja().width));
        this.ylinY = (int) (0.93*(this.annaPohja().height));
        this.ylinLeveys = (int) (0.6*(this.annaPohja().width));
        this.slider1 = new GWSlider(this.pohja, this.ylinX, this.ylinY,
                this.ylinLeveys);
        this.slider1.setValueType(GWSlider.INTEGER);
        this.slider1.setLimits(1999, 1999, 2012);
        this.slider1.setTickCount(13);
        this.slider1.setStickToTicks(true);
        this.slider1.setRenderMaxMinLabel(false);
        this.slider1.setTickColour(this.annaPohja().color(255,255,255,0));
    }
    
    public int annaSlider1Arvo() {
        return slider1Arvo;
    }
    
    public PApplet annaPohja() {
        return this.pohja;
    }
    
    public int annaYlinX() {
        return this.ylinX;
    }

    public int annaYlinY() {
        return this.ylinY;
    }

    public int annaYlinLeveys() {
        return this.ylinLeveys;
    }

    public void piirraSlideri(){
        this.handleSliderEvents(this.slider1);
    }
    
    /**
     * Metodi, joka huolehtii sliderien eventtien kuuntelusta.
     * @param slider slider, jonka eventit k‰sitell‰‰n
     */
    public void handleSliderEvents(GSlider slider) {
        this.slider1Arvo = slider.getValue();
        //System.out.println(this.slider1Arvo);
    }
}
