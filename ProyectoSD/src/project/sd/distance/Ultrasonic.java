package project.sd.distance;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Ultrasonic {
	
	EV3UltrasonicSensor distanceSensor;
	SampleProvider distanceProvider;
	float[] distanceSample;

	public Ultrasonic() {
		Port s4 = LocalEV3.get().getPort("S4");
		distanceSensor = new EV3UltrasonicSensor(s4);
		distanceProvider = distanceSensor.getMode("Distance");
		distanceSample = new float[distanceProvider.sampleSize()];
	}

	public boolean checkDistanceToTheBall() {
		distanceProvider.fetchSample(distanceSample, 0);
		System.out.println("distance: " + distanceSample[0] + "\n");
		
		//check if Walle is far from the ball
		return (distanceSample[0] > 0.1 ? true: false);
	}
	
	public float getDistance() {
		distanceProvider.fetchSample(distanceSample, 0);
		System.out.println("distance: " + distanceSample[0]);
		return distanceSample[0];
	}
	
	//we only want to test the sensor
	public void testSensor(MovePilot pilot) {
		System.out.println("testing sensor!");
		while(Button.ESCAPE.isUp()) {
			
			//check the ultraSensor
			if(checkDistanceToTheBall()) {
						
				pilot.travel(getDistance());
			}
			
			Delay.msDelay(3000);
		}
	}
}
