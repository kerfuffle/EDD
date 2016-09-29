package net.kerfuffle.RaspiServer;

import java.net.InetAddress;

public class User {

	private InetAddress ip;
	private int port;
	private String username;
	
	public User(String username, InetAddress ip, int port)
	{
		this.username = username;
		this.ip = ip;
		this.port = port;
	}
	
	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}
	public String getUsername()
	{
		return username;
	}
	
}
