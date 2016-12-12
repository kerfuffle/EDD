package net.kerfuffle.GroupClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Packet {

	/**
	 * Client
	 */

	static final int LOGIN = 0, DISCONNECT = 1, WORD = 2, LETTER = 3, SUGGEST = 4, SUCCESS = 5, BUILD_WORD = 6, DONE_WORD = 7;

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
		return id+","+str+",";
	}
	public String packData(String[] strs)
	{
		String ret = ""+id+",";
		for (int i = 0; i < strs.length; i++)
		{
			ret += strs[i] + ",";
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

		if (sp[0].equals(String.valueOf(LOGIN)))			//receive
		{
			return (PacketLogin)ret;
		}
		if (sp[0].equals(String.valueOf(DISCONNECT)))		//receive and send
		{
			return (PacketDisconnect)ret;
		}
		if (sp[0].equals(String.valueOf(WORD)))				//receive and send
		{
			return (PacketWord)ret;
		}
		if (sp[0].equals(String.valueOf(LETTER)))			//receive and send
		{
			return (PacketLetter)ret;
		}

		return null;
	}


}

