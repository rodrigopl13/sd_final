package project.sd.compass;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Compass {
	
	//compass sensor, it's provider and a sample
	HiTechnicCompass compassSensor;
	SampleProvider compassProvider;
	float[] compassSample;
	
	//compass current degrees
	float currentDegrees = 0;

	public Compass() {
		//compass settings
		Port s3 = LocalEV3.get().getPort("S3");
		compassSensor = new HiTechnicCompass(s3);
		
		//set the compass to angleMode
		compassProvider = compassSensor.getAngleMode();
	}	
	
	public boolean checkDegrees(float degrees) {
		boolean condition = ( degrees <= 5 && degrees >= -5) ? false : true ;
		System.out.println("\n D: " + degrees + "\n condition: " + condition);
		return condition;
	}
	
	public float getDegrees() {
		//initialize the array 
		compassSample = new float[compassProvider.sampleSize()];
		
		//fetching a sample from the compass Provider
		compassProvider.fetchSample(compassSample, 0);
		
		//mapping the sample to a variable
		setDegrees (compassSample[0]);
		System.out.println("\ndegrees: " + currentDegrees);
		
		return currentDegrees;
	}
	
	public void setDegrees(float degrees) {
		this.currentDegrees = degrees;
	}
	
	public void findNorth(MovePilot pilot) {
		//find the nearest rout to the north!
		while( checkDegrees( getDegrees() ) ) {
			
			if(currentDegrees > 5) {
				pilot.rotate(-10);System.out.println("rNegative");
			}
			
			if(currentDegrees < -5) { 
					pilot.rotate(10);System.out.println("rPositive");
			}
			Delay.msDelay(1500);
		}
	}
}