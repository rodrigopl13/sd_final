package pack;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

@SuppressWarnings("deprecation")
public class Driving {
	
	DifferentialPilot pilot;

	public static void main(String[] args) {
		new Driving();
	}
	
	public Driving() {
		pilot = new DifferentialPilot(1.5f, 6, Motor.A, Motor.B);
		for (int i = 0; i < 5; i++) {
			travelAndRotate();
			if(i > 3) {
				pilot.travel(-6);
			}
		}
	}
	
	public void travelAndRotate() {
		pilot.travel(12);
		pilot.rotate(90);
	}

}
