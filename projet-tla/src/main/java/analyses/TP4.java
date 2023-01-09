package analyses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tla.LireFichierTxt;

public class TP4 {

	public static void main(String[] args) {
		
		String path = "src/main/ressources/level";
		File folder = new File(path);
			
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		//Le nom de tout les fichiers txt dans le repertoire
		ArrayList<String> txtFiles=new ArrayList<>();
		for(File file:listOfFiles) {
			if(file.isFile()) {
				txtFiles.add(file.getName());
			}
		}
		
		int nbFichier=0;
		//Recupere en String le contenu du fichier et faire les analyses
		for(String name:txtFiles) {
			nbFichier++;
			System.out.println("\n\n\n\nFichier numero "+nbFichier+" au nom "+name);
			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(path+"/"+name))) {
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
			        sb.append(System.lineSeparator());
			      }
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			String text = sb.toString();
			
			//fait les analyses et envoit le resultat dans la lecture de fichier pour obtenir des Listes des éléments
			ArrayList<ArrayList<String>>a=LireFichierTxt.lireFichier(testAnalyseSyntaxique(text));
			//pour afficher les listes pour voir le résultat
			for(ArrayList<String> b:a) {
				for(String c:b) {
					System.out.println(c);
				}
			}
		}
	}

	/*
	effectue l'analyse lexicale de la chaine entree,
	affiche la liste des tokens reconnus
	 */
	
	private static void testAnalyseLexicale(String entree) {
		System.out.println("test analyse lexicale"); 
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			for (Token t : tokens) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
	}

	/*
	effectue l'analyse lexicale et syntaxique de la chaine entree
	 */
	private static String testAnalyseSyntaxique(String entree) {
		System.out.println("Test analyse syntaxique qui comprend l'analyse lexicale: \n");
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			System.out.println("Pas d'erreur lors de l'analyse lexicale \n");
			String res = new AnalyseSyntaxique().analyse(tokens);
			//System.out.println(entree + "\n" + res);//pour voir la tranformation entre le texte de base et en token
			System.out.println("Pas d'erreur lors de l'analyse syntaxique\n");
			return res;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}

}
