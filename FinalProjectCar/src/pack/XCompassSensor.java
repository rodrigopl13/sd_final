package pack;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class XCompassSensor extends AbstractFilter{

	float[] locationSample;
	int setPoint; //hace falta definir el set pont, es el punto al que tiene que llegar el robot una vez qu etenga la pelota
	
	public XCompassSensor(SampleProvider source) {
		super(source);
		locationSample = new float[sampleSize];
	}
	
	public boolean location() {
		super.fetchSample(locationSample, 0);
		if(locationSample[0] != setPoint) {
			System.out.println("Different location");
			return false;
		}
		else {
			System.out.println("Final location!!");
			return true;
		}
	}

}
