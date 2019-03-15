package earthquake;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import processing.core.PApplet;

public class EartQuakeMap extends PApplet {

    private UnfoldingMap map;

    public void setup() {
        map = new UnfoldingMap(this,200, 2000, 600, 400, new Google.GoogleMapProvider());
        map.zoomToLevel(2);
    }

    public void settings() {
        size(1000, 1000);
    }

    public void draw() {
        background(10);
        map.draw();
    }

    public static void main(String[] args) {
        EartQuakeMap eqm = new EartQuakeMap();
        PApplet.runSketch(new String[]{"EartQuakeMap"}, eqm);
     }
}
