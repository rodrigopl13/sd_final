package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

@SuppressWarnings("deprecation")
public class RandomDriving {
	
	DifferentialPilot pilot;

	public static void main(String[] args) {
		new RandomDriving();
	}
	
	public RandomDriving() {
		pilot = new DifferentialPilot(1.9f, 10, Motor.A, Motor.B);
		travelAndRotate();
	}
	
	public void travelAndRotate() {
		pilot.travel(20);
		pilot.rotate(90);
		Delay.msDelay(100);
		pilot.rotate(90);
	}

}
