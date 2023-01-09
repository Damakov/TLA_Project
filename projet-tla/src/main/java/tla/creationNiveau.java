package tla;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class creationNiveau {
	
	          
	// Fonction permettant de reprendre la même map et enlever les # en fonction des paramètres
	static String NouvelleMap(String str, int positionLigne, int enleverHashDebut, int enleverHashFin )
	{
			
		String returnMap = "";
		// Permet de séparer chaque ligne pour pouvoir ensuite les modifier une par une dans le for 
		String[] mapLigne = str.split("\n");
		int compteur = 0;
		// Boucle permettant de lire ligne par ligne (les 14 lignes de 20 #)
        for (String mapXligneFinale : mapLigne) {
            compteur ++;
		// modification SI la ligne (compteur) correspond à la ligne entrée en paramètre
            if(compteur == positionLigne) {
		    // si oui, alors on appelle la fonction changerLigne qui permettera de transformer les # en espace en fonction des paramètres
		    // (enleverHashDebut et enleverHashFin)
            	mapXligneFinale = changerLigne(mapXligneFinale,enleverHashDebut,enleverHashFin);
            }
            //System.out.println(mapXligneFinale);
            returnMap += mapXligneFinale + "\n";
        }
		
	   return returnMap;
	}
	 public static String changerLigne(String str, int debut, int fin) {  
		 
		// Permet de remplacer # avec un ' ' de tel (debut) à tel endroit (fin)
		StringBuilder strBuilder = new StringBuilder(str);
		 for (int i = debut+1; i < fin+1; i++) {
			 if (
			 strBuilder.charAt(i) == '*') {
				// on fait rien étant donné que * représente la fin en bas à gauche 
			 }
			 else {		
				 // on remplace le character situé à i, par un vide
				 strBuilder.setCharAt(i, ' ');
			 }
		 }
		 String nouvelleLigne = strBuilder.toString();
         return nouvelleLigne;  
      }  
	

	

	public static void creerNiveau(ArrayList<ArrayList<String>> list, String nom) throws IOException {
		String[] a = nom.split("\\.");
		String name=a[0];
		File file = new File("./src/main/java/tla/"+name+".java");
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
		 		+ "class "+name+" extends Niveau {\n"
		 		+ "\n"
		 		+ "    "+name+"() {\n"
		 		+ "        INIT_CARREAUX =\n");
		 
		 String map = 
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"####################\"+\n" +
				 "\"###################*\"\n" ;
		 
		 	int positionLigne;
			int enleverHashDebut;
			int enleverHashFin;
		 
		 		 for (String item : list.get(0)) {
		 			 
		 			 String[] couloirs = item.split("/");
		 			 String[] coord = couloirs[2].split(";");
		 			 
		 			 if (couloirs[0].matches("c.*")) {

		 			 }
		 			 else {
		 				 positionLigne = Integer.parseInt(couloirs[1].replaceAll("[^0-9]", ""));
		 				enleverHashDebut = Integer.parseInt(coord[0].replaceAll("[^0-9]", ""));
		 				enleverHashFin = Integer.parseInt(coord[1].replaceAll("[^0-9]", ""));

		 				map = NouvelleMap(map, positionLigne, enleverHashDebut, enleverHashFin);

		 			 }
		 			 
		 		 }
		 		 
				 bw.write(map+";\n");

		 		 
		 		 
		 
		 
		 
		 
		 
		 
		 
		 bw.write("TRAPPES = Arrays.asList(\n");
		 int nbtrappe=0;
		 // Ici on fait les trappes
		 for( String item : list.get(1)) {
			 if(nbtrappe!=0){
					bw.write(",");
			 }
			 int x,y,destx,desty;
			 String[] words = item.split("/");
			 String[] entree = words[0].split(";");
	         x = Integer.parseInt(entree[0].replaceAll("[^0-9]", ""));
	         y = Integer.parseInt(entree[1].replaceAll("[^0-9]", ""));
	         String[] sortie = words[1].split(";");
	         destx = Integer.parseInt(sortie[0].replaceAll("[^0-9]", ""));
	         desty = Integer.parseInt(sortie[1].replaceAll("[^0-9]", ""));
	         nbtrappe++;
			 bw.write("\nnew Trappe("+x+", "+y+", DROITE, "+destx+", "+desty+")");
		 }
		bw.write(");\n");
		
		
		
		// On fait ensuite les fantomes
		bw.write("fantomes = Arrays.asList(\n");
		int nbfant=0;
		for ( String item: list.get(2)) {
			if(nbfant!=0){
				bw.write(",");
			}
			int x;int y;
			 // Ici on prend juste les coordonnées du spawn du fantome
			String[] words = item.split("/");
			String[] coordFant = words[0].split(";");
			x = Integer.parseInt(coordFant[0].replaceAll("[^0-9]", ""));
			y = Integer.parseInt(coordFant[1].replaceAll("[^0-9]", ""));
			bw.write("\nnew Fantome("+x+","+y+", Arrays.asList(");
			
			// Avec la deuxieme partie on a les deplacements, mais il faut les separer
			String deplacement = words[1];
			
			String[] deponly = words[1].split("\\(");
			String[] deplacementsList = deponly[1].split(";");
			
			// Pour le deplacement on regarde découpe chaque partie avec ;
			// Puis on regarde la premiere lettre de chaque partie pour savoir le mouvement
			// Et on boucle sur le chiffre pour l'ajouter le nombre de fois necessaire
			int nbdep=0;
			for (String dep : deplacementsList) {
				if(nbdep!=0){
					bw.write(",");
				}
				if (dep.startsWith("gauche")) {
					int i = Integer.parseInt(dep.replaceAll("[^0-9]", ""));
					int buf=0;
					for (int j=0 ; j<i; j++ ) {
						if(buf!=0){bw.write(",");}
						bw.write("GAUCHE\n");
						buf++;
					}
				}else
				if (dep.startsWith("droite")) {
					int buf=0;
					int i = Integer.parseInt(dep.replaceAll("[^0-9]", ""));
					for (int j=0 ; j<i; j++ ) {
						if(buf!=0){bw.write(",");}
						bw.write("DROITE\n");
						buf++;
					}
				}else
				if (dep.startsWith("haut")) {
					int i = Integer.parseInt(dep.replaceAll("[^0-9]", ""));
					int buf=0;
					for (int j=0 ; j<i; j++ ) {
						if(buf!=0){bw.write(",");}
						bw.write("HAUT\n");
						buf++;
					}
				}else
				if (dep.startsWith("bas")) {
					int buf=0;
					int i = Integer.parseInt(dep.replaceAll("[^0-9]", ""));
					for (int j=0 ; j<i; j++ ) {
						if(buf!=0){bw.write(",");}
						bw.write("BAS\n");
						buf++;
					}
				}
				nbdep++;
			}
			nbfant++;
			bw.write("))\n");
		}
		bw.write(");\n");
		
		// On fait la meme chose avec les commutateurs
		
		
		bw.write("commutateurs = Arrays.asList(\n");
		int nbcom=0;
		for (String item : list.get(3)) {
			if(nbcom!=0){
				bw.write(",");
			}
			int Levierx;
			int Leviery;
			int Portex;
			int Portey;
			String[] words = item.split("/");
			String[] coordLevier = words[0].split(";");
			String[] coordPorte = words[1].split(";");

			Levierx = Integer.parseInt(coordLevier[0].replaceAll("[^0-9]", ""));
			Leviery = Integer.parseInt(coordLevier[1].replaceAll("[^0-9]", ""));
			
			// Je ne sais pas ou mettre les coords de la porte
			Portex = Integer.parseInt(coordPorte[0].replaceAll("[^0-9]", ""));
			Portey = Integer.parseInt(coordPorte[1].replaceAll("[^0-9]", ""));
			
			bw.write("\nnew Commutateur("+Levierx+","+Leviery+")");
			
			nbcom++;
		}
		
		bw.write(");\n } \n void hookApresDeplacement(Plateau plateau) { };}\n ");
		
		bw.close();
		
	}
}


