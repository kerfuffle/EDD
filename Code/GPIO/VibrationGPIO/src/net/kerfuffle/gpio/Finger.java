package net.kerfuffle.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Finger implements Runnable{

	private Pin gpioPin;
	private Console con;
	
	private final GpioController gpio;
	private final GpioPinDigitalInput vibe;
	
	
	
	public Finger(Pin gpioPin, Console con)
	{
		this.con = con;
		this.gpioPin = gpioPin;
		
		gpio = GpioFactory.getInstance();
		vibe = gpio.provisionDigitalInputPin(gpioPin, PinPullResistance.PULL_DOWN);
		vibe.setShutdownOptions(true);
	}
	
	public void run()
	{
		vibe.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) 
            {
               // send that this finger has triggered
            }
            
        });
		
		while (con.isAlive())
		{
			
		}
	}
	
}
