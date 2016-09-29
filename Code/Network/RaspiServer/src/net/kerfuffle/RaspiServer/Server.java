package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import static net.kerfuffle.RaspiServer.Packet.*;

public class Server{

	private int port;
	private DatagramSocket socket;
	private List users = Collections.synchronizedList(new ArrayList<User>());
	
	private Receive rp;
	private Send sp;
	
	private boolean quit = false;
	
	public Server(int port) throws SocketException
	{
		this.port = port;
		socket = new DatagramSocket(port);
	}
	
	public void start()
	{
		rp = new Receive(this);
		sp = new Send(this);
		
		rp.start();
		sp.start();
	}
	
	public boolean quit()
	{
		return quit;
	}
	public void stop()
	{
		quit = true;
	}
	public DatagramSocket getSocket()
	{
		return socket;
	}
	
	
	
	
	
	public static void main (String args[]) throws SocketException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		
		Server server = new Server(port);
		
		
		
	}
}

class Receive extends Thread
{
	private DatagramSocket socket;
	private Server server;
	
	public Receive(Server server)
	{
		super("Receive Thread");
		this.socket = server.getSocket();
		this.server = server;
	}
	
	public void run()
	{
		while (!server.quit())
		{
			try 
			{
				Packet packet = receivePacket(socket);
				
				if (packet.getId() == LOGIN)
				{
					
				}
				if (packet.getId() == DISCONNECT)
				{
					
				}
				if (packet.getId() == LETTER)
				{
					
				}
				if (packet.getId() == WORD)
				{
					
				}
				
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}

class Send extends Thread
{
	private DatagramSocket socket;
	private Server server;
	
	public Send(Server server)
	{
		super("Send Thread");
		this.socket = server.getSocket();
		this.server = server;
	}
	
	public void run()
	{
		while (!server.quit())
		{
			
		}
	}
}


class Console extends Thread
{
	private Server server;
	
	public Console(Server server)
	{
		super("Console Thread");
		this.server = server;
	}
	
	public void run()
	{
		Scanner scan = new Scanner(System.in);
		while (!server.quit())
		{
			String command = scan.nextLine();
			
			if (command.contains(","))
			{
				String sp[] = command.split(",");
				
				if (sp[0].equals("stop"))
				{
					String reason = sp[1];
					//send out reason for stopping
					server.stop();
				}
				if (sp[0].equals("kick"))
				{
					String player = sp[1];
					//search through "players", kick one matching name		(remove from list, send command to make them quit)
				}
			}
			
			
		}
	}
}