package net.kerfuffle.RaspiServer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static net.kerfuffle.RaspiServer.Global.*;

public class Util {

	public static ArrayList<String> readFile(String name) throws IOException
	{
		FileReader file = new FileReader(name);
		BufferedReader in = new BufferedReader(file);
		
		ArrayList <String> ret = new ArrayList<String>();
		
		while (in.readLine() != null)
		{
			ret.add(in.readLine());
		}
		
		return ret;
	}
	
	public static String suggest(String let)
	{
		let = let.toUpperCase();
		if (let.startsWith("A"))
		{
			int letters = let.length();
			for (String s : A_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("B"))
		{
			int letters = let.length();
			for (String s : B_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("C"))
		{
			int letters = let.length();
			for (String s : C_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("D"))
		{
			int letters = let.length();
			for (String s : D_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("E"))
		{
			int letters = let.length();
			for (String s : E_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
				
		}
		if (let.startsWith("F"))
		{
			int letters = let.length();
			for (String s : F_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("G"))
		{
			int letters = let.length();
			for (String s : G_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("H"))
		{
			int letters = let.length();
			for (String s : H_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("I"))
		{
			int letters = let.length();
			for (String s : I_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("J"))
		{
			int letters = let.length();
			for (String s : J_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("K"))
		{
			int letters = let.length();
			for (String s : K_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("L"))
		{
			int letters = let.length();
			for (String s : L_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("M"))
		{
			int letters = let.length();
			for (String s : M_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("N"))
		{
			int letters = let.length();
			for (String s : N_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("O"))
		{
			int letters = let.length();
			for (String s : O_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("P"))
		{
			int letters = let.length();
			for (String s : P_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
				
		}
		if (let.startsWith("Q"))
		{
			int letters = let.length();
			for (String s : Q_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("R"))
		{
			int letters = let.length();
			for (String s : R_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("S"))
		{
			int letters = let.length();
			for (String s : S_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("T"))
		{
			int letters = let.length();
			for (String s : T_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("U"))
		{
			int letters = let.length();
			for (String s : U_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("V"))
		{
			int letters = let.length();
			for (String s : V_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("W"))
		{
			int letters = let.length();
			for (String s : W_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("X"))
		{
			int letters = let.length();
			for (String s : X_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("Y"))
		{
			int letters = let.length();
			for (String s : Y_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		if (let.startsWith("Z"))
		{
			int letters = let.length();
			for (String s : Z_words)
			{
				String compare = s.substring(0, letters-1);
				if (let.equals(compare))
				{
					return compare;
				}
			}
			
			
		}
		
		return "[SUGGESTION_NOT_FOUND]";
	}
	
}
