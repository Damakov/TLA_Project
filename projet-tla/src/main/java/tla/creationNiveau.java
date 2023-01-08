package tla;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class creationNiveau {
	
	          
	
	static String NouvelleMap(String str, int positionLigne, int enleverHashDebut, int enleverHashFin )
	{
			
		String returnMap = "";
		String[] mapLigne = str.split("\n");
		int compteur = 0;
        for (String mapXligneFinale : mapLigne) {
            compteur ++;
            if(compteur == positionLigne) {
            	mapXligneFinale = changerLigne(mapXligneFinale,enleverHashDebut,enleverHashFin);
            }
            //System.out.println(mapXligneFinale);
            returnMap += mapXligneFinale + "\n";
        }
        System.out.println("Nombre de lignes : " + compteur);
        //System.out.println("CE QUE LA FONCTION RETOURNE :");

		
	   return returnMap;
	}
	 public static String changerLigne(String str, int debut, int fin) {  
		 
		// Permet de remplacer # avec un ' ' de tel (debut) à tel endroit (fin)
		StringBuilder strBuilder = new StringBuilder(str);
		 for (int i = debut; i < fin; i++) {
			 if (
			 strBuilder.charAt(i) == '*') {
				 
			 }
			 else {
				 strBuilder.setCharAt(i, ' ');
			 }
		 }
		 String nouvelleLigne = strBuilder.toString();
         return nouvelleLigne;  
      }  
	

	
	
	
	
	

	public static void creerNiveau(ArrayList<ArrayList<String>> list, String nom) throws IOException {
		File file = new File("./"+nom+".java");
		// On créé un file avec le bon nom et l'extension Java si il n'existe pas et si il existe on le delete et le créé après
		if (file.createNewFile()==false) {
			file.delete();
			file.createNewFile();
		}
		else file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		 BufferedWriter bw = new BufferedWriter(writer);
		 bw.write("package tla;\n"
		 		+ "\n"
		 		+ "import java.util.Arrays;\n"
		 		+ "\n"
		 		+ "import static tla.Direction.*;\n"
		 		+ "\n"
		 		+ "class Niveau1 extends Niveau {\n"
		 		+ "\n"
		 		+ "    "+nom+"() {\n"
		 		+ "        INIT_CARREAUX =\n");
		 
		 String map = "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "####################\n" +
				 "###################*\n" ;
		 
		 	int positionLigne;
			int enleverHashDebut;
			int enleverHashFin;
		 
		 		 for (String item : list.get(0)) {
		 			 
		 			 String[] couloirs = item.split("/");
		 			 String[] coord = couloirs[2].split(";");
		 			 
		 			 if (couloirs[0].matches("c.*")) {

		 			 }
		 			 else {
		 				 positionLigne = Integer.parseInt(couloirs[1]);
		 				enleverHashDebut = Integer.parseInt(coord[0]);
		 				enleverHashFin = Integer.parseInt(coord[1]);

		 				map = NouvelleMap(map, positionLigne, enleverHashDebut, enleverHashFin);

		 			 }
		 			 
		 		 }
		 		 
				 bw.write(map+";\n");

		 		 
		 		 
		 
		 
		 
		 
		 
		 
		 
		 bw.write("TRAPPES = Arrays.asList(\n");
		 
		 // Ici on fait les trappes
		 for( String item : list.get(1)) {
			int x;
			int y;
			int destx;
			int desty;
			 
		           String[] words = item.split("/");
		           
		           String[] entree = words[0].split(";");
		           x = Integer.parseInt(entree[0]);
		           y = Integer.parseInt(entree[1]);
		           
		           String[] sortie = words[1].split(";");
		           destx = Integer.parseInt(sortie[0]);
		           desty = Integer.parseInt(sortie[1]);
		            
		            
		        
			 bw.write("new Trappe("+x+", "+y+", DROITE, "+destx+", "+desty+"),\n");
		 }
		bw.write(");\n");
		
		
		
		// On fait ensuite les fantomes
		bw.write("fantomes = Arrays.asList(\n");
		for ( String item: list.get(2)) {
			int x;
			int y;
			

			 // Ici on prend juste les coordonnées du spawn du fantome
			String[] words = item.split("/");
			String[] coordFant = words[0].split(";");
			
			x = Integer.parseInt(coordFant[0]);
			y = Integer.parseInt(coordFant[1]);
			
			// Avec la deuxieme partie on a les deplacements, mais il faut les separer
			String deplacement = words[1];
			
			String[] deponly = words[1].split("(");
			String[] deplacementsList = deponly[1].split(";");
			
			// Pour le deplacement on regarde découpe chaque partie avec ;
			// Puis on regarde la premiere lettre de chaque partie pour savoir le mouvement
			// Et on boucle sur le chiffre pour l'ajouter le nombre de fois necessaire
			for (String dep : deplacementsList) {
				if (dep.matches("g.*")) {
					int i = Integer.parseInt(dep);
					for (int j=0 ; j<i; j++ ) {
						bw.write("GAUCHE, \n");
					}

				};
				if (dep.matches("d.*")) {
					int i = Integer.parseInt(dep);
					for (int j=0 ; j<i; j++ ) {
						bw.write("DROITE, \n");
					}
				};
				if (dep.matches("h.*")) {
					int i = Integer.parseInt(dep);
					for (int j=0 ; j<i; j++ ) {
						bw.write("HAUT, \n");
					}
				};
				if (dep.matches("b.*")) {
					int i = Integer.parseInt(dep);
					for (int j=0 ; j<i; j++ ) {
						bw.write("BAS, \n");
					}
				};
				
			}
			
			
		}
		
		
		// On fait la meme chose avec les commutateurs
		
		
		bw.write("commutateurs = Arrays.asList(\n");
		
		for (String item : list.get(3)) {
			int Levierx;
			int Leviery;
			int Portex;
			int Portey;
			String[] words = item.split("/");
			String[] coordLevier = words[0].split(";");
			String[] coordPorte = words[1].split(";");

			Levierx = Integer.parseInt(coordLevier[0]);
			Leviery = Integer.parseInt(coordLevier[1]);
			
			// Je ne sais pas ou mettre les coords de la porte
			Portex = Integer.parseInt(coordPorte[0]);
			Portey = Integer.parseInt(coordPorte[1]);
			
			bw.write("new Commutateur("+Levierx+","+Leviery+")\n");
			
			
		}
		
		bw.write(");\n } \n void hookApresDeplacement(Plateau plateau) { \n ");
		
		
		
		
		
		
		
		
		bw.close();
		
		
	}

}


