package net.kerfuffle.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LED_Blink {

	private Console con;
	
	private final GpioController gpio;
	private final GpioPinDigitalOutput pin;
	
	public LED_Blink(Console con)
	{
		this.con=con;
		
		 gpio = GpioFactory.getInstance();
	     pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
	     pin.setShutdownOptions(true, PinState.LOW);
	}
	
	public void run()
	{
		while (con.isAlive())
		{
			
			if (con.getLastCommand().equalsIgnoreCase("led off"))
			{
				pin.low();
			}
			else if (con.getLastCommand().equalsIgnoreCase("led on"))
			{
				pin.high();
			}
			else if (con.getLastCommand().equalsIgnoreCase("led toggle"))
			{
				pin.toggle();
				System.out.println("Toggled!");
			}
			else if (con.getLastCommand().toLowerCase().startsWith("led pulse"))
			{
				String sp[] = con.getLastCommand().split(" ");
				int ms = Integer.parseInt(sp[2]);
				pin.pulse(ms, true);
			}
			
			con.clearLastCommand();
		}
		
		gpio.shutdown();
    }
}