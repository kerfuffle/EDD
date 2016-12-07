package net.kerfuffle.RaspiServer;

import static net.kerfuffle.RaspiServer.Util.readFile;

import java.io.IOException;
import java.util.ArrayList;

public class Global {

	public static final char termChar = '_';
	public static final String termStr = "_";
	
	public static  ArrayList<String> A_words;
	public static  ArrayList<String> B_words;
	public static  ArrayList<String> C_words;
	public static  ArrayList<String> D_words;
	public static  ArrayList<String> E_words;
	public static  ArrayList<String> F_words;
	public static  ArrayList<String> G_words;
	public static  ArrayList<String> H_words;
	public static  ArrayList<String> I_words;
	public static  ArrayList<String> J_words;
	public static  ArrayList<String> K_words;
	public static  ArrayList<String> L_words;
	public static  ArrayList<String> M_words;
	public static  ArrayList<String> N_words;
	public static  ArrayList<String> O_words;
	public static  ArrayList<String> P_words;
	public static  ArrayList<String> Q_words;
	public static  ArrayList<String> R_words;
	public static  ArrayList<String> S_words;
	public static  ArrayList<String> T_words;
	public static  ArrayList<String> U_words;
	public static  ArrayList<String> V_words;
	public static  ArrayList<String> W_words;
	public static  ArrayList<String> X_words;
	public static  ArrayList<String> Y_words;
	public static  ArrayList<String> Z_words;
	
	public static  void loadWords() throws IOException
	{
		A_words = readFile("A_words");
		B_words = readFile("B_words");
		C_words = readFile("C_words");
		D_words = readFile("D_words");
		E_words = readFile("E_words");
		F_words = readFile("F_words");
		G_words = readFile("G_words");
		H_words = readFile("H_words");
		I_words = readFile("I_words");
		J_words = readFile("J_words");
		K_words = readFile("K_words");
		L_words = readFile("L_words");
		M_words = readFile("M_words");
		N_words = readFile("N_words");
		O_words = readFile("O_words");
		P_words = readFile("P_words");
		Q_words = readFile("Q_words");
		R_words = readFile("R_words");
		S_words = readFile("S_words");
		T_words = readFile("T_words");
		U_words = readFile("U_words");
		V_words = readFile("V_words");
		W_words = readFile("W_words");
		X_words = readFile("X_words");
		Y_words = readFile("Y_words");
		Z_words = readFile("Z_words");
	}
	
	
}
