package earthquake;
//Java utilities libraries

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import earthquake.parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

//import java.util.Collections;
//import java.util.Comparator;
//Processing library
//Unfolding libraries
//Parsing library

/**
 * EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 *
 * @author Your name here
 * Date: July 17, 2015
 */
public class EarthquakeCityMap extends PApplet {
    // Less than this threshold is a light earthquake
    public static final float THRESHOLD_MODERATE = 5;
    // Less than this threshold is a minor earthquake
    public static final float THRESHOLD_LIGHT = 4;
    // You can ignore this.  It's to keep eclipse from generating a warning.
    private static final long serialVersionUID = 1L;
    // IF YOU ARE WORKING OFFLINE, change the value of this variable to true
    private static final boolean offline = false;
    /**
     * This is where to find the local tiles, for working without an Internet connection
     */
    public static String mbTilesString = "blankLight-1-3.mbtiles";

    // The map
    private UnfoldingMap map;

    //feed with magnitude 2.5+ Earthquakes
    private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

    public static void main(String[] args) {
        EarthquakeCityMap eqcm = new EarthquakeCityMap();
        PApplet.runSketch(new String[]{"EarthquakeCityMap"}, eqcm);
    }

    public void setup() {
        size(950, 600);
        if (offline) {
            map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
            earthquakesURL = "2.5_week.atom";    // Same feed, saved Aug 7, 2015, for working offline
        } else {
            map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
            // IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
            //earthquakesURL = "2.5_week.atom";
        }

        map.zoomToLevel(2);
        MapUtils.createDefaultEventDispatcher(this, map);

        // The List you will populate with new SimplePointMarkers
        List<Marker> markers = new ArrayList<Marker>();
        //Use provided parser to collect properties for each earthquake
        //PointFeatures have a getLocation method
        List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

        // These print statements show you (1) all of the relevant properties
        // in the features, and (2) how to get one property and use it
        if (earthquakes.size() > 0) {
            PointFeature f = earthquakes.get(0);
            System.out.println(f.getProperties());
            Object magObj = f.getProperty("magnitude");
            float mag = Float.parseFloat(magObj.toString());
            // PointFeatures also have a getLocation method
        }

        // Here is an example of how to use Processing's color method to generate
        // an int that represents the color yellow.
        int green = color(50, 255, 50);
        int yellow = color(255, 255, 50);
        int red = color(255, 50, 50);

        //TODO: Add code here as appropriate
        for (PointFeature pf: earthquakes) {
            Marker m = createMarker(pf);
            if(Double.parseDouble(pf.getProperty("magnitude").toString()) < 4.0) {
                m.setColor(green);
            } else if (Double.parseDouble(pf.getProperty("magnitude").toString()) < 4.9) {
                m.setColor(yellow);
            } else {
                m.setColor(red);
            }
            markers.add(m);
        }
        for (Marker m: markers) {
            map.addMarker(m);
        }
    }

    // A suggested helper method that takes in an earthquake feature and
    // returns a SimplePointMarker for that earthquake
    // TODO: Implement this method and call it from setUp, if it helps
    private SimplePointMarker createMarker(PointFeature feature) {
        // finish implementing and use this method, if it helps.
        return new SimplePointMarker(feature.getLocation());
    }

    public void draw() {
        background(10);
        map.draw();
        addKey();
    }

    // helper method to draw key in GUI
    // TODO: Implement this method to draw the key
    private void addKey() {
        // Remember you can use Processing's graphics methods here

    }
}
