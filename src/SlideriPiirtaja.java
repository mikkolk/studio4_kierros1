import guicomponents.*;
import processing.core.*;
import guicomponents.*;


/**
 * Luokka, joka vastaa piirrett‰v‰n vuosiv‰lin piirt‰misest‰. K‰ytt‰‰ 
 * gui4processing-kirjastoa.
 * @author Mikko
 *
 */
public class SlideriPiirtaja {
    private PApplet pohja;
    private int slider1arvo;
    private GWSlider slider1;
    
    public SlideriPiirtaja(Visualisaatio pohja){
        this.pohja = pohja;
        this.slider1 = new GWSlider(this.pohja, (int) (0.2*(this.annaPohja().width)),
                (int) (0.9*(this.annaPohja().height)),
                (int) (0.6*(this.annaPohja().width)));
        this.slider1.setValueType(GWSlider.INTEGER);
        this.slider1.setLimits(1, 1999, 2012);
        this.slider1.setTickCount(13);
        this.slider1.setStickToTicks(true);
        this.slider1.setRenderMaxMinLabel(false);
        this.slider1.setTickColour(this.annaPohja().color(0,0,0,0));
    }
    
    public PApplet annaPohja() {
        return this.pohja;
    }
    
    public void piirraSliderit(){
        this.handleSliderEvents(slider1);
    }
    
    public void handleSliderEvents(GSlider slider) {
        this.slider1arvo = (int) slider.getValuef();
        System.out.println(this.slider1arvo);
    }
}
