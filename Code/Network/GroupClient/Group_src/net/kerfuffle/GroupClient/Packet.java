package net.kerfuffle.GroupClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static net.kerfuffle.GroupClient.Global.*;

public class Packet {

	/**
	 * Group Client
	 */

	static final int LOGIN = 0, DISCONNECT = 1, WORD = 2, LETTER = 3, SUGGEST = 4, SUCCESS = 5, BUILD_WORD = 6,
			DONE_WORD = 7, ERROR = 8;

	protected String data;
	protected int id;

	private InetAddress ip;
	private int port;

	public Packet(InetAddress ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	public Packet(InetAddress ip, int port, String data)
	{
		this.ip = ip;
		this.port = port;
		this.data = data;
	}
	public Packet(){};

	public int getId()
	{
		return id;
	}

	/**
	 * Same as getData()
	 */
	public String toString()
	{
		return data;
	}
	/**
	 * Same as toString()
	 */
	public String getData()
	{
		return data;
	}

	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}
	public String packData(String str)
	{
		return id+(termStr)+str+(termStr);
	}
	public String packData(String[] strs)
	{
		String ret = ""+id+(termStr);
		for (int i = 0; i < strs.length; i++)
		{
			ret += strs[i] + (termStr);
		}
		return ret;
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

		Packet ret = new Packet(receivePacket.getAddress(), receivePacket.getPort(), data);

		if (sp[0].equals(String.valueOf(LOGIN)))			//send
		{
			return (PacketLogin)ret;
		}
		if (sp[0].equals(String.valueOf(DISCONNECT)))		//receive and send
		{
			return (PacketDisconnect)ret;
		}
		if (sp[0].equals(String.valueOf(WORD)))				//receive
		{
			return (PacketWord)ret;
		}
		if (sp[0].equals(String.valueOf(LETTER)))			//receive
		{
			return (PacketLetter)ret;
		}

		return null;
	}


}


class PacketLogin extends Packet
{
	public PacketLogin(String username)
	{
		id = LOGIN;
		data = username;
	}
}
class PacketDisconnect extends Packet
{
	private String reason;
	
	public PacketDisconnect(String reasonOrData)
	{
		id = DISCONNECT;
		
		if (reasonOrData.contains(termStr))						//reason from server (server quitting)
		{
			String sp[] = reasonOrData.split(termStr);
			reason = sp[1];
		}
		else													// reason from client (client quitting)
		{
			reason = reasonOrData;
		}
	}
}
class PacketWord extends Packet
{
	private String word;
	
	public PacketWord(String data)
	{
		id = WORD;
		String sp[] = data.split(termStr);
		word = sp[1];
	}
	
	public String getWord()
	{
		return word;
	}
}
class PacketLetter extends Packet
{
	private String letter;
	
	public PacketLetter(String data)
	{
		id = LETTER;
		String sp[] = data.split(termStr);
		letter = sp[1];
	}
	
	public String getLetter()
	{
		return letter;
	}
}

class PacketError extends Packet
{
	private String message;
	
	public PacketError(String data)
	{
		id = ERROR;
		String sp[] = data.split(termStr);
		message = sp[1];
	}
	
	public String getMessage()
	{
		return message;
	}
}

class PacketSuccess extends Packet
{
	private String message;
	
	public PacketSuccess(String data)
	{
		id = SUCCESS;
		String sp[] = data.split(termStr);
		message = sp[1];
	}
	
	public String getMessage()
	{
		return message;
	}
}