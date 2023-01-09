package analyses;

import java.util.List;
import java.util.ArrayList;

public class TP4 {

	public static void main(String[] args) {
			String path = "C://Users/Sewraj/Documents/tlaTest.txt" ;
			ArrayList<ArrayList<String>> arr = LireFichierTxt.lireFichier(path);
			try {
				String entree = LireFichierTxt.readFileAsString(path);
				System.out.println(entree);
				testAnalyseLexicale(entree);
				testAnalyseSyntaxique(entree);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	private static void testAnalyseSyntaxique(String entree) {
		System.out.println("test analyse syntaxique");
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			String res = new AnalyseSyntaxique().analyse(tokens);
			System.out.println("L'analyse lexical et synaxique a ete fait");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();
	}

}
