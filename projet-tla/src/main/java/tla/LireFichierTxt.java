package tla;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class LireFichierTxt {
	
	
	public static ArrayList<ArrayList<String>> lireFichier(String fichier) {
		
		
		ArrayList<ArrayList<String>> allElements = new ArrayList<ArrayList<String>>();
		ArrayList<String> Couloirs= new ArrayList<String>();
		ArrayList<String> Trappes= new ArrayList<String>();
		ArrayList<String> Fantomes= new ArrayList<String>();
		ArrayList<String> Commutateurs= new ArrayList<String>();
		 
		
		try(BufferedReader br = new BufferedReader(new StringReader(fichier))) {
			String line;
			while((line = br.readLine()) != null) {
				if(line.startsWith("Couloir")){
					Couloirs.add(line);
				}
				else if(line.startsWith("Fantome")){
					Fantomes.add(line);
				}
				else if(line.startsWith("Trappe")){
					Trappes.add(line);
				}
				else if(line.startsWith("Commutateur")){
					Commutateurs.add(line);
				}
				else if(line.startsWith("Fin")){
					break;
				}
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		
		allElements.add(Couloirs);
		allElements.add(Trappes);
		allElements.add(Fantomes);
		allElements.add(Commutateurs);
		return allElements;
	}
}
