package tla;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LireFichierTxt {
	
	
	public static ArrayList<ArrayList<String>> lireFichier(String fichier) {
		
		
		System.out.println("----------------------------------------------------------------------");
		ArrayList<ArrayList<String> > allElements = 
                new ArrayList<ArrayList<String> >();

		
		//test
		String path = "C://Users/marti/Desktop/tla.txt" ;
		fichier = path;
		
		
		String line = "";
		 ArrayList<String> Couloirs= new ArrayList<String>();
		 ArrayList<String> Trappes= new ArrayList<String>();
		 ArrayList<String> Fantomes= new ArrayList<String>();
		 ArrayList<String> Commutateurs= new ArrayList<String>();
		 // ArrayList<String> X= new ArrayList<String>();
		 // String[][] X = null;
		 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			
			while((line = br.readLine()) != null) {		
				
				if(line.equals("Couloir") == true) {
						line = br.readLine();
						while(line.equals("end") == false) {
							Couloirs.add(line);
							line = br.readLine();

						
						}
				}
				
				else if(line.equals("Trappe") == true) {
					line = br.readLine();
					while(line.equals("end") == false) {
						Trappes.add(line);
						line = br.readLine();
					}
					
				}
				else if(line.equals("Fantome") == true) {
					line = br.readLine();
					while(line.equals("end") == false) {
						Fantomes.add(line);
						line = br.readLine();
					}
					
				}
				else if(line.equals("Commutateur") == true) {
					line = br.readLine();
					while(line.equals("end") == false) {
						Commutateurs.add(line);
						line = br.readLine();
					}
					
				}
			}
			 
			 // PERMET DE LIRE TOUS LES ELEMENTS SEPAREMENT
//			 for (int i = 0; i < Couloirs.size(); i++) {
//				 System.out.println("Couloir " + i + " " + Couloirs.get(i));
				 
//		 }
			 
			 //////////////////////////////////////////////////////////
			 
			 
			 // PERMET DE LIRE EGALEMENT EN SPLITANT LES ELEMENTS A LINTERIEUR AVEC LE "/"
//			 String[]ligne = null;
//			 int compteur = 0;
//			 for (int i = 0; i < Couloirs.size(); i++) {
//				 	compteur ++;
//				 	System.out.println("======");
//				 	ligne = Couloirs.get(i).split("/");	
//					for (int j = 0; j < ligne.length; j++) {
//			            System.out.println("L"+compteur + "=" + ligne[j]);
//			           
//			            
//					}
//					
//				}
			 
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
//		allElements[0] = Couloirs ;
//		allElements[1] = Trappes ;
//		allElements[2] = Fantomes ;
//		allElements[3] = Commutateurs ;
		System.out.println("SALUT" + allElements);
		System.out.println("SALUT FDP0" + allElements.get(0));
		System.out.println("SALUT FDP1" + allElements.get(1));
		System.out.println("SALUT FDP2" + allElements.get(2));
		System.out.println("SALUT FDP3" + allElements.get(3));
		
		return allElements;		
}


	public static void main(String[] args) {
		
		String path = "C://Users/marti/Desktop/tla.txt" ;
		lireFichier(path);
		// Afficher tout l'arraylist, chaque éléments est en [0],[1],[2],[3]
 		//System.out.println(lireFichier(path));
		
	}	

}
