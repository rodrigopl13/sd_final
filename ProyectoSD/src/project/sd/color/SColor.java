package project.sd.color;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SColor {
	
	EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	
	public SColor() {
		Port s1 = LocalEV3.get().getPort("S1");
		colorSensor = new EV3ColorSensor(s1);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
	}
	
	public boolean searchColor() {
		colorProvider.fetchSample(colorSample, 0);
		System.out.println("C " + colorSample[0] + colorSample[1] + colorSample[2] + "\n");
		   
		//check if Walle is picking any color
		return ((colorSample[0] + colorSample[1] + colorSample[2]) > 0.01) ? true : false;
	}
	
	//this method is only to test the color sensor!
	public void printColor() {
		
		while(Button.ESCAPE.isUp()) {
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("\n R " + colorSample[0]); 
			System.out.println("G " + colorSample[1]); 
			System.out.println("B " + colorSample[2]);
			
			//System.out.println("\n C: " + colorSample[0] + colorSample[1] + colorSample[2]);
			
			Delay.msDelay(3000);
		}
		
	}
}