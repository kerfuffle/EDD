package net.kerfuffle.GroupClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	private InetAddress serverIp;
	private int serverPort;
	
	public Client(InetAddress serverIp, int serverPort)
	{
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	
	public void start()
	{
		
	}
	
	public static void main (String args[]) throws UnknownHostException
	{
		
		String direct = JOptionPane.showInputDialog("IP:Port");
		String sp[] = direct.split(":");
		InetAddress serverIp = InetAddress.getByName(sp[0]);
		int serverPort = Integer.parseInt(sp[1]);
		
		Client client = new Client(serverIp, serverPort);
		
		client.start();
	}
	
}


class 
