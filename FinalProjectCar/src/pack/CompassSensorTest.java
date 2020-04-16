package pack;

import javax.sound.sampled.Port;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.I2CPort;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class CompassSensorTest {
	
	HiTechnicCompass compasSensor;
	SampleProvider compasProvider;
	float[] compasSample;
	
	public static void main(String[] args) {
		new CompassSensorTest();
	}
	
	public CompassSensorTest(){
		
		Port s2 = (Port) LocalEV3.get().getPort("S2");
		compasSensor = new HiTechnicCompass((I2CPort) s2);
		compasProvider = compasSensor.getCompassMode();
		compasSample = new float[compasProvider.sampleSize()];
		
		while (Button.ESCAPE.isUp()) {
			//compasSensor
			  compasProvider.fetchSample(compasSample, 0);
			  System.out.println("compas: " + compasSample[0]);
			  
			  Motor.B.forward();Motor.C.forward();
			  Delay.msDelay(1000);
			  
			  Motor.B.stop(); Motor.C.stop();
			  Motor.B.forward();
			  Delay.msDelay(1000);
			  
			  Motor.B.stop();
			  Motor.C.forward();Motor.B.forward();
			  Delay.msDelay(1000);
			  
			  Motor.C.stop();Motor.B.stop(); 
			  
			  compasProvider.fetchSample(compasSample, 0);
			  System.out.println("compas: " + compasSample[0]);
			  
			  Motor.B.forward();Motor.C.forward();
			  
			  //the rotation is relative to the tire
			  //Motor.B.rotate(180);
			  
			  //Delay.msDelay(2000);
		}
	}

}
