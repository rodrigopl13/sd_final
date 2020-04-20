package pack;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class XColorSensor extends AbstractFilter{
	
	float[] colorSample;

	public XColorSensor(SampleProvider source) {
		super(source);
		colorSample = new float[sampleSize];
	}
	
	public boolean colorFound() {
		super.fetchSample(colorSample, 0);
		if(colorSample[0] > 0 && colorSample[1] > 0 && colorSample[2] > 0) {
			System.out.println("Color Found!!");
			return true;
		}
		else {
			System.out.println("NO Color Found");
			return false;
		}
	}

}
