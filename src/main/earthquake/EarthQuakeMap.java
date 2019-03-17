package earthquake;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthQuakeMap extends PApplet {

    private UnfoldingMap map;

    public static void main(String[] args) {
        EarthQuakeMap eqm = new EarthQuakeMap();
        PApplet.runSketch(new String[]{"EarthQuakeMap"}, eqm);
    }

    public void setup() {
        size(800, 600);
        map = new UnfoldingMap(this, 0, 0, 800, 600, new OpenStreetMap.OpenStreetMapProvider());
        map.zoomAndPanTo(16, new Location(50.06f, 19.938f));
        MapUtils.createDefaultEventDispatcher(this, map);

        //kosciol
        Location kosciolLocation = new Location(50.0616f, 19.9393f);
        Feature kosciolFeature = new PointFeature(kosciolLocation);
        kosciolFeature.addProperty("year", "1347");
        Marker kosciolMarker = new SimplePointMarker(kosciolLocation, kosciolFeature.getProperties());
        kosciolMarker.setColor(color(50, 50, 200));

        //sukiennice
        Location sukienniceLocation = new Location(50.0616f, 19.9373f);
        Feature sukienniceFeature = new PointFeature(sukienniceLocation);
        sukienniceFeature.addProperty("year", "1344");
        Marker sukienniceMarker = new SimplePointMarker(sukienniceLocation, sukienniceFeature.getProperties());
        sukienniceMarker.setColor(color(50, 200, 50));

        map.addMarkers(kosciolMarker, sukienniceMarker);
    }

    public void draw() {
        map.draw();
    }
}
