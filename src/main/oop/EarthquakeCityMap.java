package oop;

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
import oop.parsing.ParseFeed;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeCityMap extends PApplet {

    //feed with magnitude 2.5+ Earthquakes
    private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

    // The files containing city names and info and country names and info
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

    // NEW IN MODULE 5
    private CommonMarker lastSelected;
    private CommonMarker lastClicked;

    public static void main(String[] args) {
        EarthquakeCityMap ecm = new EarthquakeCityMap();
        PApplet.runSketch(new String[]{"EarthquakeCityMap"}, ecm);
    }

    public void setup() {
        size(900, 700);
        map = new UnfoldingMap(this, 0, 0, 900, 700, new OpenStreetMap.OpenStreetMapProvider());
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

        map.addMarkers(quakeMarkers);
        map.addMarkers(cityMarkers);

    }

    public void draw() {
        background(0);
        map.draw();
//        addKey();

    }

    /**
     * Event handler that gets called automatically when the
     * mouse moves.
     */
    @Override
    public void mouseMoved() {
        // clear the last selection
        if (lastSelected != null) {
            lastSelected.setSelected(false);
            lastSelected = null;
        }
        selectMarkerIfHover(quakeMarkers);
        selectMarkerIfHover(cityMarkers);
    }

    // If there is a marker under the cursor, and lastSelected is null
    // set the lastSelected to be the first marker found under the cursor
    // Make sure you do not select two markers.
    //
    private void selectMarkerIfHover(List<Marker> markers) {
        for (Marker marker : markers) {
            if (marker.isInside(map, mouseX, mouseY)) {
                lastSelected = (CommonMarker) marker;
                lastSelected.setSelected(true);
                break;
            }
        }
    }

    /**
     * The event handler for mouse clicks
     * It will display an earthquake and its threat circle of cities
     * Or if a city is clicked, it will display all the earthquakes
     * where the city is in the threat circle
     */
    @Override
    public void mouseClicked() {
        if (lastClicked != null) {
            lastClicked.setClicked(false);
            lastClicked = null;
            unhideMarkers();
        } else {
            for (Marker marker : quakeMarkers) {
                if (marker.isInside(map, mouseX, mouseY)) {
                    lastClicked = (CommonMarker) marker;
                    lastClicked.setSelected(true);
                    break;
                }
            }
            for (Marker marker : cityMarkers) {
                if (marker.isInside(map, mouseX, mouseY)) {
                    lastClicked = (CommonMarker) marker;
                    lastClicked.setSelected(true);
                    break;
                }
            }
            hideMarkers();
        }
    }

    private void hideMarkers() {
        for (Marker marker : quakeMarkers) {
            if (!marker.equals(lastClicked))
                marker.setHidden(true);
        }

        for (Marker marker : cityMarkers) {
            if (!marker.equals(lastClicked))
                marker.setHidden(true);
        }
    }

    private void unhideMarkers() {
        for (Marker marker : quakeMarkers) {
            marker.setHidden(false);
        }

        for (Marker marker : cityMarkers) {
            marker.setHidden(false);
        }
    }

    private void addKey() {
        fill(255, 250, 240);

        int xbase = 25;
        int ybase = 50;

        rect(xbase, ybase, 150, 250);

        fill(0);
        textAlign(LEFT, CENTER);
        textSize(12);
        text("Earthquake Key", xbase + 25, ybase + 25);

        fill(150, 30, 30);
        int tri_xbase = xbase + 35;
        int tri_ybase = ybase + 50;
        triangle(tri_xbase, tri_ybase - CityMarker.TRI_SIZE, tri_xbase - CityMarker.TRI_SIZE,
                tri_ybase + CityMarker.TRI_SIZE, tri_xbase + CityMarker.TRI_SIZE,
                tri_ybase + CityMarker.TRI_SIZE);

        fill(0, 0, 0);
        textAlign(LEFT, CENTER);
        text("City Marker", tri_xbase + 15, tri_ybase);

        text("Land Quake", xbase + 50, ybase + 70);
        text("Ocean Quake", xbase + 50, ybase + 90);
        text("Size ~ Magnitude", xbase + 25, ybase + 110);

        fill(255, 255, 255);
        ellipse(xbase + 35,
                ybase + 70,
                10,
                10);
        rect(xbase + 35 - 5, ybase + 90 - 5, 10, 10);

        fill(color(255, 255, 0));
        ellipse(xbase + 35, ybase + 140, 12, 12);
        fill(color(0, 0, 255));
        ellipse(xbase + 35, ybase + 160, 12, 12);
        fill(color(255, 0, 0));
        ellipse(xbase + 35, ybase + 180, 12, 12);

        textAlign(LEFT, CENTER);
        fill(0, 0, 0);
        text("Shallow", xbase + 50, ybase + 140);
        text("Intermediate", xbase + 50, ybase + 160);
        text("Deep", xbase + 50, ybase + 180);

        text("Past hour", xbase + 50, ybase + 200);

        fill(255, 255, 255);
        int centerx = xbase + 35;
        int centery = ybase + 200;
        ellipse(centerx, centery, 12, 12);

        strokeWeight(2);
        line(centerx - 8, centery - 8, centerx + 8, centery + 8);
        line(centerx - 8, centery + 8, centerx + 8, centery - 8);
    }

    private boolean isLand(PointFeature earthquake) {
        for (Marker country : countryMarkers) {
            if (isInCountry(earthquake, country)) {
                return true;
            }
        }
        return false;
    }

    private void printQuakes() {
        int totalWaterQuakes = quakeMarkers.size();
        for (Marker country : countryMarkers) {
            String countryName = country.getStringProperty("name");
            int numQuakes = 0;
            for (Marker marker : quakeMarkers) {
                EarthquakeMarker eqMarker = (EarthquakeMarker) marker;
                if (eqMarker.isOnLand()) {
                    if (countryName.equals(eqMarker.getStringProperty("country"))) {
                        numQuakes++;
                    }
                }
            }
            if (numQuakes > 0) {
                totalWaterQuakes -= numQuakes;
                System.out.println(countryName + ": " + numQuakes);
            }
        }
        System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
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
