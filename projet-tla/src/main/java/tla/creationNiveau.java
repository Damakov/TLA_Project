package tla;

import static tla.Direction.DROITE;

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
		 int i=0;
		 for( String item : list.get(1)) {
			int x=0;
			int y=0;
			int destx=0;
			int desty=0;
			 
			 String trappe = list.get(1).get(i);
			 for (int j = 0; i < trappe.length(); j++) {
		           String[] words = trappe.split("/");
		           for (String word : words) {
		        	   String[] parses = word.split(";");
		        	   int k=0;
		        	   for( String parse : parses) {
		        		   if (k==0) {
		        			   x = Integer.parseInt(parse);
		        			   k+=1;
		        		   }
		        		   if (k==1) {
		        			   y = Integer.parseInt(parse);
		        			   k+=1;

		        		   }
		        		   if (k==2) {
		        			   destx = Integer.parseInt(parse);
		        			   k+=1;

		        		   }
		        		   if (k==3) {
		        			   desty = Integer.parseInt(parse);
		        			   k+=1;

		        		   }
		        		   

		        	   }
		           }
		            
		            
		        }
			 bw.write("new Trappe("+x+", "+y+", DROITE, "+destx+", "+desty+"),\n");
			 i+=1;
		 }
		bw.write(");\n");
		
		
		
	}

}
