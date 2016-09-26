package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static net.kerfuffle.RaspiServer.Global.*;

public class Packet {

	static final int LOGIN = 0, DISCONNECT = 1, WORD = 2, LETTER = 3, SUGGEST = 4;
	
	protected String data;
	protected int id;
	
	private InetAddress ip;
	private int port;
	
	public int getId()
	{
		return id;
	}
	
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
	
	public static Packet receivePacket(DatagramSocket socket) throws IOException
	{
		byte buffer[] = new byte[256];
		DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
		socket.receive(receivePacket);
		
		String data = new String(receivePacket.getData());
		String sp[] = data.split(termStr);
		
		if (sp[0].equals(String.valueOf(LOGIN)))			//receive
		{
			return new PacketLogin(data);
		}
		if (sp[0].equals(String.valueOf(DISCONNECT)))		//receive and send
		{
			return new PacketDisconnect(data);
		}
		if (sp[0].equals(String.valueOf(WORD)))				//receive and send
		{
			return new PacketWord(data);
		}
		if (sp[0].equals(String.valueOf(LETTER)))			//receive and send
		{
			return new PacketLetter(data);
		}
		
		return null;
	}
	
	
}


class PacketLogin extends Packet
{
	private String username;
	
	public PacketLogin(String data)
	{
		id = LOGIN;
		this.data = data;
		String sp[] = data.split(termStr);
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
	public PacketDisconnect(String dataormessage)
	{
		id = DISCONNECT;
		this.data = dataormessage;
	}
	
	public String toString()
	{
		return data;
	}
}

class PacketWord extends Packet				//T9 processing for word suggestions
{
	public PacketWord(String dataorword)
	{
		id = WORD;
		if (dataorword.contains(termStr))	//from Packet
		{
			String sp[] = dataorword.split(termStr);
			data = sp[1];
		}
		else								//from server
		{
			data = dataorword;
		}
	}
	
	public String toString()
	{
		return data;
	}
}

class PacketLetter extends Packet
{
	public PacketLetter(String dataorletter)
	{
		id = LETTER;
		if (dataorletter.contains(termStr))
		{
			String sp[] = dataorletter.split(termStr);
			data = sp[1];
		}
		else
		{
			data = dataorletter;
		}
	}
	
	public String toString()
	{
		return data;
	}
}
