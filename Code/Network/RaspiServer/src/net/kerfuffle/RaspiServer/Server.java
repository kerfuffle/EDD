package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import static net.kerfuffle.RaspiServer.Packet.*;

public class Server{
	
	private int port;
	private DatagramSocket socket;
	public List users = Collections.synchronizedList(new ArrayList<User>());
	public Patient patient;
	
	private Receive rp;
	private Send sp;
	private Console console;
	
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
		console = new Console(this);
		
		console.start();
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
	public List getUsers()
	{
		return users;
	}
	
	
	
	public static void main (String args[]) throws SocketException, UnknownHostException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		
		Server server = new Server(port);
		server.start();
		
		
	}
}

/**
 * 10.11.16
 * For now the server will only be receiving from the patient and sending to the client. (With the exception of T9 word replacing)
 * Send process not needed, will only send to users as per receive from patient
 *
 */
class Receive extends Thread
{
	private DatagramSocket socket;
	private Server server;
	private List<User> users;
	private Patient patient;
	
	public Receive(Server server)
	{
		super("Receive Thread");
		this.socket = server.getSocket();
		this.server = server;
		this.users = server.users;
		this.patient = server.patient;
	}
	
	public void run()
	{
		while (!server.quit())
		{
			try 
			{
				Packet packet = receivePacket(socket);
				
				if (packet.getId() == LOGIN)			//right now only configured to watch, not participate    10.11.16
				{
					PacketLogin p = (PacketLogin) packet;
					if (p.getUsername().equals("[PATIENT]"))
					{
						patient = new Patient("Patient", p.getIp(), p.getPort());
						users.add(patient);
					}
					else
					{
						users.add(new User(p.getUsername(), p.getIp(), p.getPort()));
					}
				}
				if (packet.getId() == DISCONNECT)
				{
					PacketDisconnect p = (PacketDisconnect)packet;
					users.remove(lookupUser(p.getIp()));
				}
				if (packet.getId() == LETTER)							//letter selected (actually confirmed as correct letter)
				{
					PacketLetter p = (PacketLetter)packet;
					patient.currentLetter = p.toString();
					
					if (patient.currentLetter.equals("[CREATE_WORD]"))			//declare new word and send to clients
					{
						patient.newWord();
						PacketDoneWord pdone = new PacketDoneWord(patient.getCurrentWord());
						for (User u : users)
						{
							sendPacket(pdone, socket, u.getIp(), u.getPort());
						}
					}
					else if (patient.currentLetter.equals("[REMOVE_LAST]"))			//backspace
					{
						patient.removeLastLetter();
						PacketBuildWord pbuild = new PacketBuildWord(patient.getCurrentWord());
						for (User u : users)
						{
							sendPacket(pbuild, socket, u.getIp(), u.getPort());
						}
					}
					else			//normal letter adding, send word being built to clients
					{
						patient.addLetter(patient.currentLetter);
						PacketBuildWord pbuild = new PacketBuildWord(patient.getCurrentWord());
						for (User u : users)
						{
							sendPacket(pbuild, socket, u.getIp(), u.getPort());
						}
					}
					
					
					//word completion
					
					String suggestion = Util.suggest(patient.getCurrentWord());
					PacketSuggest sug = new PacketSuggest(suggestion);
					
					//sending word suggestion to only patient
					sendPacket(sug, socket, patient.getIp(), patient.getPort());
					
					
				}
				
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public int lookupUser(InetAddress ip)
	{
		int i = 0;
		for (User u : users)
		{
			if (u.getIp().toString().equals(ip.toString()))
			{
				return i;
			}
			i++;
		}
		
		return -1;
	}
	public int lookupUser(String username)
	{
		int i = 0;
		for (User u : users)
		{
			if (u.getUsername().equals(username))
			{
				return i;
			}
		}
		
		return -1;
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
		out("Console Started!");
	}
	
	public void out(String str)
	{
		System.out.println(str);
	}
	public void outError(String str)
	{
		System.err.println(str);
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
					//send out reason for stopping to users
					server.stop();
				}
				if (sp[0].equals("kick"))
				{
					String user = sp[1];
					//search through users, kick one matching name		(remove from list, send command to make them quit)
				}
			}
			
			
		}
	}
}