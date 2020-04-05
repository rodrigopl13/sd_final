package carro;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.utility.Delay;
import lejos.robotics.SampleProvider;

public class HelloWorld {

	/*EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	
	
	EV3UltrasonicSensor distanceSensor;
	SampleProvider distanceProvider;
	float[] distanceSample;
	
	
	EV3GyroSensor gyroSensor;
	SampleProvider gyroProvider;
	float[] gyroSample;*/
	
	HiTechnicCompass compasSensor;
	SampleProvider compasProvider;
	float[] compasSample;
			
	

	 public static void main(String[] args) {
		 new HelloWorld();
	 }

	 public HelloWorld() {

	  /*Port s1 = LocalEV3.get().getPort("S1");
	  colorSensor = new EV3ColorSensor(s1);
	  colorProvider = colorSensor.getRGBMode();
	  colorSample = new float[colorProvider.sampleSize()];

	  
	  Port s4 = LocalEV3.get().getPort("S4");
	  distanceSensor = new EV3UltrasonicSensor(s4);
	  distanceProvider = distanceSensor.getMode("Distance");
	  distanceSample = new float[distanceProvider.sampleSize()];
	  
	  
	  Port s2 = LocalEV3.get().getPort("S2");
	  gyroSensor = new EV3GyroSensor(s2);
	  gyroProvider = gyroSensor.getAngleMode();
	  gyroSample = new float[gyroProvider.sampleSize()];*/
	  
	  Port s2 = LocalEV3.get().getPort("S2");
	  compasSensor = new HiTechnicCompass(s2);
	  compasProvider = compasSensor.getCompassMode();
	  compasSample = new float[compasProvider.sampleSize()];
	  

	  while (Button.ESCAPE.isUp()) {

		  //colorSensor
		   /*colorProvider.fetchSample(colorSample, 0);
		   System.out.println("R " + colorSample[0]); 
		   System.out.println("G " + colorSample[1]); 
		   System.out.println("B " + colorSample[2] + "\n"); */
		  
		  
		  //distanceSensor
		   /*distanceProvider.fetchSample(distanceSample, 0);
		   System.out.println("distance: " + distanceSample[0] + "\n"); 
		   
		   if(distanceSample[0] > 0.2) {
			   System.out.println("forward!");
			   Motor.B.forward();
			   Motor.C.forward();
		   }else {
			   System.out.println("stop!");
			   Motor.B.stop();
			   Motor.C.stop();
		   }*/
		  
		  
		  //gyroSensor
		  /*Motor.C.forward();
		  gyroProvider.fetchSample(gyroSample, 0);
		  
		  System.out.println("gyro: " + gyroSample[0]);*/
		  
		  
		  //compasSensor
		  /*compasProvider.fetchSample(compasSample, 0);
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
		  
		  Motor.B.forward();Motor.C.forward();*/
		  
		  //the rotation is relative to the tire
		  Motor.B.rotate(-360);
		  
		  Delay.msDelay(2000);

	  	}
	 }
}
