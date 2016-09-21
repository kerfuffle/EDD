package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Packet {

	static final int LOGIN = 0, DISCONNECT = 1, WORD = 2, LETTER = 3;
	
	
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
		
		String sp[] = new String(receivePacket.getData()).split(",");
		
		if (sp[0].equals(String.valueOf(Packet.LOGIN)))
		{
			return new ;
		}
		if (sp[0].equals(String.valueOf(Packet.DISCONNECT)))
		{
			return new Client_PacketDisconnect();
		}
		if (sp[0].equals(String.valueOf(Packet.OTHER_COORDS)))
		{
			return new Client_PacketOtherCoords(data);
		}
		if (sp[0].equals(String.valueOf(Packet.OTHER_SHOOT)))
		{
			return new Client_PacketOtherShoot(data);
		}
		
		return null;
	}
	
}
