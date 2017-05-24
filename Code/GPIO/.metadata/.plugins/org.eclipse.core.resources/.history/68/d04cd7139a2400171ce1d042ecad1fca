package net.kerfuffle.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Vibration_In {

	private Console con;
	
	private final GpioController gpio;
	private final GpioPinDigitalInput vibe;
	
	public Vibration_In(Console con)
	{
		this.con=con;
		
		gpio = GpioFactory.getInstance();
		vibe = gpio.provisionDigitalInputPin(RaspiPin.GPIO_21, PinPullResistance.PULL_DOWN);
		vibe.setShutdownOptions(true);
	}
	
	public void run()
	{
		vibe.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }
            
        });
		
		
		while (con.isAlive())
		{
			
		}
	}
	
}
