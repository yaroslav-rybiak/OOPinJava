package oop.module4;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import oop.module3.parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EarthquakeCityMap extends PApplet {
    HashMap<String, Integer> landQuakes = new HashMap<>();
    HashMap<String, Integer> oceanQuakes = new HashMap<>();
    //    private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
    private String earthquakesURL = "quiz1.atom";
    private String cityFile = "city-data.json";
    private String countryFile = "countries.geo.json";

    // The map
    private UnfoldingMap map;

    // Markers for each city
    private List<Marker> cityMarkers;

    // Markers for each earthquake
    private List<Marker> quakeMarkers;

    // A List of country markers
    private List<Marker> countryMarkers;

    public static void main(String[] args) {
        EarthquakeCityMap ecm = new EarthquakeCityMap();
        PApplet.runSketch(new String[]{"EarthquakeCityMap"}, ecm);
    }

    public void setup() {
        size(750, 600);
        map = new UnfoldingMap(this, 0, 0, 750, 600, new OpenStreetMap.OpenStreetMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);

        List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
        countryMarkers = MapUtils.createSimpleMarkers(countries);

        List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
        cityMarkers = new ArrayList<>();
        for (Feature city : cities) {
            cityMarkers.add(new CityMarker(city));
        }

        List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
        quakeMarkers = new ArrayList<>();

        for (PointFeature feature : earthquakes) {
            if (isLand(feature)) {
                quakeMarkers.add(new LandQuakeMarker(feature));
            } else {
                quakeMarkers.add(new OceanQuakeMarker(feature));
            }
        }
        printQuakes();
        map.addMarkers(quakeMarkers);
        map.addMarkers(cityMarkers);
    }

    public void draw() {
        map.draw();
//        addKey();
    }

    private void addKey() {
        // Remember you can use Processing's graphics methods here
        fill(255, 250, 240);
        rect(25, 50, 150, 250);

        fill(0);
        textAlign(LEFT, CENTER);
        textSize(12);
        text("Earthquake Key", 50, 75);

        fill(color(255, 0, 0));
        ellipse(50, 125, 15, 15);
        fill(color(255, 255, 0));
        ellipse(50, 175, 10, 10);
        fill(color(0, 0, 255));
        ellipse(50, 225, 5, 5);

        fill(0, 0, 0);
        text("5.0+ Magnitude", 75, 125);
        text("4.0+ Magnitude", 75, 175);
        text("Below 4.0", 75, 225);
    }

    private boolean isLand(PointFeature earthquake) {
        for (Marker country : countryMarkers) {
            if (isInCountry(earthquake, country))
                return true;
        }
        return false;
    }

    private void printQuakes() {

        oceanQuakes.put("Ocean", 0);
        for (Marker m : quakeMarkers) {
            if (m.getProperties().containsKey("country")) {
                String country = m.getProperty("country").toString();
                if (!landQuakes.containsKey(country)) {
                    landQuakes.put(country, 1);
                } else {
                    landQuakes.put(country, landQuakes.get(country) + 1);
                }
            } else {
                oceanQuakes.put("Ocean", oceanQuakes.get("Ocean") + 1);
            }
        }
        for (Map.Entry<String, Integer> e : landQuakes.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        for (Map.Entry<String, Integer> e : oceanQuakes.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    private boolean isInCountry(PointFeature earthquake, Marker country) {
        Location checkLoc = earthquake.getLocation();
        if (country.getClass() == MultiMarker.class) {
            for (Marker marker : ((MultiMarker) country).getMarkers()) {
                if (((AbstractShapeMarker) marker).isInsideByLocation(checkLoc)) {
                    earthquake.addProperty("country", country.getProperty("name"));
                    return true;
                }
            }
        } else if (((AbstractShapeMarker) country).isInsideByLocation(checkLoc)) {
            earthquake.addProperty("country", country.getProperty("name"));

            return true;
        }
        return false;
    }
}
