import guicomponents.*;
import processing.core.*;


/**
 * Luokka, joka vastaa piirrett‰v‰n vuosiv‰lin piirt‰misest‰. K‰ytt‰‰ 
 * gui4processing-kirjastoa.
 * @author Mikko
 *
 */
public class SlideriPiirtaja {
    private PApplet pohja;
    
    public SlideriPiirtaja(Visualisaatio pohja){
        this.pohja = pohja;
    }
    
    public PApplet annaPohja() {
        return this.pohja;
    }
    
    public void piirraSliderit(){
        GWSlider slider1 = new GWSlider(this.pohja, (int) (0.2*(this.annaPohja().width)),
                (int) (0.9*(this.annaPohja().height)),
                (int) (0.6*(this.annaPohja().width)));
        slider1.setValueType(GWSlider.INTEGER);
        slider1.setLimits(13, 1999, 2012);
        slider1.setTickCount(13);
        slider1.setStickToTicks(true);
        slider1.setRenderMaxMinLabel(false);
        slider1.setTickColour(this.annaPohja().color(0,0,0,0));
    }
    
    
}
