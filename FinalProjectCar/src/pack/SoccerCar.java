package pack;

import java.util.Random;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.HiTechnicCompass;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

@SuppressWarnings("deprecation")
public class SoccerCar {
	DifferentialPilot pilot;
	Random ran;
	TouchSensorTest touch;
	UltrasonicSensor distance;
	XColorSensor color;
	XCompassSensor compass;
	int CurrentState;

	public static void main(String[] args) {
		new SoccerCar();
	}
	
	public SoccerCar() {
		pilot = new DifferentialPilot(1.9f, 10, Motor.A, Motor.B);
		ran = new Random();
		
		Brick brick1 = BrickFinder.getDefault();
		Port s1 = brick1.getPort("S1");
		EV3TouchSensor sensor1 = new EV3TouchSensor(s1);
		touch = new TouchSensorTest(sensor1);
		
		Brick brick2 = BrickFinder.getDefault();
		Port s2 = brick2.getPort("S2");
		EV3UltrasonicSensor sensor2 = new EV3UltrasonicSensor(s2);
		distance = new UltrasonicSensor(sensor2);
		
		Brick brick3 = BrickFinder.getDefault();
		Port s3 = brick3.getPort("S3");
		EV3ColorSensor sensor3 = new EV3ColorSensor(s3);
		color = new XColorSensor(sensor3);
		
		Brick brick4 = BrickFinder.getDefault();
		Port s4 = brick4.getPort("S4");
		HiTechnicCompass sensor4 = new HiTechnicCompass(s4);
		compass = new XCompassSensor(sensor4);
		
		
		while(Button.ESCAPE.isUp()) {
			/*
			if(CurrentState == 1) {
				lookAround();
			}
			*/
			Delay.msDelay(2);
			lookAround();
			CurrentState = 1;
			
		//Touch Sensor
			if(touch.pressed()) {
				Delay.msDelay(2);
				goBack();
				if(distance.objectFound()) {
					identifyObject();
					if(color.colorFound()) {
						followBall();
						if(compass.location()) {
							turnOff();
						}
						else {
							goArch();
							CurrentState = 6;
						}
					}
					else {
						identifyObject();
					}
				}
				else {
					turnAround();
				}
			}
			Delay.msDelay(2);
		//Touch Sensor end	
		
		//Color and Ultrasonic sensors
			if(color.colorFound() || distance.objectFound()) {
				Delay.msDelay(2);
				identifyObject();
				if(color.colorFound()) {
					followBall();
					if(compass.location()) {
						turnOff();
					}
					else {
						goArch();
					}
				}
				else {
					turnAround();
				}
			}
			Delay.msDelay(2);
		//Color and Ultrasonic sensors end
			
		//CompassSensor
		
			Delay.msDelay(2);
		//CompassSensor end
		}
		
	}
	
	public void lookAround() {
		Delay.msDelay(2);
		pilot.forward();
	}
	
	public void goBack() {
		Delay.msDelay(2);
		pilot.stop();
		pilot.travel(-3);
		if(ran.nextBoolean()) {
			pilot.rotate(20);
		} 
		else {
			pilot.rotate(-20);
		}
	}
	
	public void identifyObject() {
		if(color.colorFound()) {
			pilot.travel(3);
		}
		else {
			pilot.rotate(10);
			if(color.colorFound()) {
				pilot.travel(3);
			}
			else {
				pilot.rotate(-20);
				if(color.colorFound()) {
					pilot.travel(3);
				}
				else {
					pilot.rotate(30);
					if(color.colorFound()) {
						pilot.travel(3);
					}
					else {
						pilot.rotate(-40);
						if(color.colorFound()) {
							pilot.travel(3);
						}
					}
				}
			}
		}
	}
	
	public void followBall() {
		Delay.msDelay(2);
		pilot.stop();
		if(color.colorFound()) {
			pilot.travel(3);
		}
		else {
			pilot.rotate(10);
			if(color.colorFound()) {
				pilot.travel(3);
			}
			else {
				pilot.rotate(-20);
				if(color.colorFound()) {
					pilot.travel(3);
				}
				else {
					pilot.rotate(30);
					if(color.colorFound()) {
						pilot.travel(3);
					}
					else {
						pilot.rotate(-40);
						if(color.colorFound()) {
							pilot.travel(3);
						}
					}
				}
			}
		}
		
		
	}
	
	public void turnAround() {
		Delay.msDelay(2);
		pilot.stop();
		pilot.travel(-6);
		if(ran.nextBoolean()) {
			pilot.rotate(90);
		} 
		else {
			pilot.rotate(-90);
		}
	}
	
	public void goArch() {
		
	}
	
	public void turnOff() {
		pilot.stop();
		System.exit(0);
	}

}
