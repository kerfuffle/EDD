package net.kerfuffle.RaspiServer;

import java.net.InetAddress;
import java.util.ArrayList;

public class Patient extends User{

	public String currentLetter;
	public String lastWord;
	private StringBuilder currentWord;
	private ArrayList<String> words = new ArrayList<String>();
	
	
	public Patient (String username, InetAddress ip, int port)
	{
		super(username, ip, port);
	}
	
	
	public void addLetter(String c)
	{
		currentWord.append(c);
	}
	public void removeLastLetter()
	{
		currentWord.deleteCharAt(currentWord.length()-1);
	}
	public void newWord()
	{
		words.add(currentWord.toString());
	}
	
	public ArrayList<String> getAllWords()
	{
		return words;
	}
	public String getCurrentWord()
	{
		return currentWord.toString();
	}
	
}
