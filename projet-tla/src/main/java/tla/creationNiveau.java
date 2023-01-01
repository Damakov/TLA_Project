package tla;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class creationNiveau {

	public static void creerNiveau(ArrayList<ArrayList<String>> list, String nom) throws IOException {
		File file = new File("./"+nom+".java");
		file.createNewFile();
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
			
			// Il faut trouver un moyen savoir si c'est gauche droite etc

			
			
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
		
		
		
		
		
		
	}

}


