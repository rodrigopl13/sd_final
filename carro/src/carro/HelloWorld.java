package carro;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.utility.Delay;
import lejos.robotics.SampleProvider;

/*
 * Helloworld
 */

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
	public static final int WHEELDIST = 115;
	public static final int WHEELSIZE = 56;
	

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
	  
	  Port s3 = LocalEV3.get().getPort("S3");
	  compasSensor = new HiTechnicCompass(s3);
	  
	  Motor.B.setSpeed(60);
	  int degrees = 0;
	  degrees = ((numRotations() *4)/2)*450;
	  System.out.println("degrees is "+degrees);
	  compasSensor.startCalibration();
	  Motor.B.rotate(degrees);
	  while(Motor.B.isMoving()) Thread.yield();
	  compasSensor.stopCalibration();
		  
	  compasProvider = compasSensor.getAngleMode();
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
		  compasProvider.fetchSample(compasSample, 0);
		  System.out.println("compas: " + compasSample[0]);
		  Motor.B.rotate(90);
		  //Delay.msDelay(2000);
		  
		  /*
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
		  //Motor.B.rotate(-360);
		  
		  Delay.msDelay(1000);

	  	}
	 }
	 public static int numRotations() {
		 return ((WHEELDIST * 3142)/1000)/((WHEELSIZE * 3142)/1000);
	 }
}
