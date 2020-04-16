package pack;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class DistanceSensorTest {
	
	EV3UltrasonicSensor distanceSensor;
	SampleProvider distanceProvider;
	float[] distanceSample;

	public static void main(String[] args) {
		new DistanceSensorTest();
	}
	
	public DistanceSensorTest() {
		
		Port s4 = LocalEV3.get().getPort("S4");
		distanceSensor = new EV3UltrasonicSensor(s4);
		distanceProvider = distanceSensor.getMode("Distance");
		distanceSample = new float[distanceProvider.sampleSize()];
		
		while (Button.ESCAPE.isUp()) {
			//distanceSensor
			   distanceProvider.fetchSample(distanceSample, 0);
			   System.out.println("distance: " + distanceSample[0] + "\n"); 
			   
			   if(distanceSample[0] > 0.3) {
				   System.out.println("forward!");
				   Motor.B.forward();
				   Motor.C.forward(); 
			   }
			   /*else if(colorSample[0] > 0 && colorSample[1] > 0 && colorSample[2] > 0) {
				   System.out.println("Color detected!");
				   Motor.B.stop();
				   Motor.C.stop();
				   
				   Delay.msDelay(2500);
				   
				   Motor.B.forward();
				   Motor.C.stop();
				   
				   Delay.msDelay(2500);
			   }*/
			   else {
				   System.out.println("stop!");
				   Motor.B.stop();
				   Motor.C.stop();
				   
				   Delay.msDelay(250);
				   
				   Motor.B.forward();
				   Motor.C.backward();
				   
				   Delay.msDelay(250);
			   }
		}
		
	}

}
