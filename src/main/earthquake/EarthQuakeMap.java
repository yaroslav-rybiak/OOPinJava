package earthquake;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthQuakeMap extends PApplet {

    private int height = 600;
    private int width = 800;

    private UnfoldingMap map;

    public static void main(String[] args) {
        EarthQuakeMap eqm = new EarthQuakeMap();
        PApplet.runSketch(new String[]{"EarthQuakeMap"}, eqm);
    }

    public void setup() {
        Location kosciolLocation = new Location(50.06f, 19.938f);
        SimplePointMarker kosciolMarker = new SimplePointMarker(kosciolLocation);
        map = new UnfoldingMap(this, 0, 0, width, height, new OpenStreetMap.OpenStreetMapProvider());
        map.zoomAndPanTo(12, new Location(50.06f, 19.938f));
        MapUtils.createDefaultEventDispatcher(this, map);
        map.addMarker(kosciolMarker);
    }

    public void settings() {
        size(width, height);
    }

    public void draw() {
        map.draw();
    }
}
