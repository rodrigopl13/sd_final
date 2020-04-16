package pack;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorTest {
	
	EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	
	public static void main(String[] args) {
		new ColorSensorTest();
	}
	
	public ColorSensorTest() {
		
		Port s1 = LocalEV3.get().getPort("S1");
		colorSensor = new EV3ColorSensor(s1);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		
		while (Button.ESCAPE.isUp()) {

			  //colorSensor
			   colorProvider.fetchSample(colorSample, 0);
			   
			   System.out.println("R " + colorSample[0]); 
			   System.out.println("G " + colorSample[1]); 
			   System.out.println("B " + colorSample[2] + "\n");
			   
			   /*if(colorSample[0] > 0 && colorSample[1] > 0 && colorSample[2] > 0) {
				   System.out.println("Color detected!");
			   }
			   else if(colorSample[1] > 5) {
				   System.out.println("color = Green");
			   }
			   else if(colorSample[2] > 5) {
				   System.out.println("color = Blue");
			   }
			   else {
				   System.out.println("NO color detected!");
			   }
			   
			   Delay.msDelay(2500);*/
			   
			  /*Insertar codigo para que cuando detecte un cambio en los valores RGB siga ese patrón.
			   Cuando NO hay luz detecta:
			   R: 0
			   G: 0
			   B: 0.00005465
			   
			   Con luz detecta: (dependiendo del color que tenga)
			   R: 0.05423
			   G: 0.01452
			   B: 0.06542
			   */
		}
	}

}
