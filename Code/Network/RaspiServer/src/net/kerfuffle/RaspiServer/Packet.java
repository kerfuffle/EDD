package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Packet {

	static final int LOGIN = 0, DISCONNECT = 1, WORD = 2, LETTER = 3, SUGGEST = 4;
	
	
	private InetAddress ip;
	private int port;
	
	
	public static void sendPacket(Packet p, DatagramSocket socket, InetAddress ip, int port) throws IOException
	{
		if (p.toString() == null)
		{
			return;
		}
		
		byte buffer[] = p.toString().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, port);
		socket.send(sendPacket);
	}
	
	public static Packet receivePacket(DatagramSocket socket, InetAddress ip, int port) throws IOException
	{
		byte buffer[] = new byte[256];
		DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length, ip, port);
		socket.receive(receivePacket);
		
		String data = new String(receivePacket.getData())
		String sp[] = data.split(",");
		
		if (sp[0].equals(String.valueOf(LOGIN)))			//receive
		{
			return new PacketLogin(data);
		}
		if (sp[0].equals(String.valueOf(DISCONNECT)))		//receive and send
		{
			return new ;
		}
		if (sp[0].equals(String.valueOf(WORD)))				//receive and send
		{
			return new ;
		}
		if (sp[0].equals(String.valueOf(Packet.LETTER)))	//receive and send
		{
			return new ;
		}
		
		return null;
	}
	
	
}


class PacketLogin extends Packet
{
	private String data, username;
	
	public PacketLogin(String data)
	{
		this.data = data;
		String sp[] = data.split(",");
		username = sp[1];
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String toString()
	{
		return data;
	}
}

class PacketDisconnect extends Packet
{
	private String data;
	
	public PacketDisconnect(String dataormessage)
	{
		this.data = dataormessage;
	}
	
	public String toString()
	{
		return data;
	}
}

class PacketWord extends Packet				//T9 processing for word suggestions
{
	public PacketWord(String data)
	{
		
	}
	public PacketWord()
}

class PacketLetter
{
	
}
