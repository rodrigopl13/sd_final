package pack;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class GyroscopeSensorTest {
	
	EV3GyroSensor gyroSensor;
	SampleProvider gyroProvider;
	float[] gyroSample;

	public static void main(String[] args) {
		new GyroscopeSensorTest();
	}
	
	public GyroscopeSensorTest() {
	
	Port s2 = LocalEV3.get().getPort("S2");
	  gyroSensor = new EV3GyroSensor(s2);
	  gyroProvider = gyroSensor.getAngleMode();
	  gyroSample = new float[gyroProvider.sampleSize()];
	  
	  while (Button.ESCAPE.isUp()) {
		//gyroSensor
		  Motor.C.forward();
		  gyroProvider.fetchSample(gyroSample, 0);
		  
		  System.out.println("gyro: " + gyroSample[0]);
		  
		  Delay.msDelay(10000);
		  Motor.C.stop();
		  
		  Motor.B.forward();
		  gyroProvider.fetchSample(gyroSample, 0);
		  
		  System.out.println("gyro: " + gyroSample[0]);
		  
		  Delay.msDelay(10000);
		  Motor.B.stop();
	  }
	  
	}

}
