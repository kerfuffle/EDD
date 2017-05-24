package net.kerfuffle.gpio;

import java.util.Scanner;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MainTest {

	public static final int LED_BLINK = 0, VIBRATION_IN = 1;
	
	public static void main(String args[]) throws InterruptedException
	{
		
		String progs[] = {"0 - LED BLINK", "1 - VIBRATION IN"};
		
		System.out.println("Program Started");
		System.out.println("Choose a program to run...");
		for (int i = 0; i < progs.length; i++)
		{
			System.out.println(progs[i]);
		}
		
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		
		Console console = new Console(scan);
		console.start();
		
		if (choice == LED_BLINK)
		{
			LED_Blink lb = new LED_Blink(console);
			lb.run();
		}
		else if (choice == VIBRATION_IN)
		{
			Vibration_In vi = new Vibration_In(console);
			vi.run();
		}
	}
	
}

class Console implements Runnable
{
	private Thread t;
	
	private String command = "nothing";
	private boolean alive = false;
	
	private Scanner scan;
	
	public Console(Scanner scan)
	{
		this.scan=scan;
	}
	
	public void run()
	{
		System.out.println("Console Started");
		while (alive)
		{
			command = scan.nextLine();
			
			if (command.equals("quit"))
			{
				scan.close();
				System.out.println("Program Terminated");
				alive = false;
			}
		}
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	public String getLastCommand()
	{
		return command;
	}
	public void clearLastCommand()
	{
		command = "nothing";
	}
	
	public void start()
	{
		alive = true;
		t = new Thread(this, "Console");
		t.start();
	}
}
