package oop;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PConstants;
import processing.core.PGraphics;


public class CityMarker extends CommonMarker {

	public static int TRI_SIZE = 5;

	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	}

	public void drawMarker(PGraphics pg, float x, float y) {
		pg.pushStyle();
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		pg.popStyle();
	}
	
	public void showTitle(PGraphics pg, float x, float y)
	{
		pg.pushStyle();
		pg.fill(255, 255, 202);
		pg.rect(x+TRI_SIZE+1, y-TRI_SIZE, 150, 15);
		pg.textAlign(PConstants.LEFT, PConstants.CENTER);
		pg.fill(0,0,0);
		pg.text(getCity()+", " + getCountry() + ", " + getPopulation(), x+TRI_SIZE+2, y);
		pg.popStyle();
	}

	public String getCity()
	{
		return getStringProperty("name");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
}
