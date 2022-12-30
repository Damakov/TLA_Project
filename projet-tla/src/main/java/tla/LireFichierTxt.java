package projet_tla;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LireFichierTxt {

	public static void main(String[] args) {
		
		
		String path = "C://Users/marti/Desktop/tla.txt" ;
		String line = "";
		 ArrayList<String> Couloirs= new ArrayList<String>();
		 ArrayList<String> Trappes= new ArrayList<String>();
		 ArrayList<String> Fantomes= new ArrayList<String>();
		 ArrayList<String> Commutateurs= new ArrayList<String>();
		 // ArrayList<String> X= new ArrayList<String>();
		 // String[][] X = null;
		 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
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
			 System.out.println("\nCOULOIRS : " + Couloirs);
			 System.out.println("\nTRAPPES : " + Trappes);
			 System.out.println("\nFANTOMES : " + Fantomes);
			 System.out.println("\nCOMMUTATEURS : " + Commutateurs);
			 
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
	}	

}
