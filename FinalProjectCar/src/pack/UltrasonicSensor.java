package pack;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class UltrasonicSensor extends AbstractFilter{
	
	float[] distanceSample;

	public UltrasonicSensor(SampleProvider source) {
		super(source);
		distanceSample = new float[sampleSize];
	}
	
	public boolean objectFound() {
		super.fetchSample(distanceSample, 0);
		if(distanceSample[0] > 0.3) {
			System.out.println("NO Object");
			return false;
		}
		else {
			System.out.println("Object Found");
			return true;
		}
	}

}
