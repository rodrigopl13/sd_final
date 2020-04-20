package project.sd.launch;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import project.sd.color.SColor;
import project.sd.compass.Compass;
import project.sd.distance.Ultrasonic;

public class LaunchWalle {
	
	//these are to create the objects that contains the sensors functionality
	Compass wCompass;
	SColor wColor;
	Ultrasonic wUltra;
	
	MovePilot pilot; //pilot for the robot
	
	Chassis chassis; //to create a type of chassis
	
	//the two wheels
	Wheel wRight;
	Wheel wLeft;
	
	//what I'm doing now?
	public String STATE;
	
	//walle states
	public final String SEARCHING_COLOR = "1";
	public final String SEARCHING_BALL = "2";
	public final String SEARCHING_NORTH = "3";
	
	public LaunchWalle() {
		
		//setup pilot
		pilot = setPilot();
		
		wCompass = new Compass();
		wColor = new SColor();
		wUltra = new Ultrasonic();

	}
	
	public void walleAction() {
		//the first state is searching for color
		setState(SEARCHING_COLOR);
				
		while( Button.ESCAPE.isUp() ) {
			System.out.println("wState: " + printState());Delay.msDelay(2000);
					
			//search for a color while I'm making arcs
			if(getState().equals(SEARCHING_COLOR)) {
				//spin until find some color
				while(!wColor.searchColor()) {
					pilot.arc(10, 10);
					Delay.msDelay(1500);
				}
						
				//searching the ball
				setState(SEARCHING_BALL);
						
				//make a little turn to pickup the ball
				pilot.arc(20, 30);
			}
					
			if( getState().equals(SEARCHING_BALL) ) {
						
				//check the ultraSensor
				if(wUltra.checkDistanceToTheBall()) {
							
					pilot.travel(wUltra.getDistance());//go near the ball
					//pilot.forward();
			
				}else {
					setState(SEARCHING_NORTH);
				}
							
			}
						
			if (getState().equals(SEARCHING_NORTH)) {
				//find north
				wCompass.findNorth(pilot);
			}
					
			//wait for 1.5 seconds
			Delay.msDelay(1500);
		}
	}
	
	public MovePilot setPilot() {
		
		//setting the wheels
		wRight = WheeledChassis.modelWheel(Motor.D, 56).offset(-70);
		wLeft = WheeledChassis.modelWheel(Motor.A, 56).offset(70);
						
		//setting the two wheels chassis
		chassis = new WheeledChassis(new Wheel[] { wLeft, wRight }, WheeledChassis.TYPE_DIFFERENTIAL);
						
		//creating pilot with the defined chassis
		pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(40); //5 cm per second
		
		return pilot;
	}
	
	public MovePilot getPilot() {
		return this.pilot;
	}
	
	public String printState() {
		String state = null;
		
		switch(STATE) {
			
			case "1" :
				state = "sColor";
			break;
			case "2" :
				state = "sBall";
			break;
			case "3" :
				state = "sNorth";
			break;
		
		}
		
		return state;
	}
	
	public String getState() {
		return this.STATE;
	}
	
	public void setState(String state) {
		this.STATE = state;
	}

	public static void main(String[] args) {
		
		new LaunchWalle().walleAction();
		
		//testing sensors
		//new SColor().printColor();
		//new Ultrasonic().testSensor(new LaunchWalle().getPilot());
		//new Compass().findNorth(new LaunchWalle().getPilot());;
	}

}
