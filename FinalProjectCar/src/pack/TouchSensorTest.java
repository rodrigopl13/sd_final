package pack;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class TouchSensorTest extends AbstractFilter {

	float[] sample;
	
	public TouchSensorTest(SampleProvider source) {
		super(source);
		sample = new float[sampleSize];
	}
	
	public boolean pressed() {
		System.out.println("Touch " + sample[0]);
		super.fetchSample(sample, 0);
		if (sample[0] == 0) {
			System.out.println("It is NOT touched");
			return false;
		}
		else {
			System.out.println("It is touched");
			return true;
		}
	}
	

}
