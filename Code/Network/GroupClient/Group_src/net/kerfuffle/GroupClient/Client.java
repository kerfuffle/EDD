package net.kerfuffle.GroupClient;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import static net.kerfuffle.GroupClient.Packet.*;

public class Client {

	private InetAddress serverIp;
	private int serverPort;
	private DatagramSocket socket;
	private String username;
	
	private Display display;
	private SendReceive sr;
	private boolean running = true;
	
	public Client(InetAddress serverIp, int serverPort) throws IOException
	{
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		socket = new DatagramSocket(serverPort);
		sr = new SendReceive(this);
		
		boolean accepted = false;
		PacketLogin log = new PacketLogin(username);
		
		while (!accepted)
		{
			username = JOptionPane.showInputDialog("Username");
			log = new PacketLogin(username);
			sendPacket(log, socket, serverIp, serverPort);
			
			Packet packet = receivePacket(socket);
			
			if (packet.id == ERROR)
			{
				PacketError p = (PacketError)packet;
				JOptionPane.showMessageDialog(null, p.getMessage());
			}
			if (packet.id == SUCCESS)
			{
				PacketSuccess p = (PacketSuccess)packet;
				JOptionPane.showMessageDialog(null, p.getMessage());
				accepted = true;
			}
		}
		
	}
	
	public void start()
	{
		display.start();
		sr.start();
	}
	
	public boolean isRunning()
	{
		return running;
	}
	public DatagramSocket getSocket()
	{
		return socket;
	}
	
	
	
	public static void main (String args[]) throws IOException
	{
		
		String direct = JOptionPane.showInputDialog("IP:Port");
		String sp[] = direct.split(":");
		InetAddress serverIp = InetAddress.getByName(sp[0]);
		int serverPort = Integer.parseInt(sp[1]);
		
		
		Client client = new Client(serverIp, serverPort);
		
		client.start();
	}
	
}

class Display extends Thread
{
	public Display()
	{
		
	}
	
	public void run()
	{
		
	}
}

class SendReceive extends Thread
{
	private Client client;
	
	public SendReceive(Client client)
	{
		this.client = client;
	}
	
	public void run()
	{
		while (client.isRunning())
		{
			try 
			{
				Packet packet = Packet.receivePacket(client.getSocket());
				
				if (packet.id == WORD)
				{
					PacketWord p = (PacketWord) packet;
					
				}
				if (packet.id == LETTER)
				{
					PacketLetter p = (PacketLetter) packet;
					
				}
				if (packet.id == DISCONNECT)
				{
					PacketDisconnect p = (PacketDisconnect) packet;
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//send disconnect message to server
		}
	}
}

