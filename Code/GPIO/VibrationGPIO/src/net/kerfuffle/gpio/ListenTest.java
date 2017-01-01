package net.kerfuffle.gpio;

import java.util.Scanner;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ListenTest {

	public static void main(String args[]) throws InterruptedException
	{
		final GpioController gpio = GpioFactory.getInstance();
		
		final GpioPinDigitalInput vibe = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN);
		
		vibe.setShutdownOptions(true);
		
		vibe.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }
            
        });
		
		Console console = new Console();
		console.start();
		
		while (console.getAlive())
		{
			Thread.sleep(500);
		}
		
		gpio.shutdown();
	}
	
}

class Console extends Thread
{
	private boolean alive = true;
	
	public void run()
	{
		while (alive)
		{
			Scanner scan = new Scanner(System.in);
			String com = scan.nextLine();
			
			if (com.equals("quit"))
			{
				alive = false;
			}
		}
	}
	
	public boolean getAlive()
	{
		return alive;
	}
}
