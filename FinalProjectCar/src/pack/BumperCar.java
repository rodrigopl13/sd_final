package pack;

import java.util.Random;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

@SuppressWarnings("deprecation")
public class BumperCar {
	
	DifferentialPilot pilot;
	Random ran;
	TouchSensorTest touch;

	public static void main(String[] args) {
		new BumperCar();
	}
	
	public BumperCar() {
		pilot = new DifferentialPilot(1.5f, 6, Motor.A, Motor.B);
		ran = new Random();
		Brick brick = BrickFinder.getDefault();
		Port s3 = brick.getPort("S3");
		EV3TouchSensor sensor = new EV3TouchSensor(s3);
		touch = new TouchSensorTest(sensor);
		
		pilot.forward();
		while(Button.ESCAPE.isUp()) {
			Delay.msDelay(2);
			if(touch.pressed()) {
				pilot.stop();
				pilot.travel(-6);
				if(ran.nextBoolean()) {
					pilot.rotate(90);
				} else {
					pilot.forward();
				}
				pilot.forward();
			}
		}
	}

}
